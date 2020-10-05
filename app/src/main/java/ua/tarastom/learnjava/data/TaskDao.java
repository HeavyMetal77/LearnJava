package ua.tarastom.learnjava.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM tasks")
    LiveData<List<Task>> getAllTasks();

    @Query("SELECT * FROM tasks WHERE topic == :topic")
    LiveData<List<Task>> getAllTasksByTopic(String topic);

    @Query("SELECT * FROM tasks WHERE idTask == :idTask")
    Task getTaskById(int idTask);

    @Query("DELETE FROM tasks")
    void deleteAllTasks();

    @Delete
    void deleteTask(Task task);

    @Insert
    void insertTask(Task task);

}
