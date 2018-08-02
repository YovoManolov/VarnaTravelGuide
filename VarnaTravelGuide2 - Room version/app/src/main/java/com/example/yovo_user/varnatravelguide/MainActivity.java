package com.example.yovo_user.varnatravelguide;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yovo_user.varnatravelguide.databasePackage.VTGDatabase;

public class MainActivity extends AppCompatActivity {

    private VTGDatabase vtgDatabase;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setVtgDatabase(VTGDatabase.getInstance(getApplicationContext()));

        setViewPager((ViewPager) findViewById(R.id.viewPagerId));
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
    }


    public VTGDatabase getVtgDatabase() {
        return vtgDatabase;
    }

    public void setVtgDatabase(VTGDatabase vtgDatabase) {
        this.vtgDatabase = vtgDatabase;
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

}
