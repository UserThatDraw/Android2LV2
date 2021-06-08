package com.example.android2l1v2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    List<OnBoardModel> list = new ArrayList<>();
    FragmentManager manager;

    public ViewPagerAdapter(@NonNull FragmentManager fm, List<OnBoardModel>list) {
        super(fm);
        this.manager = fm;
        this.list=list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new OnBoardItemFragment();
        switch (position){
            case 0:
                fragment = OnBoardItemFragment.newInstance(list.get(position).getTitle(), list.get(position ).getDescr(), list.get(position).getImage());
                break;
            case 1:
                fragment = OnBoardItemFragment.newInstance(list.get(position).getTitle(), list.get(position ).getDescr(), list.get(position).getImage());
                break;
            case 2:
                fragment = OnBoardItemFragment.newInstance(list.get(position).getTitle(), list.get(position ).getDescr(), list.get(position).getImage());
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
