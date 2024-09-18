package org.liz_space.dimdoorsretexture.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;
import org.dimdev.dimdoors.block.entity.DetachedRiftBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DetachedRiftBlockEntity.class)
public class DetachedRiftBlockEntityMixin {

    @Inject(method = "applySpreadDecay", cancellable = true, at = @At(value = "INVOKE", target = "Lorg/dimdev/dimdoors/world/decay/Decay;decayBlock(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lorg/dimdev/dimdoors/world/decay/DecaySource;)V"))
    private void injectBreak(ServerLevel world, BlockPos pos, CallbackInfo ci, @Local(ordinal = 1) BlockPos selected, @Local float radius) {
        Vec3 targetCenter = selected.getCenter();
        Vec3 posCenter = pos.getCenter();
        if (posCenter.distanceTo(targetCenter) >= (int) radius) {
            ci.cancel();
        }
    }
}