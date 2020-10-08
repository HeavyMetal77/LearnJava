package ua.tarastom.learnjava.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private static StatisticsDatabase statisticsDatabase;

    public MainViewModel(@NonNull Application application) {
        super(application);
        statisticsDatabase = StatisticsDatabase.getInstance(getApplication());
    }

    public static StatisticsDatabase getStatisticsDatabase() {
        return statisticsDatabase;
    }

    //getAllStatistics
    public List<Statistic> getAllStatistics() {
        Thread thread = new Thread(new GetAllStatistics());
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return GetAllStatistics.listStatisticsLiveData;
    }

    private static class GetAllStatistics implements Runnable {
        private static List<Statistic> listStatisticsLiveData;

        public GetAllStatistics() {
        }

        @Override
        public void run() {
            listStatisticsLiveData = statisticsDatabase.statDao().getAllStatistics();
        }
    }

    //getStatisticById
    public Statistic getStatisticsById(int id) {
        Thread thread = new Thread(new GetStatisticById(id));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return GetStatisticById.statisticById;
    }

    private static class GetStatisticById implements Runnable {
        private static Statistic statisticById;
        private static int id;

        public GetStatisticById(int idTask) {
            GetStatisticById.id = idTask;
        }

        @Override
        public void run() {
            statisticById = statisticsDatabase.statDao().getStatisticsById(id);
        }
    }

    //getStatisticByNameTopic
    public Statistic getStatisticByNameTopic(String nameTopic) {
        Thread thread = new Thread(new GetStatisticByNameTopic(nameTopic));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return GetStatisticByNameTopic.statisticByNameTopic;
    }

    private static class GetStatisticByNameTopic implements Runnable {
        private static Statistic statisticByNameTopic;
        private static String nameTopic;

        public GetStatisticByNameTopic(String nameTopic) {
            GetStatisticByNameTopic.nameTopic = nameTopic;
        }

        @Override
        public void run() {
            statisticByNameTopic = statisticsDatabase.statDao().getStatisticByTopic(nameTopic);
        }
    }

    public void deleteAllStatistics() {
        Thread thread = new Thread(new DeleteAllStatistics());
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class DeleteAllStatistics implements Runnable {
        @Override
        public void run() {
            statisticsDatabase.statDao().deleteAllStatistics();
        }
    }

    public void deleteStatistic(Statistic statistic) {
        Thread thread = new Thread(new DeleteStatistic(statistic));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class DeleteStatistic implements Runnable {
        private static Statistic statistic;

        public DeleteStatistic(Statistic statistic) {
            DeleteStatistic.statistic = statistic;
        }

        @Override
        public void run() {
            statisticsDatabase.statDao().deleteStatistic(statistic);
        }
    }

    public Long insertStatistic(Statistic statistic) {
        Thread thread = new Thread(new InsertStatistic(statistic));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return InsertStatistic.ret;
    }

    public static class InsertStatistic implements Runnable {
        private static Statistic statistic;
        private static Long ret;

        public InsertStatistic(Statistic statistic) {
            InsertStatistic.statistic = statistic;
        }

        @Override
        public void run() {
            ret = statisticsDatabase.statDao().insertStatistic(statistic);
        }
    }
}
