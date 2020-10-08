package ua.tarastom.learnjava.data;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;

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

}
