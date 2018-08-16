package net.runelite.client.plugins.loottracker;
import lombok.Value;
import java.util.Date;

@Value
class LootTrackerCSVEntry
{
    private final String monsterName;
    private final int monsterLvl;
    private final Date date;
    private final LootTrackerItemEntry[] items;

}
