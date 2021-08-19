package com.ethanhua.skeleton;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.supercharge.shimmerlayout.ShimmerLayout;

/**
 * Created by ethanhua on 2017/7/29.
 */

@SuppressWarnings("unused")
public class SkeletonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int mItemCount;
    private int mLayoutReference;
    private int mColor;
    private boolean mShimmer;
    private int mShimmerDuration;
    private int mShimmerAngle;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (mShimmer) {
            return new ShimmerViewHolder(inflater, parent, mLayoutReference);
        }
        return new RecyclerView.ViewHolder(inflater.inflate(mLayoutReference, parent, false)) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (mShimmer) {
            ShimmerLayout layout = (ShimmerLayout) holder.itemView;
            layout.setShimmerAnimationDuration(mShimmerDuration);
            layout.setShimmerAngle(mShimmerAngle);
            layout.setShimmerColor(mColor);
            layout.startShimmerAnimation();
        }
    }

    @Override
    public int getItemCount() {
        return mItemCount;
    }

    public void setLayoutReference(int layoutReference) {
        this.mLayoutReference = layoutReference;
    }

    public void setItemCount(int itemCount) {
        this.mItemCount = itemCount;
    }

    public void setShimmerColor(int color) {
        this.mColor = color;
    }

    public void shimmer(boolean shimmer) {
        this.mShimmer = shimmer;
    }

    public void setShimmerDuration(int shimmerDuration) {
        this.mShimmerDuration = shimmerDuration;
    }

    public void setShimmerAngle(@IntRange(from = 0, to = 30) int shimmerAngle) {
        this.mShimmerAngle = shimmerAngle;
    }
}