package my.mymvpdemo.Base;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.ButterKnife;
import my.mymvpdemo.Common.Constans;
import my.mymvpdemo.R;
import my.mymvpdemo.Utils.ActivityManager;
import my.mymvpdemo.Utils.SysApplication;
import my.mymvpdemo.Utils.ToastUtil;

public abstract class BaseActivity<V, P extends BasePresentImpl<V>>extends AutoLayoutActivity implements OnClickListener{
    ImageView btn_left;
    TextView btn_right;
    LinearLayout ly_car;
    TextView tv_title;
    RelativeLayout title_R;
    AutoLinearLayout ly_content;
    // 内容区域的布局
    private View contentView;
    private Toast toast;
    /**
     * P层引用
     */
    protected P mPresent;
    private ActivityManager activityManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//隐藏自带得标题
        SysApplication.getInstance().addActivity(this);
        setContentView(R.layout.act_title_main);
        btn_left= (ImageView) findViewById(R.id.act_title_main_left);
        btn_right= (TextView) findViewById(R.id.act_title_main_right);
        ly_car= (LinearLayout) findViewById(R.id.act_title_main_car);
        tv_title= (TextView) findViewById(R.id.act_title_main_content);
        title_R= (RelativeLayout) findViewById(R.id.act_title_main_R);
        ly_content= (AutoLinearLayout) findViewById(R.id.titleView);
        setOnclick();
        initTitle();

        mPresent = createPresent();
        //做绑定关联View
        mPresent.attachView((V) this);
//        Activity的管理，将Activity压入栈
        activityManager = ActivityManager.getScreenManager();
        activityManager.pushActivity(this);

        if(getretunData()){//如果调过获取数据就直接加载layout
            initChild();
        }else{//获取数据
            getData();
        }

        //为了适配摩托罗拉的pop展示问题获取到标题栏和状态栏的高度。
        title_R.measure(0, 0);
        //获取高度
        int height = title_R.getMeasuredHeight();
        int statusBarHeight1 = -1;
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
        }
        Constans.BASE_TITLE_HEIGHT = height + statusBarHeight1;
    }
    /*
     * @params
     * @name 子类实现具体的构建过程
     */
    protected abstract P createPresent();

    /**
     * 获取数据后执行的初始化界面
     */
    public void initChild() {
        setContentLayout();
        ButterKnife.bind(BaseActivity.this);
        setUpView();
        setUpData();
    }
    private void setOnclick() {
        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);
        ly_car.setOnClickListener(this);
        tv_title.setOnClickListener(this);
    }

    /**
     * 界面中间Toast消息
     * @param str
     */
    public void toastShow2(String str) {
        ToastUtil.createToastConfig().ToastShow(getApplicationContext(),str);
    }
    /**
     * 普通显示Toast消息
     * @param str
     */
    public void toastShow(String str) {
        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
    }
    public void startIntentAct(Context context,Class<?> tClass){
        Intent intent=new Intent(context,tClass);
        startActivity(intent);
    }

    /**
     * 初始化标题栏
     */
    protected abstract void initTitle();

    protected abstract void setUpView();

    /**
     * 设置数据
     * @return
     */
    protected abstract void setUpData();
    protected abstract int setLayoutResourceID();
    /**
     * 是否跳过数据请求直接加载界面布局
     * @return
     */
    protected abstract boolean getretunData();
    /**
     * 获取数据
     * @return
     */
    protected abstract void getData();
    protected abstract void reFreshData();
    /***
     * 设置内容区域
     *
     * @param资源文件ID
     */
    protected void setContentLayout() {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(setLayoutResourceID(), null);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.FILL_PARENT);
        contentView.setLayoutParams(layoutParams);
        contentView.setBackgroundDrawable(null);
        if (null != ly_content) {
            ly_content.addView(contentView);
        }

    }

    /***
     * 设置内容区域
     *
     * @param view View对象
     */
    public void setContentLayout(View view) {
        if (null != ly_content) {
            ly_content.addView(view);
        }
    }

    /**
     * 得到内容的View
     *
     * @return
     */
    public View getLyContentView() {

        return contentView;
    }

    /**
     * 得到左边的按钮
     *
     * @return
     */
    public ImageView getbtn_left() {
        return btn_left;
    }

    /**
     * 得到右边的按钮
     *
     * @return
     */
    public TextView getbtn_right() {
        return btn_right;
    }

    /**
     * 得到右边的购物车按钮
     *
     * @return
     */
    public LinearLayout getbtn_car() {
        return ly_car;
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {

        if (null != tv_title) {
            tv_title.setText(title);
        }

    }


    public TextView getTv_title() {
        return tv_title;
    }

    /**
     * 设置标题
     *
     * @param resId
     */
    public void setTitle(int resId) {
        tv_title.setText(getString(resId));
    }

    /**
     * 设置左边按钮的图片资源
     *
     * @param resId
     */
    public void setbtn_leftRes(int resId) {
        if (null != btn_left) {
            btn_left.setBackgroundResource(resId);
        }

    }

    /**
     * 设置左边按钮的图片资源
     *
     * @param
     */
    public void setbtn_leftRes(Drawable drawable) {
        if (null != btn_left) {
            btn_left.setBackgroundDrawable(drawable);
        }

    }

    /**
     * 设置右边按钮的图片资源
     *
     * @param resId
     */
    public void setbtn_rightRes(int resId) {
        if (null != btn_right) {
            btn_right.setBackgroundResource(resId);
        }
    }

    /**
     * 设置右边按钮的图片资源
     *
     * @param drawable
     */
    public void setbtn_rightRes(Drawable drawable) {
        if (null != btn_right) {
            btn_right.setBackgroundDrawable(drawable);
        }
    }

    /**
     * 隐藏上方的标题栏
     */
    public void hideTitleView() {

        title_R.setVisibility(View.GONE);
    }

    public View getTitleView() {
        return title_R;
    }

    /**
     * 隐藏左边的按钮
     */
    public void hidebtn_left() {

        if (null != btn_left) {
            btn_left.setVisibility(View.GONE);
        }

    }

    /***
     * 隐藏右边的按钮
     */
    public void hidebtn_right() {
        if (null != btn_right) {
            btn_right.setVisibility(View.GONE);
        }

    }

    public BaseActivity() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //这里可以做挤下线后重新登陆后刷新数据resultCode自定义
        if(resultCode==20100){
            initChild();
            reFreshData();
        }
    }

    @Override
    protected void onDestroy() {
        mPresent.detach();
        activityManager.popActivity(this);
        super.onDestroy();
    }
    @Override
    public void onClick(View v) {

    }
}
