package ua.tarastom.learnjava;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ua.tarastom.learnjava.data.MainViewModel;
import ua.tarastom.learnjava.data.Statistic;
import ua.tarastom.learnjava.data.StatisticAdapter;

public class StatisticsActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        if (mainViewModel == null) {
            mainViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(MainViewModel.class);
        }
        RecyclerView recyclerViewStat = findViewById(R.id.recyclerViewStat);
        StatisticAdapter statisticAdapter = new StatisticAdapter(mainViewModel);
        recyclerViewStat.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewStat.setAdapter(statisticAdapter);
        List<Statistic> allStatistics = mainViewModel.getAllStatistics();
        statisticAdapter.setStatisticResult(allStatistics);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ListTopicActivity.class);
        startActivity(intent);
        finish();
    }
}