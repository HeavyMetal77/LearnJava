package ua.tarastom.learnjava.data;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ua.tarastom.learnjava.R;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {
    private List<Topic> topicList;
    private OnTopicClickListener onTopicClickListener;
    private List<Statistic> statisticList;
    private int language;

    public TopicAdapter(int language) {
        this.language = language;
        topicList = new ArrayList<>();
        statisticList = new ArrayList<>();
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
        notifyDataSetChanged();
    }

    public List<Statistic> getStatisticList() {
        return statisticList;
    }

    public void setStatisticList(List<Statistic> statisticList) {
        this.statisticList = statisticList;
    }

    public void setOnTopicClickListener(OnTopicClickListener onTopicClickListener) {
        this.onTopicClickListener = onTopicClickListener;
    }

    public interface OnTopicClickListener {
        void onTopicClick(int position);
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_topic, parent, false);
        return new TopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {
        Topic topic = topicList.get(position);
        holder.textViewTopicCategory.setText(topic.getNameTopic().get(language));
        Statistic statisticsById = null;
        int quantitySolvedTasks = 0;
        int numberOfCorrectlySolvedTasks = 0;
        if (statisticList.size() - 1 >= position) {
            statisticsById = statisticList.get(position);
            quantitySolvedTasks = statisticsById.getQuantitySolvedTasks();
            numberOfCorrectlySolvedTasks = statisticsById.getNumberOfCorrectlySolvedTasks();
        }
        int quantityTasksInTopic = topic.getQuantityTasksInTopic();
        int incorrectSolvedTask = quantitySolvedTasks - numberOfCorrectlySolvedTasks;

        String quantitySolvedTasksStr = getColoredSpanned(String.valueOf(quantitySolvedTasks),
                String.valueOf(ContextCompat.getColor(holder.itemView.getContext(), R.color.colorLabelText)));
        String numberOfCorrectlySolvedTasksStr = getColoredSpanned(String.valueOf(numberOfCorrectlySolvedTasks),
                String.valueOf(ContextCompat.getColor(holder.itemView.getContext(), R.color.colorTextCorrectly)));
        String incorrectSolvedTaskStr = getColoredSpanned(String.valueOf(incorrectSolvedTask),
                String.valueOf(ContextCompat.getColor(holder.itemView.getContext(), R.color.colorError)));

        holder.textViewQuantityTasksInTopic.setText(String.valueOf(quantityTasksInTopic));
        holder.textViewSolvedProblem
                .setText(Html.fromHtml(quantitySolvedTasksStr + "/" + numberOfCorrectlySolvedTasksStr + "/" + incorrectSolvedTaskStr));

        //змінюю колір фону item при завершенні опрацювання всіх завдань
        if (statisticsById != null && topic.getQuantityTasksInTopic() == statisticsById.getQuantitySolvedTasks()) {
            holder.itemView.setBackgroundResource(R.drawable.style_item_topic_finished);
        }
    }

    private String getColoredSpanned(String text, String color) {
        return String.format("<font color=%s>%s</font>", color, text);
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    class TopicViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTopicCategory;
        private TextView textViewQuantityTasksInTopic;
        private TextView textViewSolvedProblem;

        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTopicCategory = itemView.findViewById(R.id.textViewTopicCategory);
            textViewQuantityTasksInTopic = itemView.findViewById(R.id.textViewQuantityTasksInTopic);
            textViewSolvedProblem = itemView.findViewById(R.id.textViewSolvedProblem);
            itemView.setOnClickListener(view -> {
                if (onTopicClickListener != null) {
                    onTopicClickListener.onTopicClick(getAdapterPosition());
                }
            });
        }
    }
}
