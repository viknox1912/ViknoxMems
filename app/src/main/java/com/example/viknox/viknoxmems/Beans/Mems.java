package com.example.viknox.viknoxmems.Beans;

import java.lang.annotation.Annotation;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


/**
 * Created by x230 on 11/29/2016.
 */

public class Mems  extends RealmObject{
    private String what;
    @PrimaryKey
    private long added;
    private long when;
    private boolean completed;

    public Mems(boolean completed, String what, long added, long when) {
        this.completed = completed;
        this.what = what;
        this.added = added;
        this.when = when;
    }
    public Mems(){}

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public long getAdded() {
        return added;
    }

    public void setAdded(long added) {
        this.added = added;
    }

    public long getWhen() {
        return when;
    }

    public void setWhen(long when) {
        this.when = when;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


}
