package net.runelite.client.plugins.loottracker;

import lombok.Setter;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static net.runelite.client.RuneLite.RUNELITE_DIR;

public class LootTrackerExportFile {

    private static final File CSV_DIR = new File(RUNELITE_DIR, "csv");
    private static final DateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
    private static final String AppendedCsvFile = "LootTrackerData";

    @Setter
    LootTrackerExportConfig config;
    public LootTrackerExportFile(LootTrackerExportConfig config)
    {
        this.config = config;
        CSV_DIR.mkdirs();
    }

    static String format(Date date)
    {
        synchronized (TIME_FORMAT)
        {
            return TIME_FORMAT.format(date);
        }
    }

    public void exportCsv(String[] csvText)
    {
        if (csvText[1].length() > 1) {

            String fileName = "";
            boolean fileExists = false;
            if (config.appendFile()) {
                fileName = AppendedCsvFile;
                File file = new File(CSV_DIR, fileName + ".csv");
                fileExists = file.exists();
                try {
                    file.createNewFile();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    PrintWriter pw = new PrintWriter(new FileOutputStream(
                            file,
                            true));

                    if (fileExists) {
                        pw.print(csvText[1]);
                    } else {
                        pw.print(csvText[0] + csvText[1]);
                    }

                    pw.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            } else {
                fileName = "LootTracker_" + format(new Date());
                File file = new File(CSV_DIR, fileName + ".csv");
                try {
                    file.createNewFile();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    PrintWriter pw = new PrintWriter(file);
                    pw.println(csvText[0] + csvText[1]);
                    pw.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
