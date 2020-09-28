package ua.tarastom.learnjava;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import ua.tarastom.learnjava.data.Task;
import ua.tarastom.learnjava.data.Topic;

public class TaskActivity extends AppCompatActivity {
    private List<Task> taskList;
    private Topic topic;
    private TextView textViewTopic;
    private TextView textViewLabelTask;
    private TextView textLabelResultTask;
    private TextView textViewQuestion;
    private TextView editTextTextMultiLine;
    private List<CheckBox> checkBoxList;
    private int idTask = 0; //номер завдання
    private Button buttonShowRightAnswer;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

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

        createTempData();
        generateNextTask(idTask);
    }

    private void createTempData() {
        topic = new Topic("Основы", "Переменные");

        taskList = new ArrayList<>();
        ArrayList<String> listAllAnswer = new ArrayList<>();
        listAllAnswer.add("1");
        listAllAnswer.add("2");
        listAllAnswer.add("3");
        listAllAnswer.add("4");
        listAllAnswer.add("5");

        ArrayList<Boolean> listRightAnswer = new ArrayList<>();
        listRightAnswer.add(false);
        listRightAnswer.add(true);
        listRightAnswer.add(false);
        listRightAnswer.add(false);
        listRightAnswer.add(true);

        Task task1 = new Task(1, "Какая строка не скомпилируется?",
                "       1. byte b1 = 125;\n" +
                        "       2. byte b2 = -228;\n" +
                        "       3. byte b3 = 0b0101;\n" +
                        "       4. byte b4 = 0b101;\n" +
                        "       5. byte b5 = 225",
                listAllAnswer,
                listRightAnswer, false);
        taskList.add(task1);
        ArrayList<String> listAllAnswer2 = new ArrayList<>();
        listAllAnswer2.add("5");
        listAllAnswer2.add("4");
        listAllAnswer2.add("3");

        ArrayList<Boolean> listRightAnswer2 = new ArrayList<>();
        listRightAnswer2.add(false);
        listRightAnswer2.add(false);
        listRightAnswer2.add(true);

        Task task2 = new Task(2, "Какая строка не скомпилируется?",
                "       1. int i1 = 10;\n" +
                        "       2. int i2 = 1_000_000_000;\n" +
                        "       3. int i3 = _1000_000_000;\n" +
                        "       4. int i4 = 1_0_00_00_0_000;\n" +
                        "       5. int i5 = 10_000_000_000;",
                listAllAnswer2,
                listRightAnswer2, false);
        taskList.add(task2);
    }

    //генерація наступного завдання
    public void generateNextTask(int idTask) {
        for (CheckBox checkBox : checkBoxList) {
            checkBox.setVisibility(View.VISIBLE); //всі чекбокси роблю видимими
        }
        textViewTopic.setText(getResources().getString(R.string.topic_label) + topic.getNameTopic() + ". " + topic.getNameSubTopic());
        textViewLabelTask.setText(getResources().getString(R.string.task_label) + " " + taskList.get(idTask).getIdTask());
        textLabelResultTask.setText(getResources().getText(R.string.result) + " 2/5");
        textViewQuestion.setText(taskList.get(idTask).getQuestion());
        editTextTextMultiLine.setText(taskList.get(idTask).getTaskStr());
        int answerListSize = taskList.get(idTask).getAllAnswersList().size();
        for (int i = 0; i < answerListSize; i++) {
            checkBoxList.get(i).setText(taskList.get(idTask).getAllAnswersList().get(i));
            checkBoxList.get(i).setChecked(false);
            checkBoxList.get(i).setBackground(getResources().getDrawable(R.drawable.style_btn_blue, getTheme()));
        }
        //встановлюю кількість видимих чекбоксів, в залежності від кількості відповідей
        int unusedCheckBoxes = checkBoxList.size() - answerListSize;
        for (int i = checkBoxList.size() - 1; i >= checkBoxList.size() - unusedCheckBoxes; i--) {
            checkBoxList.get(i).setVisibility(View.INVISIBLE); //лишні чекбокси роблю невидимими
        }
        //якщо завдання вже вирішувалось - показую правильні відповіді
        if (taskList.get(idTask).isResolved()) {
            showRightAnswer();
        }
    }

    //метод онклік для наступного завдання
    public void goNextTask(View view) {
        if (idTask < taskList.size() - 1) {
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
    private void isRightAnswers(int i) {
        List<Boolean> rightAnswers = taskList.get(i).getRightAnswers();
        boolean choiceRightAnswer = true;
        for (int j = 0; j < rightAnswers.size(); j++) {
            if (checkBoxList.get(j).isChecked() != rightAnswers.get(j)) {
                choiceRightAnswer = false;
            }
        }
        if (choiceRightAnswer) {
            showRightAnswer();
            Toast.makeText(this, "Правильно!", Toast.LENGTH_SHORT).show();
            taskList.get(idTask).setResolved(true); //встановити флаг чи вирішена задача
        } else {
            Toast.makeText(this, "Не правильно!", Toast.LENGTH_SHORT).show();
            buttonShowRightAnswer.setVisibility(View.VISIBLE); //показати кнопку правильної відповіді
        }
    }

    //метод для перевірки правильності відповіді
    public void showRightAnswer() {
        for (int i = 0; i < taskList.get(idTask).getAllAnswersList().size(); i++) {
            Boolean aBoolean = taskList.get(idTask).getRightAnswers().get(i);
            if (aBoolean) {
                checkBoxList.get(i).setBackground(getResources().getDrawable(R.drawable.style_checkbox_green, getTheme())); ///
                checkBoxList.get(i).setChecked(true);
            } else {
                if (checkBoxList.get(i).isChecked()) {
                    checkBoxList.get(i).setBackground(getResources().getDrawable(R.drawable.style_checkbox_red, getTheme())); ///
                    checkBoxList.get(i).setChecked(false);
                } else {
                    checkBoxList.get(i).setBackground(getResources().getDrawable(R.drawable.style_btn_blue, getTheme()));
                    checkBoxList.get(i).setChecked(false);
                }
            }
        }
        taskList.get(idTask).setResolved(true); //встановити флаг чи вирішена задача
    }

    //метод онклік для перевірки правильності відповіді
    public void showRightAnswer(View view) {
        showRightAnswer();
    }

    //метод онклік для зміни фону вибраного чекбокса
    public void onClickCheckBox(View view) {
        CheckBox checkBox = (CheckBox) view;
        if (checkBox.isChecked()) {
            checkBox.setBackground(getResources().getDrawable(R.drawable.style_btn_white, getTheme()));
        } else {
            checkBox.setBackground(getResources().getDrawable(R.drawable.style_btn_blue, getTheme()));
        }
    }
}