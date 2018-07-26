package my.mymvpdemo.MVP.PresenterImpl;

import android.content.Context;

import java.util.Map;

import my.mymvpdemo.Base.BasePresentImpl;
import my.mymvpdemo.MVP.Contract.MainContract;
import my.mymvpdemo.MVP.ModleImpl.MainModleImpl;

/**
 * Created by kw on 2018/7/25.
 */

public class MainPresenterImpl extends BasePresentImpl<MainContract.View> implements MainContract.Presenter,MainContract.Model.modleToastListner{
    private Context context;
    private MainContract.Model model;
    private MainContract.View view;
    public MainPresenterImpl(Context context,MainContract.View view){
        this.context=context;
        this.view=view;
        model=new MainModleImpl(context,this);
    }
    @Override
    public void fetch() {
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void success(String ss) {
        view.settoastToAct(ss);
    }

    @Override
    public void citySuccess(String ss) {
        view.setTextCity(ss);
    }

    @Override
    public void toastTomodle() {
        model.getModelSucce();
    }

    @Override
    public void togetData(Map map) {
        model.getCitys(map);
    }
}
