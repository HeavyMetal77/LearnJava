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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import ua.tarastom.learnjava.data.MainViewModel;
import ua.tarastom.learnjava.data.Statistic;
import ua.tarastom.learnjava.data.Task;

public class TaskActivity extends AppCompatActivity {
    private List<Task> taskList = new ArrayList<>();
    private TextView textViewTopic;
    private TextView textViewLabelTask;
    private TextView textLabelResultTask;
    private TextView textViewQuestion;
    private TextView textViewTextMultiLine;
    private List<CheckBox> checkBoxList;
    private ImageView imageViewArrowBack;
    private ImageView imageViewArrowForward;
    private int taskId = 0; //номер завдання
    private Button buttonShowRightAnswer;
    private Button buttonCheckAnswer;
    private MainViewModel mainViewModel;
    private Statistic currentStatisticTask;
    private ScrollView scrollViewTaskActivity;
    private int language;
    private String nameCollection = "taskList";
    private ConstraintLayout constraintLayout;
    private ProgressBar progressBarTaskActivity;
    public static final String APP_PREFERENCES = "LearnJavaSettings";
    public static final String APP_PREFERENCES_LANGUAGE = "language";
    private MenuItem itemExitAccount;
    private MenuItem itemStatistics;
    private MenuItem itemLanguage;
    private Task task;
    private TextView textViewHintAnswer;
    private Button buttonShowHintAnswer;

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

    private void getLanguagePreferences() {
        SharedPreferences mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (mSettings.contains(APP_PREFERENCES_LANGUAGE)) {
            language = mSettings.getInt(APP_PREFERENCES_LANGUAGE, language);
        } else {
            setLanguage();
        }
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

    private void initView() {
        if (mainViewModel == null) {
            mainViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(MainViewModel.class);
        }
        imageViewArrowBack = findViewById(R.id.imageViewArrowBack);
        imageViewArrowForward = findViewById(R.id.imageViewArrowForward);
        imageViewArrowBack.setVisibility(View.INVISIBLE);
        textViewTopic = findViewById(R.id.textViewLabelTopic);
        textViewLabelTask = findViewById(R.id.textViewLabelTask);
        textLabelResultTask = findViewById(R.id.textLabelResultTask);
        textViewQuestion = findViewById(R.id.textViewLabelQuestion);
        textViewTextMultiLine = findViewById(R.id.textViewTextMultiLine);
        CheckBox checkBox1 = findViewById(R.id.checkBox1);
        CheckBox checkBox2 = findViewById(R.id.checkBox2);
        CheckBox checkBox3 = findViewById(R.id.checkBox3);
        CheckBox checkBox4 = findViewById(R.id.checkBox4);
        CheckBox checkBox5 = findViewById(R.id.checkBox5);
        CheckBox checkBox6 = findViewById(R.id.checkBox6);
        CheckBox checkBox7 = findViewById(R.id.checkBox7);
        checkBoxList = new ArrayList<>();
        checkBoxList.add(checkBox1);
        checkBoxList.add(checkBox2);
        checkBoxList.add(checkBox3);
        checkBoxList.add(checkBox4);
        checkBoxList.add(checkBox5);
        checkBoxList.add(checkBox6);
        checkBoxList.add(checkBox7);
        buttonShowRightAnswer = findViewById(R.id.buttonShowRightAnswer);
        buttonCheckAnswer = findViewById(R.id.button_check_answer);
        scrollViewTaskActivity = findViewById(R.id.scrollViewTaskActivity);
        constraintLayout = findViewById(R.id.layoutActivityTask);
        progressBarTaskActivity = findViewById(R.id.progressBarTaskActivity);
        textViewHintAnswer = findViewById(R.id.textViewHintAnswer);
        buttonShowHintAnswer = findViewById(R.id.buttonShowHintAnswer);
    }

    @Override
    protected void onResume() {
        super.onResume();
        buttonCheckAnswer.setText(getResStringLanguage(R.string.submit, getLanguageAbbreviation(language)));
        buttonShowRightAnswer.setText(getResStringLanguage(R.string.right_answer, getLanguageAbbreviation(language)));
        if (itemExitAccount != null && itemStatistics != null && itemLanguage != null) {
            itemExitAccount.setTitle(getResStringLanguage(R.string.item_exit_account, getLanguageAbbreviation(language)));
            itemStatistics.setTitle(getResStringLanguage(R.string.item_statistics, getLanguageAbbreviation(language)));
            itemLanguage.setTitle(getResStringLanguage(R.string.item_language, getLanguageAbbreviation(language)));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        getLanguagePreferences();


        initView();
        buttonCheckAnswer.setText(getResStringLanguage(R.string.submit, getLanguageAbbreviation(language)));
        buttonShowRightAnswer.setText(getResStringLanguage(R.string.right_answer, getLanguageAbbreviation(language)));
        constraintLayout.setVisibility(View.GONE);
        progressBarTaskActivity.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        int idTopic = intent.getIntExtra("idTopic", -1);
        String nameTopic = intent.getStringExtra("nameTopic");
        int quantityTasksInTopic = intent.getIntExtra("quantityTasksInTopic", 0);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(nameCollection)
                .whereEqualTo("idTopic", idTopic)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    taskList = queryDocumentSnapshots.toObjects(Task.class);
                    //отримуємо номер останнього вирішеного завдання з SQLite Statistic
                    List<Statistic> allStatistics = mainViewModel.getAllStatistics();
                    for (Statistic statistic : allStatistics) {
                        if (statistic.getNameTopic().get(language).equals(nameTopic)) {
                            currentStatisticTask = statistic;
                        }
                    }

                    if (currentStatisticTask != null) {
                        int quantitySolvedTasks = currentStatisticTask.getQuantitySolvedTasks();
                        if (quantitySolvedTasks > taskId) {
                            taskId = quantitySolvedTasks;
                        }
                    } else {
                        Task task = taskList.get(taskId);
                        currentStatisticTask = new Statistic(task.getIdTopic(), task.getTopic(),
                                quantityTasksInTopic, 0, 0);
                        mainViewModel.insertStatistic(currentStatisticTask);
                    }
                    generateNextTask(taskId);
                    constraintLayout.setVisibility(View.VISIBLE);
                    progressBarTaskActivity.setVisibility(View.GONE);
                }).addOnFailureListener(e -> startActivity(new Intent(getApplicationContext(), ListTopicActivity.class)));
    }
    //генерація наступного завдання

