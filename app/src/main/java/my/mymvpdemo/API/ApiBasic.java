package my.mymvpdemo.API;


import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import my.mymvpdemo.Bean.CityBean;
import my.mymvpdemo.Bean.CodeBean;
import my.mymvpdemo.Bean.HttpResult;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/12/14.
 */

public interface ApiBasic {
    @POST("sp/user/sendCode")
    Observable<HttpResult<CodeBean>> getCode(@QueryMap Map<String, String> map);
    @FormUrlEncoded
    @POST("sp/user/sendVoiceCode")
    Observable<HttpResult> getVoiceCode(@FieldMap Map<String, String> map);
    @GET("sp/user/getPaoDaoCity")
    Observable<HttpResult<List<CityBean>>> getCity();
}
