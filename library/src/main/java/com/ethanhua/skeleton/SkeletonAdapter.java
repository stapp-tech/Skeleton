package com.ethanhua.skeleton;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;


/**
 * Created by ethanhua on 2017/7/29.
 */

public class SkeletonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int mItemCount;
    private int mLayoutReference;
    private int[] mLayoutArrayReferences;
    private Shimmer mShimmer;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (doesArrayOfLayoutsExist()) {
            mLayoutReference = viewType;
        }
        if (mShimmer != null) {
            ShimmerViewHolder viewHolder = new ShimmerViewHolder(inflater, parent, mLayoutReference);
            viewHolder.setShimmer(mShimmer);
            return viewHolder;
        }

        return new RecyclerView.ViewHolder(inflater.inflate(mLayoutReference, parent, false)) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mShimmer != null) {
            ShimmerFrameLayout layout = (ShimmerFrameLayout) holder.itemView;
            layout.startShimmer();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (doesArrayOfLayoutsExist()) {
            return getCorrectLayoutItem(position);
        }
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mItemCount;
    }

    public void setLayoutReference(int layoutReference) {
        this.mLayoutReference = layoutReference;
    }

    public void setArrayOfLayoutReferences(int[] layoutReferences) {
        this.mLayoutArrayReferences = layoutReferences;
    }

    public void setItemCount(int itemCount) {
        this.mItemCount = itemCount;
    }

    public void shimmer(Shimmer shimmer) {
        this.mShimmer = shimmer;
    }

    public int getCorrectLayoutItem(int position) {
        if (doesArrayOfLayoutsExist()) {
            return mLayoutArrayReferences[position % mLayoutArrayReferences.length];
        }
        return mLayoutReference;
    }

    private boolean doesArrayOfLayoutsExist() {
        return mLayoutArrayReferences != null && mLayoutArrayReferences.length != 0;
    }
}
