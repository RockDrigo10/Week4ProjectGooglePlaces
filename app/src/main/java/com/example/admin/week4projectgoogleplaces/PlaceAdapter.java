package com.example.admin.week4projectgoogleplaces;

import android.app.Dialog;
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


public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {
    private static final String TAG = "Adapter";
    private static final int PLACE_PICKER_REQUEST = 1;
    List<Result> placeList = new ArrayList<>();
    Context context;
    private int lastPosition = -1;

    public PlaceAdapter(List<Result> placeList) {
        this.placeList = placeList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvText, tvText2, tvText3, tvText5;

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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Result item = placeList.get(position);
        Log.d(TAG, "onBindViewHolder: " + item.getName());
        holder.tvText.setText(item.getName());
        holder.tvText2.setText(item.getVicinity());
        holder.tvText3.setText(item.getTypes().get(0).toString());
        holder.tvText5.setText((String.valueOf(item.getRating())));
        if (item.getPhotos() != null && item.getPhotos().size() > 0) {
            String a = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=" + item.getPhotos().get(0).getPhotoReference() + "&key=AIzaSyA7KlO9rgzRf4gC4nNOyIIKjlLmGDOdncA";
            Glide.with(holder.itemView.getContext()).load(a).into(holder.ivImage);
        } else
            Glide.with(holder.itemView.getContext()).load(item.getIcon()).into(holder.ivImage);
        setAnimation(holder.itemView, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final Dialog dialogCustom = new Dialog(v.getContext());
                dialogCustom.setContentView(R.layout.details_view);
                ImageView ivDetail = (ImageView) dialogCustom.findViewById(R.id.ivDetail);
                TextView tvText = (TextView) dialogCustom.findViewById(R.id.tvText);
                TextView tvText2 = (TextView) dialogCustom.findViewById(R.id.tvText2);
                TextView tvText3 = (TextView) dialogCustom.findViewById(R.id.tvText3);
                TextView tvText4 = (TextView) dialogCustom.findViewById(R.id.tvText4);
                tvText.setText(item.getName());
                tvText2.setText(item.getVicinity());
                tvText3.setText(item.getTypes().get(0).toString());
                tvText4.setText((String.valueOf(item.getRating())));

                if (item.getPhotos() != null && item.getPhotos().size() > 0) {
                    String a = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="
                            + item.getPhotos().get(0).getPhotoReference() + "&key=AIzaSyA7KlO9rgzRf4gC4nNOyIIKjlLmGDOdncA";
                    Glide.with(v.getContext()).load(a).into(ivDetail);
                } else
                    Glide.with(v.getContext()).load(item.getIcon()).into(ivDetail);

                dialogCustom.show();
            }
        });
//                new AlertDialog.Builder(v.getContext())
//                        .setTitle("Place's details")
//                        .setMessage(item.getName())
//                        .setCancelable(false)false
//                        .setView(holder.itemView.findViewById(R.layout.details_view))
//                        .setPositiveButton("Details", new DialogInterface.OnClickListener()
//                        {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                final Dialog dialogCustom = new Dialog(v.getContext());
//                                dialogCustom.setContentView(R.layout.details_view);
//                                ImageView ivDetail = (ImageView) dialogCustom.findViewById(R.id.ivDetail);
//                                if(item.getPhotos()!=null&&item.getPhotos().size()>0) {
//                                    String a = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="
//                                            + item.getPhotos().get(0).getPhotoReference() + "&key=AIzaSyA7KlO9rgzRf4gC4nNOyIIKjlLmGDOdncA";
//                                    Glide.with(v.getContext()).load(a).into(ivDetail);
//                                }
//                                else
//                                    Glide.with(v.getContext()).load(item.getIcon()).into(ivDetail);
//                                dialogCustom.show();
//                            }
//                        }

//                        .setNegativeButton("Close", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        })
//                        .show();
//            }
//        });

    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.ZORDER_BOTTOM, 0.5f, Animation.ZORDER_BOTTOM, 0.5f);
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
