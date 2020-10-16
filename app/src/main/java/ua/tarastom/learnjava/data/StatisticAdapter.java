package ua.tarastom.learnjava.data;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ua.tarastom.learnjava.R;

public class StatisticAdapter extends RecyclerView.Adapter<StatisticAdapter.StatisticViewHolder> {
    private List<List<String>> statisticResult;
    private List<Statistic> allStatistics;
    private MainViewModel mainViewModel;
    private int language;

    public StatisticAdapter(MainViewModel mainViewModel, int language) {
        this.mainViewModel = mainViewModel;
        this.language = language;
    }

    public List<Statistic> getStatisticResult() {
        return allStatistics;
    }

    public void setStatisticResult(List<Statistic> allStatistics) {
        this.allStatistics = allStatistics;
        statisticResult = new ArrayList<>();
        for (Statistic statistic : allStatistics) {
            if (statistic.getQuantitySolvedTasks() > 0) {
                statisticResult.add(statistic.getNameTopic());
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StatisticViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_statistic, parent, false);
        return new StatisticViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatisticViewHolder holder, int position) {
        List<String> nameTopic = statisticResult.get(position);
        if (statisticResult.size() > 0) {
            holder.textResultStat.setText(nameTopic.get(language));
        }
        Statistic temp = new Statistic();
        for (Statistic statistic : allStatistics) {
            if (statistic.getNameTopic().equals(nameTopic)) {
                temp = statistic;
            }
        }
        final Statistic statisticByNameTopic = temp;
        int quantityTasksInTopic = statisticByNameTopic.getQuantityTasksInTopic();
        int quantitySolvedTasks = statisticByNameTopic.getQuantitySolvedTasks();
        int numberOfCorrectlySolvedTasks = statisticByNameTopic.getNumberOfCorrectlySolvedTasks();
        int incorrectSolvedTask = quantitySolvedTasks - numberOfCorrectlySolvedTasks;

        String quantityTasksInTopicStr = getColoredSpanned(String.valueOf(quantityTasksInTopic),
                String.valueOf(ContextCompat.getColor(holder.itemView.getContext(), R.color.colorLabelText)));
        String numberOfCorrectlySolvedTasksStr = getColoredSpanned(String.valueOf(numberOfCorrectlySolvedTasks),
                String.valueOf(ContextCompat.getColor(holder.itemView.getContext(), R.color.colorTextCorrectly)));
        String incorrectSolvedTaskStr = getColoredSpanned(String.valueOf(incorrectSolvedTask),
                String.valueOf(ContextCompat.getColor(holder.itemView.getContext(), R.color.colorError)));

        holder.textViewStat
                .setText(Html.fromHtml(quantityTasksInTopicStr + " / " + numberOfCorrectlySolvedTasksStr + " / " + incorrectSolvedTaskStr));

        holder.buttonClearTopic.setOnClickListener(view -> {
            statisticByNameTopic.setQuantitySolvedTasks(0);  //обнуляю статистику вирішених задач
            statisticByNameTopic.setNumberOfCorrectlySolvedTasks(0); //обнуляю статистику кількості правильно вирішених задач
            statisticByNameTopic.setListOfIncorrectlySolvedProblems(new ArrayList<>()); //обнуляю статистику правильно-неправильно вирішених задач
            mainViewModel.insertStatistic(statisticByNameTopic);
            setStatisticResult(mainViewModel.getAllStatistics());
        });
        //напис на кнопці виходячи з вибору мови
        holder.buttonClearTopic.setText(getResStringLanguage(holder, R.string.button_clear, getLanguageAbbreviation(language)));

        //змінюю колір фону item при завершенні опрацювання всіх завдань
        if (statisticByNameTopic.getQuantityTasksInTopic() == statisticByNameTopic.getQuantitySolvedTasks()) {
            holder.itemView.setBackgroundResource(R.drawable.style_item_topic_finished);
        } else {
            holder.itemView.setBackgroundResource(R.drawable.style_item_topic_in_process);
        }
    }

    private String getColoredSpanned(String text, String color) {
        return String.format("<font color=%s>%s</font>", color, text);
    }

    @Override
    public int getItemCount() {
        return statisticResult.size();
    }

    static class StatisticViewHolder extends RecyclerView.ViewHolder {
        private TextView textResultStat;
        private Button buttonClearTopic;
        private TextView textViewStat;

        public StatisticViewHolder(@NonNull View itemView) {
            super(itemView);
            textResultStat = itemView.findViewById(R.id.textResultStat);
            buttonClearTopic = itemView.findViewById(R.id.buttonClearTopic);
            textViewStat = itemView.findViewById(R.id.textViewStat);
            itemView.setOnClickListener(view -> {
            });
        }
    }

    private static String getLanguageAbbreviation(int language) {
        String abbr;
        switch (language) {
            case 0:
                abbr = "ru";
                break;
            case 1:
                abbr = "us";
                break;
            case 2:
                abbr = "uk";
                break;
            default:
                abbr = "us";
        }
        return abbr;
    }

    public static  String getResStringLanguage(StatisticViewHolder holder, int id, String lang){
        //Get default locale to back it
        Resources res = holder.itemView.getResources();
        Configuration conf = res.getConfiguration();
        Locale savedLocale = conf.locale;
        //Retrieve resources from desired locale
        Configuration confAr = holder.itemView.getResources().getConfiguration();
        confAr.locale = new Locale(lang);
        DisplayMetrics metrics = new DisplayMetrics();
        Resources resources = new Resources(holder.itemView.getResources().getAssets(), metrics, confAr);
        //Get string which you want
        String string = resources.getString(id);
        //Restore default locale
        conf.locale = savedLocale;
        res.updateConfiguration(conf, null);
        //return the string that you want
        return string;
    }
}
