package com.example.piyush.todolist.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.piyush.todolist.MainActivity;
import com.example.piyush.todolist.Model.ToDo;
import com.example.piyush.todolist.R;

import java.util.List;

class ListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener {

    ItemClickListener itemClickListener;
    TextView item_title,item_description;


    public ListItemViewHolder(View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this);

        item_title=itemView.findViewById(R.id.item_title);
        item_description=itemView.findViewById(R.id.item_description);


    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Select the Action");
        menu.add(0,0,getAdapterPosition(),"DELETE");

    }
}

public class ListItemAdapter extends RecyclerView.Adapter<ListItemViewHolder> {

    MainActivity mainActivity;
    List<ToDo> toDoList;

    public ListItemAdapter(MainActivity mainActivity, List<ToDo> toDoList) {
        this.mainActivity = mainActivity;
        this.toDoList = toDoList;
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mainActivity.getBaseContext());
        View view=inflater.inflate(R.layout.list_item,parent,false);
        return new ListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {

        holder.item_title.setText(toDoList.get(position).getTitle());
        holder.item_description.setText(toDoList.get(position).getDescription());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                mainActivity.title.setText(toDoList.get(position).getTitle());
                mainActivity.description.setText(toDoList.get(position).getDescription());

                mainActivity.isUpdated=true;
                mainActivity.idUpdate=toDoList.get(position).getId();
            }
        });
    }

    @Override
    public int getItemCount() {
        return toDoList.size();
    }
}
