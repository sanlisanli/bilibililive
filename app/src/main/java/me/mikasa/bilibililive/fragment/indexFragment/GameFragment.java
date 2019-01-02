package me.mikasa.bilibililive.fragment.indexFragment;

import android.os.Bundle;

import me.mikasa.bilibililive.R;
import woo.mikasa.lib.base.BaseFragment;

/**
 * Created by mikasa on 2018/12/31.
 */
public class GameFragment extends BaseFragment {
    private static GameFragment instance;
    public static GameFragment getInstance(){
        if (instance==null){
            instance=new GameFragment();
        }
        return instance;
    }
    @Override
    protected int setLayoutResId() {
        return R.layout.fragment_game;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }
}
