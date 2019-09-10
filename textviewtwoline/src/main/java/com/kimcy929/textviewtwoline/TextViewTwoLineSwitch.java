package com.kimcy929.textviewtwoline;

import android.view.View;
import android.widget.CompoundButton;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class TextViewTwoLineSwitch {
    private View root;
    private TextViewTwoLine tv;
    private SwitchMaterial switchMaterial;

    private String currentDescription = "";

    public TextViewTwoLineSwitch(View root, int idTv, int idSwitch) {
        this.root = root;
        this.tv = root.findViewById(idTv);
        this.switchMaterial = root.findViewById(idSwitch);

        root.setOnClickListener(v -> switchMaterial.setChecked(!switchMaterial.isChecked()));
    }

    public View getRoot() {
        return root;
    }

    public SwitchMaterial getSwitch() {
        return switchMaterial;
    }

    public TextViewTwoLine getTv() {
        return tv;
    }

    public void setTitle(String title) {
        tv.setTextTitle(title);
    }

    public void setDescription(String description) {
        tv.setTextDescription(description);
        currentDescription = description;
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        switchMaterial.setOnCheckedChangeListener(onCheckedChangeListener);
    }
}
