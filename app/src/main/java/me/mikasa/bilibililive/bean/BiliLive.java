package me.mikasa.bilibililive.bean;

import java.util.List;

/**
 * Created by mikasa on 2018/12/31.
 */
public class BiliLive {
    private int code;
    private String message;
    private Data data;
    public void setCode(int i){
        this.code=i;
    }
    public int getCode(){
        return code;
    }
    public void setMessage(String s){
        this.message=s;
    }
    public String getMessage(){
        return message;
    }
    public void setData(Data d){
        this.data=d;
    }
    public Data getData(){
        return data;
    }
    public static class Data{
        private List<Banner>banner;//key:banner
        private List<Partitions>partitions;//key:partitions
        public void setBanner(List<Banner>beans){
            this.banner=beans;
        }
        public List<Banner>getBanner(){
            return banner;
        }
        public void setPartitions(List<Partitions>partitionsBeans){
            this.partitions=partitionsBeans;
        }
        public List<Partitions>getPartitions(){
            return partitions;
        }
        public static class Banner{
            private String title;
            private String link;
            private String img;
            public void setLink(String s){
                this.link=s;
            }
            public String getLink(){
                return link;
            }
            public void setImg(String s){
                this.img=s;
            }
            public String getImg(){
                return img;
            }
            public void setTitle(String s){
                this.title=s;
            }
            public String getTitle(){
                return title;
            }
        }
        public static class Partitions {
            private Partition partition;//key:partition
            private List<Lives>lives;//key:lives
            public void setPartition(Partition bean){
                this.partition=bean;
            }
            public Partition getPartition(){
                return partition;
            }
            public void setLives(List<Lives> beans){
                this.lives=beans;
            }
            public List<Lives>getLives(){
                return lives;
            }
            public static class Partition{
                private String name;//key:name
                private int count;
                private Sub_icon sub_icon;
                private int id;
                public void setId(int i){
                    this.id=i;
                }
                public int getId(){
                    return id;
                }
                public void setName(String s){
                    this.name=s;
                }
                public String getName(){
                    return name;
                }
                public void setCount(int i){
                    this.count=i;
                }
                public int getCount(){
                    return count;
                }
                public void setSub_icon(Sub_icon icon){
                    this.sub_icon=icon;
                }
                public Sub_icon getSub_icon(){
                    return sub_icon;
                }
                public static class Sub_icon{
                    private String src;
                    public void setSrc(String s){
                        this.src=s;
                    }
                    public String getSrc(){
                        return src;
                    }
                }
            }
            public static class Lives{
                private String title;//key:title
                private int online;
                private Owner owner;
                private Cover cover;
                private String playurl;
                private int room_id;
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
                public void setOwner(Owner o){
                    this.owner=o;
                }
                public Owner getOwner(){
                    return owner;
                }
                public Cover getCover(){
                    return cover;
                }
                public void setCover(Cover c){
                    this.cover=c;
                }
                public static class Owner{
                    private String face;
                    private String name;
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
                }
                public static class Cover{
                    private String src;
                    public void setSrc(String s){
                        this.src=s;
                    }
                    public String getSrc(){
                        return src;
                    }
                }
            }
        }
    }
}
