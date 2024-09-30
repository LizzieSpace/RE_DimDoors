package org.liz_space.re_dimdoors.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.Direction;
import org.dimdev.dimdoors.api.client.DimensionalPortalRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.HashSet;
import java.util.Set;

import static org.liz_space.re_dimdoors.RE_DimDoorsMain.CONFIG;
import static org.liz_space.re_dimdoors.client.RE_DimDoorsClient.CLIENT_LOGGER;

@Environment(EnvType.CLIENT)
@Mixin(DimensionalPortalRenderer.class)
public class DimensionalPortalRendererMixin {
    @Unique
    private static Set<Direction> CUBOID_DIRECTIONS;

    /**
     * Retrieves a set of all possible directions for constructing a cuboid.
     * Initializes the set if it is not already initialized.
     *
     * @return a set of direction constants representing all possible directions
     */
    @Unique
    private static Set<Direction> getDirections() {
        if (CUBOID_DIRECTIONS == null) { CUBOID_DIRECTIONS = new HashSet<>(Set.of(Direction.values())); }
        return CUBOID_DIRECTIONS;
    }

    /**
     * Creates and returns a new instance of ModelPart.Cuboid with predefined dimensions
     * and directions.
     * <p>
     * Original Values small:
     .2F, .2F, -.1F,
     15.8F, 15.8f, .01F,
     * <p>
     * Original Values big:
     .2F, .2F, -.1F,
     15.8F, 31.8f, .01F,
     * @return a new instance of ModelPart.Cuboid with specified attributes
     */
    @Unique
    private static ModelPart.Cube createCuboid(
            float originX, float originY, float originZ,
            float dimensionX, float dimensionY, float dimensionZ
    ) {
        return new ModelPart.Cube(
                0, 0,
                originX, originY, originZ,
                dimensionX, dimensionY, dimensionZ,
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
    private static ModelPart.Cube injectSmall(ModelPart.Cube cuboid) {
        if (CONFIG.client.getDimPortalRendererMixin()) {
            CLIENT_LOGGER.debug("Injecting small cuboid values");

            return createCuboid(
                    CONFIG.client.dimPortalRenderer.smallPortal.getOriginX(),
                    CONFIG.client.dimPortalRenderer.smallPortal.getOriginY(),
                    CONFIG.client.dimPortalRenderer.smallPortal.getOriginZ(),
                    CONFIG.client.dimPortalRenderer.smallPortal.getDimensionX(),
                    CONFIG.client.dimPortalRenderer.smallPortal.getDimensionY(),
                    CONFIG.client.dimPortalRenderer.smallPortal.getDimensionZ()
            );
        } else return cuboid;
    }

    /**
     * Modifies the given cuboid during the specified point in the method execution.
     *
     * @param cuboid the original ModelPart.Cuboid to be modified
     * @return a new ModelPart.Cuboid
     */
    @ModifyVariable(method = "<clinit>", at = @At("STORE"), ordinal = 1)
    private static ModelPart.Cube injectBig(ModelPart.Cube cuboid) {
        if (CONFIG.client.getDimPortalRendererMixin()) {
            CLIENT_LOGGER.debug("Injecting big cuboid values");

            return createCuboid(
                    CONFIG.client.dimPortalRenderer.bigPortal.getOriginX(),
                    CONFIG.client.dimPortalRenderer.bigPortal.getOriginY(),
                    CONFIG.client.dimPortalRenderer.bigPortal.getOriginZ(),
                    CONFIG.client.dimPortalRenderer.bigPortal.getDimensionX(),
                    CONFIG.client.dimPortalRenderer.bigPortal.getDimensionY(),
                    CONFIG.client.dimPortalRenderer.bigPortal.getDimensionZ()
            );
        } else return cuboid;
    }
}
