package me.mikasa.bilibililive.fragment.indexFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.mikasa.bilibililive.Constant.Constants;
import me.mikasa.bilibililive.R;
import me.mikasa.bilibililive.activity.PlayerActivity;
import me.mikasa.bilibililive.adapter.BiliLiveContentAdapter;
import me.mikasa.bilibililive.bean.BiliLive;
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
                        loadData(biliLive);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        showToast("网络数据请求失败");
                    }
                });
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
    private void netTest(){
        RequestManager.getInstance().getBiliLiveClient()
                .getBiliLive("android", "android", "xxhdpi")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BiliLive>() {
                    @Override
                    public void accept(BiliLive biliLive) throws Exception {
                        String s=biliLive.getData().getPartitions().get(1).getLives().get(2).getTitle();
                        Logger.i(s);
                        //netTest.setText(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        showToast("请求失败");
                    }
                });
    }
}
