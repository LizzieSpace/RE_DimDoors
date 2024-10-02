package org.liz_space.re_dimdoors.client;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.liz_space.re_dimdoors.ModConfig;

import static org.liz_space.re_dimdoors.client.RE_DimDoorsClient.CLIENT_LOGGER;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            CLIENT_LOGGER.info("ModMenuIntegration getting config screen");
            return AutoConfig.getConfigScreen(ModConfig.class, parent).get();
        };
    }

}