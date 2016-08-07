package com.lego.geektask2.adapter;


import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lego.geektask2.R;
import com.lego.geektask2.fragment.PageFragment;

/**
 * @author Lego on 07.08.2016.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"PALINDROME", "FACTORIAL", "PAIRS"};
    private Context context;

    public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

    public View getTabView(int position) {
        // Given you have a custom layout in `res/layout/custom_tab.xml` with a TextView and ImageView
        View v = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
        TextInputLayout inputLayoutName = (TextInputLayout) v.findViewById(R.id.inputText);
        if (position == 3) {
            inputLayoutName.setHint("Enter The pairs (Example 13 14 34 56)");
        } else {
            inputLayoutName.setHint("Enter the number");
        }
        return v;
    }
}
