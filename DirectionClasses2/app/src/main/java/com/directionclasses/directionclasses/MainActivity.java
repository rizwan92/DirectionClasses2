package com.directionclasses.directionclasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG=MainActivity.class.getName();
    private TextView mTextMessage;
    private Button homebutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homebutton=(Button)findViewById(R.id.homeacivity);
        Toolbar mToolBar=(Toolbar)findViewById(R.id.toolbar);
        mToolBar.setTitle(getString(R.string.app_name));
      //  mToolBar.setNavigationIcon(R.drawable.ic_launcher);
        mToolBar.inflateMenu(R.menu.menu_main);

    mTextMessage=(TextView)findViewById(R.id.link_signup);
        mTextMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,RegisterationActivity.class);
                startActivity(i);
            }
        });

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(LOG_TAG,"this insiede home button");
                Toast.makeText(MainActivity.this,"this is Home button",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });



    }

}