    private void generateNextTask(int idTask) {
        buttonShowHintAnswer.setVisibility(View.GONE);
        textViewHintAnswer.setVisibility(View.GONE);

        //загальний фон встановлюю на білий
        scrollViewTaskActivity.setBackground(ContextCompat.getDrawable(this, R.color.colorWhite));
        //якщо завдань в темі більше не має встановлюю idTask на останнє
        if (idTask >= taskList.size()) {
            idTask = taskList.size() - 1;
        }

        //стрілка назад невидима, якщо перше завдання
        if (idTask < 1) {
            imageViewArrowBack.setVisibility(View.INVISIBLE);
        } else {
            imageViewArrowBack.setVisibility(View.VISIBLE);
        }

        //стрілка вперед невидима, якщо останнє завдання
        if (idTask == taskList.size() - 1) {
            imageViewArrowForward.setVisibility(View.INVISIBLE);
        } else {
            imageViewArrowForward.setVisibility(View.VISIBLE);
        }

        //якщо кількість вирішених задач більше ніж номер поточної -
        // кнопку перевірки відповіді та чекбокси роблю не активними
        if (currentStatisticTask.getQuantitySolvedTasks() > idTask) {
            buttonCheckAnswer.setVisibility(View.INVISIBLE);
            for (CheckBox checkBox : checkBoxList) {
                checkBox.setClickable(false);
            }
        } else {
            buttonCheckAnswer.setClickable(true);
            for (CheckBox checkBox : checkBoxList) {
                checkBox.setClickable(true);
            }
            buttonCheckAnswer.setVisibility(View.VISIBLE);
        }

        for (CheckBox checkBox : checkBoxList) {
            checkBox.setVisibility(View.VISIBLE); //всі чекбокси роблю видимими
        }
        task = taskList.get(idTask);

        List<Integer> listOfIncorrectlySolvedProblems = currentStatisticTask.getListOfIncorrectlySolvedProblems();
        if (listOfIncorrectlySolvedProblems != null
                && listOfIncorrectlySolvedProblems.size() != 0
                && listOfIncorrectlySolvedProblems.size() > idTask
                && listOfIncorrectlySolvedProblems.get(idTask) == 0) {
            //загальний фон змінюється на світло-червоний
            scrollViewTaskActivity.setBackground(ContextCompat.getDrawable(this, R.color.colorBackgroundIncorrectly));
        }
        if (listOfIncorrectlySolvedProblems != null
                && listOfIncorrectlySolvedProblems.size() != 0
                && listOfIncorrectlySolvedProblems.size() > idTask
                && listOfIncorrectlySolvedProblems.get(idTask) == 1) {
            //загальний фон змінюється на світло-зелений
            scrollViewTaskActivity.setBackground(ContextCompat.getDrawable(this, R.color.colorBackgroundCorrectly));
        }

        //встановлюю написи
        String topic = getResStringLanguage(R.string.topic_label, getLanguageAbbreviation(language)) + " " + task.getTopic().get(language);
        textViewTopic.setText(topic);
        String labelTask = getResStringLanguage(R.string.task_label, getLanguageAbbreviation(language)) + " " + (idTask + 1);
        textViewLabelTask.setText(labelTask);
        String labelLabelResultTask = getResStringLanguage(R.string.result, getLanguageAbbreviation(language)) + " "
                + currentStatisticTask.getNumberOfCorrectlySolvedTasks() + " / "
                + currentStatisticTask.getQuantityTasksInTopic();
        textLabelResultTask.setText(labelLabelResultTask);

        if (taskList != null && taskList.size() > 0) {
            textViewQuestion.setText(task.getQuestion().get(language));
            textViewTextMultiLine.setText(task.getTaskStr());
            Map<String, Boolean> map = task.getAnswermap().get(language);
            int answerListSize = map.size();
            int countCheckbox = 0;
            for (String key : map.keySet()) {
                CheckBox checkBox = checkBoxList.get(countCheckbox++);
                checkBox.setText(key);
                checkBox.setChecked(false);
                checkBox.setBackground(ContextCompat.getDrawable(this, R.drawable.style_btn_blue));
            }

            //встановлюю кількість видимих чекбоксів, в залежності від кількості відповідей
            int unusedCheckBoxes = checkBoxList.size() - answerListSize;
            for (int i = checkBoxList.size() - 1; i >= checkBoxList.size() - unusedCheckBoxes; i--) {
                checkBoxList.get(i).setVisibility(View.INVISIBLE); //лишні чекбокси роблю невидимими
            }
            //для activity_task.xml встановлюю програмно значення buttonCheckAnswer та buttonShowHintAnswer
            // -  app:layout_constraintTop_toBottomOf="@id/checkBoxXXXX"
            //під останнім checkBox
            CheckBox lastCheckBox = checkBoxList.get(answerListSize - 1);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) buttonCheckAnswer.getLayoutParams();
            layoutParams.topToBottom = lastCheckBox.getId();
            ConstraintLayout.LayoutParams layoutParamsHint = (ConstraintLayout.LayoutParams) buttonShowHintAnswer.getLayoutParams();
            layoutParamsHint.topToBottom = lastCheckBox.getId();

            //якщо завдання вже вирішувалось - показую правильні відповіді
            if (task.isResolved() || currentStatisticTask.getQuantitySolvedTasks() > idTask) {
                showRightAnswer();
            }

            //якщо номер поточного завдання більший аніж у SQLite Statistic інкременую їх
            if (currentStatisticTask.getQuantitySolvedTasks() < idTask) {
                currentStatisticTask.setQuantitySolvedTasks(idTask);
                mainViewModel.insertStatistic(currentStatisticTask);
            }
        }
    }

    //метод онклік для наступного завдання
    public void goNextTask(View view) {
        //заборонено перехід до наступного завдання поки не виконано поточне
        boolean resolved = taskList.get(taskId).isResolved();
        if (!resolved) {
            Toast.makeText(this, R.string.toast_solve_this_problem_first, Toast.LENGTH_SHORT).show();
            return;
        }
        //переходимо до наступного завдання
        if (taskList != null && taskId < taskList.size() - 1) {
            taskId += 1;
            generateNextTask(taskId);
            buttonShowRightAnswer.setVisibility(View.INVISIBLE);
        }
    }

    //метод онклік для попереднього завдання
    public void goPreviousTask(View view) {
        if (taskId > 0) {
            taskId -= 1;
            generateNextTask(taskId);
            buttonShowRightAnswer.setVisibility(View.INVISIBLE);
        }
    }

    //метод онклік для перевірки правильності відповіді
    public void checkRightAnswer(View view) {
        isRightAnswers(taskId);
    }

    //перевірка правильності відповіді
    private void isRightAnswers(int idTask) {
        if (taskList != null && taskList.size() > 0) {
            Task task = taskList.get(idTask);

            //звіряю кожне значення чекбокса з значенням мапи відповідей завдання
            Map<String, Boolean> map = task.getAnswermap().get(language);
            Set<Map.Entry<String, Boolean>> entries = map.entrySet();
            boolean choiceRightAnswer = true; // флаг правильної відповіді
            for (int j = 0; j < entries.size(); j++) {
                String textCheckBox = checkBoxList.get(j).getText().toString().trim();
                for (Map.Entry<String, Boolean> entry : entries) {
                    if (entry.getKey().equals(textCheckBox)) {
                        choiceRightAnswer = entry.getValue().equals(checkBoxList.get(j).isChecked());
                        break;
                    }
                }
                if (!choiceRightAnswer) {
                    break;
                }
            }

            if (choiceRightAnswer) {
                showRightAnswer();
                //якщо задача ще не вирішувалась - збільшую кількість вирішених задач
                if (!task.isResolved() || currentStatisticTask.getListOfIncorrectlySolvedProblems().size() - 1 < idTask) {
                    task.setResolved(true); //встановити флаг чи вирішена задача
                    currentStatisticTask.getListOfIncorrectlySolvedProblems().add(1);//встановити флаг - вирішена задача правильно
                    int numberOfCorrectlySolvedTasks = currentStatisticTask.getNumberOfCorrectlySolvedTasks(); //кількість правильно вирішених завдань інкременую
                    currentStatisticTask.setNumberOfCorrectlySolvedTasks(numberOfCorrectlySolvedTasks + 1);
                    currentStatisticTask.setQuantitySolvedTasks(currentStatisticTask.getQuantitySolvedTasks() + 1);  //збільшую кількість вирішених задач (правильних+неправильних)
                }

                // кнопку перевірки відповіді, показу правильної відповіді та чекбокси роблю не активними
                buttonCheckAnswer.setVisibility(View.INVISIBLE);
                buttonShowRightAnswer.setVisibility(View.INVISIBLE);
                for (CheckBox checkBox : checkBoxList) {
                    checkBox.setClickable(false);
                }

                //загальний фон змінюється на світло-зелений
                scrollViewTaskActivity.setBackground(ContextCompat.getDrawable(this, R.color.colorBackgroundCorrectly));
                String labelLabelResultTask = getResStringLanguage(R.string.result, getLanguageAbbreviation(language)) + " "
                        + currentStatisticTask.getNumberOfCorrectlySolvedTasks() + " / " + currentStatisticTask.getQuantityTasksInTopic();
                textLabelResultTask.setText(labelLabelResultTask);
                mainViewModel.insertStatistic(currentStatisticTask);
            } else {
                //якщо задача ще не вирішувалась - збільшую кількість вирішених задач
                if (!task.isResolved() || currentStatisticTask.getListOfIncorrectlySolvedProblems().size() - 1 < idTask) {
                    currentStatisticTask.getListOfIncorrectlySolvedProblems().add(0);//встановити флаг - вирішена задача неправильно
                    currentStatisticTask.setQuantitySolvedTasks(currentStatisticTask.getQuantitySolvedTasks() + 1);
                }
//                Toast.makeText(this, "Не правильно!", Toast.LENGTH_SHORT).show();
                buttonShowRightAnswer.setVisibility(View.VISIBLE); //показати кнопку правильної відповіді
                //загальний фон змінюється на світло-червоний
                scrollViewTaskActivity.setBackground(ContextCompat.getDrawable(this, R.color.colorBackgroundIncorrectly));
                mainViewModel.insertStatistic(currentStatisticTask);
            }
        }
    }

    //метод для показу правильності відповіді
    private void showRightAnswer() {
        //якщо завдань в темі більше не має встановлюю idTask на останнє
        if (taskId >= taskList.size()) {
            taskId = taskList.size() - 1;
        }
        if (taskList.size() > 0) {
            Task task = taskList.get(taskId);
            //звіряю кожне значення чекбокса з значенням мапи відповідей завдання
            Set<Map.Entry<String, Boolean>> entries = task.getAnswermap().get(language).entrySet();
            for (int j = 0; j < entries.size(); j++) {
                String textCheckBox = checkBoxList.get(j).getText().toString();
                for (Map.Entry<String, Boolean> entry : entries) {
                    if (entry.getKey().equals(textCheckBox)) {
                        CheckBox checkBox = checkBoxList.get(j);
                        if (entry.getValue()) {
                            checkBox.setBackground(ContextCompat.getDrawable(this, R.drawable.style_checkbox_green));
                        } else {
                            if (checkBox.isChecked()) {
                                checkBox.setBackground(ContextCompat.getDrawable(this, R.drawable.style_checkbox_red));
                            } else {
                                checkBox.setBackground(ContextCompat.getDrawable(this, R.drawable.style_btn_blue));
                            }
                        }
                        break;
                    }
                }
            }

            task.setResolved(true); //встановити флаг чи вирішена задача
            //якщо номер поточного завдання більший аніж у SQLite Statistic інкременую їх
            if (currentStatisticTask.getQuantitySolvedTasks() < taskId) {
                currentStatisticTask.setQuantitySolvedTasks(taskId);
                mainViewModel.insertStatistic(currentStatisticTask);
            }
            // кнопку перевірки відповіді, показу правильної відповіді та чекбокси роблю не активними
            buttonCheckAnswer.setVisibility(View.INVISIBLE);
            buttonShowRightAnswer.setVisibility(View.INVISIBLE);
            for (CheckBox checkBox : checkBoxList) {
                checkBox.setClickable(false);
            }
        }
    }

    //метод онклік для перевірки правильності відповіді
    public void showRightAnswer(View view) {
        showRightAnswer();
        buttonShowHintAnswer.setVisibility(View.VISIBLE);
    }

    //метод онклік для зміни фону вибраного чекбокса
    public void onClickCheckBox(View view) {
        CheckBox checkBox = (CheckBox) view;
        if (checkBox.isChecked()) {
            checkBox.setBackground(ContextCompat.getDrawable(this, R.drawable.style_btn_white));
        } else {
            checkBox.setBackground(ContextCompat.getDrawable(this, R.drawable.style_btn_blue));
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ListTopicActivity.class);
        startActivity(intent);
        finish();
    }

    public void showHintAnswer(View view) {
        textViewHintAnswer.setText(task.getHint().get(language)); //встановлюю текст підказки
        textViewHintAnswer.setVisibility(View.VISIBLE);
        buttonShowHintAnswer.setVisibility(View.GONE); //після нажаття кнопки - роблю її невидимою
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        taskList = null;
//    }
}