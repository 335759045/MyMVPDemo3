package my.mymvpdemo.MVP.UI;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import my.mymvpdemo.Base.BaseActivity;
import my.mymvpdemo.MVP.Contract.MainContract;
import my.mymvpdemo.MVP.PresenterImpl.MainPresenterImpl;
import my.mymvpdemo.R;

public class MainActivity extends BaseActivity<MainContract.View, MainPresenterImpl> implements MainContract.View {
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.text)
    TextView text;

    @Override
    protected MainPresenterImpl createPresent() {
        return new MainPresenterImpl(this, this);
    }

    @Override
    protected void initTitle() {
        setTitle("测试");
    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void setUpData() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean getretunData() {
        return false;
    }

    @Override
    protected void getData() {
        Map<String,String > map=new HashMap<>();
        map.put("aa","测试");
        mPresent.togetData(map);
    }
    @Override
    protected void reFreshData() {
        getData();
    }

    @OnClick({R.id.btn1, R.id.btn2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                mPresent.toastTomodle();
                break;
            case R.id.btn2:
                getData();
                break;
        }
    }

    @Override
    public void settoastToAct(String sampleInfo) {
        text.setText(sampleInfo);
    }
    @Override
    public void setTextCity(String city) {
        initChild();
        text.setText(city);
    }
}
