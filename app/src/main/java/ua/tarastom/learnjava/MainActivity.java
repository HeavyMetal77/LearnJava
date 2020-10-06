package ua.tarastom.learnjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import ua.tarastom.learnjava.data.Task;
import ua.tarastom.learnjava.data.Topic;

public class MainActivity extends AppCompatActivity {

    public FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();

//        uploadDataToFirestore(); //тест завантаження даних в Cloud Firestore
//        uploadListQuantityTasksToFirestore(); //тест завантаження даних в Cloud Firestore
    }

    public void onClickGoTrainingMode(View view) {
        Intent intent = new Intent(this, ListTopicActivity.class);
        intent.putExtra("mode", 1);
        startActivity(intent);
    }

    public void onClickGoTestMode(View view) {
        Intent intent = new Intent(this, ListTopicActivity.class);
        intent.putExtra("mode", 2);
        startActivity(intent);
    }

    public void onClickGoStatistics(View view) {
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }

    public void uploadDataToFirestore() {
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

        ArrayList<String> listAllAnswer2 = new ArrayList<>();
        listAllAnswer2.add("5");
        listAllAnswer2.add("4");
        listAllAnswer2.add("3");

        ArrayList<Boolean> listRightAnswer2 = new ArrayList<>();
        listRightAnswer2.add(false);
        listRightAnswer2.add(false);
        listRightAnswer2.add(true);

        db.collection("taskList").add(new Task(1, "Типы данных, переменные и массивы.", "Какая строка не скомпилируется?",
                "       1. byte b1 = 125;\n" +
                        "       2. byte b2 = -228;\n" +
                        "       3. byte b3 = 0b0101;\n" +
                        "       4. byte b4 = 0b101;\n" +
                        "       5. byte b5 = 225",
                listAllAnswer,
                listRightAnswer, false)).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });
        db.collection("taskList").add(new Task(2, "Типы данных, переменные и массивы.", "Какая строка не скомпилируется?",
                "       1. int i1 = 10;\n" +
                        "       2. int i2 = 1_000_000_000;\n" +
                        "       3. int i3 = _1000_000_000;\n" +
                        "       4. int i4 = 1_0_00_00_0_000;\n" +
                        "       5. int i5 = 10_000_000_000;",
                listAllAnswer2,
                listRightAnswer2, false)).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void uploadListQuantityTasksToFirestore() {
        List<Topic> topicList = new ArrayList<>();
        topicList.add(new Topic("Синтаксис Java. Типы данных, переменные, системы счисления, вывод данных в консоль"));
        topicList.add(new Topic("Арифметические и логические операции, операции сравнения и присваивания."));
        topicList.add(new Topic("Классы и объекты, ссылочные типы данных"));
        topicList.add(new Topic("Создание объектов, конструкторы. Объявление и вызов методов."));
        topicList.add(new Topic("Перезагрузка методов и конструкторов."));
        topicList.add(new Topic("Модификаторы видимости"));
        topicList.add(new Topic("Модификаторы «final» и «static»"));
        topicList.add(new Topic("Разновидности переменных и пределы их видимости. «import» и «import static»"));
        topicList.add(new Topic("Примитивные и ссылочные типы данных при вызове метода"));
        topicList.add(new Topic("Конструкции «if» и «if else». Ternary оператор. Конструкция «switch»"));
        topicList.add(new Topic("Циклы «for», «foreach», «while» и «do while»"));
        topicList.add(new Topic("Класс String"));
        topicList.add(new Topic("Класс StringBuilder"));
        topicList.add(new Topic("Работа с массивами"));
        topicList.add(new Topic("Параметры метода типа varargs."));
        topicList.add(new Topic("Коллекции"));
        topicList.add(new Topic("«Garbage collection»"));
        topicList.add(new Topic("Инкапсуляция."));
        topicList.add(new Topic("Overriding Hiding Final"));
        topicList.add(new Topic("Абстрактные, дефолтные, статические методы."));
        topicList.add(new Topic("Полиморфизм."));
        topicList.add(new Topic("Методы «equals» и «toString». Wrapper классы."));
        topicList.add(new Topic("Исключения и Ошибки."));
        topicList.add(new Topic("Классы, отвечающие за работу с датами и временем."));
        topicList.add(new Topic("Алгоритмы."));
        topicList.add(new Topic("Интерфейсы Comparable и Comparator."));
        topicList.add(new Topic("Generics."));
        topicList.add(new Topic("Nested классы."));
        topicList.add(new Topic("Lambda выражения."));
        topicList.add(new Topic("Streams."));
        topicList.add(new Topic("Многопоточность."));
        topicList.add(new Topic("Работа с файлами IO и NIO."));
        topicList.add(new Topic("Регулярные выражения."));
        topicList.add(new Topic("Enum."));
        topicList.add(new Topic("Класс Scanner."));
        topicList.add(new Topic("Reflection."));

        int i = 0;
        //тест - рандом значення
        for (Topic topic : topicList) {
            topic.setId(++i);
            topic.setQuantityTasksInTopic(new Random().nextInt(100));
        }

        LinkedHashMap<String, Integer> integerMap = new LinkedHashMap<>();
        for (Topic topic : topicList) {
            integerMap.put(topic.getNameTopic(), topic.getQuantityTasksInTopic());
        }

        for (int j = 0; j < topicList.size(); j++) {
            db.collection("topicList").add(topicList.get(j))
                    .addOnSuccessListener(documentReference ->
                            Toast.makeText(MainActivity.this, "Коллекция успешно добавлена в Cloud Firestore!",
                                    Toast.LENGTH_SHORT).show()).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, "Ошибка! Коллекция не добавлена в Cloud Firestore!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}