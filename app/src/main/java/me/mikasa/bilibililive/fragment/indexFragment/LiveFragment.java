package me.mikasa.bilibililive.fragment.indexFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.mikasa.bilibililive.Constant.Constants;
import me.mikasa.bilibililive.R;
import me.mikasa.bilibililive.activity.PlayerActivity;
import me.mikasa.bilibililive.activity.WebViewActivity;
import me.mikasa.bilibililive.adapter.BiliLiveContentAdapter;
import me.mikasa.bilibililive.bean.BiliLive;
import me.mikasa.bilibililive.bean.BiliLiveBanner;
import me.mikasa.bilibililive.bean.BiliLiveContent;
import me.mikasa.bilibililive.network.RequestManager;
import me.mikasa.bilibililive.util.BiliLiveUtil;
import me.mikasa.bilibililive.util.Logger;
import woo.mikasa.lib.base.BaseFragment;

/**
 * Created by mikasa on 2018/12/31.
 */
public class LiveFragment extends BaseFragment {
    private static LiveFragment instance;
    private RecyclerView recyclerView;
    private BiliLiveContentAdapter adapter;
    private List<BiliLiveContent>contentList=new ArrayList<>();
    public static LiveFragment getInstance(){
        if (instance==null){
            instance=new LiveFragment();
        }
        return instance;
    }
    @Override
    protected int setLayoutResId() {
        return R.layout.fragment_live;
    }

    @Override
    protected void initData(Bundle bundle) {
    }

    @Override
    protected void initView() {
        recyclerView=mRootView.findViewById(R.id.rv_live);
        adapter=new BiliLiveContentAdapter(contentList);
        adapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                /* 关键内容：通过 setSpanSizeLookup 来告诉布局，你的 item 占几个横向单位，
                    如果你横向有 2 个单位，而你返回当前 item 占用 2 个单位，那么它就会看起来单独占用一行 */
                if (contentList.get(position).getItemType()==Constants.BILI_LIVE_CONTENT){
                    return 1;//是网格
                }
                return 2;
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(mBaseActivity,2));//layoutManage
        recyclerView.setAdapter(adapter);//adapter
    }
    private void getLiveData(){
        RequestManager.getInstance().getBiliLiveClient()
                .getBiliLive("android", "android", "xxhdpi")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BiliLive>() {
                    @Override
                    public void accept(BiliLive biliLive) throws Exception {
                        initBanner(biliLive);
                        loadData(biliLive);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        showToast("网络数据请求失败");
                    }
                });
    }
    private void initBanner(BiliLive biliLive){
        final List<BiliLiveBanner>bannerList=BiliLiveUtil.getBiliLiveBanner(biliLive);
        View bannerView=LayoutInflater.from(mBaseActivity).inflate(R.layout.layout_live_header,null);
        Banner banner=bannerView.findViewById(R.id.live_banner);
        //banner.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, MyApp.heightPixels / 6));
        banner.setImages(bannerList)
                .setImageLoader(new BannerLoader())
                .setIndicatorGravity(BannerConfig.RIGHT)
                .setBannerAnimation(Transformer.DepthPage)
                .start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent=new Intent(mBaseActivity,WebViewActivity.class);
                intent.putExtra("link",bannerList.get(position).getLink());
                startActivity(intent);
            }
        });
        adapter.addHeaderView(bannerView);
    }
    private void loadData(BiliLive biliLive){
        contentList=BiliLiveUtil.getBiliLiveContent(biliLive);
        adapter.setNewData(contentList);
    }

    @Override
    protected void setListener() {
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.cv_live_content:
                        Intent intent=new Intent(mBaseActivity,PlayerActivity.class);
                        intent.putExtra("title",contentList.get(position).getTitle());
                        intent.putExtra("src",contentList.get(position).getSrc());
                        intent.putExtra("playurl",contentList.get(position).getPlayurl());
                        startActivity(intent);
                        break;
                }
            }
        });
        //请求网络数据
        getLiveData();
    }
    class BannerLoader extends ImageLoader{
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(mBaseActivity)
                    .load(((BiliLiveBanner)path).getImg())
                    .crossFade()
                    .placeholder(R.drawable.bili_default_image_tv)
                    .error(R.drawable.img_tips_error_banner_tv)
                    .into(imageView);
        }
    }

}
