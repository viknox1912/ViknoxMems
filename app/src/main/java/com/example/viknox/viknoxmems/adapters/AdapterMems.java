package com.example.viknox.viknoxmems.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.viknox.viknoxmems.Beans.Mems;
import com.example.viknox.viknoxmems.R;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by x230 on 12/1/2016.
 */

public class AdapterMems extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements SwipeListener {
    public static final int ITEM = 0;
    public static final int FOOTER = 1;
    private  MarkListener mMarkListener;

    private LayoutInflater mInflater;
    private RealmResults<Mems> mResults;
    private Realm mRealm;
    public AdapterMems(Context context, Realm realm, RealmResults<Mems> results,MarkListener markListener){
       mInflater = LayoutInflater.from(context);
        mRealm = realm;
        update(results);
        mMarkListener = markListener;
    }
    public void update(RealmResults<Mems> reults){
        mResults = reults;
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        if (mResults == null || position<mResults.size()){
            return ITEM;
        }else{
            return FOOTER;
        }
    }






    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == FOOTER){
            View view =  mInflater.inflate(R.layout.footer, parent,false);
            FooterHolder footerHolder = new FooterHolder(view);
            return footerHolder;

        }else{
       View view =  mInflater.inflate(R.layout.row_mem, parent,false);

            return new MemsHolder(view,mMarkListener);

        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MemsHolder){
            MemsHolder memsHolder = (MemsHolder) holder;
        Mems mems = mResults.get(position);
        memsHolder.mTextWhat.setText(mems.getWhat());}else{

        }


    }

    @Override
    public int getItemCount() {
        if(mResults==null || mResults.isEmpty()){
            return 0;
        }
        return mResults.size() + 1;
    }

    @Override
    public void onSwipe(int position) {
        if (position < mResults.size()){
        mRealm.beginTransaction();
        mResults.get(position).deleteFromRealm();
        mRealm.commitTransaction();
        notifyDataSetChanged();
        }

    }

    public void markComplete(int position) {
        if (position < mResults.size()){
        mRealm.beginTransaction();
        mResults.get(position).setCompleted(true);
        mRealm.commitTransaction();
        notifyItemChanged(position);
        }
    }

    public static class MemsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final MarkListener mMarkListener;
        TextView mTextWhat;

        public MemsHolder(View itemView, MarkListener listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTextWhat = (TextView)itemView.findViewById(R.id.tv_what);
            mMarkListener = listener;
        }

        @Override
        public void onClick(View view) {
            mMarkListener.onmMark(getAdapterPosition());

        }
    }
    public static class FooterHolder extends RecyclerView.ViewHolder{
        Button mAddMem;

        public FooterHolder(View itemView) {
            super(itemView);
            mAddMem = (Button) itemView.findViewById(R.id.bt_footer);
        }
    }
}
