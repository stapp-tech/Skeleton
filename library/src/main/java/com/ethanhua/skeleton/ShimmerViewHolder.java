package com.ethanhua.skeleton;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;

/**
 * Created by ethanhua on 2017/7/29.
 */

public class ShimmerViewHolder extends RecyclerView.ViewHolder {
    public ShimmerViewHolder(LayoutInflater inflater, ViewGroup parent, int innerViewResId) {
        super(inflater.inflate(R.layout.layout_shimmer, parent, false));
        ShimmerFrameLayout layout = (ShimmerFrameLayout) itemView;
        View view = inflater.inflate(innerViewResId, layout, false);
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp != null) {
            layout.setLayoutParams(lp);
        }
        layout.addView(view);
    }

    public void setShimmer(Shimmer shimmer) {
        ((ShimmerFrameLayout) itemView).setShimmer(shimmer);
    }
}
