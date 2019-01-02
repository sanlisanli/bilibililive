package me.mikasa.bilibililive.network;

import io.reactivex.Observable;
import me.mikasa.bilibililive.bean.BiliLive;
import me.mikasa.bilibililive.bean.PandaGame;
import me.mikasa.bilibililive.bean.PandaGameList;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mikasa on 2018/12/31.
 */
public interface IRetrofitClient {
    /**
     * 直播
     */
    @GET("AppNewIndex/common")
    Observable<BiliLive>getBiliLive(@Query("_device") String _device,
                                    @Query("platform") String platform,
                                    @Query("scale") String scale);
    /**
     * 熊猫直播
     */
    @GET("index.php")
    Observable<PandaGame> getPaderGame(@Query("method") String method,
                                       @Query("type") String type);

    /*熊猫-gameList*/
//    http://api.m.panda.tv/ajax_get_live_list_by_cate?cate=lol&pageno=1&pagenum=10
    @GET("ajax_get_live_list_by_cate")
    Observable<PandaGameList> getPadaGameList(@Query("cate") String cate,
                                              @Query("pageno") int pageno,
                                              @Query("pagenum") int pagenum);
}
