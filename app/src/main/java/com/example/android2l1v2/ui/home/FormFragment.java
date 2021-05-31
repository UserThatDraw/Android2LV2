package com.example.android2l1v2.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android2l1v2.R;
import com.example.android2l1v2.TaskModel;
import com.example.android2l1v2.ui.home.HomeAdapter;
import com.example.android2l1v2.ui.home.HomeFragment;


public class FormFragment extends Fragment {

    TextView title, desc;
    Button save;
    HomeFragment fragment;
    TaskModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form, container, false);
        title = view.findViewById(R.id.text_et);
        desc = view.findViewById(R.id.desc_et);
        save = view.findViewById(R.id.save_btn);

        initClick();

        return view;
    }

    private void initClick() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tit = title.getText().toString();
                String des = desc.getText().toString();
                model = new TaskModel(tit, des);
                Bundle bundle = new Bundle();
                bundle.putSerializable("model", model);
                getParentFragmentManager().setFragmentResult("rv_model", bundle);
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
                navController.navigateUp();
            }

        });

    }
}