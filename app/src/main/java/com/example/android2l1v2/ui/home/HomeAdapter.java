package com.example.android2l1v2.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android2l1v2.R;
import com.example.android2l1v2.TaskModel;
import com.example.android2l1v2.ui.OnClickInterface;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter <HomeAdapter.ViewHolder> {
    public OnClickInterface onClickInterface;
    private ArrayList<TaskModel> list = new ArrayList<>();

    public void addModel(TaskModel model, OnClickInterface onClickInterface) {
        this.onClickInterface = onClickInterface;
        list.add(model);
        notifyDataSetChanged();
    }

    public void addListOfModel(List<TaskModel>list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item,parent,false);
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

    public void filteredList(ArrayList<TaskModel>filteredList){
        list = filteredList;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, desc;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_item);
            desc = itemView.findViewById(R.id.desc_item);
        }

        public void onBind(TaskModel model) {
          /*  itemView.setOnClickListener(v -> {
                onClickInterface.onItemClick(getAdapterPosition(), model);
            });*/
            title.setText(model.getTitle());
            desc.setText(model.getDesc());
        }
    }
}