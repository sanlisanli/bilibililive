package me.mikasa.bilibililive.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import me.mikasa.bilibililive.R;
import me.mikasa.bilibililive.fragment.indexFragment.GameFragment;
import me.mikasa.bilibililive.fragment.indexFragment.LiveFragment;
import woo.mikasa.lib.base.BaseFragment;

/**
 * Created by mikasa on 2018/12/31.
 */
public class MainFragment extends BaseFragment {
    private static MainFragment instance;
    private String[] titles;
    private List<Fragment>fragmentList=new ArrayList<>();
    public static MainFragment getInstance(){
        if (instance==null){
            instance=new MainFragment();
        }
        return instance;
    }
    @Override
    protected int setLayoutResId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initData(Bundle bundle) {
        titles=mBaseActivity.getResources().getStringArray(R.array.MainTabTitle);
        fragmentList.add(LiveFragment.getInstance());
        fragmentList.add(GameFragment.getInstance());
    }

    @Override
    protected void initView() {
        TabLayout tabLayout=mRootView.findViewById(R.id.main_tab);
        ViewPager viewPager=mRootView.findViewById(R.id.main_vp);
        MainVpAdapter adapter=new MainVpAdapter(getChildFragmentManager());//child
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void setListener() {

    }
    class MainVpAdapter extends FragmentPagerAdapter{
        MainVpAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
