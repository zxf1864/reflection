package com.example.administrator.reflection;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.administrator.reflection.reflection.FunctionModule;
import com.example.administrator.reflection.reflection.FunctionModuleAdapter;
import com.example.administrator.reflection.reflection.ModuleControllerCore;
import com.example.administrator.reflection.reflection.ModuleLoadDataInf;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<FunctionModule> mModuleList;
    private GridView gridView;
    private FunctionModuleAdapter mModuleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inti();
        loadModules();
        mModuleAdapter = new FunctionModuleAdapter(mModuleList);
        gridView.setAdapter(mModuleAdapter);
        gridView.setOnItemClickListener(this);
    }


    /**
     * 加载资源文件
     */
    private void loadModules() {
        mModuleList = new ArrayList<FunctionModule>();
        ModuleControllerCore moduleControllerCore = new ModuleControllerCore(MainActivity.this,
                R.raw.module_config);
        try {
            String moduleConfigName = "MainModuleList";
            mModuleList.addAll(moduleControllerCore.readModuleList(moduleConfigName));
            for (FunctionModule item : mModuleList) {
                if (item instanceof ModuleLoadDataInf) {
                    try {
                        ((ModuleLoadDataInf) item).loading();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        mModuleList.get(arg2).onClick(arg0, arg1, arg2, arg3);
    }


    private void inti() {
        gridView = (GridView) findViewById(R.id.gridView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
