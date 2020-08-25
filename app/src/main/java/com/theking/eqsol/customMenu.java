package com.theking.eqsol;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;

public class customMenu extends PopupMenu {
    public customMenu(@NonNull Context context, @NonNull View anchor, int gravity) {
        super(context, anchor, gravity);
    }

    @Override
    public void inflate(int menuRes) {
        super.inflate(menuRes);
    }
}
