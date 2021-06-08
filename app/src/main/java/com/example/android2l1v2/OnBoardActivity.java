package com.example.android2l1v2;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class OnBoardActivity extends AppCompatActivity {

    private ViewPagerAdapter adapter;
    private ViewPager pager;
    private Button btnSkip, btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);

        checkShowOnBoard();

        pager = findViewById(R.id.vp_onboard);
        btnStart = findViewById(R.id.btn_start);
        btnSkip = findViewById(R.id.btn_skip);

        initButton();

        List<OnBoardModel> list = new ArrayList<>();
        list.add(new OnBoardModel("Забыли?", "Мы поможем вспомнить",R.raw.find));
        list.add(new OnBoardModel("Запишите", "Записывайте важные поручения или просто то, что важно запомнить", R.raw.idk));
        list.add(new OnBoardModel("По полочкам", "Все в одном месте и легко в управлении", R.raw.cubes));
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2){
                    btnStart.setVisibility(View.VISIBLE);
                    btnSkip.setVisibility(View.GONE);
                }else{
                    btnStart.setVisibility(View.GONE);
                    btnSkip.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void checkShowOnBoard() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(OnBoardActivity.this);
        Boolean showOnBoard = pref.getBoolean("showOnBoard", false);
        if (showOnBoard){
            Intent intent = new Intent(OnBoardActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void initButton() {
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnBoardActivity.this, MainActivity.class);
                startActivity(intent);
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(OnBoardActivity.this);
                pref.edit().putBoolean("showOnBoard", true).apply();
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnBoardActivity.this, MainActivity.class);
                startActivity(intent);
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(OnBoardActivity.this);
                pref.edit().putBoolean("showOnBoard", true).apply();
            }
        });
    }
}