package ua.tarastom.learnjava.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class TaskDatabase extends RoomDatabase {
    private static TaskDatabase taskDatabase;
    private static final Object LOCK = new Object();
    private static final String DB_NAME = "taskList.db";

    public static TaskDatabase getInstance(Context context) {
        synchronized (LOCK) {
            if (taskDatabase == null) {
                taskDatabase = Room.databaseBuilder(context, TaskDatabase.class, DB_NAME)
                        .fallbackToDestructiveMigration().build();
            }
            return taskDatabase;
        }
    }

    public abstract TaskDao taskDao();
}
