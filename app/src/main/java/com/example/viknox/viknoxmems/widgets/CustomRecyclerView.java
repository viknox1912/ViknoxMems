package com.example.viknox.viknoxmems.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.viknox.viknoxmems.extras.Util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by x230 on 12/1/2016.
 */

public class CustomRecyclerView extends RecyclerView {
    private final String TAG = "lololo";
    private List<View> mEmptyViews = Collections.emptyList();
    private List<View> mNonEmptyViews = Collections.emptyList();
    private AdapterDataObserver mObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            toggleViews();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            toggleViews();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            toggleViews();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            toggleViews();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            toggleViews();
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            toggleViews();
        }
    };

    public CustomRecyclerView(Context context) {
        super(context);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void toggleViews() {
        Log.d(TAG, "toggleViews: Started");
        if (getAdapter() != null && !mEmptyViews.isEmpty() && !mNonEmptyViews.isEmpty()) {
            if (getAdapter().getItemCount() == 0) {
                Log.d(TAG, "toggleViews: List is empty");
                Util.showViews(mEmptyViews);

                setVisibility(View.GONE);

                Util.hideVIews(mNonEmptyViews);
            } else {
                Log.d(TAG, "toggleViews: List has items");
                Util.showViews(mNonEmptyViews);

                setVisibility(View.VISIBLE);

                Util.hideVIews(mEmptyViews);

            }

        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(mObserver);
        }
        mObserver.onChanged();
    }

    public void hideIfEmpty(View... view) {
        mNonEmptyViews = Arrays.asList(view);
    }

    public void showIfEmpty(View... mEmptyView) {
        mEmptyViews = Arrays.asList(mEmptyView);
    }
}
