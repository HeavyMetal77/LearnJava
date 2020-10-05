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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

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
    private FirebaseFirestore db;

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
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        imageViewArrowBack = findViewById(R.id.imageViewArrowBack);
        imageViewArrowForward = findViewById(R.id.imageViewArrowForward);
        imageViewArrowBack.setVisibility(View.INVISIBLE);

        db = FirebaseFirestore.getInstance();
        db.collection("taskList").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null) {
                    taskList = value.toObjects(Task.class);
                    generateNextTask(idTask);
                }
            }
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
    }

    //генерація наступного завдання
    public void generateNextTask(int idTask) {
        //стрілка назад невидима, якщо перше завдання
        if (idTask < 1) {
            imageViewArrowBack.setVisibility(View.INVISIBLE);
        } else {
            imageViewArrowBack.setVisibility(View.VISIBLE);
        }

        //стрілка вперед невидима, якщо останнє завдання
        if (idTask == taskList.size()-1) {
            imageViewArrowForward.setVisibility(View.INVISIBLE);
        } else {
            imageViewArrowForward.setVisibility(View.VISIBLE);
        }

        for (CheckBox checkBox : checkBoxList) {
            checkBox.setVisibility(View.VISIBLE); //всі чекбокси роблю видимими
        }
        Task task = taskList.get(idTask);
        textViewTopic.setText(getResources().getString(R.string.topic_label) + task.getTopic());
        textViewLabelTask.setText(getResources().getString(R.string.task_label) + " " + task.getIdTask());
        textLabelResultTask.setText(getResources().getText(R.string.result) + " 2/5");

        if (taskList != null && taskList.size() > 0) {
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
            List<Boolean> rightAnswers = task.getRightAnswers();
            boolean choiceRightAnswer = true;
            for (int j = 0; j < rightAnswers.size(); j++) {
                if (checkBoxList.get(j).isChecked() != rightAnswers.get(j)) {
                    choiceRightAnswer = false;
                }
            }
            if (choiceRightAnswer) {
                showRightAnswer();
                Toast.makeText(this, "Правильно!", Toast.LENGTH_SHORT).show();
                task.setResolved(true); //встановити флаг чи вирішена задача
            } else {
                Toast.makeText(this, "Не правильно!", Toast.LENGTH_SHORT).show();
                buttonShowRightAnswer.setVisibility(View.VISIBLE); //показати кнопку правильної відповіді
            }
        }
    }

    //метод для показу правильності відповіді
    public void showRightAnswer() {
        if (taskList != null && taskList.size() > 0) {
            Task task = taskList.get(idTask);
            for (int i = 0; i < task.getAllAnswersList().size(); i++) {
                Boolean aBoolean = task.getRightAnswers().get(i);
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
            task.setResolved(true); //встановити флаг чи вирішена задача
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
            checkBox.setBackground(getResources().getDrawable(R.drawable.style_btn_white, getTheme()));
        } else {
            checkBox.setBackground(getResources().getDrawable(R.drawable.style_btn_blue, getTheme()));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}