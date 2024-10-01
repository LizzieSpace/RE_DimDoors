/*
 * Copyright (c) 2024.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.liz_space.re_dimdoors;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

import static org.liz_space.re_dimdoors.RE_DimDoorsMain.MOD_ID;

@Config(name = MOD_ID)
public class ModConfig implements ConfigData {

    @ConfigEntry.Gui.TransitiveObject
    @ConfigEntry.Category("Common")
    public Common common = new Common();

    @ConfigEntry.Gui.TransitiveObject
    @ConfigEntry.Category("Client")
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

        @Comment("Overrides the hardcoded values for DimPortalRenderer")
        boolean dimPortalRendererMixin = false;

        @ConfigEntry.Gui.CollapsibleObject
        public DimPortalRendererMixin dimPortalRenderer = new DimPortalRendererMixin();

        public static class DimPortalRendererMixin {

            @ConfigEntry.Gui.CollapsibleObject
            public bigPortal bigPortal = new bigPortal();

            @ConfigEntry.Gui.CollapsibleObject
            public smallPortal smallPortal = new smallPortal();

            public static class bigPortal {
                float originX = .2F;
                float originY = .2F;
                float originZ = -.1F;
                float dimensionX = 15.8F;
                float dimensionY = 31.8f;
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
                float originX = .2F;
                float originY = .2F;
                float originZ = -.1F;
                float dimensionX = 15.8F;
                float dimensionY = 15.8f;
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
