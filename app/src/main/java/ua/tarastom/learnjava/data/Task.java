package ua.tarastom.learnjava.data;

import java.util.List;
import java.util.Map;

public class Task {
    private int idTask; //номер завдання
    private int idTopic; //номер теми
    private List<String> topic; //тема
    private List<String> question; //запитання
    private String taskStr; //суть завдання
    private Map<String, Boolean> answermap; //всі відповіді / правильні відповіді
    private boolean isResolved; //чи вирішена задача

    public Task() {
    }

    public Task(int idTask, int idTopic, List<String> topic, List<String> question, String taskStr, Map<String, Boolean> answermap, boolean isResolved) {
        this.idTask = idTask;
        this.idTopic = idTopic;
        this.topic = topic;
        this.question = question;
        this.taskStr = taskStr;
        this.answermap = answermap;
        this.isResolved = isResolved;
    }

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public int getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(int idTopic) {
        this.idTopic = idTopic;
    }

    public List<String> getTopic() {
        return topic;
    }

    public void setTopic(List<String> topic) {
        this.topic = topic;
    }

    public List<String> getQuestion() {
        return question;
    }

    public void setQuestion(List<String> question) {
        this.question = question;
    }

    public String getTaskStr() {
        return taskStr;
    }

    public void setTaskStr(String taskStr) {
        this.taskStr = taskStr;
    }

    public void setAnswermap(Map<String, Boolean> answermap) {
        this.answermap = answermap;
    }

    public Map<String, Boolean> getAnswermap() {
        return answermap;
    }

    public boolean isResolved() {
        return isResolved;
    }

    public void setResolved(boolean resolved) {
        isResolved = resolved;
    }
}
