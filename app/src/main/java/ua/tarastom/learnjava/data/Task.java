package ua.tarastom.learnjava.data;

import java.util.List;

public class Task {
    private int idTask;
    private String question; //запитання
    private String taskStr; //суть завдання
    private List<String> allAnswersList; //всі відповіді
    private List<Boolean> rightAnswers; //правильні відповіді

    public Task(int idTask, String question, String taskStr, List<String> allAnswersList, List<Boolean> rightAnswers) {
        this.idTask = idTask;
        this.question = question;
        this.taskStr = taskStr;
        this.allAnswersList = allAnswersList;
        this.rightAnswers = rightAnswers;
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
}
