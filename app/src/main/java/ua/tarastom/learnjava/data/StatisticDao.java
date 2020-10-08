package ua.tarastom.learnjava.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StatisticDao {
    @Query("SELECT * FROM statistic")
    List<Statistic> getAllStatistics();

    @Query("SELECT * FROM statistic WHERE id == :id")
    Statistic getStatisticsById(int id);

    @Query("SELECT * FROM statistic WHERE nameTopic == :nameTopic")
    Statistic getStatisticByTopic(String nameTopic);

    @Query("DELETE FROM statistic")
    void deleteAllStatistics();

    @Delete
    void deleteStatistic(Statistic statistic);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertStatistic(Statistic statistic);

    @Insert
    void insert(List<Statistic> statistics);
}
