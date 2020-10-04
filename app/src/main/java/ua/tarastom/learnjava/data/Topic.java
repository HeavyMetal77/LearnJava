package ua.tarastom.learnjava.data;

public class Topic {
    private String nameTopic;
    private String nameSubTopic;

    //конструктор без параметрів обов'язковий
    public Topic() {
    }

    public Topic(String nameTopic, String nameSubTopic) {
        this.nameTopic = nameTopic;
        this.nameSubTopic = nameSubTopic;
    }

    public String getNameTopic() {
        return nameTopic;
    }

    public void setNameTopic(String nameTopic) {
        this.nameTopic = nameTopic;
    }

    public String getNameSubTopic() {
        return nameSubTopic;
    }

    public void setNameSubTopic(String nameSubTopic) {
        this.nameSubTopic = nameSubTopic;
    }

    @Override
    public String toString() {
        return nameTopic + " " + nameSubTopic;
    }
}
