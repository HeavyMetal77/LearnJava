package ua.tarastom.learnjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import ua.tarastom.learnjava.data.Task;
import ua.tarastom.learnjava.data.Topic;

public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();

//        uploadDataToFirestore(); //тест завантаження даних в Cloud Firestore - tasks
//        uploadListQuantityTasksToFirestore(); //тест завантаження даних в Cloud Firestore - topics
    }

    public void onClickGoTrainingMode(View view) {
        Intent intent = new Intent(this, ListTopicActivity.class);
        intent.putExtra("mode", 1);
        startActivity(intent);
    }

    public void onClickGoTestMode(View view) {
        Intent intent = new Intent(this, TestActivity.class);
        intent.putExtra("mode", 2);
        startActivity(intent);
    }

    public void onClickGoStatistics(View view) {
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }

    public void uploadDataToFirestore() {
        for (int i = 0; i < 10; i++) {
            List<String> topic = new ArrayList<>();
            topic.add("Синтаксис Java. Типы данных, переменные, системы счисления, вывод данных в консоль.");
            topic.add("Java syntax. Data types, variables, number systems, data output to the console.");
            topic.add("Синтаксис Java. Типи даних, змінні, системи числення, вивід даних в консоль.");
            List<String> question = new ArrayList<>();
            question.add("Какие из следующих идентификаторов являются действительными идентификаторами Java? (Выберите все, что подходит)");
            question.add("Which of the following are valid Java identifiers? (Choose all that apply)");
            question.add("Які з наступних ідентифікаторів є дійсними ідентифікаторами Java? (Виберіть всі, що підходять)");

            String taskStr =
                    "A.  A$B\n" +
                            "B. _helloWorld\n" +
                            "C.  true\n" +
                            "D.  java.lang\n" +
                            "E.  Public\n" +
                            "F.  1980_s\n";

            Map<String, Boolean> answerMap = new LinkedHashMap<>();
            answerMap.put("A", true);
            answerMap.put("B", true);
            answerMap.put("C", false);
            answerMap.put("D", false);
            answerMap.put("E", true);
            answerMap.put("F", false);
            List<Map<String, Boolean>> answer = new ArrayList<>();
            answer.add(answerMap);
            answer.add(answerMap);
            answer.add(answerMap);

            List<String> hint = new ArrayList<>();
            hint.add("A, B, E. Вариант A действителен, потому что вы можете использовать знак доллара в идентификаторах. Вариант Б " +
                    "действителен, поскольку в идентификаторах можно использовать подчеркивание. Вариант C не является допустимым идентификатором, " +
                    "потому что true - это зарезервированное слово Java. Вариант D недействителен, потому что точка (.) не " +
                    "разрешена в идентификаторах. Вариант E действителен, потому что Java чувствителен к регистру, поэтому Public не является " +
                    "зарезервированным словом и, следовательно, действительным идентификатором. Вариант F недействителен, потому что первый " +
                    "символ не является буквой, $ или знаком нижнего подчеркивания (_).");
            hint.add("A, B, E. Option A is valid because you can use the dollar sign in identifiers. Option B is " +
                    "valid because you can use an underscore in identifiers. Option C is not a valid identifier " +
                    "because true is a Java reserved word. Option D is not valid because the dot (.) is not " +
                    "allowed in identifiers. Option E is valid because Java is case sensitive, so Public is not " +
                    "a reserved word and therefore a valid identifier. Option F is not valid because the first " +
                    "character is not a letter, $, or _.");
            hint.add("A, B, E. Варіант A дійсний, оскільки ви можете використовувати знак долара в ідентифікаторах. Варіант B є " +
                    "дійсний, оскільки ви можете використовувати підкреслення в ідентифікаторах. Варіант C не є дійсним ідентифікатором " +
                    "тому, що true - це зарезервоване слово Java. Варіант D недійсний, оскільки крапка (.) не " +
                    "допускається в ідентифікаторах. Варіант E є дійсним, оскільки Java чутливий до регістру, тому Public - не " +
                    "зарезервоване слово і, отже, дійсний ідентифікатор. Варіант F не дійсний, оскільки перший " +
                    "символ - це не буква, $ або знак нижного підкреслення (_).");

            db.collection("taskList").add(
                    new Task(i, 1, topic, question, taskStr, answer, false, hint))
                    .addOnSuccessListener(documentReference -> Toast.makeText(MainActivity.this, "Success!" , Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show());
        }
    }

    public void uploadListQuantityTasksToFirestore() {
        List<Topic> topicList = new ArrayList<>();
//        topicList.add(new Topic("Синтаксис Java. Типы данных, переменные, системы счисления, вывод данных в консоль"));
//        topicList.add(new Topic("Арифметические и логические операции, операции сравнения и присваивания."));
//        topicList.add(new Topic("Классы и объекты, ссылочные типы данных"));
//        topicList.add(new Topic("Создание объектов, конструкторы. Объявление и вызов методов."));
//        topicList.add(new Topic("Перезагрузка методов и конструкторов."));
//        topicList.add(new Topic("Модификаторы видимости"));
//        topicList.add(new Topic("Модификаторы «final» и «static»"));
//        topicList.add(new Topic("Разновидности переменных и пределы их видимости. «import» и «import static»"));
//        topicList.add(new Topic("Примитивные и ссылочные типы данных при вызове метода"));
//        topicList.add(new Topic("Конструкции «if» и «if else». Ternary оператор. Конструкция «switch»"));
//        topicList.add(new Topic("Циклы «for», «foreach», «while» и «do while»"));
//        topicList.add(new Topic("Класс String"));
//        topicList.add(new Topic("Класс StringBuilder"));
//        topicList.add(new Topic("Работа с массивами"));
//        topicList.add(new Topic("Параметры метода типа varargs."));
//        topicList.add(new Topic("Коллекции"));
//        topicList.add(new Topic("«Garbage collection»"));
//        topicList.add(new Topic("Инкапсуляция."));
//        topicList.add(new Topic("Overriding Hiding Final"));
//        topicList.add(new Topic("Абстрактные, дефолтные, статические методы."));
//        topicList.add(new Topic("Полиморфизм."));
//        topicList.add(new Topic("Методы «equals» и «toString». Wrapper классы."));
//        topicList.add(new Topic("Исключения и Ошибки."));
//        topicList.add(new Topic("Классы, отвечающие за работу с датами и временем."));
//        topicList.add(new Topic("Алгоритмы."));
//        topicList.add(new Topic("Интерфейсы Comparable и Comparator."));
//        topicList.add(new Topic("Generics."));
//        topicList.add(new Topic("Nested классы."));
//        topicList.add(new Topic("Lambda выражения."));
//        topicList.add(new Topic("Streams."));
//        topicList.add(new Topic("Многопоточность."));
//        topicList.add(new Topic("Работа с файлами IO и NIO."));
//        topicList.add(new Topic("Регулярные выражения."));
//        topicList.add(new Topic("Enum."));
//        topicList.add(new Topic("Класс Scanner."));
//        topicList.add(new Topic("Reflection."));

        int i = 0;
        //тест - рандом значення
        for (Topic topic : topicList) {
            topic.setId(++i);
            topic.setQuantityTasksInTopic(new Random().nextInt(100));
        }

        LinkedHashMap<String, Integer> integerMap = new LinkedHashMap<>();
        for (Topic topic : topicList) {
            integerMap.put(topic.getNameTopic().get(0), topic.getQuantityTasksInTopic());
        }

        for (int j = 0; j < topicList.size(); j++) {
            db.collection("topicList").add(topicList.get(j))
                    .addOnSuccessListener(documentReference ->
                            Toast.makeText(MainActivity.this, "Коллекция успешно добавлена в Cloud Firestore!",
                                    Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText(MainActivity.this, "Ошибка! Коллекция не добавлена в Cloud Firestore!", Toast.LENGTH_SHORT).show());
        }
    }

    @Override
    public void onBackPressed() {
        //эмулируем нажатие на HOME, сворачивая приложение
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}