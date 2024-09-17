package org.liz_space.dimdoorsretexture.client;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;

public class DimPortalRetexture implements ClientModInitializer {
    public static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void onInitializeClient() {
        LOGGER.atInfo().log("tampering with dimPortal models");

    }
}
