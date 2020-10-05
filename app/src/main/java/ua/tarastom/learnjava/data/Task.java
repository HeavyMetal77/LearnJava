package ua.tarastom.learnjava.data;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "tasks")
public class Task {
    @PrimaryKey
    private int idTask;
    private String topic; //тема
    private String question; //запитання
    private String taskStr; //суть завдання
    private List<String> allAnswersList; //всі відповіді
    private List<Boolean> rightAnswers; //правильні відповіді
    private boolean isResolved; //чи вирішена задача

    public Task() {
    }

    @Ignore
    public Task(int idTask, String topic, String question, String taskStr, List<String> allAnswersList, List<Boolean> rightAnswers, boolean isResolved) {
        this.idTask = idTask;
        this.topic = topic;
        this.question = question;
        this.taskStr = taskStr;
        this.allAnswersList = allAnswersList;
        this.rightAnswers = rightAnswers;
        this.isResolved = isResolved;
    }

    public String getTaskStr() {
        return taskStr;
    }

    public void setTaskStr(String taskStr) {
        this.taskStr = taskStr;
    }

    public List<String> getAllAnswersList() {
        return allAnswersList;
    }

    public void setAllAnswersList(List<String> allAnswersList) {
        this.allAnswersList = allAnswersList;
    }

    public List<Boolean> getRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(List<Boolean> rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public boolean isResolved() {
        return isResolved;
    }

    public void setResolved(boolean resolved) {
        isResolved = resolved;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
