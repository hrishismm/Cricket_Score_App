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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

private List<Model> modelList;
private Context context;

    public MyAdapter(List<Model> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
 final Model model=modelList.get(position);
 holder.team1Tv.setText(model.getTeam1());

        holder.team2Tv.setText(model.getTeam2());
        holder.matchType.setText(model.getType());
        holder.matchstatus.setText(model.getStatus());
        holder.date.setText(model.getDate());

        
holder.cardView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
 final String  matchid=model.getID();
 final String date=model.getDate();

 String[] options={"Match details","Players List","Match Summary"};
        AlertDialog.Builder builder=new AlertDialog.Builder(view.getRootView().getContext());
        builder.setTitle("Choose Options");
        builder.setItems(options,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
if(i==0)
{
    Intent intent=new Intent(context,MatchDetailActivity.class);
    intent.putExtra("match_id",matchid);
    intent.putExtra("date",date);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(intent);

}
if(i==1)
{
    Intent intent=new Intent(context,PlayerActivity.class);
    intent.putExtra("match_id",matchid);
    //intent.putExtra("date",date);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(intent);

}
                if(i==2)
                {
                    Intent intent=new Intent(context,MatchSummaryActvity.class);
                    intent.putExtra("match_id",matchid);
                    //intent.putExtra("date",date);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }

            }
        });
        builder.create().show();
    }
});

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
 {

     TextView team1Tv,team2Tv,matchType,matchstatus,date;
CardView cardView;


     public ViewHolder(@NonNull View itemView) {
         super(itemView);
         team1Tv=itemView.findViewById(R.id.team1Tv);
         team2Tv=itemView.findViewById(R.id.team2Tv);
         matchType=itemView.findViewById(R.id.matchType);
          matchstatus=itemView.findViewById(R.id.status);
         date=itemView.findViewById(R.id.date);
         cardView=itemView.findViewById(R.id.cardView);


     }

 }
}
