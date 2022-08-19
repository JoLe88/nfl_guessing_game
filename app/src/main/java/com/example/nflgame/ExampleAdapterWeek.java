package com.example.nflgame;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExampleAdapterWeek extends RecyclerView.Adapter<ExampleAdapterWeek.ExempleViewHolder> {
    private ArrayList<WeekItem> mWeekList;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;

    }

    public static class ExempleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mStarsImageView;
        public TextView mWeekTextView;
//        public TextView mStarsTextView;

        public ExempleViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            mStarsImageView = itemView.findViewById(R.id.starsImageView);
            mWeekTextView = itemView.findViewById(R.id.weekTextView);
//            mStarsTextView = itemView.findViewById(R.id.starsTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

    }

    public ExampleAdapterWeek(ArrayList<WeekItem> weekItems) {
        mWeekList = weekItems;
    }

    @Override
    public ExempleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.week_item_for_recyclerview, parent, false);
        ExempleViewHolder evh = new ExempleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleAdapterWeek.ExempleViewHolder holder, int position) {
        WeekItem currentItem = mWeekList.get(position);

        holder.mStarsImageView.setImageResource(currentItem.getmImageResource());
        holder.mWeekTextView.setText(String.valueOf(currentItem.getmWeek()));
//        holder.mStarsTextView.setText(String.valueOf(currentItem.getmStars()));
    }

    @Override
    public int getItemCount() {
        return mWeekList.size();
    }
}