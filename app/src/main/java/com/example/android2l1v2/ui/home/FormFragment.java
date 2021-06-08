package com.example.android2l1v2.ui.home;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android2l1v2.R;
import com.example.android2l1v2.TaskModel;

import java.util.ArrayList;

public class FormFragment extends Fragment {

    EditText title, desc;
    TextView titleIt, descIt;
    Button saveBlue, saveRed, saveGray, saveOrange;
    TaskModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form, container, false);

        initView(view);
        getData();
        initClick();
        
        return view;
    }

    private void getData() {
        if (getArguments() != null){
            model = (TaskModel) getArguments().getSerializable("mod");
            title.setText(model.getTitle());
            desc.setText(model.getDesc());
        }
    }

    @SuppressLint("ResourceAsColor")
    private void initClick() {

        saveBlue.setOnClickListener(v -> {
            String tit = title.getText().toString();
            String des = desc.getText().toString();
            title.setBackgroundColor(R.color.deep_blue);
            desc.setBackgroundColor(R.color.little_wave);
            saverClick(tit, des);
        });
/*
        saveRed.setOnClickListener(v -> {
            String tit = title.getText().toString();

            String des = desc.getText().toString();
            title.setBackgroundColor(R.color.strawberry);
            desc.setBackgroundColor(R.color.cherry);
            saverClick(tit, des);
        });

        saveGray.setOnClickListener(v -> {
            String tit = title.getText().toString();
            String des = desc.getText().toString();
            title.setBackgroundColor(R.color.rainy_weather);
            desc.setBackgroundColor(R.color.gary_day);
            saverClick(tit, des);
        });

        saveOrange.setOnClickListener(v -> {
            String tit = title.getText().toString();
            String des = desc.getText().toString();
            title.setBackgroundColor(R.color.orange);
            desc.setBackgroundColor(R.color.peach);
            saverClick(tit, des);
        });
*/
    }

    private void saverClick(String tit, String des) {
        if (tit.length() > 0 && des.length() > 0) {
            model = new TaskModel(tit, des);
            Bundle bundle = new Bundle();
            bundle.putSerializable("model", model);
            getParentFragmentManager().setFragmentResult("rv_model", bundle);
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            navController.navigateUp();
        }
    }

    private void initView(View view) {
        title = view.findViewById(R.id.text_et);
        desc = view.findViewById(R.id.desc_et);
        titleIt = view.findViewById(R.id.title_item);
        descIt = view.findViewById(R.id.desc_item);
        saveBlue = view.findViewById(R.id.save_blue_btn);
        saveRed = view.findViewById(R.id.save_red_btn);
        saveGray = view.findViewById(R.id.save_gray_btn);
        saveOrange = view.findViewById(R.id.save_orange_btn);
    }
}
