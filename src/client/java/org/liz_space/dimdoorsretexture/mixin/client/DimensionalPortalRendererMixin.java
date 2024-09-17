package org.liz_space.dimdoorsretexture.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.util.math.Direction;
import org.dimdev.dimdoors.api.client.DimensionalPortalRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Environment(EnvType.CLIENT)
@Mixin(DimensionalPortalRenderer.class)
public class DimensionalPortalRendererMixin {
    @Unique
    private static Set<Direction> CUBOID_DIRECTIONS;

    @Unique
    private static Set<Direction> getDirections() {
        if (CUBOID_DIRECTIONS == null) { CUBOID_DIRECTIONS = new HashSet<>(Set.of(Direction.values())); }
        return CUBOID_DIRECTIONS;
    }

    /**
     * Creates and returns a new instance of ModelPart.Cuboid with predefined dimensions
     * and directions.
     *
     * @return a new instance of ModelPart.Cuboid with specified attributes
     */
    @Unique
    private static ModelPart.Cuboid createCuboid(float size_y) {
        return new ModelPart.Cuboid(
                0, 0,
                .2F, .2F, -.1F,
                15.8F, size_y, .01F,
                .0F, .0F, .0F,
                false,
                1024.0F, 1024.0F,
                getDirections()
        );
    }

    /**
     * Modifies the given cuboid during the specified point in the method execution.
     *
     * @param cuboid the original ModelPart.Cuboid to be modified
     * @return a new ModelPart.Cuboid
     */
    @ModifyVariable(method = "<clinit>", at = @At("STORE"), ordinal = 0)
    private static ModelPart.Cuboid injectSmall(ModelPart.Cuboid cuboid) {
        return createCuboid(15.8F);
    }

    /**
     * Modifies the given cuboid during the specified point in the method execution.
     *
     * @param cuboid the original ModelPart.Cuboid to be modified
     * @return a new ModelPart.Cuboid
     */
    @ModifyVariable(method = "<clinit>", at = @At("STORE"), ordinal = 1)
    private static ModelPart.Cuboid injectBig(ModelPart.Cuboid cuboid) {
        return createCuboid(31.8F);
    }
}
