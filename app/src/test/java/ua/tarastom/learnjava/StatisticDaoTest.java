package ua.tarastom.learnjava;

import android.app.Application;

import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import org.junit.After;
import org.junit.Before;

import ua.tarastom.learnjava.data.MainViewModel;
import ua.tarastom.learnjava.data.StatisticDao;
import ua.tarastom.learnjava.data.StatisticsDatabase;

public class StatisticDaoTest extends Application {
    private StatisticsDatabase db;
    private StatisticDao statisticDao;
    private MainViewModel mainViewModel;

    @Before
    public void createDb() {
        db = Room.inMemoryDatabaseBuilder(this ,
                StatisticsDatabase.class)
                .build();
        statisticDao = db.statDao();
        if (mainViewModel == null) {
            mainViewModel = new ViewModelProvider.AndroidViewModelFactory(this).create(MainViewModel.class);
        }
    }

    @After
    public void closeDb() {
        db.close();
    }
}
