package com.example.hendrikzhou.dialogdemo;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

public class MyDialog extends Dialog {

    public MyDialog(@NonNull Context context) {
        super(context, R.style.MyDialog);
        setContentView(R.layout.layout);

        Button positiveBtn = findViewById(R.id.yes_btn);
        Button negativeBtn = findViewById(R.id.no_btn);

        negativeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        positiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }
}
