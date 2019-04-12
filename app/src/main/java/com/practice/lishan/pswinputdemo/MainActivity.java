package com.practice.lishan.pswinputdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.practice.lishan.pswinputview.PswInputView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tvTitle = findViewById(R.id.tv_title);
        PswInputView pivDefault = findViewById(R.id.piv_default);
        pivDefault.setOnPswInputCompletedListener(new PswInputView.OnPswInputCompletedListener() {
            @Override
            public void onPswInputCompleted(String psw) {
                tvTitle.setText(psw);
            }
        });
    }
}
