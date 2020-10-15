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
import java.util.Random;

import ua.tarastom.learnjava.data.Topic;

public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();

        uploadDataToFirestore(); //тест завантаження даних в Cloud Firestore
//        uploadListQuantityTasksToFirestore(); //тест завантаження даних в Cloud Firestore
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

//        for (int i = 0; i < 10; i++) {
//            List<String> topic = new ArrayList<>();
//            topic.add("Синтаксис Java. Типы данных, переменные, системы счисления, вывод данных в консоль.");
//            topic.add("Java syntax. Data types, variables, number systems, data output to the console.");
//            topic.add("Синтаксис Java. Типи даних, змінні, системи числення, вивід даних в консоль.");
//            List<String> question = new ArrayList<>();
//            question.add("Какие из следующих идентификаторов являются действительными идентификаторами Java? \n(Выберите все правильные ответы)");
//            question.add("Which of the following are valid Java identifiers? \n(Choose all that apply)");
//            question.add("Які з вказаних ідентифікаторів являються дійсними ідентифікаторами Java? \n(Виберіть всі правильні відповіді)");
//
//            Map<String, Boolean> answer = new LinkedHashMap<>();
//            answer.put("1", false);
//            answer.put("2", true);
//            answer.put("3", false);
//            answer.put("4", true);
//            answer.put("5", false);
//
//            db.collection("taskList").add(
//                    new Task(i, 0, topic, question,
//                    "       1. byte b11 = 125;\n" +
//                            "       2. byte b21 = -228;\n" +
//                            "       3. byte b31 = 0b0101;\n" +
//                            "       4. byte b4 = 0b101;\n" +
//                            "       5. byte b5 = 225", answer, false)).addOnSuccessListener(documentReference -> Toast.makeText(MainActivity.this, "Success!" , Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show());
//        }


//        for (int i = 0; i < 10; i++) {
//            List<String> topic = new ArrayList<>();
//            topic.add("Арифметические и логические операции, операции сравнения и присваивания.");
//            topic.add("Arithmetic and logical operations, comparison and assignment operations.");
//            topic.add("Арифметичні та логічні операції, операції порівняння та присвоювання.");
//            List<String> question = new ArrayList<>();
//            question.add("Какие ? \n(Выберите все правильные ответы)");
//            question.add("Which ? \n(Choose all that apply)");
//            question.add("Які ? \n(Виберіть всі правильні відповіді)");
//
//            Map<String, Boolean> answer = new LinkedHashMap<>();
//            answer.put("1", false);
//            answer.put("2", true);
//            answer.put("3", false);
//            answer.put("4", true);
//            answer.put("5", false);
//
//            db.collection("taskList").add(
//                    new Task(i, 1, topic, question,
//                            "       1. byte b11 = 125;\n" +
//                                    "       2. byte b21 = -228;\n" +
//                                    "       3. byte b31 = 0b0101;\n" +
//                                    "       4. byte b4 = 0b101;\n" +
//                                    "       5. byte b5 = 225", answer, false)).addOnSuccessListener(documentReference -> Toast.makeText(MainActivity.this, "Success!" , Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show());
//        }

//        for (int i = 0; i < 10; i++) {
//            List<String> topic = new ArrayList<>();
//            topic.add("Классы и объекты, ссылочные типы данных.");
//            topic.add("Classes and objects, reference data types.");
//            topic.add("Класи і об'єкти, посилальні типи даних.");
//            List<String> question = new ArrayList<>();
//            question.add("Какие ? \n(Выберите все правильные ответы)");
//            question.add("Which ? \n(Choose all that apply)");
//            question.add("Які ? \n(Виберіть всі правильні відповіді)");
//
//            Map<String, Boolean> answer = new LinkedHashMap<>();
//            answer.put("1", false);
//            answer.put("2", true);
//            answer.put("3", false);
//            answer.put("4", true);
//            answer.put("5", false);
//
//            db.collection("taskList").add(
//                    new Task(i, 3, topic, question,
//                            "       1. byte b11 = 125;\n" +
//                                    "       2. byte b21 = -228;\n" +
//                                    "       3. byte b31 = 0b0101;\n" +
//                                    "       4. byte b4 = 0b101;\n" +
//                                    "       5. byte b5 = 225", answer, false)).addOnSuccessListener(documentReference -> Toast.makeText(MainActivity.this, "Success!" , Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show());
//        }


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