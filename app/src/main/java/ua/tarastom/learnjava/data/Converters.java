package ua.tarastom.learnjava.data;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Converters {

    @TypeConverter
    public String fromArrayListIntToString(List<Integer> listOfIncorrectlySolvedProblems) {
        if (listOfIncorrectlySolvedProblems != null && listOfIncorrectlySolvedProblems.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (Integer problem : listOfIncorrectlySolvedProblems) {
                sb.append(problem).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        } else {
            return null;
        }
    }

    @TypeConverter
    public List<Integer> fromStringToArrayListInt(String data) {
        if (data != null && data.length() > 0) {
            String[] stringList = data.split(",");
            List<Integer> integerList = new ArrayList<>();
            for (String string : stringList) {
                integerList.add(Integer.parseInt(string));
            }
            return integerList;
        } else {
            return null;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @TypeConverter
    public String fromArrayList(List<String> allAnswersList) {
        return allAnswersList.stream().collect(Collectors.joining("-"));
    }

    @TypeConverter
    public List<String> fromString(String data) {
        return Arrays.asList(data.split("-"));
    }

}
