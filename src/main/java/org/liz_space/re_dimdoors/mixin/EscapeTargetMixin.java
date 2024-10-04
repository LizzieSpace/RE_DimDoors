package org.liz_space.re_dimdoors.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Rotations;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import org.apache.logging.log4j.Logger;
import org.dimdev.dimdoors.DimensionalDoors;
import org.dimdev.dimdoors.api.util.EntityUtils;
import org.dimdev.dimdoors.api.util.Location;
import org.dimdev.dimdoors.api.util.TeleportUtil;
import org.dimdev.dimdoors.block.ModBlocks;
import org.dimdev.dimdoors.block.UnravelUtil;
import org.dimdev.dimdoors.rift.targets.EscapeTarget;
import org.dimdev.dimdoors.world.ModDimensions;
import org.spongepowered.asm.mixin.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static net.minecraft.core.registries.Registries.DIMENSION;
import static org.dimdev.dimdoors.DimensionalDoors.getWorld;
import static org.liz_space.re_dimdoors.RE_DimDoorsMain.CONFIG;

@Debug(export = true)
@Mixin(EscapeTarget.class)
public class EscapeTargetMixin {

    @Final
    @Shadow(remap = false)
    private static Logger LOGGER;

    @Final
    @Shadow(remap = false)
    protected boolean canEscapeLimbo;


    @Shadow(remap = false)
    public static Location randomizeLimboReturn(Location loc, int range) {
        return null;
    }


    @Unique
    protected ResourceKey<Level> getTargetWorldResourceKey(RandomSource random) {
        List<String> list = CONFIG.common.getWorldList();
        int index = random.nextInt(list.size());
        String world = list.get(index);
        ResourceKey<Level> key = ResourceKey.create(DIMENSION, new ResourceLocation(world));
        if (getWorld(key) == null) {
            CONFIG.common.rmFromWorldList(world);
            return getTargetWorldResourceKey(random);
        }
        return key;
    }

    /**
     * @author Liz C.
     * @reason Refactoring royally messy code and adding a random escapeTarget world feature ;)
     */
    @Overwrite
    public boolean receiveEntity(Entity entity, Vec3 relativePos, Rotations relativeAngle, Vec3 relativeVelocity, Location location2) {
        UUID uuid = entity.getUUID(); // honestly, things deserve to blow up if somehow there's no UUID
        ServerPlayer player = (ServerPlayer) Objects.requireNonNull(entity.level().getPlayerByUUID(uuid));
        Location destLoc;
        RandomSource random = RandomSource.create();

        ResourceKey<Level> escapeTargetWorld = (!CONFIG.common.getUseEscapeTargetWorld())
                                               ? DimensionalDoors.getConfig().getLimboConfig().escapeTargetWorld
                                               : getTargetWorldResourceKey(random);

        class SendLocation {
            static Location playerSpawn(ServerPlayer player) {
                LOGGER.log(org.apache.logging.log4j.Level.INFO, "Sending player from limbo to their spawnpoint, good luck!");
                return new Location(player.getRespawnDimension(), player.getRespawnPosition());
            }

            static Location worldSpawn() {
                LOGGER.log(org.apache.logging.log4j.Level.INFO, "Sending player from limbo to worldspawn, good luck!");
                return new Location(DimensionalDoors.getServer().overworld(), DimensionalDoors.getServer().overworld().getSharedSpawnPos());
            }

            static Location escapeTargetWorld(Entity entity, ResourceKey<Level> world) {
                LOGGER.log(org.apache.logging.log4j.Level.INFO, "Sending player from limbo to the exit dimension, good luck!");
                return new Location(getWorld(world),
                                    new BlockPos(entity.blockPosition().getX(),
                                                 DimensionalDoors.getConfig().getLimboConfig().escapeTargetWorldYSpawn,
                                                 entity.blockPosition().getZ()
                                    ));
            }
        }


        if (// returns false if any of the following statements are true.
                (!ModDimensions.isPocketDimension(entity.level()) && !ModDimensions.isLimboDimension(entity.level()))
                || (ModDimensions.isLimboDimension(entity.level()) && !this.canEscapeLimbo)
                || (entity.level().isClientSide)
        ) {return false;}

        else if (entity.level().getPlayerByUUID(uuid) == null) {
            LOGGER.log(org.apache.logging.log4j.Level.ERROR, "Tried to get player for escape target from uuid, but player does not exist, uh oh");
            return false;
        }

        else if (
                player.getRespawnPosition() != null
                && escapeTargetWorld == null
                && !DimensionalDoors.getConfig().getLimboConfig().escapeToWorldSpawn
        ) {destLoc = SendLocation.playerSpawn(player);}

        else if (
                escapeTargetWorld != null
                && !DimensionalDoors.getConfig().getLimboConfig().escapeToWorldSpawn
        ) {destLoc = SendLocation.escapeTargetWorld(entity, escapeTargetWorld);}

        else {destLoc = SendLocation.worldSpawn();}

        destLoc = randomizeLimboReturn(destLoc, DimensionalDoors.getConfig().getLimboConfig().limboReturnDistance);
        if (destLoc != null && this.canEscapeLimbo) {
            Location location = destLoc;
            entity = TeleportUtil.teleport(entity, location.getWorld(), location.getBlockPos(), relativeAngle, relativeVelocity);
            entity.fallDistance = -500.0F;
            location.getWorld().setBlockAndUpdate(location.getBlockPos(), Blocks.AIR.defaultBlockState());
            location.getWorld().setBlockAndUpdate(location.getBlockPos().offset(0, 1, 0), Blocks.AIR.defaultBlockState());
            BlockPos.withinManhattan(location.pos.offset(0, -3, 0), 3, 2, 3).forEach((pos1) -> {
                if (random.nextFloat() < 1.0F / (float) location.pos.distSqr(pos1) * DimensionalDoors.getConfig().getLimboConfig().limboBlocksCorruptingExitWorldAmount) {
                    Block block = location.getWorld().getBlockState(pos1).getBlock();
                    if (UnravelUtil.unravelBlocksMap.containsKey(block)) {
                        location.getWorld().setBlockAndUpdate(pos1,
                                                              UnravelUtil.unravelBlocksMap.get(block).defaultBlockState());
                    }

                    else if (UnravelUtil.whitelistedBlocksForLimboRemoval.contains(block)) {
                        location.getWorld().setBlockAndUpdate(pos1,
                                                              ModBlocks.UNRAVELLED_FABRIC.get().defaultBlockState());
                    }
                }
            });
        }

        else if (destLoc == null) {
            EntityUtils.chat(entity, Component.translatable("rifts.destinations.escape.did_not_use_rift"));
        }

        else {
            EntityUtils.chat(entity, Component.translatable("rifts.destinations.escape.rift_has_closed"));
        }

        return true;
    }
}
