package ua.tarastom.learnjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
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
    private TextView editTextTextMultiLine;
    private List<CheckBox> checkBoxList;
    private ImageView imageViewArrowBack;
    private ImageView imageViewArrowForward;
    private int idTask = 0; //номер завдання
    private Button buttonShowRightAnswer;
    private Button buttonCheckAnswer;
    private MainViewModel mainViewModel;
    private Statistic currentStatisticTask;
    private ScrollView scrollViewTaskActivity;
    private int language = 0;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemMain) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.itemTopics) {
            Intent intent = new Intent(this, ListTopicActivity.class);
            startActivity(intent);
        }
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        if (mainViewModel == null) {
            mainViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(MainViewModel.class);
        }

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);
        String nameTopic = intent.getStringExtra("nameTopic");

        //отримуємо номер останнього вирішеного завдання з SQLite Statistic
        List<Statistic> allStatistics = mainViewModel.getAllStatistics();
        currentStatisticTask = allStatistics.get(position - 1);
        int quantitySolvedTasks = currentStatisticTask.getQuantitySolvedTasks();
        if (quantitySolvedTasks > idTask) {
            idTask = quantitySolvedTasks;
        }

        imageViewArrowBack = findViewById(R.id.imageViewArrowBack);
        imageViewArrowForward = findViewById(R.id.imageViewArrowForward);
        imageViewArrowBack.setVisibility(View.INVISIBLE);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("taskList")
                .whereEqualTo("idTopic", position)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    taskList = queryDocumentSnapshots.toObjects(Task.class);
                    generateNextTask(idTask);
                }).addOnFailureListener(e -> {
            startActivity(new Intent(getApplicationContext(), ListTopicActivity.class));
        });

        textViewTopic = findViewById(R.id.textViewLabelTopic);
        textViewLabelTask = findViewById(R.id.textViewLabelTask);
        textLabelResultTask = findViewById(R.id.textLabelResultTask);
        textViewQuestion = findViewById(R.id.textViewLabelQuestion);
        editTextTextMultiLine = findViewById(R.id.editTextTextMultiLine);
        CheckBox checkBox1 = findViewById(R.id.checkBox1);
        CheckBox checkBox2 = findViewById(R.id.checkBox2);
        CheckBox checkBox3 = findViewById(R.id.checkBox3);
        CheckBox checkBox4 = findViewById(R.id.checkBox4);
        CheckBox checkBox5 = findViewById(R.id.checkBox5);
        checkBoxList = new ArrayList<>();
        checkBoxList.add(checkBox1);
        checkBoxList.add(checkBox2);
        checkBoxList.add(checkBox3);
        checkBoxList.add(checkBox4);
        checkBoxList.add(checkBox5);
        buttonShowRightAnswer = findViewById(R.id.buttonShowRightAnswer);
        buttonCheckAnswer = findViewById(R.id.button_check_answer);
        scrollViewTaskActivity = findViewById(R.id.scrollViewTaskActivity);
    }

    //генерація наступного завдання
    public void generateNextTask(int idTask) {
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
        Task task = taskList.get(idTask);


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
        String topic = getResources().getString(R.string.topic_label) + " " + task.getTopic().get(language);
        textViewTopic.setText(topic);
        String labelTask = getResources().getString(R.string.task_label) + " " + (idTask + 1);
        textViewLabelTask.setText(labelTask);
        String labelLabelResultTask = getResources().getText(R.string.result).toString() + " "
                + currentStatisticTask.getNumberOfCorrectlySolvedTasks() + " / "
                + currentStatisticTask.getQuantityTasksInTopic();
        textLabelResultTask.setText(labelLabelResultTask);

        if (taskList != null && taskList.size() > 0) {
            textViewQuestion.setText(task.getQuestion().get(language));
            editTextTextMultiLine.setText(task.getTaskStr());
            int answerListSize = task.getAnswermap().size();
            int countCheckbox = 0;
            for (String key : task.getAnswermap().keySet()) {
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
        boolean resolved = taskList.get(idTask).isResolved();
        if (!resolved) {
            Toast.makeText(this, "Спочатку вирішіть це завдання!", Toast.LENGTH_SHORT).show();
            return;
        }
        //переходимо до наступного завдання
        if (taskList != null && idTask < taskList.size() - 1) {
            idTask += 1;
            generateNextTask(idTask);
            buttonShowRightAnswer.setVisibility(View.INVISIBLE);
        }
    }

    //метод онклік для попереднього завдання
    public void goPreviousTask(View view) {
        if (idTask > 0) {
            idTask -= 1;
            generateNextTask(idTask);
            buttonShowRightAnswer.setVisibility(View.INVISIBLE);
        }
    }

    //метод онклік для перевірки правильності відповіді
    public void checkRightAnswer(View view) {
        isRightAnswers(idTask);
    }

    //перевірка правильності відповіді
    private void isRightAnswers(int idTask) {
        if (taskList != null && taskList.size() > 0) {
            Task task = taskList.get(idTask);

            //звіряю кожне значення чекбокса з значенням мапи відповідей завдання
            Set<Map.Entry<String, Boolean>> entries = task.getAnswermap().entrySet();
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
                Toast.makeText(this, "Правильно!", Toast.LENGTH_SHORT).show();
                //якщо задача ще не вирішувалась - збільшую кількість вирішених задач
                if (!task.isResolved() || currentStatisticTask.getListOfIncorrectlySolvedProblems().size() - 1 < idTask) {
                    task.setResolved(true); //встановити флаг чи вирішена задача
                    currentStatisticTask.getListOfIncorrectlySolvedProblems().add(1);//встановити флаг - вирішена задача
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
                String labelLabelResultTask = getResources().getText(R.string.result).toString() + " "
                        + currentStatisticTask.getNumberOfCorrectlySolvedTasks() + " / " + currentStatisticTask.getQuantityTasksInTopic();
                textLabelResultTask.setText(labelLabelResultTask);
                mainViewModel.insertStatistic(currentStatisticTask);
            } else {
                Toast.makeText(this, "Не правильно!", Toast.LENGTH_SHORT).show();
                buttonShowRightAnswer.setVisibility(View.VISIBLE); //показати кнопку правильної відповіді
                //загальний фон змінюється на світло-червоний
                scrollViewTaskActivity.setBackground(ContextCompat.getDrawable(this, R.color.colorBackgroundIncorrectly));
                mainViewModel.insertStatistic(currentStatisticTask);
            }
        }
    }

    //метод для показу правильності відповіді
    public void showRightAnswer() {
        //якщо завдань в темі більше не має встановлюю idTask на останнє
        if (idTask >= taskList.size()) {
            idTask = taskList.size() - 1;
        }
        if (taskList.size() > 0) {
            Task task = taskList.get(idTask);
            int size = task.getAnswermap().size();
            for (int i = 0; i < size; i++) {
                //звіряю кожне значення чекбокса з значенням мапи відповідей завдання
                Set<Map.Entry<String, Boolean>> entries = task.getAnswermap().entrySet();
                for (int j = 0; j < entries.size(); j++) {
                    String textCheckBox = checkBoxList.get(j).getText().toString();
                    for (Map.Entry<String, Boolean> entry : entries) {
                        if (entry.getKey().equals(textCheckBox)) {
                            CheckBox checkBox = checkBoxList.get(j);
                            if (entry.getValue()) {
                                checkBox.setBackground(ContextCompat.getDrawable(this, R.drawable.style_checkbox_green));
                                break;
                            } else {
                                if (checkBox.isChecked()) {
                                    checkBox.setBackground(ContextCompat.getDrawable(this, R.drawable.style_checkbox_red));
                                    break;
                                } else {
                                    checkBox.setBackground(ContextCompat.getDrawable(this, R.drawable.style_btn_blue));
                                    break;
                                }
                            }
                        }
                    }
                }
                break;
            }
            task.setResolved(true); //встановити флаг чи вирішена задача
            //якщо номер поточного завдання більший аніж у SQLite Statistic інкременую їх
            if (currentStatisticTask.getQuantitySolvedTasks() < idTask) {
                currentStatisticTask.setQuantitySolvedTasks(idTask);
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
}