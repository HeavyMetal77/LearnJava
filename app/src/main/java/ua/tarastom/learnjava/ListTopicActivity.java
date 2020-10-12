package ua.tarastom.learnjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemStatistics) {
            Intent intent = new Intent(this, StatisticsActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.itemExitAccount) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("exit", 0);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

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
        RecyclerView recyclerViewTopic = findViewById(R.id.recyclerViewTopic);
        topicAdapter = new TopicAdapter();
        recyclerViewTopic.setLayoutManager(new LinearLayoutManager(this));
        Collections.sort(topicList, (o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
        topicAdapter.setTopicList(topicList);
        topicAdapter.setStatisticList(statisticList);
        recyclerViewTopic.setAdapter(topicAdapter);
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
        if (statisticList == null || statisticList.size() == 0) {
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
            db.collection(nameCollection).addSnapshotListener((value, error) -> {
                if (value != null) {
                    topicList = value.toObjects(Topic.class);
                }
                setStatisticsList(); //створюю нову або дістаю з БД SQLite дані статистики
                loadRecyclerView(); //встановлюю елементи інтерфейсу
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
        finish();
    }
}