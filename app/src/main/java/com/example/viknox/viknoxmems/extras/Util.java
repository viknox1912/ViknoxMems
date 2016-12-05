package com.example.viknox.viknoxmems.extras;

import android.view.View;

import java.util.List;

/**
 * Created by x230 on 12/1/2016.
 */

public class Util {
    public static void showViews(List<View> views) {
        for (View view : views){
            view.setVisibility(View.VISIBLE);
        }

    }

    public static void hideVIews(List<View> views) {
        for (View view: views){
            view.setVisibility(View.GONE);
        }


    }
}
