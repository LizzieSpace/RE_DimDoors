package org.liz_space.re_dimdoors;

import com.mojang.logging.LogUtils;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.slf4j.event.Level;

import static org.liz_space.re_dimdoors.RE_DimDoorsMain.*;

public class RE_DimDoorsPreLaunch implements PreLaunchEntrypoint {

    @Override
    public void onPreLaunch() {
        LogUtils.configureRootLoggingLevel(Level.DEBUG);

        AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

        //TODO: REORGANIZE LOGGING
        MAIN_LOGGER.info("Prelaunching Configs for {}", MOD_ID);
    }
}
