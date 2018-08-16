package net.runelite.client.plugins.loottracker;

import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LootTrackerBuildCSV {

    @Setter
    private String separator;

    private List<LootTrackerCSVEntry> csvEntries = new ArrayList<>();


    public LootTrackerBuildCSV (String separator)
    {
        this.separator = separator;
    }

    public void addLog(final String eventName, final int actorLevel, LootTrackerItemEntry[] items)
    {
        LootTrackerCSVEntry csvEntry = new LootTrackerCSVEntry(eventName, actorLevel, new Date(), items);
        csvEntries.add(csvEntry);
    }

    public String[] generateCsv ()
    {
        String csvText = "";
        String topRow = "timestamp" + this.separator + "event";

        int columnCount = 0;

        for (final LootTrackerCSVEntry e : csvEntries)
        {
            csvText += System.lineSeparator() + e.getDate().getTime() + this.separator
                    + e.getMonsterName() + " (level-" + e.getMonsterLvl() + ")";

            if (columnCount < e.getItems().length) columnCount = e.getItems().length;

            for (final LootTrackerItemEntry i : e.getItems()) {
                csvText += this.separator + i.getQuantity() + this.separator + i.getName() + this.separator + i.getPrice();
            }
        }
        for (int i = 0; i < columnCount; i++)
        {
            topRow += this.separator + "itemquant" + i + this.separator + "itemname" + i + this.separator + "itemvalue" + i;
        }

        String[] csvData = {topRow,csvText};

        return csvData;
    }
    public void resetCsv()
    {
        csvEntries.clear();
    }


}
