package cn.jestar.statepagedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        int id = v.getId();
        Class<? extends AppCompatActivity> aClass = id == R.id.btn_create_in_xml ? XmlActivity.class : CustomInitActivity.class;
        startActivity(new Intent(this, aClass));
    }
}
