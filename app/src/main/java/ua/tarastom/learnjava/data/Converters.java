package ua.tarastom.learnjava.data;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Converters {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @TypeConverter
    public String fromArrayList(List<String> allAnswersList) {
        return allAnswersList.stream().collect(Collectors.joining(","));
    }

    @TypeConverter
    public List<String> fromString(String data) {
        return Arrays.asList(data.split(","));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @TypeConverter
    public String fromArrayListBoolean(List<Boolean> rightAnswers) {
        StringBuilder listBooleanInString = new StringBuilder();
        for (Boolean rightAnswer : rightAnswers) {
            listBooleanInString.append(rightAnswer).append(", ");
        }
        listBooleanInString.substring(0, listBooleanInString.length() - 3);
        return listBooleanInString.toString();
    }

    @TypeConverter
    public List<Boolean> fromStringToBoolean(String data) {
        List<String> strings = Arrays.asList(data.split(","));
        List<Boolean> booleanList = new ArrayList<>();
        for (String string : strings) {
            if (string.equals("true")) {
                booleanList.add(true);
            }
            if (string.equals("false")) {
                booleanList.add(false);
            }
        }
        return booleanList;
    }
}
