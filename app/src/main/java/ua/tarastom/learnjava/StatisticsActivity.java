package ua.tarastom.learnjava;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

import ua.tarastom.learnjava.data.MainViewModel;
import ua.tarastom.learnjava.data.Statistic;
import ua.tarastom.learnjava.data.StatisticAdapter;

public class StatisticsActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    public static final String APP_PREFERENCES = "LearnJavaSettings";
    public static final String APP_PREFERENCES_LANGUAGE = "language";
    private int language;

    private void getLanguagePreferences() {
        SharedPreferences mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (mSettings.contains(APP_PREFERENCES_LANGUAGE)) {
            language = mSettings.getInt(APP_PREFERENCES_LANGUAGE, language);
        } else {
            setLanguage();
        }
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

    public String getResStringLanguage(int id, String lang){
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        getLanguagePreferences();
        if (mainViewModel == null) {
            mainViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(MainViewModel.class);
        }
        RecyclerView recyclerViewStat = findViewById(R.id.recyclerViewStat);
        StatisticAdapter statisticAdapter = new StatisticAdapter(mainViewModel, language);
        recyclerViewStat.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewStat.setAdapter(statisticAdapter);
        List<Statistic> allStatistics = mainViewModel.getAllStatistics();
        statisticAdapter.setStatisticResult(allStatistics);
        TextView textViewLabelStat = findViewById(R.id.textViewLabelStat);
        textViewLabelStat.setText(getResStringLanguage(R.string.label_stat, getLanguageAbbreviation(language)));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ListTopicActivity.class);
        startActivity(intent);
        finish();
    }
}