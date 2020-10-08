package ua.tarastom.learnjava.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Statistic.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class StatisticsDatabase extends RoomDatabase {
    private static final String NAME_DB = "statistic.db";
    private static StatisticsDatabase statisticsDatabase;
    private static final Object LOCK = new Object();

    public static StatisticsDatabase getInstance(Context context) {
        synchronized (LOCK) {
            if (statisticsDatabase == null) {
                statisticsDatabase = Room.databaseBuilder(context, StatisticsDatabase.class, NAME_DB).fallbackToDestructiveMigration().build();
            }
        }
        return statisticsDatabase;
    }

    public abstract StatisticDao statDao ();
}
