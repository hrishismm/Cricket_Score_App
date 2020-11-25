package com.example.cricketlivescore;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder>{

private List<Model1> modelList;
private Context context;

    public MyAdapter2(List<Model1> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row1,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
 final Model1 model=modelList.get(position);
 holder.author.setText(model.getAuthor());

        holder.title.setText(model.getTitle());
        holder.description.setText(model.getDescription());
        holder.url.setText(model.getUrl());
        holder.content.setText(model.getContent());

        

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
 {

     TextView author,title,description,url,content;
CardView cardView;


     public ViewHolder(@NonNull View itemView) {
         super(itemView);
         author=itemView.findViewById(R.id.authorTv);
         title=itemView.findViewById(R.id.titleTv);
         description=itemView.findViewById(R.id.descriptionTv);
          url=itemView.findViewById(R.id.urlTv);
         content=itemView.findViewById(R.id.contentTv);
         cardView=itemView.findViewById(R.id.cardView);


     }

 }
}
