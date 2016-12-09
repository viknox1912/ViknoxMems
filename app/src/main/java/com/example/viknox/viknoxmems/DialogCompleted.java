package com.example.viknox.viknoxmems;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.viknox.viknoxmems.adapters.CompletedListener;

/**
 * Created by x230 on 12/9/2016.
 */

public class DialogCompleted extends DialogFragment implements View.OnClickListener{
    private ImageButton mBtnClose;
    private Button mBtnCompleted;
    private CompletedListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_completed, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBtnClose =(ImageButton) view.findViewById(R.id.btn_close);
        mBtnCompleted = (Button)view.findViewById(R.id.btn_mark_completed);
        Bundle arguments = getArguments();
        if (arguments!=null){
            int position = arguments.getInt("POSITION");
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_mark_completed:
                //TODO: Set item as completed and remove from list
                MarkAsComplete();
                break;
        }
        dismiss();
    }

    private void MarkAsComplete() {
        Bundle arguments = getArguments();

        if (mListener != null && arguments!= null) {
            int position = arguments.getInt("POSITION");
            mListener.onComplete(position);

        }


    }

    public void setCompletedListener(CompletedListener mCompletedListener) {
        mListener = mCompletedListener;
    }
}
