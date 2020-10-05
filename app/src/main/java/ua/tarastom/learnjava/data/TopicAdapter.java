package ua.tarastom.learnjava.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ua.tarastom.learnjava.R;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {

    private List<Topic> topicList;
    private OnTopicClickListener onTopicClickListener;

    public TopicAdapter() {
        topicList = new ArrayList<>();
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
        notifyDataSetChanged();
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
        holder.textViewTopicCategory.setText(topic.getNameTopic());
        holder.textViewSolvedProblem.setText(String.valueOf(topic.getQuantityTasksInTopic()));
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    class TopicViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTopicCategory;
        private TextView textViewSolvedProblem;


        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTopicCategory = itemView.findViewById(R.id.textViewTopicCategory);
            textViewSolvedProblem = itemView.findViewById(R.id.textViewSolvedProblem);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onTopicClickListener != null) {
                        onTopicClickListener.onTopicClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
