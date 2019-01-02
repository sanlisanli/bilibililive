package me.mikasa.bilibililive.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by mikasa on 2018/12/31.
 * 一个title+四个live+一个footer
 */
public class BiliLiveContent implements MultiItemEntity {
    private int itemtype;
    //liveTitle
    private String titleName;//key:name,直播类别
    private int count;//当前直播数量
    private String sub_icon;//直播类别图标
    public void setTitleName(String s){
        this.titleName=s;
    }
    public String getTitleName(){
        return titleName;
    }
    public void setCount(int i){
        this.count=i;
    }
    public int getCount(){
        return count;
    }
    public void setSub_icon(String s){
        this.sub_icon=s;
    }
    public String getSub_icon(){
        return sub_icon;
    }
    //liveContent
    private String title;//key:title，直播title
    private int online;//在线观看人数
    private String playurl;//直播视频url
    private int room_id;
    private String face;//作者头像
    private String src;//直播封面
    private String name;//作者名字
    public void setName(String s){
        this.name=s;
    }
    public String getName(){
        return name;
    }
    public void setFace(String s){
        this.face=s;
    }
    public String getFace(){
        return face;
    }

    public void setSrc(String s){
        this.src=s;
    }
    public String getSrc(){
        return src;
    }
    public void setRoom_id(int i){
        this.room_id=i;
    }
    public int getRoom_id(){
        return room_id;
    }
    public void setPlayurl(String s){
        this.playurl=s;
    }
    public String getPlayurl(){
        return playurl;
    }
    public void setTitle(String s){
        this.title=s;
    }
    public String getTitle(){
        return title;
    }
    public void setOnline(int i){
        this.online=i;
    }
    public int getOnline(){
        return online;
    }
    public void setItemtype(int i){
        this.itemtype=i;
    }
    public int getItemtype(){
        return itemtype;
    }

    @Override
    public int getItemType() {
        return itemtype;
    }
}
