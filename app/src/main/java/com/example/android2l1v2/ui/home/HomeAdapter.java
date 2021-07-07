package com.example.android2l1v2.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android2l1v2.App;
import com.example.android2l1v2.R;
import com.example.android2l1v2.TaskModel;
import com.example.android2l1v2.ui.ChatModel;
import com.example.android2l1v2.ui.OnClickInterface;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    public OnClickInterface onClickInterface;
    private ArrayList<TaskModel> list;
    private ArrayList<TaskModel> filteredData;
    public boolean isList;

    public HomeAdapter(boolean isList, OnClickInterface onClickInterface) {
        this.list = new ArrayList<>();
        this.onClickInterface = onClickInterface;
        this.filteredData = new ArrayList<>();
        this.isList = isList;
    }

    public void addModel(TaskModel model) {
        list.add(model);
        filteredData.add(model);
        notifyDataSetChanged();
    }

    public void deleteModel(int pos) {
        App.getInstance().getTaskDao().deleteTask(list.get(pos));
        list.remove(pos);
        notifyItemRemoved(pos);
    }

    public void updateModel(int pos, TaskModel model) {
        list.set(pos, model);
        notifyItemChanged(pos);
    }

    public void addListOfModel(List<TaskModel> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filteredList(ArrayList<TaskModel> filteredList) {
        list = filteredList;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, desc, time, chat;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            chat = itemView.findViewById(R.id.chat_item_txt);
            title = itemView.findViewById(R.id.title_item);
            desc = itemView.findViewById(R.id.desc_item);
            time = itemView.findViewById(R.id.time_item);
        }

        public void onBind(TaskModel model) {
            time.setText(model.getTime());
            title.setText(model.getTitle());
            desc.setText(model.getDesc());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickInterface.onItemClick(getAdapterPosition(), model);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onClickInterface.onLongItemClick(getAdapterPosition());
                    return false;
                }
            });
        }
    }
}