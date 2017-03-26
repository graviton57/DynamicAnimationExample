package com.havrylyuk.dynamicanimationexample.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.havrylyuk.dynamicanimationexample.fragment.AnimationFragment;
import com.havrylyuk.dynamicanimationexample.animation.AnimationType;

import java.util.List;


/**
 *
 * Created by Igor Havrylyuk on 26.03.2017.
 */

public class AnimationPagerAdapter extends FragmentStatePagerAdapter {

    private List<AnimationType> typeList;

    public AnimationPagerAdapter(FragmentManager fm, List<AnimationType> animations) {
        super(fm);
        this.typeList = animations;
    }

    public void addItem(AnimationType item) {
        typeList.add(item);
        notifyDataSetChanged();
    }

    public void addItems(List<AnimationType> data) {
        typeList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return typeList.get(position).getName();
    }

    @Override
    public Fragment getItem(int position) {
        return AnimationFragment.getInstance(typeList.get(position).ordinal());
    }

    @Override
    public int getCount() {
        return typeList != null ? typeList.size() : 0;
    }

}