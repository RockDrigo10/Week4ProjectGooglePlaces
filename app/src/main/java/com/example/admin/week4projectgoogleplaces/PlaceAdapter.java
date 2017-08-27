package com.example.admin.week4projectgoogleplaces;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.week4projectgoogleplaces.Model.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Admin on 8/27/2017.
 */

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {
    private static final String TAG = "Adapter";
    List<Result> placeList = new ArrayList<>();
    Context context;
    private int lastPosition = -1;

    public PlaceAdapter(List<Result> placeList) {
        this.placeList = placeList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvText, tvText2,tvText3,tvText5;
        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
            tvText = (TextView) itemView.findViewById(R.id.tvText);
            tvText2 = (TextView) itemView.findViewById(R.id.tvText2);
            tvText3 = (TextView) itemView.findViewById(R.id.tvText3);
            tvText5 = (TextView) itemView.findViewById(R.id.tvText5);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.places, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Result item = placeList.get(position);
        Log.d(TAG, "onBindViewHolder: " + item.getName());
        holder.tvText.setText(item.getName());
        holder.tvText2.setText(item.getVicinity());
        holder.tvText3.setText(item.getTypes().get(0).toString());
        holder.tvText5.setText((String.valueOf(item.getRating())));
        Glide.with(holder.itemView.getContext()).load(item.getIcon()).into(holder.ivImage);
        setAnimation(holder.itemView, position);

    }
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(new Random().nextInt(501));//to make duration random number between [0,501)
            viewToAnimate.startAnimation(anim);
            lastPosition = position;
        }
    }
    @Override
    public int getItemCount() {
        return placeList.size();
    }


}
