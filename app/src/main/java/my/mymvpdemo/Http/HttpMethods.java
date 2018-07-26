package my.mymvpdemo.Http;

import java.util.List;
import java.util.Map;

import io.reactivex.observers.DisposableObserver;
import my.mymvpdemo.API.ApiBasic;
import my.mymvpdemo.Bean.CityBean;
import my.mymvpdemo.Bean.CodeBean;
import my.mymvpdemo.Http.HttpManager.HttpResultFunc;
import my.mymvpdemo.Http.HttpManager.ObjectLoader;
import my.mymvpdemo.Http.HttpManager.RetrofitManager;


/**方法类
 * Created by Administrator on 2017/12/19.
 */

public class HttpMethods extends ObjectLoader {
    private static ApiBasic moService;
    public HttpMethods(){
        moService = RetrofitManager.getInstance().create(ApiBasic.class);
    }
    public static HttpMethods getInstance(){
        return new HttpMethods();
    }

    /**
     * 登录
     */
    public void login(Map<String,String> params, DisposableObserver<CodeBean> subscriber){
        observe(moService.getCode(params)).map(new HttpResultFunc<CodeBean>()).subscribe(subscriber);
//        moService.login2(params)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .map( new HttpResultFunc())
//                .subscribe(subscriber);
    }
    /**
     * 获取城市列表
     */
    public void getCity(Map<String,String> params, DisposableObserver<List<CityBean>> subscriber){
        observe(moService.getCity()).map(new HttpResultFunc<List<CityBean>>()).subscribe(subscriber);
    }
}