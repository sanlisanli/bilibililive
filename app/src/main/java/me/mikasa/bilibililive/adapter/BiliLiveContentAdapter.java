package me.mikasa.bilibililive.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import me.mikasa.bilibililive.Constant.Constants;
import me.mikasa.bilibililive.R;
import me.mikasa.bilibililive.bean.BiliLiveContent;
import me.mikasa.bilibililive.util.CommonUtil;

/**
 * Created by mikasa on 2018/12/31.
 */
public class BiliLiveContentAdapter extends BaseMultiItemQuickAdapter<BiliLiveContent,BaseViewHolder> {
    public BiliLiveContentAdapter(List<BiliLiveContent>data){
        super(data);
        addItemType(Constants.BILI_LIVE_TITLE,R.layout.item_live_title);
        addItemType(Constants.BILI_LIVE_CONTENT,R.layout.item_live_content);
        addItemType(Constants.BILI_LIVE_MORE,R.layout.item_live_more);
    }
    @Override
    protected void convert(BaseViewHolder helper, BiliLiveContent item) {
        switch (helper.getItemViewType()){
            case Constants.BILI_LIVE_TITLE:
                helper.setText(R.id.title_live,item.getTitleName());
                String s="当前"+CommonUtil.formatCount(item.getCount())+"个直播";
                helper.setText(R.id.tv_title_count,s);
                Glide.with(mContext).load(item.getSub_icon()).into((ImageView)helper.getView(R.id.title_sub_icon));
                break;
            case Constants.BILI_LIVE_CONTENT:
                helper.setText(R.id.tv_content_title,item.getTitle());
                helper.setText(R.id.tv_userName,item.getName());
                helper.setText(R.id.guankancount,String.valueOf(item.getOnline()));
                Glide.with(mContext).load(item.getSrc())
                        .crossFade()
                        .placeholder(R.drawable.ic_next_video_placeholder)
                        .error(R.drawable.img_tips_error_banner_tv)
                        .into((ImageView)helper.getView(R.id.imgFace));
                Glide.with(mContext).load(item.getFace())
                        .into((ImageView)helper.getView(R.id.iv_avatar));
                break;
            case Constants.BILI_LIVE_MORE:
                String rand=CommonUtil.getRandom()+"条新动态，点击刷新！";
                helper.setText(R.id.tv_content_more,rand);
                break;
            default:
                break;
        }
    }
}
