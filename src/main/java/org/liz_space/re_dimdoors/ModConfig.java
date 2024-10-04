package org.liz_space.re_dimdoors;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.liz_space.re_dimdoors.RE_DimDoorsMain.*;

@Config(name = MOD_ID)
@Config.Gui.Background(value = "dimdoors:textures/other/warp.png")
public class ModConfig implements ConfigData {

    @ConfigEntry.Gui.TransitiveObject
    @ConfigEntry.Category("Common")
    @ConfigEntry.Gui.PrefixText
    public Common common = new Common();

    @ConfigEntry.Gui.TransitiveObject
    @ConfigEntry.Category("Client")
    @ConfigEntry.Gui.PrefixText
    public Client client = new Client();
    @Override
    public void validatePostLoad() throws ValidationException {
        this.common.worldList = this.common.worldList.stream().distinct().collect(Collectors.toList());
        if (this.common.worldList.isEmpty()) {this.common.worldList.add(this.common.defaultEscapeTargetWorld);}

        ConfigData.super.validatePostLoad();
    }

    public static class Common {


        @Comment("""
                 List of possible limbo escape dimensions:
                 <namespace>:<dimension_id>
                 """)
        public List<String> worldList = new ArrayList<>();
        @Comment("""
                 Fallback value for EscapeTarget dimension.
                 if invalid value is set, things may break.
                 """)
        public String defaultEscapeTargetWorld = "minecraft:overworld";
        @Comment("""
                 patches the Detached Rift decay function,
                 making the decay pattern spherical instead of cubic.
                 """)
        boolean radialDecayMixin = true;
        @ConfigEntry.Gui.Excluded
        @Comment("""
                 Uses the default limbo escape world
                 in lieu of Implementing a list of possible escape targets.
                 
                 Current Implementation of EscapeTargetMixin may break if true
                 """)
        boolean useEscapeTargetWorld = false;

        @Comment("Activates debug logging for the mod")
        @ConfigEntry.Gui.Excluded
        boolean debug = true;

        public boolean getDebug() {return this.debug;}

        public boolean getRadialDecayMixin() {return this.radialDecayMixin;}

        public boolean getUseEscapeTargetWorld() {return this.useEscapeTargetWorld;}

        public String getDefaultEscapeTargetWorld() {return this.defaultEscapeTargetWorld;}

        public List<String> getWorldList() {return this.worldList;}

        public void rmFromWorldList(String world) {
            MAIN_LOGGER.info("Removing faulty world {} from EscapeTarget worldList...", world);
            this.worldList.remove(world);
            if (this.worldList.isEmpty()) this.worldList.add(CONFIG.common.getDefaultEscapeTargetWorld());
        }

    }

    public static class Client {

        @ConfigEntry.Gui.CollapsibleObject
        @ConfigEntry.Gui.TransitiveObject
        @Comment("""
                 Edit values for the portal polygons.
                 Requires restart.
                 """)
        public DimPortalRendererMixin dimPortalRenderer = new DimPortalRendererMixin();
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""
                 Enables editing values for the portal polygons.
                 Requires restart
                 """)
        boolean dimPortalRendererMixin = false;

        public boolean getDimPortalRendererMixin() {return this.dimPortalRendererMixin;}

        public static class DimPortalRendererMixin {

            @ConfigEntry.Gui.CollapsibleObject
            public bigPortal bigPortal = new bigPortal();

            @ConfigEntry.Gui.CollapsibleObject
            public smallPortal smallPortal = new smallPortal();

            public static class bigPortal {
                @ConfigEntry.Gui.RequiresRestart
                float originX = .2F;
                @ConfigEntry.Gui.RequiresRestart
                float originY = .2F;
                @ConfigEntry.Gui.RequiresRestart
                float originZ = -.1F;
                @ConfigEntry.Gui.RequiresRestart
                float dimensionX = 15.8F;
                @ConfigEntry.Gui.RequiresRestart
                float dimensionY = 31.8f;
                @ConfigEntry.Gui.RequiresRestart
                float dimensionZ = .01F;

                public float getDimensionX() {return this.dimensionX;}

                public float getDimensionY() {return this.dimensionY;}

                public float getDimensionZ() {return this.dimensionZ;}

                public float getOriginX() {return this.originX;}

                public float getOriginY() {return this.originY;}

                public float getOriginZ() {return this.originZ;}
            }

            public static class smallPortal {
                @ConfigEntry.Gui.RequiresRestart
                float originX = .2F;
                @ConfigEntry.Gui.RequiresRestart
                float originY = .2F;
                @ConfigEntry.Gui.RequiresRestart
                float originZ = -.1F;
                @ConfigEntry.Gui.RequiresRestart
                float dimensionX = 15.8F;
                @ConfigEntry.Gui.RequiresRestart
                float dimensionY = 15.8f;
                @ConfigEntry.Gui.RequiresRestart
                float dimensionZ = .01F;

                public float getDimensionX() {return this.dimensionX;}

                public float getDimensionY() {return this.dimensionY;}

                public float getDimensionZ() {return this.dimensionZ;}

                public float getOriginX() {return this.originX;}

                public float getOriginY() {return this.originY;}

                public float getOriginZ() {return this.originZ;}
            }
        }
    }
}
