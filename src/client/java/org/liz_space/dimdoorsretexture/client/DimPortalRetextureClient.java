package org.liz_space.dimdoorsretexture.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;

import static org.liz_space.dimdoorsretexture.DimDoorsRetextureMain.LOGGER;
import static org.liz_space.dimdoorsretexture.DimDoorsRetextureMain.MOD_ID;

public class DimPortalRetextureClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        LOGGER.info("tampering with dimPortal rendering");

//        public static final ResourceLocation id = new ResourceLocation("dimdoors");

        FabricLoader.getInstance().getModContainer(MOD_ID).ifPresent(modContainer -> {
            ResourceManagerHelper.registerBuiltinResourcePack(
                    new ResourceLocation(MOD_ID, "default"),
                    modContainer,
                    ResourcePackActivationType.DEFAULT_ENABLED
            );
        });
    }
}
