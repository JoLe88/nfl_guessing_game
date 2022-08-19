package com.example.nflgame;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExampleAdapterSeason extends RecyclerView.Adapter<ExampleAdapterSeason.ExempleViewHolder> {
    private ArrayList<SeasonItem> mSeasonList;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;

    }

    public static class ExempleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mSuperBowlLogoImageView;
        public TextView mSeasonTextView;
        public TextView mIncorrectTextView;
        public TextView mCorrectTextView;

        public ExempleViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            mSuperBowlLogoImageView = itemView.findViewById(R.id.superBowlLogoImageView);
            mSeasonTextView = itemView.findViewById(R.id.seasonTextView);
            mIncorrectTextView = itemView.findViewById(R.id.incorrectTextView);
            mCorrectTextView = itemView.findViewById(R.id.correctTextView);

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

    public ExampleAdapterSeason(ArrayList<SeasonItem> seasonItems) {
        mSeasonList = seasonItems;
    }

    @Override
    public ExempleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.season_item_for_recyclerview, parent, false);
        ExempleViewHolder evh = new ExempleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleAdapterSeason.ExempleViewHolder holder, int position) {
        SeasonItem currentItem = mSeasonList.get(position);

        holder.mSuperBowlLogoImageView.setImageResource(currentItem.getmImageResource());
        holder.mSeasonTextView.setText(currentItem.getmSeason());
        holder.mIncorrectTextView.setText(currentItem.getmIncorrect());
        holder.mCorrectTextView.setText(currentItem.getmCorrect());
    }

    @Override
    public int getItemCount() {
        return mSeasonList.size();
    }
}