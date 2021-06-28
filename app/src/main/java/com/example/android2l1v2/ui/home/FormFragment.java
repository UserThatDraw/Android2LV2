package com.example.android2l1v2.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android2l1v2.App;
import com.example.android2l1v2.R;
import com.example.android2l1v2.TaskModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormFragment extends Fragment {

    EditText desc;
    String titleIt = "Нужно сделать:";
    Button saveBlue;
    TaskModel model;
    TaskModel mod;

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
            mod = (TaskModel) getArguments().getSerializable("mod");
            if (mod != null) {
                desc.setText(mod.getDesc());
            }
        }
    }

    private void initClick() {
        saveBlue.setOnClickListener(v -> {
            String tit = titleIt;
            String des = desc.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            String date = sdf.format(new Date());
            saverClick(tit, des, date);
        });
    }

    private void saverClick(String tit, String des, String date) {
        if ( des.length() > 0) {
            model = new TaskModel(tit, des, date);
            Bundle bundle = new Bundle();
            if (mod == null){
                bundle.putSerializable("model", model);
            }else {
                App.getInstance().getTaskDao().update(mod);
                bundle.putSerializable("upmodel", mod);
            }
            getParentFragmentManager().setFragmentResult("rv_model", bundle);
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            navController.navigateUp();
        }
    }

    private void initView(View view) {
        desc = view.findViewById(R.id.desc_et);
        saveBlue = view.findViewById(R.id.save_blue_btn);
    }
}
