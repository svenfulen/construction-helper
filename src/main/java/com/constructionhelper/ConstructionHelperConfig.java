package com.constructionhelper;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("ConstructionHelper")
public interface ConstructionHelperConfig extends Config
{
    String GROUP = "ConstructionHelper";
    @ConfigItem(
            keyName = "showSidebar",
            name = "Show Icon in Sidebar",
            description = "Show the Construction Helper icon in the sidebar",
            position = 1
    )
    default boolean showSidebar()
    {
        return true;
    }
}
