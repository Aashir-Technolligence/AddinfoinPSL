package com.example.addinfoinpsl;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.ViewHolder> {
    ArrayList<AddPlayerAttr> addPlayerAttrs;
    Activity activity;

    public PlayersAdapter(ArrayList<AddPlayerAttr> addPlayerAttrs, Activity activity) {
        this.addPlayerAttrs = addPlayerAttrs;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.name.setText(addPlayerAttrs.get(position).getName());
        holder.age.setText(addPlayerAttrs.get(position).getAge());
        holder.bestScore.setText( String.valueOf(addPlayerAttrs.get(position).getBestScore()));
        holder.hundred.setText( String.valueOf(addPlayerAttrs.get(position).getHundred()));
        holder.totalscore.setText( String.valueOf(addPlayerAttrs.get(position).getTotalScore()));
        holder.hand.setText(addPlayerAttrs.get(position).getHand());
        holder.bWicket.setText( String.valueOf(addPlayerAttrs.get(position).getBestBWicket()));
        holder.bScore.setText( String.valueOf(addPlayerAttrs.get(position).getBestBScore()));
        Picasso.get().load(addPlayerAttrs.get(position).getImage_url()).into(holder.player);

        final String type = addPlayerAttrs.get(position).getType();
        final String id = addPlayerAttrs.get(position).getId();
        final String team = addPlayerAttrs.get(position).getTeam();
        final String name = addPlayerAttrs.get(position).getName();

        if(type.equals("Batsman"))
        {
            holder.bowl1.setVisibility(View.GONE);
            holder.bowl2.setVisibility(View.GONE);
        }
        if(type.equals("Bowler"))
        {
            holder.bat.setVisibility(View.GONE);
            holder.bat2.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, UpdatePlayer.class);
                i.putExtra("id", id);
                i.putExtra("team",team);
                i.putExtra("name" , name);
                activity.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return addPlayerAttrs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView player;
        TextView name, age, bestScore, totalscore, hand, bWicket, bScore,hundred;
        LinearLayout bat,bat2, bowl1, bowl2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            player = (ImageView) itemView.findViewById(R.id.imgPlayer);

            name = (TextView) itemView.findViewById(R.id.txtName);
            age = (TextView) itemView.findViewById(R.id.txtAge);
            bestScore = (TextView) itemView.findViewById(R.id.txtBestScore);
            totalscore = (TextView) itemView.findViewById(R.id.txtTotalWickets);
            hand = (TextView) itemView.findViewById(R.id.txtHand);
            bWicket = (TextView) itemView.findViewById(R.id.txtBestWicket);
            bScore = (TextView) itemView.findViewById(R.id.txtBscore);
            hundred = (TextView) itemView.findViewById(R.id.txtHundred);
            bat = (LinearLayout) itemView.findViewById(R.id.linBat);
            bat2 = (LinearLayout) itemView.findViewById(R.id.linBat2);
            bowl1 = (LinearLayout) itemView.findViewById(R.id.linBow);
            bowl2 = (LinearLayout) itemView.findViewById(R.id.linBowler);


        }
    }
}
