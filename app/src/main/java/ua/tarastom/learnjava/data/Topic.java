package ua.tarastom.learnjava.data;

public class Topic {
    private String nameTopic;
    private int quantityTasksInTopic;

    //конструктор без параметрів обов'язковий
    public Topic() {
    }

    public Topic(String nameTopic) {
        this.nameTopic = nameTopic;
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
}
