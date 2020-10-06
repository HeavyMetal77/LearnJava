package ua.tarastom.learnjava.data;

import java.util.List;

public class Statistic {
    private int id;
    private String nameTopic; //назва теми
    private int quantityTasksInTopic; //кількість всіх задач в темі
    private int quantitySolvedTasks; //кількість всіх вирішених задач (правильно і не правильно)
    private int numberOfCorrectlySolvedTasks; //кількість правильно вирішених задач
    private List<Integer> listOfIncorrectlySolvedProblems; //список номерів неправильно вирішених задач

    public Statistic(int id, String nameTopic) {
        this.id = id;
        this.nameTopic = nameTopic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameTopic() {
        return nameTopic;
    }

    public void setNameTopic(String nameTopic) {
        this.nameTopic = nameTopic;
    }

    public int getQuantityTasksInTopic() {
        return quantityTasksInTopic;
    }

    public void setQuantityTasksInTopic(int quantityTasksInTopic) {
        this.quantityTasksInTopic = quantityTasksInTopic;
    }

    public int getQuantitySolvedTasks() {
        return quantitySolvedTasks;
    }

    public void setQuantitySolvedTasks(int quantitySolvedTasks) {
        this.quantitySolvedTasks = quantitySolvedTasks;
    }

    public int getNumberOfCorrectlySolvedTasks() {
        return numberOfCorrectlySolvedTasks;
    }

    public void setNumberOfCorrectlySolvedTasks(int numberOfCorrectlySolvedTasks) {
        this.numberOfCorrectlySolvedTasks = numberOfCorrectlySolvedTasks;
    }

    public List<Integer> getListOfIncorrectlySolvedProblems() {
        return listOfIncorrectlySolvedProblems;
    }

    public void setListOfIncorrectlySolvedProblems(List<Integer> listOfIncorrectlySolvedProblems) {
        this.listOfIncorrectlySolvedProblems = listOfIncorrectlySolvedProblems;
    }
}
