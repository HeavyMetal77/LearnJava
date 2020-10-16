package ua.tarastom.learnjava;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {
    private int language;
    public static final String APP_PREFERENCES = "LearnJavaSettings";
    public static final String APP_PREFERENCES_LANGUAGE = "language";
    private SharedPreferences mSettings;
    private TextView textViewLanguageLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        RadioGroup radioGroupLanguage = findViewById(R.id.radioGroupLanguage);
        textViewLanguageLabel = findViewById(R.id.textViewLanguageLabel);

        getLanguagePreferences();
        setLanguageRadioButton(language);

        radioGroupLanguage.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i) {
                case R.id.radioButtonRussian:
                    language = 0;
                    break;
                case R.id.radioButtonEnglish:
                    language = 1;
                    break;
                case R.id.radioButtonUkraine:
                    language = 2;
                    break;
            }
            textViewLanguageLabel.setText(getResStringLanguage(R.string.select_application_language, getLanguageAbbreviation(language)));
            saveLanguageToPreferences(language);
        });
    }

    private void getLanguagePreferences() {
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (mSettings.contains(APP_PREFERENCES_LANGUAGE)) {
            language = mSettings.getInt(APP_PREFERENCES_LANGUAGE, language);
        } else {
            setLanguage();
        }
    }

    private void setLanguageRadioButton(int language) {
        RadioButton radioButtonEnglish = findViewById(R.id.radioButtonEnglish);
        RadioButton radioButtonRussian = findViewById(R.id.radioButtonRussian);
        RadioButton radioButtonUkraine = findViewById(R.id.radioButtonUkraine);
        switch (language) {
            case 0:
                radioButtonRussian.setChecked(true);
                break;
            case 1:
                radioButtonEnglish.setChecked(true);
                break;
            case 2:
                radioButtonUkraine.setChecked(true);
                break;
            default:
                radioButtonEnglish.setChecked(true);
                break;
        }
        textViewLanguageLabel.setText(getResStringLanguage(R.string.select_application_language, getLanguageAbbreviation(language)));
    }

    private void saveLanguageToPreferences(int language) {
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(APP_PREFERENCES_LANGUAGE, language);
        editor.apply();
    }

    private void setLanguage() {
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
                language = 0;
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
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ListTopicActivity.class);
        startActivity(intent);
        finish();
    }
}


