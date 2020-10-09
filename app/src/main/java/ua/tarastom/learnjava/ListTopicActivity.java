package ua.tarastom.learnjava;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ua.tarastom.learnjava.data.MainViewModel;
import ua.tarastom.learnjava.data.Statistic;
import ua.tarastom.learnjava.data.Topic;
import ua.tarastom.learnjava.data.TopicAdapter;

public class ListTopicActivity extends AppCompatActivity {

    private TopicAdapter topicAdapter;
    private List<Topic> topicList = new ArrayList<>();
    private int mode;
    private MainViewModel mainViewModel;
    private List<Statistic> statisticList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_topic);

        if (mainViewModel == null) {
            mainViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(MainViewModel.class);
        }

        //ListTopicActivity використовується в навчальному та тренувальному режимах
        //визначаємо в який режим здійснено вхід
//        Intent intent = getIntent();
//        if (intent.hasExtra("mode")) {
//            mode = intent.getIntExtra("mode", 0);
//        }
        //отримую з БД Cloud Firestore інформацію про назви тем і кількість завдань в кожній темі
        //і завантажую в RecyclerView
        getDataFromCloudDB("topicList");
    }

    private void loadRecyclerView() {
        RecyclerView recyclerViewTest = findViewById(R.id.recyclerViewTest);
        topicAdapter = new TopicAdapter();
        recyclerViewTest.setLayoutManager(new LinearLayoutManager(this));
        Collections.sort(topicList, (o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
        topicAdapter.setTopicList(topicList);
        topicAdapter.setStatisticList(statisticList);
        recyclerViewTest.setAdapter(topicAdapter);
        topicAdapter.setOnTopicClickListener(position -> {
            Intent intent;
//            if (mode == 2) {
//            intent = new Intent(ListTopicActivity.this, TestActivity.class);
//            } else {
                intent = new Intent(ListTopicActivity.this, TaskActivity.class);
//            }
            Topic topic = topicAdapter.getTopicList().get(position);
            intent.putExtra("position", topic.getId());
            startActivity(intent);
        });
    }

    //створюю нову або дістаю з БД SQLite дані статистики
    private void setStatisticsList() {
        statisticList = mainViewModel.getAllStatistics();
        if (statisticList == null || statisticList.size()==0) {
            statisticList = new ArrayList<>();
            for (int i = 0; i < topicList.size(); i++) {
                Topic topic = topicList.get(i);
                Statistic statistic = new Statistic(topic.getId(), topic.getNameTopic(), topic.getQuantityTasksInTopic());
                statisticList.add(statistic);
                mainViewModel.insertStatistic(statistic);
            }
        }
    }

    //getTopics
    public void getDataFromCloudDB(String nameCollection) {
        GetDataFromCloudDB fromCloudDB = new GetDataFromCloudDB(nameCollection);
        Thread thread = new Thread(fromCloudDB);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class GetDataFromCloudDB implements Runnable {
        private String nameCollection;

        public GetDataFromCloudDB(String collectionName) {
            this.nameCollection = collectionName;
        }

        @Override
        public void run() {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection(nameCollection).addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    if (value != null) {
                        topicList = value.toObjects(Topic.class);
                    }
                    setStatisticsList(); //створюю нову або дістаю з БД SQLite дані статистики
                    loadRecyclerView(); //встановлюю елементи інтерфейсу
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        //эмулируем нажатие на HOME, сворачивая приложение
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}