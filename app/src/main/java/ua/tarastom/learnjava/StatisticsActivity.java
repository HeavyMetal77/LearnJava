package ua.tarastom.learnjava;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ua.tarastom.learnjava.data.MainViewModel;
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
        TextView textViewLabelStat = findViewById(R.id.textViewLabelStat);

        StatisticAdapter statisticAdapter = new StatisticAdapter(mainViewModel);
        recyclerViewStat.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewStat.setAdapter(statisticAdapter);
        statisticAdapter.setStatisticResult(mainViewModel.getAllStatistics());

        textViewLabelStat.setText("Статистика по темам: \n");
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ListTopicActivity.class);
        startActivity(intent);
        finish();
    }
}