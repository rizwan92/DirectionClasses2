package com.directionclasses.directionclasses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class RegisterationActivity extends AppCompatActivity {
    public static final String LOG_TAG=RegisterationActivity.class.getName();
    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        mTextMessage=(TextView)findViewById(R.id.link_login);
        Toolbar mToolBar=(Toolbar)findViewById(R.id.toolbar);
        mToolBar.setTitle(getString(R.string.app_name));
     //   mToolBar.setNavigationIcon(R.drawable.ic_launcher);
        mToolBar.inflateMenu(R.menu.menu_main);





        mTextMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RegisterationActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
