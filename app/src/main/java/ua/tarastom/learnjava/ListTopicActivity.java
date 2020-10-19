package ua.tarastom.learnjava;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

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
import java.util.Locale;

import ua.tarastom.learnjava.data.MainViewModel;
import ua.tarastom.learnjava.data.Statistic;
import ua.tarastom.learnjava.data.Task;
import ua.tarastom.learnjava.data.Topic;
import ua.tarastom.learnjava.data.TopicAdapter;

public class ListTopicActivity extends AppCompatActivity {

    private TopicAdapter topicAdapter;
    private List<Topic> topicList;
    private List<Task> taskList = new ArrayList<>();
    private int language;
    private MainViewModel mainViewModel;
    private List<Statistic> statisticList;
    private String nameCollection = "taskList";
    private ProgressBar progressBarTopicActivity;
    private RecyclerView recyclerViewTopic;
    public static final String APP_PREFERENCES = "LearnJavaSettings";
    public static final String APP_PREFERENCES_LANGUAGE = "language";
    private MenuItem itemExitAccount;
    private MenuItem itemStatistics;
    private MenuItem itemLanguage;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        //локалізую меню
        itemExitAccount = menu.getItem(0);
        itemLanguage = menu.getItem(1);
        itemStatistics = menu.getItem(2);
        itemExitAccount.setTitle(getResStringLanguage(R.string.item_exit_account, getLanguageAbbreviation(language)));
        itemStatistics.setTitle(getResStringLanguage(R.string.item_statistics, getLanguageAbbreviation(language)));
        itemLanguage.setTitle(getResStringLanguage(R.string.item_language, getLanguageAbbreviation(language)));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemExitAccount) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("exit", 0);
            startActivity(intent);
            finish();
        }
        if (item.getItemId() == R.id.itemStatistics) {
            Intent intent = new Intent(this, StatisticsActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.itemLanguage) {
            Intent intent = new Intent(this, LanguageActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setLanguage() {
        //локалізація списку тем
        String displayLanguage = Locale.getDefault().getDisplayLanguage();
        switch (displayLanguage) {
            case "русский":
                language = 0;
                break;
            case "English":
                language = 1;
                break;
            case "українська":
                language = 2;
                break;
            default:
                language = 1;
        }
    }

    private void getLanguagePreferences() {
        SharedPreferences mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (mSettings.contains(APP_PREFERENCES_LANGUAGE)) {
            language = mSettings.getInt(APP_PREFERENCES_LANGUAGE, language);
        } else {
            setLanguage();
        }
    }

    private String getLanguageAbbreviation(int language) {
        String abbr;
        switch (language) {
            case 0:
                abbr = "ru";
                break;
            case 1:
                abbr = "us";
                break;
            case 2:
                abbr = "uk";
                break;
            default:
                abbr = "us";
        }
        return abbr;
    }

    public String getResStringLanguage(int id, String lang) {
        //Get default locale to back it
        Resources res = getResources();
        Configuration conf = res.getConfiguration();
        Locale savedLocale = conf.locale;
        //Retrieve resources from desired locale
        Configuration confAr = getResources().getConfiguration();
        confAr.locale = new Locale(lang);
        DisplayMetrics metrics = new DisplayMetrics();
        Resources resources = new Resources(getAssets(), metrics, confAr);
        //Get string which you want
        String string = resources.getString(id);
        //Restore default locale
        conf.locale = savedLocale;
        res.updateConfiguration(conf, null);
        //return the string that you want
        return string;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //при зміні мови перезавантажую адаптер
        if (topicList != null) {
            getLanguagePreferences();
            loadRecyclerView();
        }
        //також міняю мову меню
        if (itemExitAccount != null && itemStatistics != null && itemLanguage != null) {
            itemExitAccount.setTitle(getResStringLanguage(R.string.item_exit_account, getLanguageAbbreviation(language)));
            itemStatistics.setTitle(getResStringLanguage(R.string.item_statistics, getLanguageAbbreviation(language)));
            itemLanguage.setTitle(getResStringLanguage(R.string.item_language, getLanguageAbbreviation(language)));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_topic);
        progressBarTopicActivity = findViewById(R.id.progressBarTopicActivity);
        progressBarTopicActivity.setVisibility(View.VISIBLE);
        recyclerViewTopic = findViewById(R.id.recyclerViewTopic);
        recyclerViewTopic.setVisibility(View.GONE);

        getLanguagePreferences();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            //отримую з БД Cloud Firestore інформацію про назви тем і кількість завдань в кожній темі
            //і завантажую в RecyclerView
            mainViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(MainViewModel.class);
            getDataFromCloudDB(nameCollection);
        }
    }

    private void loadRecyclerView() {
        RecyclerView recyclerViewTopic = findViewById(R.id.recyclerViewTopic);
        topicAdapter = new TopicAdapter(language);
        recyclerViewTopic.setLayoutManager(new LinearLayoutManager(this));
        if (topicList.size() > 1) {
            Collections.sort(topicList, (o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
        }
        topicAdapter.setTopicList(topicList);
        topicAdapter.setStatisticList(statisticList);
        recyclerViewTopic.setAdapter(topicAdapter);
        topicAdapter.setOnTopicClickListener(position -> {
            Intent intent = new Intent(ListTopicActivity.this, TaskActivity.class);
            Topic topic = topicAdapter.getTopicList().get(position);
            intent.putExtra("nameTopic", topic.getNameTopic().get(language));
            intent.putExtra("idTopic", topic.getId());
            intent.putExtra("quantityTasksInTopic", topic.getQuantityTasksInTopic());
            startActivity(intent);
        });
    }

    //створюю нову або дістаю з БД SQLite дані статистики, оновлюю їх
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
        } else {
            //у випадку оновлення БД, синхронізую загальну кількість завдань в Statistic з topicList
            for (Statistic statistic : statisticList) {
                for (Topic topic : topicList) {
                    if (topic.getNameTopic().equals(statistic.getNameTopic())) {
                        statistic.setQuantityTasksInTopic(topic.getQuantityTasksInTopic());
                        mainViewModel.insertStatistic(statistic);
                    }
                }
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
                    taskList = value.toObjects(Task.class);
                }
                topicList = new ArrayList<>(); //необхідний для правильного онлайн оновлення БД
                for (Task task : taskList) {
                    //для кожного завдання перевіряю чи містить таку назву теми
                    Topic newTopic = new Topic(task.getIdTopic(), task.getTopic(), 0);
                    if (!topicList.contains(newTopic)) {
                        //якщо ні - добавляю нову тему
                        newTopic.setQuantityTasksInTopic(newTopic.getQuantityTasksInTopic() + 1);
                        topicList.add(newTopic);
                    } else {
                        int i = topicList.indexOf(newTopic);
                        Topic topic = topicList.get(i);
                        topic.setQuantityTasksInTopic(topic.getQuantityTasksInTopic() + 1);
                    }
                }
                getLanguagePreferences();
                setStatisticsList(); //створюю нову або дістаю з БД SQLite дані статистики
                loadRecyclerView(); //встановлюю елементи інтерфейсу
                progressBarTopicActivity.setVisibility(View.GONE);
                recyclerViewTopic.setVisibility(View.VISIBLE);
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