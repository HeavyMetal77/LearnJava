package ua.tarastom.learnjava.data;

import java.util.List;
import java.util.Objects;

public class Topic {
    private int id; //порядковий номер необхідний для сортування тем, починається з 0
    private List<String> nameTopic;
    private int quantityTasksInTopic;

    //конструктор без параметрів обов'язковий
    public Topic() {
    }

    public Topic(Topic topic) {
        this.id = topic.id;
        this.nameTopic = topic.getNameTopic();
        this.quantityTasksInTopic = topic.quantityTasksInTopic;
    }

    public Topic(int id, List<String> nameTopic, int quantityTasksInTopic) {
        this.id = id;
        this.nameTopic = nameTopic;
        this.quantityTasksInTopic = quantityTasksInTopic;
    }

    public Topic(List<String> nameTopic) {
        this.nameTopic = nameTopic;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return id == topic.id &&
                nameTopic.equals(topic.nameTopic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameTopic);
    }
}
