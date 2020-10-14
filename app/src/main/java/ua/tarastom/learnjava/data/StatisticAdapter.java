package ua.tarastom.learnjava.data;

import android.text.Html;
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

import ua.tarastom.learnjava.R;

public class StatisticAdapter extends RecyclerView.Adapter<StatisticAdapter.StatisticViewHolder> {
    private List<String> statisticResult;
    private List<Statistic> allStatistics;
    private MainViewModel mainViewModel;

    public StatisticAdapter(MainViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
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
        String nameTopic = statisticResult.get(position);
        if (statisticResult.size() > 0) {
            holder.textResultStat.setText(nameTopic);
        }
        Statistic statisticsById = mainViewModel.getStatisticByNameTopic(nameTopic);
        int quantityTasksInTopic = statisticsById.getQuantityTasksInTopic();
        int quantitySolvedTasks = statisticsById.getQuantitySolvedTasks();
        int numberOfCorrectlySolvedTasks = statisticsById.getNumberOfCorrectlySolvedTasks();
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
            statisticsById.setQuantitySolvedTasks(0);  //обнуляю статистику вирішених задач
            statisticsById.setNumberOfCorrectlySolvedTasks(0); //обнуляю статистику кількості правильно вирішених задач
            statisticsById.setListOfIncorrectlySolvedProblems(new ArrayList<>()); //обнуляю статистику правильно-неправильно вирішених задач
            mainViewModel.insertStatistic(statisticsById);
            setStatisticResult(mainViewModel.getAllStatistics());
        });
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
            textResultStat = itemView.findViewById(R.id.editTextResultStat);
            buttonClearTopic = itemView.findViewById(R.id.buttonClearTopic);
            textViewStat = itemView.findViewById(R.id.textViewStat);
            itemView.setOnClickListener(view -> {
            });
        }
    }
}
