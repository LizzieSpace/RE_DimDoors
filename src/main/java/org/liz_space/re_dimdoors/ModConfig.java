package org.liz_space.re_dimdoors;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

import static org.liz_space.re_dimdoors.RE_DimDoorsMain.MOD_ID;

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
    public static class Common {


        @Comment("patches the Detached Rift decay function, making the decay pattern spherical instead of cubic.")
        boolean radialDecayMixin = true;

        @Comment("Activates debug logging for the mod")
        @ConfigEntry.Gui.Excluded
        boolean debug = true;

        public boolean getDebug() {
            return debug;
        }

        public boolean getRadialDecayMixin() {
            return radialDecayMixin;
        }
    }

    public static class Client {

        @ConfigEntry.Gui.CollapsibleObject
        @ConfigEntry.Gui.TransitiveObject
        @Comment("Edit values for the portal polygons.\nRequires restart.")
        public DimPortalRendererMixin dimPortalRenderer = new DimPortalRendererMixin();
        @ConfigEntry.Gui.RequiresRestart
        @Comment("Enables editing values for the portal polygons.\nRequires restart")
        boolean dimPortalRendererMixin = false;

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

                public float getDimensionX() {
                    return dimensionX;
                }

                public float getDimensionY() {
                    return dimensionY;
                }

                public float getDimensionZ() {
                    return dimensionZ;
                }

                public float getOriginX() {
                    return originX;
                }

                public float getOriginY() {
                    return originY;
                }

                public float getOriginZ() {
                    return originZ;
                }
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

                public float getDimensionX() {
                    return dimensionX;
                }

                public float getDimensionY() {
                    return dimensionY;
                }

                public float getDimensionZ() {
                    return dimensionZ;
                }

                public float getOriginX() {
                    return originX;
                }

                public float getOriginY() {
                    return originY;
                }

                public float getOriginZ() {
                    return originZ;
                }
            }
        }

        public boolean getDimPortalRendererMixin() {
            return dimPortalRendererMixin;
        }
    }


}
