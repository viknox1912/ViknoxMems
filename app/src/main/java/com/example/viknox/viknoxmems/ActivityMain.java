package com.example.viknox.viknoxmems;

import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.viknox.viknoxmems.Beans.Mems;
import com.example.viknox.viknoxmems.adapters.AdapterMems;
import com.example.viknox.viknoxmems.adapters.CompletedListener;
import com.example.viknox.viknoxmems.adapters.Divider;
import com.example.viknox.viknoxmems.adapters.MarkListener;
import com.example.viknox.viknoxmems.adapters.SimpleCallback;
import com.example.viknox.viknoxmems.widgets.CustomRecyclerView;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class ActivityMain extends AppCompatActivity {
    CustomRecyclerView viewRecycler;
    Toolbar toobar;
    Realm mRealm;
    RealmResults<Mems> mResults;
    String TAG = "vikkk";
    AdapterMems mAdapter;
    View mEmptyView;
    private RealmChangeListener mChangeListener =new RealmChangeListener() {
        @Override
        public void onChange(Object element) {
            mAdapter.update(mResults);
            Log.d(TAG, "onChange: ");
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRealm = Realm.getDefaultInstance();
        mResults = mRealm.where(Mems.class).findAllAsync();

        loadUI();


    }

    private void loadUI() {
        toobar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toobar);
        ImageView backGroundImage = (ImageView) findViewById(R.id.bg_image);
        mEmptyView = findViewById(R.id.empty_memes);
        viewRecycler = (CustomRecyclerView)findViewById(R.id.recycler_view) ;
        viewRecycler.hideIfEmpty(toobar);
        viewRecycler.showIfEmpty(mEmptyView);

        LinearLayoutManager lmanager = new LinearLayoutManager(this);
        viewRecycler.setLayoutManager(lmanager);
        viewRecycler.addItemDecoration(new Divider(this,LinearLayoutManager.VERTICAL ));
        mAdapter = new AdapterMems(this,mRealm,mResults,mMarkListener);
        viewRecycler.setAdapter(mAdapter);
        SimpleCallback callback = new SimpleCallback(mAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);


        helper.attachToRecyclerView(viewRecycler);

        Glide.with(this)
                .load("http://media.istockphoto.com/photos/abstract-cubes-retro-styled-colorful-background-picture-id508795172?k=6&m=508795172&s=170667a&w=0&h=uxKISA4xMNkrBCcEFhdN5mm-ZDv8LFzWUhMMmG3CNuQ=")
                .centerCrop()
                .into(backGroundImage);

    }

    @Override
    protected void onStart() {
        mResults.addChangeListener(mChangeListener);

        super.onStart();
    }

    @Override
    protected void onStop() {
        mResults.removeChangeListener(mChangeListener);
        super.onStop();
    }

    public void showDialog(View view) {
        ShowDialogAdd();

    }

    private void ShowDialogAdd() {
        DialogAdd dialogAdd = new DialogAdd();
        dialogAdd.show(getSupportFragmentManager(),"Add");
    }
    private void ShowDialogCompleted(int position) {
        DialogCompleted dialogCompleted = new DialogCompleted();
        Bundle bundle = new Bundle();
        bundle.putInt("POSITION",position);
        dialogCompleted.setCompletedListener(mCompletedListener);
        dialogCompleted.setArguments(bundle);
        dialogCompleted.show(getSupportFragmentManager(),"Completed");

    }
    private CompletedListener mCompletedListener = new CompletedListener() {
        @Override
        public void onComplete(int position) {
            mAdapter.markComplete(position);


        }
    };
    private MarkListener mMarkListener = new MarkListener() {
        @Override
        public void onmMark(int position) {
            ShowDialogCompleted(position);

        }

    };
}
