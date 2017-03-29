package com.directionclasses.directionclasses;


import android.app.LoaderManager;
import android.content.Loader;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Object>> {

    public static final String LOG_TAG = HomeActivity.class.getName();
    Spinner spinner;
    Spinner spinnerClass;
    Spinner spinnerSubject;
    private static final int mBoardLoaderManager=1;
    private static final int mClassLoaderManager=2;
    private static final int mSubjectLoaderManager=3;
    private static final String boardUrl = "http://directionclasses.com/Android/selectBoard.php";
    private static final String classUrl = "http://directionclasses.com/Android/selectClass.php";
    private static final String subjectUrl = "http://directionclasses.com/Android/selectSubject.php";
    BoardAdapter boardAdapter = null;
    ClassAdapter classAdapter = null;
    SubjectAdapter subjectAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar mToolBar=(Toolbar)findViewById(R.id.toolbar);
        mToolBar.setTitle(getString(R.string.app_name));
        //  mToolBar.setNavigationIcon(R.drawable.ic_launcher);
        mToolBar.inflateMenu(R.menu.menu_main);

        android.app.LoaderManager loaderManager=getLoaderManager();
        loaderManager.initLoader(mBoardLoaderManager,null,this);


        spinner = (Spinner) findViewById(R.id.spinner1);
        spinnerClass = (Spinner) findViewById(R.id.spinner2);
        spinnerSubject = (Spinner) findViewById(R.id.spinner3);

        boardAdapter = new BoardAdapter(this, new ArrayList<Board>());
        classAdapter = new ClassAdapter(this, new ArrayList<Class>());
        subjectAdapter = new SubjectAdapter(this, new ArrayList<Subject>());
        spinner.setAdapter(boardAdapter);
        spinnerClass.setAdapter(classAdapter);
        spinnerSubject.setAdapter(subjectAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Board board = boardAdapter.getItem(position);
                int board_id = board.getBoard_id();
                if (board_id != 0) {


                    String string_board_id = String.valueOf(board_id);
                    Bundle b = new Bundle();
                    b.putString("string_board_id", string_board_id);
                    android.app.LoaderManager loaderManager=getLoaderManager();
                    loaderManager.initLoader(mClassLoaderManager,b,HomeActivity.this);


                } else {
                    Toast.makeText(HomeActivity.this, "jai ho with no id", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        spinnerClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Class c=classAdapter.getItem(position);
                int class_id=c.getClass_id();
                if (class_id!=0){
                    String string_class_id=String.valueOf(class_id);
                    Bundle b = new Bundle();
                    b.putString("string_class_id", string_class_id);
                    android.app.LoaderManager loaderManager=getLoaderManager();
                    loaderManager.initLoader(mSubjectLoaderManager,b,HomeActivity.this);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

        @Override
        public Loader<List<Object>> onCreateLoader(int id, Bundle args) {
            String s=null;

            if (id==1) {
                return new BoardLoader(this, boardUrl);
            }
            if (id==2){
                if (args!=null) {
                    s = args.getString("string_board_id");
                }
                return new ClassLoader(this, classUrl,s);
            }
            if (id==3 ){
                if (args!=null) {
                    s = args.getString("string_class_id");
                }
                return new SubjectLoader(this, subjectUrl,s);
            }
            return null;
        }

    @Override
    public void onLoadFinished(Loader<List<Object>> loader, List<Object> data) {
        int loader_id=loader.getId();
        if (loader_id==1){
            if (data !=null && data.size() != 0){
                List<Board> mythings = (List<Board>) (Object) data;
                boardAdapter.addAll(mythings);
            }
            else{
                Log.e(LOG_TAG,"problem with board  list ");
            }
        }
        if (loader_id==2){
            if (data !=null && data.size() != 0){
                List<Class> mythings = (List<Class>) (Object) data;
                classAdapter.clear();
                classAdapter.addAll(mythings);
            }
            else{
                Log.e(LOG_TAG,"problem with class  list ");
            }
        }
        if (loader_id==3){
            if (data !=null && data.size() != 0){
                List<Subject> mythings = (List<Subject>) (Object) data;
                subjectAdapter.clear();
                subjectAdapter.addAll(mythings);
            }
            else{
                Log.e(LOG_TAG,"problem with subject  list ");
            }
        }



    }

    @Override
    public void onLoaderReset(Loader<List<Object>> loader) {
        Log.v(LOG_TAG,"this is the on Load Reset method");
       // boardAdapter.clear();
    }



}
