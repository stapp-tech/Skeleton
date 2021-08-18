package com.ethanhua.skeleton;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.LayoutRes;

import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;

/**
 * Created by ethanhua on 2017/7/29.
 */

public class ViewSkeletonScreen implements SkeletonScreen {
    private static final String TAG = ViewSkeletonScreen.class.getName();
    private final ViewReplacer mViewReplacer;
    private final View mActualView;
    private final int mSkeletonResID;
    private final Shimmer mShimmer;

    private ViewSkeletonScreen(Builder builder) {
        mActualView = builder.mView;
        mSkeletonResID = builder.mSkeletonLayoutResID;
        mShimmer = builder.mShimmer;
        mViewReplacer = new ViewReplacer(builder.mView);
    }

    private ShimmerFrameLayout generateShimmerContainerLayout(ViewGroup parentView) {
        final ShimmerFrameLayout shimmerLayout = (ShimmerFrameLayout) LayoutInflater.from(mActualView.getContext()).inflate(R.layout.layout_shimmer, parentView, false);
        View innerView = LayoutInflater.from(mActualView.getContext()).inflate(mSkeletonResID, shimmerLayout, false);
        ViewGroup.LayoutParams lp = innerView.getLayoutParams();
        if (lp != null) {
            shimmerLayout.setLayoutParams(lp);
        }
        Shimmer shimmer = this.mShimmer != null ? this.mShimmer : new Shimmer.ColorHighlightBuilder().build();
        shimmerLayout.setShimmer(shimmer);
        shimmerLayout.addView(innerView);
        shimmerLayout.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                shimmerLayout.startShimmer();
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                shimmerLayout.startShimmer();
            }
        });
        shimmerLayout.startShimmer();
        return shimmerLayout;
    }

    private View generateSkeletonLoadingView() {
        ViewParent viewParent = mActualView.getParent();
        if (viewParent == null) {
            Log.e(TAG, "the source view have not attach to any view");
            return null;
        }
        ViewGroup parentView = (ViewGroup) viewParent;
        if (mShimmer != null) {
            return generateShimmerContainerLayout(parentView);
        }
        return LayoutInflater.from(mActualView.getContext()).inflate(mSkeletonResID, parentView, false);
    }

    @Override
    public void show() {
        View skeletonLoadingView = generateSkeletonLoadingView();
        if (skeletonLoadingView != null) {
            mViewReplacer.replace(skeletonLoadingView);
        }
    }

    @Override
    public void hide() {
        if (mViewReplacer.getTargetView() instanceof ShimmerFrameLayout) {
            ((ShimmerFrameLayout) mViewReplacer.getTargetView()).stopShimmer();
        }
        mViewReplacer.restore();
    }

    public static class Builder {
        private final View mView;
        private int mSkeletonLayoutResID;
        private Shimmer mShimmer = null;

        public Builder(View view) {
            this.mView = view;
        }

        /**
         * @param skeletonLayoutResID the loading skeleton layoutResID
         */
        public Builder load(@LayoutRes int skeletonLayoutResID) {
            this.mSkeletonLayoutResID = skeletonLayoutResID;
            return this;
        }

        /**
         * @param shimmer shimmer
         */
        public ViewSkeletonScreen.Builder shimmer(Shimmer shimmer) {
            this.mShimmer = shimmer;
            return this;
        }

        public ViewSkeletonScreen show() {
            ViewSkeletonScreen skeletonScreen = new ViewSkeletonScreen(this);
            skeletonScreen.show();
            return skeletonScreen;
        }

    }
}
