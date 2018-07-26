package my.mymvpdemo.MVP.ModleImpl;

import android.app.Activity;
import android.content.Context;

import java.util.List;
import java.util.Map;

import my.mymvpdemo.Bean.CityBean;
import my.mymvpdemo.Http.HttpManager.ProgressSubscriber;
import my.mymvpdemo.Http.HttpManager.SubscriberOnNextListenter;
import my.mymvpdemo.Http.HttpMethods;
import my.mymvpdemo.MVP.Contract.MainContract;

/**
 * Created by kw on 2018/7/25.
 * 方法实现的类
 */

public class MainModleImpl implements MainContract.Model {
    private MainContract.Model.modleToastListner listner;
    private Context context;
    public MainModleImpl(Context context,MainContract.Model.modleToastListner listner){
        this.context=context;
        this.listner=listner;
    }

    @Override
    public void getModelSucce() {
        listner.success("点击成功a aa ");
    }

    @Override
    public void getCitys(Map map) {
        HttpMethods.getInstance().getCity(map,new ProgressSubscriber<List<CityBean>>((Activity)context, true, new SubscriberOnNextListenter<List<CityBean>>() {
            @Override
            public void next(List<CityBean> cityBeen) {
                String s="";
                for(int i=0;i<cityBeen.size();i++){
                    s=s+" "+cityBeen.get(i).getCity();
                }
                listner.citySuccess(s);
            }
            @Override
            public void getErr(int i) {

            }
        }));
    }
}
