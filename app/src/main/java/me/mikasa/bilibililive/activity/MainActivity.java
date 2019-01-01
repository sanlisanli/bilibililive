package me.mikasa.bilibililive.activity;

import android.Manifest;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import me.mikasa.bilibililive.R;
import me.mikasa.bilibililive.fragment.MainFragment;
import woo.mikasa.lib.base.BaseActivity;

public class MainActivity extends BaseActivity implements BaseActivity.PermissionListener {

    @Override
    protected int setLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        Toolbar toolbar=findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        String[] permissions={
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE};
        requestRuntimePermission(permissions,this);
    }
    private void initFragment(){
        MainFragment mainFragment=MainFragment.getInstance();
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction tf=manager.beginTransaction();
        tf.add(R.id.fl_main,mainFragment);
        tf.commit();
    }

    @Override
    protected void initListener() {
    }

    @Override
    public void onGranted() {
        initFragment();
    }

    @Override
    public void onDenied() {
        finish();
    }
}
