package ua.tarastom.learnjava.data;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Statistic {
    @PrimaryKey
    private int id;
    private List<String> nameTopic; //назва теми
    private int quantityTasksInTopic; //кількість всіх задач в темі
    private int quantitySolvedTasks; //кількість всіх вирішених задач (правильно і не правильно)
    private int numberOfCorrectlySolvedTasks; //кількість правильно вирішених задач
    private List<Integer> listOfIncorrectlySolvedProblems; //список номерів неправильно вирішених задач - 0, правильно - 1

    public Statistic() {
    }

    public Statistic(int id, List<String> nameTopic, int quantityTasksInTopic, int quantitySolvedTasks, int numberOfCorrectlySolvedTasks) {
        this.id = id;
        this.nameTopic = nameTopic;
        this.quantityTasksInTopic = quantityTasksInTopic;
        this.quantitySolvedTasks = quantitySolvedTasks;
        this.numberOfCorrectlySolvedTasks = numberOfCorrectlySolvedTasks;
    }

    @Ignore
    public Statistic(int id, List<String> nameTopic, int quantityTasksInTopic) {
        this.id = id;
        this.nameTopic = nameTopic;
        this.quantityTasksInTopic = quantityTasksInTopic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getNameTopic() {
        return nameTopic;
    }

    public void setNameTopic(List<String> nameTopic) {
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
        if (listOfIncorrectlySolvedProblems == null) {
            listOfIncorrectlySolvedProblems = new ArrayList<>();
        }
        return listOfIncorrectlySolvedProblems;
    }

    public void setListOfIncorrectlySolvedProblems(List<Integer> listOfIncorrectlySolvedProblems) {
        this.listOfIncorrectlySolvedProblems = listOfIncorrectlySolvedProblems;
    }
}
