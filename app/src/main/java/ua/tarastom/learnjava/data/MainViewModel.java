package ua.tarastom.learnjava.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private static TaskDatabase taskDatabase;

    private LiveData<List<Task>> listTaskLiveData;

    public MainViewModel(@NonNull Application application) {
        super(application);
        taskDatabase = TaskDatabase.getInstance(getApplication());
        listTaskLiveData = taskDatabase.taskDao().getAllTasks();
    }

    //getTaskById
    public Task getTaskById(int idTask) {
        Thread thread = new Thread(new GetTaskById(idTask));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return GetTaskById.taskById;
    }

    private static class GetTaskById implements Runnable {
        private static Task taskById;
        private static int idTask;

        public GetTaskById(int idTask) {
            GetTaskById.idTask = idTask;
        }
        @Override
        public void run() {
            taskById = taskDatabase.taskDao().getTaskById(idTask);
        }
    }

    //getAllTaskByTopic
    public LiveData<List<Task>> getAllTaskByTopic(String topic) {
        Thread thread = new Thread(new GetAllTaskByTopic(topic));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return GetAllTaskByTopic.taskByTopic;
    }

    private static class GetAllTaskByTopic implements Runnable {
        private static LiveData<List<Task>> taskByTopic;
        private static String topic;

        public GetAllTaskByTopic(String topic) {
            GetAllTaskByTopic.topic = topic;
        }
        @Override
        public void run() {
            taskByTopic = taskDatabase.taskDao().getAllTasksByTopic(topic);
        }
    }

    public void deleteAllTask() {
        Thread thread = new Thread(new DeleteAllTask());
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static class DeleteAllTask implements Runnable{
        @Override
        public void run() {
            taskDatabase.taskDao().deleteAllTasks();
        }
    }

    public void deleteTask(Task task) {
        Thread thread = new Thread(new DeleteTask(task));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static class DeleteTask implements Runnable{
        private static Task task;

        public DeleteTask(Task task) {
            DeleteTask.task = task;
        }

        @Override
        public void run() {
            taskDatabase.taskDao().deleteTask(task);
        }
    }

    public void insertTask(Task task) {
        Thread thread = new Thread(new InsertTask(task));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static class InsertTask implements Runnable{
        private static Task task;

        public InsertTask(Task task) {
            InsertTask.task = task;
        }

        @Override
        public void run() {
            taskDatabase.taskDao().insertTask(task);
        }
    }
}
