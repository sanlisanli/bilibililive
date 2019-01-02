package me.mikasa.bilibililive.util;

import java.util.ArrayList;
import java.util.List;

import me.mikasa.bilibililive.Constant.Constants;
import me.mikasa.bilibililive.bean.BiliLive;
import me.mikasa.bilibililive.bean.BiliLiveBanner;
import me.mikasa.bilibililive.bean.BiliLiveContent;

/**
 * Created by mikasa on 2018/12/31.
 */
public class BiliLiveUtil {
    public static List<BiliLiveContent>getBiliLiveContent(BiliLive biliLive){
        List<BiliLive.Data.Partitions>partitionsList=biliLive.getData().getPartitions();//直播类别总数partitions
        List<BiliLiveContent>contentList=new ArrayList<>(partitionsList.size()*6+6);
        for (int i=0;i<partitionsList.size();i++){
            BiliLive.Data.Partitions.Partition partition=partitionsList.get(i).getPartition();//第i个直播类别
            BiliLiveContent bean=new BiliLiveContent();
            bean.setItemtype(Constants.BILI_LIVE_TITLE);//itemtype-->title-->1
            bean.setTitleName(partition.getName());//直播类别title
            bean.setCount(partition.getCount());//直播数目
            bean.setSub_icon(partition.getSub_icon().getSrc().replaceAll("\\\\",""));//直播类别图标
            contentList.add(bean);//加一个直播类别title
            List<BiliLive.Data.Partitions.Lives>livesList=partitionsList.get(i).getLives();//第i个直播类别下的直播
            for (int j=0;j<4;j++){
                bean=new BiliLiveContent();//重置
                BiliLive.Data.Partitions.Lives live=livesList.get(j);//第i个直播类别下的第j个直播
                bean.setItemtype(Constants.BILI_LIVE_CONTENT);//itemtype-->live-->2
                bean.setSrc(live.getCover().getSrc().replaceAll("\\\\",""));//直播封面
                bean.setTitle(live.getTitle());//直播title
                bean.setName(live.getOwner().getName());//作者名字
                bean.setOnline(live.getOnline());//在线观看人数
                bean.setFace(live.getOwner().getFace().replaceAll("\\\\",""));//作者头像
                bean.setPlayurl(live.getPlayurl().replaceAll("\\\\",""));//替换掉转义符
                contentList.add(bean);//循环添加四个live
            }
            //加一个footer
            bean=new BiliLiveContent();//重置
            bean.setItemtype(Constants.BILI_LIVE_MORE);//itemtype--footer--3
            contentList.add(bean);//加一个空bean
        }
        return contentList;
    }
    public static List<BiliLiveBanner>getBiliLiveBanner(BiliLive biliLive){
        List<BiliLive.Data.Banner>banners=biliLive.getData().getBanner();
        List<BiliLiveBanner>bannerList=new ArrayList<>(banners.size());
        for (int i=0;i< banners.size();i++){
            BiliLiveBanner banner=new BiliLiveBanner();
            banner.setImg(banners.get(i).getImg().replaceAll("\\\\",""));
            banner.setLink(banners.get(i).getLink().replaceAll("\\\\",""));
            banner.setTitle(banners.get(i).getTitle());
            bannerList.add(banner);
        }
        return bannerList;
    }
}
