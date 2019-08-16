package com.nguyenvanquan7826.appforlibs;

import java.util.ArrayList;
import java.util.List;

public class User  {
    private int id;
    private String name;

    private List<ChangeListener> listeners = new ArrayList<>();

    public void notifyListeners(Object object, String property, boolean oldValue, boolean newValue) {
        for (ChangeListener listener : listeners) {
            listener.onUpdate();
        }
    }

    public void addChangeListener(ChangeListener newListener) {
        listeners.add(newListener);
    }

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public void no() {
        notifyListeners(this, "ok", true, true);
    }
}
