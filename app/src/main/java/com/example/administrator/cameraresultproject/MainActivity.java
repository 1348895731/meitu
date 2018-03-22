package com.example.administrator.cameraresultproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public final static String INTENT_TITLE = "title";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_text).setOnClickListener(this);

    }


    private void startActivity(Class clz, View view) {
        Intent intent = new Intent(this, clz);
        if (view instanceof AppCompatButton) {
            intent.putExtra(INTENT_TITLE, ((AppCompatButton) view).getText());
        }
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        startActivity(ResultActivity.class, v);
    }
}
