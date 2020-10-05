package ua.tarastom.learnjava;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ua.tarastom.learnjava.data.Topic;
import ua.tarastom.learnjava.data.TopicAdapter;

public class ListTopicActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTest;
    private TopicAdapter topicAdapter;
    private List<Topic> topicList = new ArrayList<>();
    private int mode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_topic);

        //ListTopicActivity використовується в навчальному та тренувальному режимах
        //визначаємо в який режим здійснено вхід
        Intent intent = getIntent();
        if (intent.hasExtra("mode")) {
            mode = intent.getIntExtra("mode", 0);
        }

        //тимчасовий хардкод
        topicList.add(new Topic("Типы данных, переменные и массивы"));
        topicList.add(new Topic("Арифметические и логические операции"));
        topicList.add(new Topic("Циклы"));

        recyclerViewTest = findViewById(R.id.recyclerViewTest);
        topicAdapter = new TopicAdapter();
        recyclerViewTest.setLayoutManager(new LinearLayoutManager(this));
        topicAdapter.setTopicList(topicList);
        recyclerViewTest.setAdapter(topicAdapter);

        topicAdapter.setOnTopicClickListener(new TopicAdapter.OnTopicClickListener() {
            @Override
            public void onTopicClick(int position) {
                Intent intent;
                if (mode == 2) {
                    intent = new Intent(ListTopicActivity.this, TestActivity.class);
                }else {
                    intent = new Intent(ListTopicActivity.this, TaskActivity.class);
                }
                Topic topic = topicAdapter.getTopicList().get(position);
                intent.putExtra("position", topic.getNameTopic());
                startActivity(intent);
            }
        });
    }
}