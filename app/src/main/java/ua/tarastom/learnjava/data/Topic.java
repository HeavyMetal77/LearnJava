package ua.tarastom.learnjava.data;

public class Topic {
    private String nameTopic;
    private int quantityTasksInTopic;

    //конструктор без параметрів обов'язковий
    public Topic() {
    }

    public Topic(String nameTopic, int quantityTasksInTopic) {
        this.nameTopic = nameTopic;
        this.quantityTasksInTopic = quantityTasksInTopic;
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

    @Override
    public String toString() {
        return nameTopic;
    }
}
