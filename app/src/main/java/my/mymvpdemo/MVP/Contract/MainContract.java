package my.mymvpdemo.MVP.Contract;

import java.util.Map;

/**
 * Created by kw on 2018/7/25.
 */

public interface MainContract {
    interface View {
        void settoastToAct(String sampleInfo);
        void setTextCity(String city);
    }
    interface Presenter {
        void toastTomodle();
        void togetData(Map map);
    }
    interface Model{
        void getModelSucce();
        void getCitys(Map map);

        interface modleToastListner{
            void success(String ss);
            void citySuccess(String ss);
        }
    }
}
