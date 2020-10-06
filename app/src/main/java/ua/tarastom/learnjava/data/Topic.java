package ua.tarastom.learnjava.data;

public class Topic {
    private int id; //порядковий номер необхідний для сортування тем
    private String nameTopic;
    private int quantityTasksInTopic;

    //конструктор без параметрів обов'язковий
    public Topic() {
    }

    public Topic(Topic topic) {
        this.id = topic.id;
        this.nameTopic = topic.getNameTopic();
        this.quantityTasksInTopic = topic.quantityTasksInTopic;
    }

    public Topic(String nameTopic, int quantityTasksInTopic) {
        this.nameTopic = nameTopic;
        this.quantityTasksInTopic = quantityTasksInTopic;
    }

    public Topic(String nameTopic) {
        this.nameTopic = nameTopic;
    }

    public Topic(int id, String nameTopic, int quantityTasksInTopic) {
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
