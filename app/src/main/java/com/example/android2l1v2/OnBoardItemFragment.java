package com.example.android2l1v2;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class OnBoardItemFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    private String title;
    private String descr;
    private int image;

    private TextView tvTitle, tvDesc;
    private ImageView ivImage;

    public OnBoardItemFragment() {
        // Required empty public constructor
    }

    public static OnBoardItemFragment newInstance(String title, String descr, int image) {
        OnBoardItemFragment fragment = new OnBoardItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, title);
        args.putString(ARG_PARAM2, descr);
        args.putInt(ARG_PARAM3, image);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM1);
            descr = getArguments().getString(ARG_PARAM2);
            image = getArguments().getInt(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_on_board_item, container, false);
        tvTitle = view.findViewById(R.id.item_title_txt);
        tvDesc = view.findViewById(R.id.item_desc_txt);
        ivImage = view.findViewById(R.id.item_image_img);
        setData();
        return view;
    }

    private void setData() {
        tvTitle.setText(title);
        tvDesc.setText(descr);
        ivImage.setImageResource(image);
    }
}