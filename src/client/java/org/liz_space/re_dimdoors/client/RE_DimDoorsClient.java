package org.liz_space.re_dimdoors.client;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.event.Level;

import static org.liz_space.re_dimdoors.RE_DimDoorsMain.CONFIG;
import static org.liz_space.re_dimdoors.RE_DimDoorsMain.MOD_ID;

public class RE_DimDoorsClient implements ClientModInitializer {
    public static final Logger CLIENT_LOGGER = LogUtils.getLogger();

    @Override
    public void onInitializeClient() {
        CLIENT_LOGGER.info("tampering with dimPortal rendering");
        if (CONFIG.common.getDebug()) {
            LogUtils.configureRootLoggingLevel(Level.DEBUG);
        }

//        public static final ResourceLocation id = new ResourceLocation("dimdoors");

        FabricLoader.getInstance().getModContainer(MOD_ID).ifPresent(modContainer -> ResourceManagerHelper.registerBuiltinResourcePack(
                new ResourceLocation(MOD_ID, "default"),
                modContainer,
                ResourcePackActivationType.DEFAULT_ENABLED
        ));
    }
}
