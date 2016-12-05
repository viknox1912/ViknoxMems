package com.example.viknox.viknoxmems;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.viknox.viknoxmems.Beans.Mems;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by x230 on 11/29/2016.
 */

public class DialogAdd extends DialogFragment {
    private EditText et_memo_name;
    private DatePicker date_picker;
    private Button add_memo;
    private ImageButton close_dialog;

    private View.OnClickListener mbtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            switch (id){
                case R.id.bt_close:
                    dismiss();
                    break;
            }
            addMemo();


        }
    };

    private void addMemo() {
        String what = et_memo_name.getText().toString();
        long now = System.currentTimeMillis();
        Realm.init(getContext());
        Realm realm = Realm.getDefaultInstance();
        Mems mem = new Mems(false,what,now,0);
        realm.beginTransaction();
        realm.copyToRealm(mem);
        realm.commitTransaction();

        RealmQuery<Mems> querry = realm.where(Mems.class);
        RealmResults<Mems> result = querry.findAll();

        Toast.makeText(getActivity(), mem.getWhat() + mem.getWhen(), Toast.LENGTH_SHORT).show();

        Log.d("TAG", "addMemo: " + result.toString());
        realm.close();
        dismiss();
    }

    public DialogAdd() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.dialog_add,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        et_memo_name = (EditText)view.findViewById(R.id.editText);
        date_picker = (DatePicker)view.findViewById(R.id.datePicker);
        add_memo = (Button)view.findViewById(R.id.bt_add_memo);
        close_dialog = (ImageButton)view.findViewById(R.id.bt_close);
        close_dialog.setOnClickListener(mbtnClick);
        add_memo.setOnClickListener(mbtnClick);
    }
}
