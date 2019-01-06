package me.mikasa.bilibililive.network;

import me.mikasa.bilibililive.Constant.Constants;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mikasa on 2018/12/31.
 */
public class RequestManager {
    private static RequestManager requestManager;
    private RequestManager(){}
    //单例模式，推荐
    public static RequestManager getInstance(){
        if (requestManager==null){
            synchronized (RequestManager.class){
                if (requestManager==null){
                    requestManager=new RequestManager();
                }
            }
        }
        return requestManager;
    }
    public IRetrofitClient getBiliLiveClient(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_LIVE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(IRetrofitClient.class);
    }
    public IRetrofitClient getPandaClient(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_PANDA_GAME)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(IRetrofitClient.class);
    }

}
