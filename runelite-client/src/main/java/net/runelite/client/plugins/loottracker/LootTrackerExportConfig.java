
package net.runelite.client.plugins.loottracker;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("loottracker")
public interface LootTrackerExportConfig extends Config
{
	@ConfigItem(
		keyName = "autoExport",
		name = "Export data on exit",
		description = "Export loot data automatically when Runelite is closed",
		position = 1
	)
	default boolean autoExport()
	{
		return false;
	}

	@ConfigItem(
			keyName = "appendFile",
			name = "Append instead",
			description = "Append all loot data to single file instead of separate files",
			position = 2
	)
	default boolean appendFile()
	{
		return false;
	}

	@ConfigItem(
			keyName = "minValueExport",
			name = "Minimum value of loot to be included in exported data ",
			description = "Loot below value won't be included in the exported data",
			position = 3
	)
	default int minValueExport()
	{
		return 0;
	}

	@ConfigItem(
			keyName = "separator",
			name = "CSV Separator",
			description = "Use ;",
			position = 3
	)
	default String getSeparator()
	{
		return ";";
	}
}
