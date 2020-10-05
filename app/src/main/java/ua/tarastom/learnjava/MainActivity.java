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

import ua.tarastom.learnjava.data.Task;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        db = FirebaseFirestore.getInstance();

//        uploadDataToFirestore();
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
}