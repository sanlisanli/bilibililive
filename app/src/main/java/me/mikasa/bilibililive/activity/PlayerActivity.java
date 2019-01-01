package me.mikasa.bilibililive.activity;

import android.content.res.Configuration;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;


import me.mikasa.bilibililive.R;
import me.mikasa.bilibililive.util.CommonUtil;
import woo.mikasa.lib.base.BaseActivity;


public class PlayerActivity extends BaseActivity{
    private PlayerView playerView;
    private String title,playurl,src;



    @Override
    protected int setLayoutResId() {
        return R.layout.activity_player;
    }

    @Override
    protected void initData() {
        title=getIntent().getStringExtra("title");
        playurl=getIntent().getStringExtra("playurl");
        src=getIntent().getStringExtra("src");
        //String[] permissions={Manifest.permission.WRITE_EXTERNAL_STORAGE};
    }

    @Override
    protected void initView() {
        CommonUtil.immersiveScreen(this);
        initPlayerView();
    }

    private void initPlayerView(){
        playerView=new PlayerView(this)
                .setTitle(title)
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
                        Glide.with(mContext)
                                .load(src).into(ivThumbnail);
                    }
                })
                .setPlaySource(playurl)
                .startPlay();
    }

    @Override
    protected void initListener() {
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (playerView!=null){
            playerView.onPause();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (playerView!=null){
            playerView.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (playerView!=null){
            playerView.onDestroy();
        }
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (playerView!=null){
            playerView.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if (playerView!=null&&playerView.onBackPressed()){
            return;
        }
        super.onBackPressed();
    }
}
