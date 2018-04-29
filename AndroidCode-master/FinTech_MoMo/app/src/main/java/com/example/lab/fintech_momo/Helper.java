package com.example.lab.fintech_momo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Timer;

import me.relex.circleindicator.CircleIndicator;

public class Helper extends AppCompatActivity {

    private static ViewPager mPager;
    private static int currentPage = 0;
//    private static final Integer[] imageData = {R.drawable.loading_page,R.drawable.loading_page,R.drawable.loading_page};
    private ArrayList<Integer> imageArrayList = new ArrayList<Integer>();
    private int length;

    private Button go_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper);

        Bundle bundle = this.getIntent().getExtras();
        imageArrayList = bundle.getIntegerArrayList("list");
        length = bundle.getInt("length");
        go_back = (Button) findViewById(R.id.goHome);
        go_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        init();
    }

    private void init() {
//        for(int i = 0; i< imageData.length; i++)
//            imageArrayList.add(imageData[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlideViewAdapter(Helper.this, imageArrayList));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
//        swipeTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(Update);
//            }
//        }, 2500, 2500);
    }
}
