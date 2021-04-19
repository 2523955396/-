package com.HUANSHI.HQW;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.HUANSHI.HQW.BaseAdapter.HQWGridview;
import com.HUANSHI.HQW.BaseAdapter.HQWListView;
import com.HUANSHI.HQW.List.gridviewlist;
import com.HUANSHI.HQW.List.hqwlist;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SHUAXIN extends home implements View.OnClickListener {
    Context context;
    int ifo=0;
    int urlone=0;
    int urltwo=0;


//    LinearLayout home_two_footer;
//    ImageView home_two_footeri;
    int fotter;
    EditText edit;
    TextView sousuo;
    TextView chongzhi;

    List<hqwlist> hqwlists;
    HQWListView hqwListView;
    View view1;

    List<gridviewlist> gridviewlists;
    HQWGridview hqwGridview;
    View view2;

    RefreshLayout refreshLayout;
    RefreshLayout refreshLayout_home_two;
    public void shuaxin(
            Context context,
            RefreshLayout refreshLayout,
            RefreshLayout refreshLayout_home_two,
            ViewPager viewPager,
            List<hqwlist> hqwlists,
            ListView listView ,
            HQWListView hqwListView,
            GridView gridView,
            List<gridviewlist> gridviewlists,
            HQWGridview hqwGridview,
            View view1,
            View view2,
            View view3){
        this.hqwListView=hqwListView;
        this.hqwlists=hqwlists;
        this.view1=view1;

        this.hqwGridview=hqwGridview;
        this.gridviewlists=gridviewlists;
        this.view2=view2;

        this.context=context;

//        home_two_footer=view2.findViewById(R.id.home_two_footer);
//        home_two_footeri=view2.findViewById(R.id.home_two_footeri);
//        home_two_footer.setOnClickListener(this);
//        home_two_footeri.setOnClickListener(this);

        edit=view2.findViewById(R.id.home_two_edit);
        sousuo=view2.findViewById(R.id.home_two_sousuo);
        chongzhi=view2.findViewById(R.id.home_two_chongzhi);

        sousuo.setOnClickListener(this);
        chongzhi.setOnClickListener(this);

        this.refreshLayout=refreshLayout;
        this.refreshLayout_home_two=refreshLayout_home_two;


        new url_one_two_shree().url_one(hqwListView,listView,hqwlists,urlone,view1);
        urlone+=10;
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position==1){
                    if (ifo==0){
                        new url_one_two_shree().url_two(gridView,gridviewlists,hqwGridview,urltwo,view2);
                        System.out.println("执行了");
                        urltwo += 15;
                        ifo=1;
                    }
                }
            }
            @Override
            public void onPageSelected(int position) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        shuaxin();
        edit();
        new SHEZHI(context,view3);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.home_two_footeri:
//                start_end();
//                break;
            case R.id.home_two_sousuo:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hqwGridview.gridviewlists.removeAll(hqwGridview.gridviewlists);
                        hqwGridview.notifyDataSetChanged();
                    }
                });
                new url_one_two_shree().url_twoss(hqwGridview,edit.getText().toString(),view2,context);
                edit.setText("");
                break;
            case R.id.home_two_chongzhi:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hqwGridview.gridviewlists.removeAll(hqwGridview.gridviewlists);
                        hqwGridview.notifyDataSetChanged();
                    }
                });
                new url_one_two_shree().url_twoss(hqwGridview,"",view2,context);
                break;

        }
    }


    public void shuaxin(){
        //HOME_ONE的刷新头
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hqwListView.hqwlists.removeAll(hqwListView.hqwlists);
                        hqwListView.notifyDataSetChanged();
                    }
                });
                urlone=0;
                new url_one_two_shree().url_onei(hqwListView,urlone,view1);
                urlone+=10;
            }
        });
        //HOME_ONE的刷新尾
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                new url_one_two_shree().url_onei(hqwListView,urlone,view1);
                urlone+=10;
            }
        });
        //HOME_TWO的刷新头

        refreshLayout_home_two.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hqwGridview.gridviewlists.removeAll(hqwGridview.gridviewlists);
                        hqwGridview.notifyDataSetChanged();
                    }
                });
                urltwo=0;
                new url_one_two_shree().url_twoi(hqwGridview,urltwo,view2);
                urltwo+=15;
            }
        });
        //HOME_TWO的刷新尾
        refreshLayout_home_two.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                new url_one_two_shree().url_twoi(hqwGridview,urltwo,view2);
                urltwo+=15;
            }
        });

    }

    public void edit(){
        edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId==EditorInfo.IME_ACTION_SEARCH||(event!=null&event.getKeyCode()==KeyEvent.KEYCODE_ENTER))
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hqwGridview.gridviewlists.removeAll(hqwGridview.gridviewlists);
                            hqwGridview.notifyDataSetChanged();
                        }
                    });
                    new url_one_two_shree().url_twoss(hqwGridview,edit.getText().toString(),view2,context);
                    edit.setText("");

                    return false;
                }
                return false;
            }
        });
    }
//    public void start_end(){
//        if (fotter!=50) {
//            Timer timer = new Timer();
//            TimerTask timerTask = new TimerTask() {
//                @Override
//                public void run() {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (fotter == 50) {
//                                timer.cancel();
//                            } else {
//                                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) home_two_footer.getLayoutParams();
//                                lp.topMargin = -(dip2px(context, fotter));
//                                home_two_footer.setLayoutParams(lp);
//                                fotter += 1;
//                            }
//                        }
//                    });
//
//                }
//            };
//            timer.schedule(timerTask, 0, 1);
//        }
//        else{
//            Timer timer = new Timer();
//            TimerTask timerTask = new TimerTask() {
//                @Override
//                public void run() {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (fotter == 19) {
//                                timer.cancel();
//                            } else {
//                                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) home_two_footer.getLayoutParams();
//                                lp.topMargin = -(dip2px(context, fotter));
//                                home_two_footer.setLayoutParams(lp);
//                                fotter -= 1;
//                            }
//                        }
//                    });
//
//                }
//            };
//            timer.schedule(timerTask, 0, 1);
//
//        }
//    }

    private int dip2px(Context context, float dipValue) {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dipValue, r.getDisplayMetrics());
    }


    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }




}
