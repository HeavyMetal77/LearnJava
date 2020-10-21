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
        List<Task> taskList = new ArrayList<>();
        taskList.add(topic1task1());
        taskList.add(topic1task2());
        taskList.add(topic1task3());
        taskList.add(topic1task4());
        taskList.add(topic1task5());
        taskList.add(topic1task6());
        taskList.add(topic1task7());
        taskList.add(topic1task8());
        taskList.add(topic1task9());
        taskList.add(topic1task10());
        taskList.add(topic1task11());
        taskList.add(topic3task1());
        taskList.add(topic3task2());
        taskList.add(topic6task1());
        taskList.add(topic6task2());
        taskList.add(topic6task3());

        for (Task task : taskList) {
            db.collection("taskList").add(task)
                    .addOnSuccessListener(documentReference -> Toast.makeText(MainActivity.this, "Success!" , Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show());
        }
    }

    public Task topic1task1() {
//        int  A$B;
//        int _helloWorld;
//        int true;
//        int java.lang;
//        int Public;
//        int 1980_;

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
                        "F.  1980_s";

        Map<String, Boolean> answer = new LinkedHashMap<>();
        answer.put("A", true);
        answer.put("B", true);
        answer.put("C", false);
        answer.put("D", false);
        answer.put("E", true);
        answer.put("F", false);
        List<Map<String, Boolean>> answerListMap = new ArrayList<>();
        answerListMap.add(answer);
        answerListMap.add(answer);
        answerListMap.add(answer);


        List<String> hint = new ArrayList<>();
        hint.add("A, B, E. \n" +
                "Вариант A действительный, поскольку вы можете использовать знак доллара в идентификаторах.\n" +
                "Вариант B действительный, поскольку вы можете использовать подчеркивание в идентификаторах.\n" +
                "Вариант C не является действительным идентификатором потому, что true - это зарезервированное слово Java.\n" +
                "Вариант D недействителен, поскольку точка (.) не допускается в идентификаторах.\n" +
                "Вариант E является действительным, поскольку Java чувствителен к регистру, поэтому Public - не " +
                "зарезервированное слово и, следовательно, действительный идентификатор.\n" +
                "Вариант F не действителен, поскольку первый символ - это не буква, $ или знак подчеркивания.");
        hint.add("A, B, E. " +
                "Option A is valid because you can use the dollar sign in identifiers.\n" +
                "Option B is valid because you can use an underscore in identifiers.\n" +
                "Option C is not a valid identifier because true is a Java reserved word.\n" +
                "Option D is not valid because the dot (.) is not allowed in identifiers.\n" +
                "Option E is valid because Java is case sensitive, so Public is not " +
                "a reserved word and therefore a valid identifier.\n" +
                "Option F is not valid because the first character is not a letter, $, or _.");
        hint.add("A, B, E. " +
                "Варіант A дійсний, оскільки ви можете використовувати знак долара в ідентифікаторах.\n" +
                "Варіант B є дійсний, оскільки ви можете використовувати підкреслення в ідентифікаторах.\n" +
                "Варіант C не є дійсним ідентифікатором тому, що true - це зарезервоване слово Java.\n" +
                "Варіант D недійсний, оскільки крапка (.) не допускається в ідентифікаторах.\n" +
                "Варіант E є дійсним, оскільки Java чутливий до регістру, тому Public - не " +
                "зарезервоване слово і, отже, дійсний ідентифікатор.\n" +
                "Варіант F не дійсний, оскільки перший символ - це не буква, $ або знак нижного підкреслення.");

        return new Task(1, 1, topic, question, taskStr, answerListMap, false, hint);
    }

    public Task topic1task2() {

//        short numPets = 5;
//        int numGrains = 5.6;
//        String name = "Scruffy";
//        umPets.length();
//        numGrains.length();
//        name.length();

        List<String> topic = new ArrayList<>();
        topic.add("Синтаксис Java. Типы данных, переменные, системы счисления, вывод данных в консоль.");
        topic.add("Java syntax. Data types, variables, number systems, data output to the console.");
        topic.add("Синтаксис Java. Типи даних, змінні, системи числення, вивід даних в консоль.");
        List<String> question = new ArrayList<>();
        question.add("Что из следующего верно? (Выберите все правильные ответы)");
        question.add("Which of the following are true? \n(Choose all that apply)");
        question.add("Що з переліченого є правдою? (Виберіть всі правильні відповіді)");

        String taskStr =
                "4: short numPets = 5;\n" +
                        "5: int numGrains = 5.6;\n" +
                        "6: String name = \"Scruffy\";\n" +
                        "7: numPets.length();\n" +
                        "8: numGrains.length();\n" +
                        "9: name.length();";

        List<Map<String, Boolean>> answerListMap = new ArrayList<>();
        Map<String, Boolean> answer1 = new LinkedHashMap<>();
        answer1.put("A.  Строка 4 генерирует ошибку компилятора.", false);
        answer1.put("B.  Строка 5 генерирует ошибку компилятора.", true);
        answer1.put("C.  Строка 6 генерирует ошибку компилятора.", false);
        answer1.put("D.  Строка 7 генерирует ошибку компилятора.", true);
        answer1.put("E.  Строка 8 генерирует ошибку компилятора.", true);
        answer1.put("F.  Строка 9 генерирует ошибку компилятора.", false);
        answer1.put("G.  Весь код компилируется.", false);
        answerListMap.add(answer1);

        Map<String, Boolean> answer2 = new LinkedHashMap<>();
        answer2.put("A.  Line 4 generates a compiler error.", false);
        answer2.put("B.  Line 5 generates a compiler error.", true);
        answer2.put("C.  Line 6 generates a compiler error.", false);
        answer2.put("D.  Line 7 generates a compiler error.", true);
        answer2.put("E.  Line 8 generates a compiler error.", true);
        answer2.put("F.  Line 9 generates a compiler error.", false);
        answer2.put("G.  The code compiles as is.", false);
        answerListMap.add(answer2);

        Map<String, Boolean> answer3 = new LinkedHashMap<>();
        answer3.put("A.  Рядок 4 генерує помилку компілятора.", false);
        answer3.put("B.  Рядок 5 генерує помилку компілятора.", true);
        answer3.put("C.  Рядок 6 генерує помилку компілятора.", false);
        answer3.put("D.  Рядок 7 генерує помилку компілятора.", true);
        answer3.put("E.  Рядок 8 генерує помилку компілятора.", true);
        answer3.put("F.  Рядок 9 генерує помилку компілятора.", false);
        answer3.put("G.  Весь код компілюється.", false);
        answerListMap.add(answer3);


        List<String> hint = new ArrayList<>();
        hint.add("B, D, E.\n" +
                "Вариант A (строка 4) компилируется, поскольку short - это целочисленный тип.\n" +
                "Вариант B (строка 5) генерирует ошибку компилятора, поскольку int - это целочисленный тип, а 5.6 - тип с плавающей запятой.\n" +
                "Вариант C (строка 6) компилируется, поскольку переменной класса String присвоено строку.\n" +
                "Варианты D и E (строки 7 и 8) не компилируются, поскольку short и int является примитивами. Примитивы не позволяют вызвать на них методы.\n" +
                "Вариант F (строка 9) компилируется, поскольку метод length() определен в классе String.");
        hint.add("B, D, E.\n" +
                "Option A (line 4) compiles because short is an integral type.\n" +
                "Option B (line 5) generates a compiler error because int is an integral type, but 5.6 is a floating-point type.\n" +
                "Option C (line 6) compiles because it is assigned a String.\n" +
                "Options D and E (lines 7 and 8) do not compile because short and int are primitives. Primitives do not allow methods to be called on them.\n" +
                "Option F (line 9) compiles because length() is defined on String.");
        hint.add("B, D, E.\n" +
                "Варіант A (рядок 4) компілюється, оскільки short - це цілочислений тип.\n" +
                "Варіант B (рядок 5) генерує помилку компілятора, оскільки int - це цілочислений тип, а 5.6 - тип із плаваючою комою.\n" +
                "Варіант C (рядок 6) компілюється, оскільки змінній класу String присвоєно рядок.\n" +
                "Варіанти D і E (рядки 7 і 8) не компілюються, оскільки short і int є примітивами. Примітиви не дозволяють викликати на них методи.\n" +
                "Варіант F (рядок 9) компілюється, оскільки метод length() визначений у класі String.");

        return new Task(2, 1, topic, question, taskStr, answerListMap, false, hint);

    }

    public Task topic1task3() {
        List<String> topic = new ArrayList<>();
        //ru
        topic.add("Синтаксис Java. Типы данных, переменные, системы счисления, вывод данных в консоль.");
        //us
        topic.add("Java syntax. Data types, variables, number systems, data output to the console.");
        //uk
        topic.add("Синтаксис Java. Типи даних, змінні, системи числення, вивід даних в консоль.");

        List<String> question = new ArrayList<>();
        //ru
        question.add("Учитывая следующий класс, какая из следующих команд выводит в консоль - Blue Jay?\n" +
                "(Выберите все, что подходит)");
        //us
        question.add("Given the following class, which of the following calls print out Blue Jay? \n(Choose all that apply)");
        //uk
        question.add("З огляду на наступний клас, яка із наведених команд виводить в консоль - Blue Jay?\n" +
                "(Виберіть все, що підходить)");

        String taskStr =
                "public class BirdDisplay {\n" +
                        "  public static void main(String[] name) {\n" +
                        "    System.out.println(name[1]);\n" +
                        "} }";

        //ru
        List<Map<String, Boolean>> answerListMap = new ArrayList<>();
        Map<String, Boolean> answer1 = new LinkedHashMap<>();
        answer1.put("A.  java BirdDisplay Sparrow Blue Jay", false);
        answer1.put("B.  java BirdDisplay Sparrow \"Blue Jay\"", true);
        answer1.put("C.  java BirdDisplay Blue Jay Sparrow", false);
        answer1.put("D.  java BirdDisplay \"Blue Jay\" Sparrow", false);
        answer1.put("E.  java BirdDisplay.class Sparrow \"Blue Jay\"", false);
        answer1.put("F.  java BirdDisplay.class \"Blue Jay\" Sparrow", false);
        answer1.put("G.  Не компилируется.", false);
        answerListMap.add(answer1);

        //us
        Map<String, Boolean> answer2 = new LinkedHashMap<>();
        answer2.put("A.  java BirdDisplay Sparrow Blue Jay", false);
        answer2.put("B.  java BirdDisplay Sparrow \"Blue Jay\"", true);
        answer2.put("C.  java BirdDisplay Blue Jay Sparrow", false);
        answer2.put("D.  java BirdDisplay \"Blue Jay\" Sparrow", false);
        answer2.put("E.  java BirdDisplay.class Sparrow \"Blue Jay\"", false);
        answer2.put("F.  java BirdDisplay.class \"Blue Jay\" Sparrow", false);
        answer2.put("G.  Does not compile.", false);
        answerListMap.add(answer2);

        //uk
        Map<String, Boolean> answer3 = new LinkedHashMap<>();
        answer3.put("A.  java BirdDisplay Sparrow Blue Jay", false);
        answer3.put("B.  java BirdDisplay Sparrow \"Blue Jay\"", true);
        answer3.put("C.  java BirdDisplay Blue Jay Sparrow", false);
        answer3.put("D.  java BirdDisplay \"Blue Jay\" Sparrow", false);
        answer3.put("E.  java BirdDisplay.class Sparrow \"Blue Jay\"", false);
        answer3.put("F.  java BirdDisplay.class \"Blue Jay\" Sparrow", false);
        answer3.put("G.  Не компілюється.", false);
        answerListMap.add(answer3);

        List<String> hint = new ArrayList<>();
        //ru
        hint.add("B.\n" +
                "Вариант B правильный, поскольку массивы начинают отсчет от нуля, а строки с пробелами должны быть в кавычках.\n" +
                "Вариант A неправильный, поскольку выводит Blue.\n" +
                "C неправильный, поскольку выводит Jay.\n" +
                "Вариант D неправильный, поскольку выводит Sparrow.\n" +
                "Варианты E и F неправильные, поскольку они выдают Ошибка: не удалось найти или загрузить основной класс BirdDisplay.class.");
        //us
        hint.add("B.\n" +
                "Option B is correct because arrays start counting from zero and strings with spaces must be in quotes.\n" +
                "Option A is incorrect because it outputs Blue.\n" +
                "C is incorrect because it outputs Jay.\n" +
                "Option D is incorrect because it outputs Sparrow.\n" +
                "Options E and F are incorrect because they output Error: Could not find or load main class BirdDisplay.class.");
        //uk
        hint.add("B.\n" +
                "Варіант B правильний, оскільки масиви починають відлік від нуля, а рядки з пробілами повинні бути в лапках.\n" +
                "Варіант A неправильний, оскільки виводить Blue.\n" +
                "C неправильний, оскільки виводить Jay.\n" +
                "Варіант D неправильний, оскільки виводить Sparrow.\n" +
                "Варіанти E та F неправильні, оскільки вони видають Помилка: Не вдалося знайти або завантажити основний клас BirdDisplay.class.");

        return new Task(3, 1, topic, question, taskStr, answerListMap, false, hint);

    }

    public Task topic1task4() {
        List<String> topic = new ArrayList<>();
        //ru
        topic.add("Синтаксис Java. Типы данных, переменные, системы счисления, вывод данных в консоль.");
        //us
        topic.add("Java syntax. Data types, variables, number systems, data output to the console.");
        //uk
        topic.add("Синтаксис Java. Типи даних, змінні, системи числення, вивід даних в консоль.");

        List<String> question = new ArrayList<>();
        //ru
        question.add("Чем из указанного допускается заполнить поле, чтобы Вы могли запустить метод main() из " +
                "командной строки? \n(Выберите все, что подходит)");
        //us
        question.add("Which of the following legally fill in the blank so you can run the main() method" +
                " from the command line? \n(Choose all that apply)");
        //uk
        question.add("Чим із зазначеного допускається заповнити поле, щоб Ви могли запустити метод main() " +
                "з командного рядка? \n(Виберіть все, що підходить)");

        String taskStr =
                "public static void main(______)";

        //ru
        List<Map<String, Boolean>> answerListMap = new ArrayList<>();
        Map<String, Boolean> answer1 = new LinkedHashMap<>();
        answer1.put("A.  String[] _names", true);
        answer1.put("B.  String[] 123", false);
        answer1.put("C.  String abc[]", true);
        answer1.put("D.  String _Names[]", true);
        answer1.put("E.  String... $n", true);
        answer1.put("F.  String names", false);
        answer1.put("G.  Ничего из вышеперечисленного", false);
        answerListMap.add(answer1);

        //us
        Map<String, Boolean> answer2 = new LinkedHashMap<>();
        answer2.put("A.  String[] _names", true);
        answer2.put("B.  String[] 123", false);
        answer2.put("C.  String abc[]", true);
        answer2.put("D.  String _Names[]", true);
        answer2.put("E.  String... $n", true);
        answer2.put("F.  String names", false);
        answer2.put("G.  None of the above.", false);
        answerListMap.add(answer2);

        //uk
        Map<String, Boolean> answer3 = new LinkedHashMap<>();
        answer3.put("A.  String[] _names", true);
        answer3.put("B.  String[] 123", false);
        answer3.put("C.  String abc[]", true);
        answer3.put("D.  String _Names[]", true);
        answer3.put("E.  String... $n", true);
        answer3.put("F.  String names", false);
        answer3.put("G.  Жодне з вищезазначеного.", false);
        answerListMap.add(answer3);

        List<String> hint = new ArrayList<>();
        //ru
        hint.add("A, C, D, E." +
                "Вариант A правильный, потому что это традиционная сигнатура метода main(), а переменные могут начинаться с подчеркивания.\n" +
                "Варианты C и D правильные, потому что оператор массива может стоять после имени переменной.\n" +
                "Вариант E правильный, потому что вместо массива разрешены varargs.\n" +
                "Вариант B неправильный, потому что переменные не могут начинаться с цифры.\n" +
                "Вариант F неправильный, потому что аргумент должен быть массивом или varargs.\n" +
                "Вариант F - имеет неправильный тип параметра.");
        //us
        hint.add("A, C, D, E.\n" +
                "Option A is correct because it is the traditional main() method signature and variables may begin with underscores.\n" +
                "Options C and D are correct because the array operator may appear after the variable name.\n" +
                "Option E is correct because varargs are allowed in place of an array.\n" +
                "Option B is incorrect because variables are not allowed to begin with a digit.\n" +
                "Option F is incorrect because the argument must be an array or varargs.\n" +
                "Option F is incorrect because it has the wrong parameter type.");
        //uk
        hint.add("A, C, D, E.\n" +
                "Варіант A правильний, оскільки це традиційна сигнатура методу main(), а змінні можуть починатися з підкреслення.\n" +
                "Варіанти C і D правильні, оскільки оператор масиву може з'являтися після імені змінної.\n" +
                "Варіант Е правильний, оскільки замість масиву допускаються varargs.\n" +
                "Варіант B неправильний, оскільки змінні не можуть починатися з цифри.\n" +
                "Варіант F неправильний, оскільки аргумент повинен бути масивом або varargs.\n" +
                "Варіант F - неправильний тип параметра.");

        return new Task(4, 1, topic, question, taskStr, answerListMap, false, hint);

    }

    public Task topic1task5() {
        List<String> topic = new ArrayList<>();
        //ru
        topic.add("Синтаксис Java. Типы данных, переменные, системы счисления, вывод данных в консоль.");
        //us
        topic.add("Java syntax. Data types, variables, number systems, data output to the console.");
        //uk
        topic.add("Синтаксис Java. Типи даних, змінні, системи числення, вивід даних в консоль.");
        List<String> question = new ArrayList<>();
        //ru
        question.add("Какие из перечисленных ниже методов точки входа можно запустить с помощью командной строки? \n(Выберите все, которые подходят)");
        //us
        question.add("Which of the following are legal entry point methods that can be run from the command line? \n(Choose all that apply)");
        //uk
        question.add("Які з перерахованих нижче методів точки входу можна запустити за допомогою командного рядка?\n" +
                "(Виберіть всі, що підходять)");

        String taskStr =
                "";

        //ru
        List<Map<String, Boolean>> answerListMap = new ArrayList<>();
        Map<String, Boolean> answer1 = new LinkedHashMap<>();
        answer1.put("A.  private static void main(String[] args)", false);
        answer1.put("B.  public static final main(String[] args)", false);
        answer1.put("C.  public void main(String[] args)", false);
        answer1.put("D.  public static void test(String[] args)", false);
        answer1.put("E.  public static void main(String[] args)", true);
        answer1.put("F.  public static main(String[] args)", false);
        answer1.put("G.  Ни один из вышеперечисленных.", false);
        answerListMap.add(answer1);

        //us
        Map<String, Boolean> answer2 = new LinkedHashMap<>();
        answer2.put("A.  private static void main(String[] args)", false);
        answer2.put("B.  public static final main(String[] args)", false);
        answer2.put("C.  public void main(String[] args)", false);
        answer2.put("D.  public static void test(String[] args)", false);
        answer2.put("E.  public static void main(String[] args)", true);
        answer2.put("F.  public static main(String[] args)", false);
        answer2.put("G.  None of the above.", false);
        answerListMap.add(answer2);

        //uk
        Map<String, Boolean> answer3 = new LinkedHashMap<>();
        answer3.put("A.  private static void main(String[] args)", false);
        answer3.put("B.  public static final main(String[] args)", false);
        answer3.put("C.  public void main(String[] args)", false);
        answer3.put("D.  public static void test(String[] args)", false);
        answer3.put("E.  public static void main(String[] args)", true);
        answer3.put("F.  public static main(String[] args)", false);
        answer3.put("G.  Жоден з вищезазначених.", false);
        answerListMap.add(answer3);

        List<String> hint = new ArrayList<>();
        //ru
        hint.add("E.\n" +
                "Вариант E - это правильная сигнатура main() метода.\n" +
                "Вариант A неправильный, поскольку метод main() должен быть public.\n" +
                "Варианты B и F неправильные, поскольку метод main() должен возвращать тип void.\n" +
                "Вариант C неправильный, поскольку метод main() должен быть static.\n" +
                "Вариант D неправильный, поскольку метод main() должен быть назван main.");
        //us
        hint.add("E. Option E is the canonical main() method signature.\n" +
                "Option A is incorrect because the main() method must be public. Options B and F \n" +
                "are incorrect because the main() method must have a void return type. Option C is \n" +
                "incorrect because the main() method must be static. Option D is incorrect because the \n" +
                "main() method must be named main.");
        //uk
        hint.add("E.\n" +
                "Варіант E - це правильна сигнатура main() методу.\n" +
                "Варіант A неправильний, оскільки метод main() повинен бути public.\n" +
                "Варіанти B та F неправильні, оскільки метод main() повинен мати тип повернення void.\n" +
                "Варіант C неправильний, оскільки метод main() повинен бути static.\n" +
                "Варіант D неправильний, оскільки метод main() повинен бути названий main.");

        return new Task(5, 1, topic, question, taskStr, answerListMap, false, hint);

    }

    public Task topic1task6() {
        List<String> topic = new ArrayList<>();
        //ru
        topic.add("Синтаксис Java. Типы данных, переменные, системы счисления, вывод данных в консоль.");
        //us
        topic.add("Java syntax. Data types, variables, number systems, data output to the console.");
        //uk
        topic.add("Синтаксис Java. Типи даних, змінні, системи числення, вивід даних в консоль.");
        List<String> question = new ArrayList<>();
        //ru
        question.add("Что из следующего верно? \n(Выберите все, что подходит)");
        //us
        question.add("Which of the following are true? \n(Choose all that apply)");
        //uk
        question.add("Що з переліченого є правдою? \n(Виберіть все, що підходить)");

        String taskStr =
                "";

        //ru
        List<Map<String, Boolean>> answerListMap = new ArrayList<>();
        Map<String, Boolean> answer1 = new LinkedHashMap<>();
        answer1.put("A.  Instance переменная типа double по умолчанию имеет значение null.", false);
        answer1.put("B.  Instance переменная типа int по умолчанию имеет значение null.", false);
        answer1.put("C.  Instance переменная типа String по умолчанию имеет значение null.", true);
        answer1.put("D.  Для instance переменной типа double по умолчанию установлено значение 0,0.", true);
        answer1.put("E.  Для instance переменной типа int по умолчанию установлено значение 0,0.", false);
        answer1.put("F.  Для instance переменной типа String по умолчанию установлено значение 0,0.", false);
        answer1.put("G.  Ни один из вышеперечисленных.", false);
        answerListMap.add(answer1);

        //us
        Map<String, Boolean> answer2 = new LinkedHashMap<>();
        answer2.put("A.  An instance variable of type double defaults to null.", false);
        answer2.put("B.  An instance variable of type int defaults to null.", false);
        answer2.put("C.  An instance variable of type String defaults to null.", true);
        answer2.put("D.  An instance variable of type double defaults to 0.0.", true);
        answer2.put("E.  An instance variable of type int defaults to 0.0.", false);
        answer2.put("F.  An instance variable of type String defaults to 0.0.", false);
        answer2.put("G.  None of the above.", false);
        answerListMap.add(answer2);

        //uk
        Map<String, Boolean> answer3 = new LinkedHashMap<>();
        answer3.put("A.  Instance змінна типу double має за замовчуванням значення null.", false);
        answer3.put("B.  Instance змінна типу int має за замовчуванням значення null.", false);
        answer3.put("C.  Instance змінна типу String має за замовчуванням значення null.", true);
        answer3.put("D.  Instance змінна екземпляра типу double має за замовчуванням 0.0", true);
        answer3.put("E.  Instance змінна екземпляра типу int має за замовчуванням 0.0", false);
        answer3.put("F.  Instance змінна екземпляра типу String має за замовчуванням 0.0", false);
        answer3.put("G.  Жоден з вищезазначених.", false);
        answerListMap.add(answer3);

        List<String> hint = new ArrayList<>();
        //ru
        hint.add("C, D.\n" +
                "Вариант C правильный, потому что по умолчанию все непримитивные значения равны нулю.\n" +
                "Вариант D верен, поскольку примитивы float и double по умолчанию равны 0,0.\n" +
                "Параметры B и E неверны, поскольку примитивы int по умолчанию равны 0");
        //us
        hint.add("C, D.\n" +
                "Option C is correct because all non-primitive values default to null.\n" +
                "Option D is correct because float and double primitives default to 0.0.\n" +
                "Options B and E are incorrect because int primitives default to 0");
        //uk
        hint.add("C, D.\n" +
                "Варіант C правильний, оскільки всі непримітивні значення за замовчуванням мають значення null.\n" +
                "Варіант D правильний, оскільки за замовчуванням float та double примітиви мають значення 0,0.\n" +
                "Варіанти B і E неправильні, оскільки примітиви int за замовчуванням становлять 0");

        return new Task(6, 1, topic, question, taskStr, answerListMap, false, hint);

    }

    public Task topic1task7() {
        List<String> topic = new ArrayList<>();
        //ru
        topic.add("Синтаксис Java. Типы данных, переменные, системы счисления, вывод данных в консоль.");
        //us
        topic.add("Java syntax. Data types, variables, number systems, data output to the console.");
        //uk
        topic.add("Синтаксис Java. Типи даних, змінні, системи числення, вивід даних в консоль.");
        List<String> question = new ArrayList<>();
        //ru
        question.add("Что из следующего верно? \n(Выберите все, что подходит)");
        //us
        question.add("Which of the following are true? \n(Choose all that apply)");
        //uk
        question.add("Що з переліченого є правдою? \n(Виберіть все, що підходить)");

        String taskStr =
                "";

        //ru
        List<Map<String, Boolean>> answerListMap = new ArrayList<>();
        Map<String, Boolean> answer1 = new LinkedHashMap<>();
        answer1.put("A.  Локальная переменная типа boolean по умолчанию имеет значение null", false);
        answer1.put("B.  Локальная переменная типа float по умолчанию имеет значение 0", false);
        answer1.put("C.  Локальная переменная типа Object по умолчанию имеет значение null.", true);
        answer1.put("D.  Локальная переменная типа boolean по умолчанию имеет значение false", true);
        answer1.put("E.  Локальная переменная типа boolean по умолчанию имеет значение true.", false);
        answer1.put("F.  Локальная переменная типа float по умолчанию имеет значение 0.0", false);
        answer1.put("G.  Ни один вариант из вышеперечисленных.", false);
        answerListMap.add(answer1);

        //us
        Map<String, Boolean> answer2 = new LinkedHashMap<>();
        answer2.put("A.  A local variable of type boolean defaults to null.", false);
        answer2.put("B.  A local variable of type float defaults to 0.", false);
        answer2.put("C.  A local variable of type Object defaults to null.", true);
        answer2.put("D.  A local variable of type boolean defaults to false.", true);
        answer2.put("E.  A local variable of type boolean defaults to true.", false);
        answer2.put("F.  A local variable of type float defaults to 0.0.", false);
        answer2.put("G.  None of the above.", false);
        answerListMap.add(answer2);

        //uk
        Map<String, Boolean> answer3 = new LinkedHashMap<>();
        answer3.put("A.  Локальна змінна типу boolean за замовчуванням має значення null", false);
        answer3.put("B.  Локальна змінна типу float за замовчуванням має значення 0", false);
        answer3.put("C.  Локальна змінна типу Object за замовчуванням має значення null.", true);
        answer3.put("D.  Локальна змінна типу boolean за замовчуванням має значення false", true);
        answer3.put("E.  Локальна змінна типу boolean за замовчуванням має значення true", false);
        answer3.put("F.  Локальна змінна типу float за замовчуванням має значення 0.0", false);
        answer3.put("G.  Жоден варіант з вищезазначених.", false);
        answerListMap.add(answer3);

        List<String> hint = new ArrayList<>();
        //ru
        hint.add("G.\n" +
                "Вариант G правильный, поскольку локальным переменным не присваиваются значения по умолчанию.\n" +
                "Код не компилируется, если локальная переменная не инициализирована явно. Если бы вопрос " +
                "был о переменных экземпляра (instance variables), то варианты D и F были бы правильными. Логический примитив " +
                "по умолчанию - false, а примитив с плавающей запятой по умолчанию - 0.0.");
        //us
        hint.add("G.\n" +
                "Option G is correct because local variables do not get assigned default values. The \n" +
                "code fails to compile if a local variable is not explicitly initialized. If this question \n" +
                "were about instance variables, options D and F would be correct. A boolean primitive \n" +
                "defaults to false and a float primitive defaults to 0.0.");
        //uk
        hint.add("G.\n" +
                "Варіант G правильний, оскільки локальних змінних, не присвоюються значення за замовчуванням.\n" +
                "Код не компілюється, якщо локальна змінна не инициализирована явно. Якби питання " +
                "Був про змінних екземпляра (instance variables), то варіанти D і F були б правильними. Логічний примітив " +
                "За замовчуванням - false, а примітив з плаваючою комою за замовчуванням - 0.0.");
        return new Task(7, 1, topic, question, taskStr, answerListMap, false, hint);

    }

    public Task topic1task8() {
        List<String> topic = new ArrayList<>();
        //ru
        topic.add("Синтаксис Java. Типы данных, переменные, системы счисления, вывод данных в консоль.");
        //us
        topic.add("Java syntax. Data types, variables, number systems, data output to the console.");
        //uk
        topic.add("Синтаксис Java. Типи даних, змінні, системи числення, вивід даних в консоль.");
        List<String> question = new ArrayList<>();
        //ru
        question.add("Что из следующего верно? \n(Выберите все, что подходит)");
        //us
        question.add("Which of the following are true? \n(Choose all that apply)");
        //uk
        question.add("Що з переліченого є правдою? \n(Виберіть все, що підходить)");

        String taskStr =
                "";

        //ru
        List<Map<String, Boolean>> answerListMap = new ArrayList<>();
        Map<String, Boolean> answer1 = new LinkedHashMap<>();
        answer1.put("A.  Instance переменная типа boolean по умолчанию имеет значение false.", true);
        answer1.put("B.  Instance переменная типа boolean по умолчанию имеет значение true.", false);
        answer1.put("C.  Instance переменная типа boolean по умолчанию имеет значение null.", false);
        answer1.put("D.  Instance переменная типа int по умолчанию имеет значение 0.", true);
        answer1.put("E.  Instance переменная типа int по умолчанию имеет значение 0.0.", false);
        answer1.put("F.  Instance переменная типа int по умолчанию имеет значение null.", false);
        answer1.put("G.  Ни один вариант из вышеперечисленных.", false);
        answerListMap.add(answer1);

        //us
        Map<String, Boolean> answer2 = new LinkedHashMap<>();
        answer2.put("A.  An instance variable of type boolean defaults to false.", true);
        answer2.put("B.  An instance variable of type boolean defaults to true.", false);
        answer2.put("C.  An instance variable of type boolean defaults to null.", false);
        answer2.put("D.  An instance variable of type int defaults to 0.", true);
        answer2.put("E.  An instance variable of type int defaults to 0.0.", false);
        answer2.put("F.  An instance variable of type int defaults to null.", false);
        answer2.put("G.  None of the above.", false);
        answerListMap.add(answer2);

        //uk
        Map<String, Boolean> answer3 = new LinkedHashMap<>();
        answer3.put("A.  Instance змінна типу boolean за замовчуванням має значення false.", true);
        answer3.put("B.  Instance змінна типу boolean за замовчуванням має значення true.", false);
        answer3.put("C.  Instance змінна типу boolean за замовчуванням має значення null.", false);
        answer3.put("D.  Instance змінна типу int за замовчуванням має значення 0.", true);
        answer3.put("E.  Instance змінна типу int за замовчуванням має значення 0.0.", false);
        answer3.put("F.  Instance змінна типу int за замовчуванням має значення null.", false);
        answer3.put("G.  Жоден варіант з вищезазначених.", false);
        answerListMap.add(answer3);

        List<String> hint = new ArrayList<>();
        //ru
        hint.add("A, D.\n" +
                "Варианты A и D верны, поскольку логические примитивы по умолчанию имеют значение false, а примитивы int по умолчанию имеют значение 0.");
        //us
        hint.add("A, D.\n" +
                "Options A and D are correct because boolean primitives default to false and " +
                "int primitives default to 0.");
        //uk
        hint.add("A, D. Варіанти A і D правильні, оскільки логічні примітиви за замовчуванням мають значення false, а примітиви int - за замовчуванням 0.");
        return new Task(8, 1, topic, question, taskStr, answerListMap, false, hint);

    }

    public Task topic1task9() {
        List<String> topic = new ArrayList<>();
        //ru
        topic.add("Синтаксис Java. Типы данных, переменные, системы счисления, вывод данных в консоль.");
        //us
        topic.add("Java syntax. Data types, variables, number systems, data output to the console.");
        //uk
        topic.add("Синтаксис Java. Типи даних, змінні, системи числення, вивід даних в консоль.");
        List<String> question = new ArrayList<>();
        //ru
        question.add("Дано следующий класс в файле /my/directory/named/A/Bird.java. Какой из ответов заменяет INSERT CODE HERE, " +
                "если мы компилируем из каталога /my/directory\n" +
                "(Выберите все, что подходит)");
        //us
        question.add("Which of the following replaces INSERT CODE HERE if we compile from /my/directory?\n" +
                "(Choose all that apply)");
        //uk
        question.add("Дано наступний клас у файлі /my/directory/named/A/Bird.java.\n" +
                "Яка з відповідей замінює INSERT CODE HERE, якщо ми компілюємо з каталогу /my/directory" +
                "\n(Виберіть все, що підходить)");

        String taskStr =
                "INSERT CODE HERE\n" +
                "public class Bird { }";

        //ru
        List<Map<String, Boolean>> answerListMap = new ArrayList<>();
        Map<String, Boolean> answer1 = new LinkedHashMap<>();
        answer1.put("A.  package my.directory.named.a;", false);
        answer1.put("B.  package my.directory.named.A;", false);
        answer1.put("C.  package named.a;", false);
        answer1.put("D.  package named.A;", true);
        answer1.put("E.  package a;", false);
        answer1.put("F.  package A;", false);
        answer1.put("G.  Не компилируется.", false);
        answerListMap.add(answer1);

        //us
        Map<String, Boolean> answer2 = new LinkedHashMap<>();
        answer2.put("A.  package my.directory.named.a;", false);
        answer2.put("B.  package my.directory.named.A;", false);
        answer2.put("C.  package named.a;", false);
        answer2.put("D.  package named.A;", true);
        answer2.put("E.  package a;", false);
        answer2.put("F.  package A;", false);
        answer2.put("G.  Does not compile.", false);
        answerListMap.add(answer2);

        //uk
        Map<String, Boolean> answer3 = new LinkedHashMap<>();
        answer3.put("A.  package my.directory.named.a;", false);
        answer3.put("B.  package my.directory.named.A;", false);
        answer3.put("C.  package named.a;", false);
        answer3.put("D.  package named.A;", true);
        answer3.put("E.  package a;", false);
        answer3.put("F.  package A;", false);
        answer3.put("G.  Не компілюється.", false);
        answerListMap.add(answer3);

        List<String> hint = new ArrayList<>();
        //ru
        hint.add("D.\n" +
                "Имя пакета представляет собой любые папки под текущим путем, в данном случае - named.A. " +
                "Имена пакетов чувствительны к регистру, как имена переменных и другие идентификаторы.");
        //us
        hint.add("D. The package name represents any folders underneath the current path, which is named.A in this case. " +
                "Package names are case sensitive, just like variable names and other identifiers.");
        //uk
        hint.add("D.\n" +
                "Ім'я пакета представляє будь-які папки під поточним шляхом, в даному випадку - named.A. " +
                "Імена пакетів чутливі до регістру, як імена змінних та інші ідентифікатори.");
        return new Task(9, 1, topic, question, taskStr, answerListMap, false, hint);

    }

    public Task topic1task10() {
        List<String> topic = new ArrayList<>();
        //ru
        topic.add("Синтаксис Java. Типы данных, переменные, системы счисления, вывод данных в консоль.");
        //us
        topic.add("Java syntax. Data types, variables, number systems, data output to the console.");
        //uk
        topic.add("Синтаксис Java. Типи даних, змінні, системи числення, вивід даних в консоль.");
        List<String> question = new ArrayList<>();
        //ru
        question.add("Какие из следующих строк кода компилируются? \n(Выберите все, что подходит)");
        //us
        question.add("Which of the following lines of code compile? \n(Choose all that apply)");
        //uk
        question.add("Які з наступних рядків коду компілюються? \n(Виберіть всі, що підходять)");

        String taskStr =
                "";

        //ru
        List<Map<String, Boolean>> answerListMap = new ArrayList<>();
        Map<String, Boolean> answer1 = new LinkedHashMap<>();
        answer1.put("A.  int i1 = 1_234;", true);
        answer1.put("B.  double d1 = 1_234_.0;", false);
        answer1.put("C.  double d2 = 1_234._0;", false);
        answer1.put("D.  double d3 = 1_234.0_;", false);
        answer1.put("E.  double d4 = 1_234.0;", true);
        answer1.put("F.  Ни один из перечисленных.", false);
        answerListMap.add(answer1);

        //us
        Map<String, Boolean> answer2 = new LinkedHashMap<>();
        answer2.put("A.  int i1 = 1_234;", true);
        answer2.put("B.  double d1 = 1_234_.0;", false);
        answer2.put("C.  double d2 = 1_234._0;", false);
        answer2.put("D.  double d3 = 1_234.0_;", false);
        answer2.put("E.  double d4 = 1_234.0;", true);
        answer2.put("F.  None of the above.", false);
        answerListMap.add(answer2);

        //uk
        Map<String, Boolean> answer3 = new LinkedHashMap<>();
        answer3.put("A.  int i1 = 1_234;", true);
        answer3.put("B.  double d1 = 1_234_.0;", false);
        answer3.put("C.  double d2 = 1_234._0;", false);
        answer3.put("D.  double d3 = 1_234.0_;", false);
        answer3.put("E.  double d4 = 1_234.0;", true);
        answer3.put("F.  Жоден з перерахованих.", false);
        answerListMap.add(answer3);

        List<String> hint = new ArrayList<>();
        //ru
        hint.add("A, E.\n" +
                "Подчеркивания разрешены, если они находятся непосредственно между двумя другими цифрами. " +
                "Это означает, что варианты A и E верны.\n" +
                "Варианты B и C неверны, потому что подчеркивание находится рядом с десятичной точкой.\n" +
                "Вариант D неверен, потому что нижнее подчеркивание - последний символ.");
        //us
        hint.add("A, E.\n" +
                "Underscores are allowed as long as they are directly between two other digits. " +
                "This means options A and E are correct.\n" +
                "Options B and C are incorrect because the underscore is adjacent to the decimal point.\n" +
                "Option D is incorrect because the underscore is the last character.");
        //uk
        hint.add("A, E.\n" +
                "Підкреслення дозволяються, якщо вони знаходяться безпосередньо між двома іншими цифрами. " +
                "Це означає, що варіанти A та E правильні.\n" +
                "Варіанти B і C неправильні, оскільки символ підкреслення примикає до десяткової коми.\n" +
                "Варіант D неправильний, оскільки символ підкреслення - останній символ.");
        return new Task(10, 1, topic, question, taskStr, answerListMap, false, hint);

    }

    public Task topic1task11() {
        List<String> topic = new ArrayList<>();
        //ru
        topic.add("Синтаксис Java. Типы данных, переменные, системы счисления, вывод данных в консоль.");
        //us
        topic.add("Java syntax. Data types, variables, number systems, data output to the console.");
        //uk
        topic.add("Синтаксис Java. Типи даних, змінні, системи числення, вивід даних в консоль.");
        List<String> question = new ArrayList<>();
        //ru
        question.add("Учитывая следующий класс, какая из строк кода может заменить INSERT CODE HERE, " +
                "чтобы код скомпилировался? (Выберите все, что подходит)");
        //us
        question.add("Given the following class, which of the following lines of code can replace " +
                "INSERT CODE HERE to make the code compile? \n(Choose all that apply)");
        //uk
        question.add("Враховуючи наступний клас, який із вказаних рядків коду може замінити INSERT CODE HERE, щоб код компілювався?\n" +
                "(Виберіть все, що підходить)");

        String taskStr =
                "public class Price {\n" +
                "      public void admission() {\n" +
                "                INSERT CODE HERE\n" +
                "             System.out.println(amount);\n" +
                "      } }";

        //ru
        List<Map<String, Boolean>> answerListMap = new ArrayList<>();
        Map<String, Boolean> answer1 = new LinkedHashMap<>();
        answer1.put("A.  int amount = 9L;", false);
        answer1.put("B.  int amount = 0b101;", true);
        answer1.put("C.  int amount = 0xE;", true);
        answer1.put("D.  double amount = 0xE;", true);
        answer1.put("E.  double amount = 1_2_.0_0;", false);
        answer1.put("F.  int amount = 1_2_;", false);
        answer1.put("G.  Ни один из перечисленных.", false);
        answerListMap.add(answer1);

        //us
        Map<String, Boolean> answer2 = new LinkedHashMap<>();
        answer2.put("A.  int amount = 9L;", false);
        answer2.put("B.  int amount = 0b101;", true);
        answer2.put("C.  int amount = 0xE;", true);
        answer2.put("D.  double amount = 0xE;", true);
        answer2.put("E.  double amount = 1_2_.0_0;", false);
        answer2.put("F.  int amount = 1_2_;", false);
        answer2.put("G.  None of the above.", false);
        answerListMap.add(answer2);

        //uk
        Map<String, Boolean> answer3 = new LinkedHashMap<>();
        answer3.put("A.  int amount = 9L;", false);
        answer3.put("B.  int amount = 0b101;", true);
        answer3.put("C.  int amount = 0xE;", true);
        answer3.put("D.  double amount = 0xE;", true);
        answer3.put("E.  double amount = 1_2_.0_0;", false);
        answer3.put("F.  int amount = 1_2_;", false);
        answer3.put("G.  Жоден з перерахованих.", false);
        answerListMap.add(answer3);

        List<String> hint = new ArrayList<>();
        //ru
        hint.add("B, C, D.\n" +
                "0b является префиксом двоичного значения и является правильным.\n" +
                "0x - это префикс шестнадцатеричного значения. Это значение может быть присвоено " +
                "многим примитивным типам, включая int и double, что делает параметры C и D правильными.\n" +
                "Вариант А неверен, потому что 9L - значение типа long. long amount = 9L будет разрешено.\n" +
                "Вариант E неверен, потому что подчеркивание находится непосредственно перед точкой.\n" +
                "Вариант F неверен, потому что нижнее подчеркивание - самый последний символ.");
        //us
        hint.add("B, C, D.\n" +
                "0b is the prefix for a binary value and is correct. " +
                "0x is the prefix for a hexadecimal value. This value can be assigned " +
                "to many primitive types, including int and double, making options C and D correct.\n" +
                "Option A is incorrect because 9L is a long value. long amount = 9L would be allowed.\n" +
                "Option E is incorrect because the underscore is immediately before the decimal.\n" +
                "Option F is incorrect because the underscore is the very last character.\n");
        //uk
        hint.add("B, C, D. 0b є префіксом двійкового значення і є правильним.\n" +
                "0x - префікс для шістнадцяткового значення. Це значення можна присвоїти багатьом " +
                "примітивним типам, включаючи int та double, що робить варіанти C та D правильними.\n" +
                "Варіант A неправильний, оскільки 9L - значення типу long. long amount = 9L дозволений запис.\n" +
                "Варіант Е неправильний, оскільки символ нижнього підкреслення знаходиться безпосередньо перед крапкою.\n" +
                "Варіант F неправильний, оскільки підкреслення - це останній символ.");
        return new Task(11, 1, topic, question, taskStr, answerListMap, false, hint);

    }

    public Task topic3task1() {
//        1: public class WaterBottle {
//        2: private String brand;
//        3: private boolean empty;
//        4: public static void main(String[] args) {
//        5: WaterBottle wb = new WaterBottle();
//        6: System.out.print("Empty = " + wb.empty);
//        7: System.out.print(", Brand = " + wb.brand);
//        8:  } }


//        public class WaterBottle {
//            private String brand;
//            private boolean empty;
//            public static void main(String[] args) {
//                WaterBottle wb = new WaterBottle();
//                System.out.print("Empty = " + wb.empty);
//                System.out.print(", Brand = " + wb.brand);
//            } }

        List<String> topic = new ArrayList<>();
        topic.add("Классы и объекты, ссылочные типы данных");
        topic.add("Classes and objects, reference data types.");
        topic.add("Класи і об'єкти, посилальні типи даних");
        List<String> question = new ArrayList<>();
        question.add("Какой результат данной программы?");
        question.add("What is the output of the following program?");
        question.add("Який результат має наступна програма?");

        String taskStr =
                "1: public class WaterBottle {\n" +
                        "2: \tprivate String brand;\n" +
                        "3: \tprivate boolean empty;\n" +
                        "4: \tpublic static void main(String[] args) {\n" +
                        "5: \t\tWaterBottle wb = new WaterBottle();\n" +
                        "6: \t\tSystem.out.print(\"Empty = \" + wb.empty);\n" +
                        "7: \t\tSystem.out.print(\", Brand = \" + wb.brand);\n" +
                        "8:  } }";

        List<Map<String, Boolean>> answerListMap = new ArrayList<>();
        Map<String, Boolean> answer1 = new LinkedHashMap<>();
        answer1.put("A.  Строка 6 генерирует ошибку компилятора.", false);
        answer1.put("B.  Строка 7 генерирует ошибку компилятора.", false);
        answer1.put("C.  На консоль ничего не выводится.", false);
        answer1.put("D.  Empty = false, Brand = null", true);
        answer1.put("E.  Empty = false, Brand =", false);
        answer1.put("F.  Empty = null, Brand = null", false);
        answerListMap.add(answer1);

        Map<String, Boolean> answer2 = new LinkedHashMap<>();
        answer2.put("A.  Line 6 generates a compiler error.", false);
        answer2.put("B.  Line 7 generates a compiler error.", false);
        answer2.put("C.  There is no output.", false);
        answer2.put("D.  Empty = false, Brand = null", true);
        answer2.put("E.  Empty = false, Brand =", false);
        answer2.put("F.  Empty = null, Brand = null", false);
        answerListMap.add(answer2);


        Map<String, Boolean> answer3 = new LinkedHashMap<>();
        answer3.put("A.  Рядок 6 генерує помилку компілятора.", false);
        answer3.put("B.  Рядок 7 генерує помилку компілятора.", false);
        answer3.put("C.  На консоль нічого не виводиться.", false);
        answer3.put("D.  Empty = false, Brand = null", true);
        answer3.put("E.  Empty = false, Brand =", false);
        answer3.put("F.  Empty = null, Brand = null", false);
        answerListMap.add(answer3);


        List<String> hint = new ArrayList<>();
        hint.add("D. Логические поля инициализируются значением false, а ссылки инициализируются значением null, поэтому empty равно false " +
                "и brand равен null. Выводится: Empty = false, Brand = null.");
        hint.add("D. Boolean fields initialize to false and references initialize to null, so empty is false " +
                "and brand is null. Empty = false, Brand = null is output.");
        hint.add("D. Логичні поля ініціалізуються значенням false, а посилання ініціалізуються значенням null, тому empty дорівнює false,а " +
                "brand дорівнює null. Виводиться: Empty = false, Brand = null.");

        return new Task(1, 3, topic, question, taskStr, answerListMap, false, hint);

    }

    public Task topic3task2() {

        List<String> topic = new ArrayList<>();
        topic.add("Классы и объекты, ссылочные типы данных");
        topic.add("Classes and objects, reference data types.");
        topic.add("Класи і об'єкти, посилальні типи даних");
        List<String> question = new ArrayList<>();
        question.add("Что из следующего верно для указанного класса? \n(Выберите все, что подходит)");
        question.add("Given the following class, which of the following is true? \n(Choose all that apply)");
        question.add("З огляду на наступний клас, що з наведеного істинно? \n(Виберіть все, що вірно)");

        String taskStr =
                " 1: public class Snake {\n" +
                        " 2:  \n" +
                        " 3:  public void shed(boolean time) {\n" +
                        " 4:\n" +
                        " 5:    if (time) {\n" +
                        " 6:\n" +
                        " 7:    }\n" +
                        " 8:    System.out.println(result);\n" +
                        " 9:\n" +
                        "10:  }\n" +
                        "11: }";

        List<Map<String, Boolean>> answerListMap = new ArrayList<>();
        Map<String, Boolean> answer1 = new LinkedHashMap<>();
        answer1.put("A. Если String result = \"done\"; вставить в строку 2, код будет скомпилирован.", true);
        answer1.put("B. Если String result = \"done\"; вставить в строку 4, код будет скомпилирован.", true);
        answer1.put("C. Если String result = \"done\"; вставить в строку 6, код будет скомпилирован.", false);
        answer1.put("D. Если String result = \"done\"; вставить в строку 9, код будет скомпилирован.", false);
        answer1.put("E. Ни одно из перечисленных выше изменений не приведет к компиляции кода.", false);
        answerListMap.add(answer1);

        Map<String, Boolean> answer2 = new LinkedHashMap<>();
        answer2.put("A. If String result = \"done\"; is inserted on line 2, the code will compile.", true);
        answer2.put("B. If String result = \"done\"; is inserted on line 4, the code will compile.", true);
        answer2.put("C. If String result = \"done\"; is inserted on line 6, the code will compile.", false);
        answer2.put("D. If String result = \"done\"; is inserted on line 9, the code will compile.", false);
        answer2.put("E.  None of the above changes will make the code compile.", false);
        answerListMap.add(answer2);

        Map<String, Boolean> answer3 = new LinkedHashMap<>();
        answer3.put("A. Якщо String result = \"done\"; буде вставлено в рядок 2, код буде скомпільовано.", true);
        answer3.put("B. Якщо String result = \"done\"; буде вставлено в рядок 4, код буде скомпільовано.", true);
        answer3.put("C. Якщо String result = \"done\"; буде вставлено в рядок 6, код буде скомпільовано.", false);
        answer3.put("D. Якщо String result = \"done\"; буде вставлено в рядок 9, код буде скомпільовано.", false);
        answer3.put("E. Жодна із зазначених вище змін не змусить код скомпілюватись.", false);
        answerListMap.add(answer3);

        List<String> hint = new ArrayList<>();
        hint.add("A, B.\n" +
                "Добавление переменной в строку 2 превращает result в instance переменную. Поскольку instance переменные " +
                "существуют в течение всего времени жизни объекта, вариант A правильный.\n" +
                "Вариант B - правильный, потому что добавление переменной в строке 4 делает результат локальной переменной с областью действия всего метода.\n" +
                "Добавление переменной в строку 6 превращает результат в локальную переменную с областью действия 6 и 7 строк. " +
                "Так как строка 8 выходит за пределы области видимости, println не компилируется и вариант C неверен.\n" +
                "Добавление переменной в строку 9 делает результат локальной переменной с областью действия 9 и 10 строк. " +
                "Поскольку строка 8 находится перед объявлением, она не компилируется и вариант D неверен.\n" +
                "Наконец, вариант E неверен, потому что код может быть скомпилирован.");
        hint.add(" A, B.\n" +
                "Adding the variable at line 2 makes result an instance variable. Since instance " +
                "variables are in scope for the entire life of the object, option A is correct. Option B is " +
                "correct because adding the variable at line 4 makes result a local variable with a scope " +
                "of the whole method. Adding the variable at line 6 makes result a local variable with " +
                "a scope of lines 6–7. Since it is out of scope on line 8, the println does not compile and " +
                "option C is incorrect. Adding the variable at line 9 makes result a local variable with " +
                "a scope of lines 9 and 10. Since line 8 is before the declaration, it does not compile and " +
                "option D is incorrect. Finally, option E is incorrect because the code can be made to compile.\n");
        hint.add("A, B.\n" +
                "Додавання змінної в рядок 2 перетворює result в instance змінну. Оскільки instance змінні " +
                "існують протягом усього часу життя об'єкта, варіант A правильний.\n" +
                "Варіант B - правильний, тому що додавання змінної в рядку 4 робить результат локальної змінної з областю дії всього методу.\n" +
                "Додавання змінної в рядок 6 перетворює результат в локальну змінну з областю дії 6 і 7 рядків. " +
                "Так як рядок 8 виходить за межі області видимості, println не компілюється і варіант C невірний.\n" +
                "Додавання змінної в рядок 9 робить результат локальної змінної з областю дії 9 і 10 рядків. " +
                "Оскільки рядок 8 знаходиться перед оголошенням, він не компілюється і варіант D є невірним.\n" +
                "Нарешті, варіант E невірний, тому що код може бути скомпільований.");

        return new Task(2, 3, topic, question, taskStr, answerListMap, false, hint);

    }

    public Task topic6task1() {

        List<String> topic = new ArrayList<>();
        topic.add("Модификаторы видимости. Разновидности переменных и пределы их видимости, «import» и «import static»");
        topic.add("Visibility modifiers. Varieties of variables and limits of their visibility, \"import\" and \"import static\"");
        topic.add("Модифікатори видимості. Різновиди змінних і межі їх видимості, «import» і «import static»");
        List<String> question = new ArrayList<>();
        question.add("Учитывая следующие классы, какие из перечисленных ниже классов могут независимо заменить " +
                "INSERT IMPORTS HERE, чтобы код компилировался? \n(Выберите все, что подходит)");
        question.add("Given the following classes, which of the following can independently replace INSERT IMPORTS HERE" +
                " to make the code compile? \n(Choose all that apply)");
        question.add("Враховуючи наступні класи, які з наведеного можуть самостійно замінити INSERT IMPORTS HERE," +
                " щоб зробити компіляцію коду? \n(Виберіть все, що підходить)");

        String taskStr =
                "package aquarium;\n" +
                        "public class Tank { }\n\n" +
                        "package aquarium.jellies;\n" +
                        "public class Jelly { }\n\n" +
                        "package visitor;\n" +
                        "INSERT IMPORTS HERE\n" +
                        "public class AquariumVisitor {\n" +
                        "  public void admire(Jelly jelly) { } \n}";

        List<Map<String, Boolean>> answerListMap = new ArrayList<>();
        Map<String, Boolean> answer1 = new LinkedHashMap<>();
        answer1.put("A.  import aquarium.*;", false);
        answer1.put("B.  import aquarium.*.Jelly;", false);
        answer1.put("C.  import aquarium.jellies.Jelly;", true);
        answer1.put("D.  import aquarium.jellies.*;", true);
        answer1.put("E.  import aquarium.jellies.Jelly.*;", false);
        answer1.put("F.  Ни один из них не может заставить код компилироваться", false);
        answerListMap.add(answer1);

        Map<String, Boolean> answer2 = new LinkedHashMap<>();
        answer2.put("A.  import aquarium.*;", false);
        answer2.put("B.  import aquarium.*.Jelly;", false);
        answer2.put("C.  import aquarium.jellies.Jelly;", true);
        answer2.put("D.  import aquarium.jellies.*;", true);
        answer2.put("E.  import aquarium.jellies.Jelly.*;", false);
        answer2.put("F.  None of these can make the code compile.", false);
        answerListMap.add(answer2);

        Map<String, Boolean> answer3 = new LinkedHashMap<>();
        answer3.put("A.  import aquarium.*;", false);
        answer3.put("B.  import aquarium.*.Jelly;", false);
        answer3.put("C.  import aquarium.jellies.Jelly;", true);
        answer3.put("D.  import aquarium.jellies.*;", true);
        answer3.put("E.  import aquarium.jellies.Jelly.*;", false);
        answer3.put("F.  Жодний з них не може змусити компілюватись код", false);
        answerListMap.add(answer3);

        List<String> hint = new ArrayList<>();
        hint.add("C, D. \\ n \"+\n" +
                "Вариант C верен, потому что он импортирует Jelly по имени класса.\n" +
                "Вариант D верен, потому что он импортирует все классы из пакета jellies, который включает Jelly.\n" +
                "Вариант А неверен, потому что он импортирует только классы из пакета aquarium - Tank " +
                "в этом случае - а не в пакетах нижнего уровня.\n" +
                "Вариант B неверен, потому что вы не можете использовать подстановочные знаки где-либо, кроме конца оператора импорта.\n" +
                "Вариант E неверен, потому что вы не можете импортировать части класса с помощью обычного оператора импорта.\n" +
                "Вариант F неверен, потому что варианты C и D заставляют код компилироваться.");
        hint.add("C, D.\n" +
                "Option C is correct because it imports Jelly by classname. " +
                "Option D is correct because it imports all the classes in the jellies package, which includes Jelly.\n" +
                "Option A is incorrect because it only imports classes in the aquarium package — Tank " +
                "in this case—and not those in lower-level packages. \n" +
                "Option B is incorrect because you cannot use wildcards anyplace other than the end of an import statement. \n" +
                "Option E is incorrect because you cannot import parts of a class with a regular import statement. \n" +
                "Option F is incorrect because options C and D do make the code compile.");
        hint.add("C, D.\n" +
                "Варіант C правильний, оскільки він імпортує Jelly за назвою класу.\n" +
                "Варіант D правильний, оскільки він імпортує всі класи в пакеті jellies, який включає Jelly.\n" +
                "Варіант A неправильний, оскільки він імпортує лише класи в пакеті aquarium - Tank " +
                "у цьому випадку - а не в пакетах нижчого рівня.\n" +
                "Варіант B неправильний, оскільки ви не можете використовувати символи підстановки в будь-якому місці, крім кінця оператора імпорту.\n" +
                "Варіант Е неправильний, оскільки ви не можете імпортувати частини класу за допомогою звичайного оператора імпорту.\n" +
                "Варіант F неправильний, оскільки параметри C і D дійсно роблять компіляцію коду.");

        return new Task(1, 6, topic, question, taskStr, answerListMap, false, hint);

    }

    public Task topic6task2() {
        List<String> topic = new ArrayList<>();
        //ru
        topic.add("Модификаторы видимости. Разновидности переменных и пределы их видимости, «import» и «import static»");
        //us
        topic.add("Visibility modifiers. Varieties of variables and limits of their visibility, \"import\" and \"import static\"");
        //uk
        topic.add("Модифікатори видимості. Різновиди змінних і межі їх видимості, «import» і «import static»");
        List<String> question = new ArrayList<>();
        //ru
        question.add("Учитывая следующие классы, каково максимальное количество операций импорта, которые можно удалить и код все еще компилируется?");
        //us
        question.add("Given the following classes, what is the maximum number of imports that can be removed " +
                "and have the code still compile?");
        //uk
        question.add("З огляду на наступні класи, якою є максимальна кількість імпортів, які можна видалити, а код все-таки скомпілювати?");

        String taskStr =
                "package aquarium; \n" +
                "public class Water { }\n\n" +
                "package aquarium;\n" +
                "import java.lang.*;\n" +
                "import java.lang.System;\n" +
                "import aquarium.Water;\n" +
                "import aquarium.*;\n\n" +
                "public class Tank {\n" +
                "  public void print(Water water) {\n" +
                "   System.out.println(water); \n} }";

        //ru
        List<Map<String, Boolean>> answerListMap = new ArrayList<>();
        Map<String, Boolean> answer1 = new LinkedHashMap<>();
        answer1.put("A. 0", false);
        answer1.put("B. 1", false);
        answer1.put("C. 2", false);
        answer1.put("D. 3", false);
        answer1.put("E. 4", true);
        answer1.put("F. Не компилируется.", false);
        answerListMap.add(answer1);

        //us
        Map<String, Boolean> answer2 = new LinkedHashMap<>();
        answer2.put("A. 0", false);
        answer2.put("B. 1", false);
        answer2.put("C. 2", false);
        answer2.put("D. 3", false);
        answer2.put("E. 4", true);
        answer2.put("F.  Does not compile.", false);
        answerListMap.add(answer2);

        //uk
        Map<String, Boolean> answer3 = new LinkedHashMap<>();
        answer3.put("A. 0", false);
        answer3.put("B. 1", false);
        answer3.put("C. 2", false);
        answer3.put("D. 3", false);
        answer3.put("E. 4", true);
        answer3.put("F. Не компилируется.", false);
        answerListMap.add(answer3);

        List<String> hint = new ArrayList<>();
        //ru
        hint.add("E.\n" +
                "Первые два импорта могут быть удалены, поскольку java.lang импортируется автоматически.\n" +
                "Вторые два импорта могут быть удалены, потому что Tank и Water находятся в одном пакете, что дает правильный ответ E.\n" +
                "Если бы Tank и Water были в разных пакетах, тогда один из этих двух импортов мог быть удален. В этом случае ответом был бы вариант D.");
        //us
        hint.add("E.\n " +
                "The first two imports can be removed because java.lang is automatically imported.\n" +
                "The second two imports can be removed because Tank and Water are in the same package, making the correct answer E.\n" +
                "If Tank and Water were in different packages, one of these two imports could be removed. In that case, the answer would be option D.");
        //uk
        hint.add("E.\n" +
                "Перші два імпорти можна видалити, оскільки java.lang імпортується автоматично.\n" +
                "Другі два імпорти можна вилучити, оскільки Tank и Water знаходяться в одному пакеті, отже - правильна відповідь E.\n" +
                "Якби Tank and Water були в різних пакетах, тоді один із цих двох імпортів можна було б вилучити. У такому випадку відповіддю був би варіант D.");

        return new Task(2, 6, topic, question, taskStr, answerListMap, false, hint);

    }

    public Task topic6task3() {
        List<String> topic = new ArrayList<>();
        //ru
        topic.add("Модификаторы видимости. Разновидности переменных и пределы их видимости, «import» и «import static»");
        //us
        topic.add("Visibility modifiers. Varieties of variables and limits of their visibility, \"import\" and \"import static\"");
        //uk
        topic.add("Модифікатори видимості. Різновиди змінних і межі їх видимості, «import» і «import static»");
        List<String> question = new ArrayList<>();
        //ru
        question.add("Учитывая следующие классы, какой из следующих фрагментов можно вставить вместо " +
                "INSERT IMPORTS HERE и скомпилировать код? \n(Выберите все варианты)");
        //us
        question.add("Given the following classes, which of the following snippets can be inserted in place of " +
                "INSERT IMPORTS HERE and have the code compile? \n(Choose all that apply)");
        //uk
        question.add("Враховуючи наступні класи, який із наведених нижче фрагментів можна вставити замість " +
                "INSERT IMPORTS HERE і зкомпілювати код? (Виберіть всі варіанти)");

        String taskStr =
                "package aquarium;\n" +
                        "public class Water {\n" +
                        "  boolean salty = false;\n" +
                        "}\n\n" +
                        "package aquarium.jellies;\n" +
                        "public class Water {\n" +
                        "  boolean salty = true;\n" +
                        "}\n\n" +
                        "package employee;\n" +
                        "INSERT IMPORTS HERE\n" +
                        "public class WaterFiller {\n" +
                        "  Water water;\n" +
                        "}";

        //ru
        List<Map<String, Boolean>> answerListMap = new ArrayList<>();
        Map<String, Boolean> answer1 = new LinkedHashMap<>();
        answer1.put("A.  import aquarium.*;", true);
        answer1.put("B.  import aquarium.Water;\n" +
                "import aquarium.jellies.*;", true);
        answer1.put("C.  import aquarium.*;\n" +
                "import aquarium.jellies.Water;", true);
        answer1.put("D.  import aquarium.*;\n" +
                "import aquarium.jellies.*;", false);
        answer1.put("E.  import aquarium.Water;\n" +
                "import aquarium.jellies.Water;", false);
        answer1.put("F.  Ни одна из этих операций импорта не может привести к компиляции кода.", false);
        answerListMap.add(answer1);

        //us
        Map<String, Boolean> answer2 = new LinkedHashMap<>();
        answer2.put("A.  import aquarium.*;", true);
        answer2.put("B.  import aquarium.Water;\n" +
                "import aquarium.jellies.*;", true);
        answer2.put("C.  import aquarium.*;\n" +
                "import aquarium.jellies.Water;", true);
        answer2.put("D.  import aquarium.*;\n" +
                "import aquarium.jellies.*;", false);
        answer2.put("E.  import aquarium.Water;\n" +
                "import aquarium.jellies.Water;", false);
        answer2.put("F.  None of these imports can make the code compile.", false);
        answerListMap.add(answer2);

        //uk
        Map<String, Boolean> answer3 = new LinkedHashMap<>();
        answer3.put("A.  import aquarium.*;", true);
        answer3.put("B.  import aquarium.Water;\n" +
                "import aquarium.jellies.*;", true);
        answer3.put(" C.  import aquarium.*;\n" +
                "import aquarium.jellies.Water;", true);
        answer3.put("D.  import aquarium.*;\n" +
                "import aquarium.jellies.*;", false);
        answer3.put("E.  import aquarium.Water;\n" +
                "import aquarium.jellies.Water;", false);
        answer3.put("F.  Жоден із цих імпортів не може змусити компілюватись код.", false);
        answerListMap.add(answer3);

        List<String> hint = new ArrayList<>();
        //ru
        hint.add("A, B, C.\n" +
                "Вариант A правильный, поскольку он импортирует все классы в aquarium package, включая aquarium.Water.\n" +
                "Варианты B и C правильные, поскольку они импортируют Water по названию класса. " +
                "Поскольку импорт по названию класса имеет преимущество над символами подстановки, они компилируются.\n" +
                "Вариант D неправильный, поскольку Java не знает, какой из двух подстановочных классов Water использовать.\n" +
                "Вариант E неправильный, поскольку вы не можете указать одно и то же имя класса в двух импортах.");
        //us
        hint.add("A, B, C.\n" +
                "Option A is correct because it imports all the classes in the aquarium package including aquarium.Water.\n" +
                "Options B and C are correct because they import Water by classname. " +
                "Since importing by classname takes precedence over wildcards, these compile.\n" +
                "Option D is incorrect because Java doesn’t know which of the two wildcard Water classes to use.\n" +
                "Option E is incorrect because you cannot specify the same classname in two imports.");
        //uk
        hint.add("A, B, C.\n" +
                "Варіант A правильний, оскільки він імпортує всі класи в aquarium package, включаючи aquarium.Water.\n" +
                "Варіанти B і C правильні, оскільки вони імпортують Water за назвою класу. " +
                "Оскільки імпорт за назвою класу має перевагу над символами підстановки, вони компілюються.\n" +
                "Варіант D неправильний, оскільки Java не знає, який із двох підстановочних класів Water використовувати.\n" +
                "Варіант E неправильний, оскільки ви не можете вказати одне і те ж ім’я класу у двох імпортах.");

        return new Task(3, 6, topic, question, taskStr, answerListMap, false, hint);

    }

    public void uploadListQuantityTasksToFirestore() {
        List<Topic> topicList = new ArrayList<>();
//        topicList.add(new Topic("Синтаксис Java. Типы данных, переменные, системы счисления, вывод данных в консоль"));
//        topicList.add(new Topic("Арифметические и логические операции, операции сравнения и присваивания."));
//        topicList.add(new Topic("Классы и объекты, ссылочные типы данных"));
//        topicList.add(new Topic("Создание объектов, конструкторы. Объявление и вызов методов."));
//        topicList.add(new Topic("Перезагрузка методов и конструкторов."));
//        topicList.add(new Topic("Модификаторы видимости. Разновидности переменных и пределы их видимости, «import» и «import static»"));
//        topicList.add(new Topic("Модификаторы «final» и «static»"));
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