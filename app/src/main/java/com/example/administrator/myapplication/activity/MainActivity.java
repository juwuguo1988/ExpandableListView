package com.example.administrator.myapplication.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.StatusExpandAdapter;
import com.example.administrator.myapplication.common.views.XExpandableListView;
import com.example.administrator.myapplication.common.views.XExpandableListView.IXListViewListener;
import com.example.administrator.myapplication.modle.DateStatusBean;
import com.example.administrator.myapplication.modle.RecordBean;
import com.example.administrator.myapplication.modle.SucGetRecordBean;
import com.example.administrator.myapplication.modle.TimeContentStatusBean;
import com.example.administrator.myapplication.utils.IssJsonToBean;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    private String jsonObj =
            "{\"status\":\"success\",\"data\":{\"records\":[{\"id\":38186,\"medicineId\":0,\"planId\":15467,\"medicineName\":\"络活喜\","
                    + "\"takeTime\":1495670400000,\"count\":1000,\"status\":0,\"source\":1,\"confirmedAt\":null,\"dosage\":20000},"
                    + "{\"id\":38329,\"medicineId\":0,\"planId\":15430,\"medicineName\":\"拜新同\",\"takeTime\":1495670400000,"
                    + "\"count\":1000,\"status\":0,\"source\":1,\"confirmedAt\":null,\"dosage\":20000},{\"id\":38237,\"medicineId\":0,"
                    + "\"planId\":15466,\"medicineName\":\"雅施达\",\"takeTime\":1495670400000,\"count\":1000,\"status\":0,\"source\":1,"
                    + "\"confirmedAt\":null,\"dosage\":20000},{\"id\":38253,\"medicineId\":0,\"planId\":15429,\"medicineName\":\"卡托普利\","
                    + "\"takeTime\":1495670400000,\"count\":1000,\"status\":0,\"source\":1,\"confirmedAt\":null,\"dosage\":2000},"
                    + "{\"id\":38304,\"medicineId\":0,\"planId\":15966,\"medicineName\":\"阿司匹林肠溶片\",\"takeTime\":1495670400000,"
                    + "\"count\":1000,\"status\":0,\"source\":1,\"confirmedAt\":null,\"dosage\":20000},{\"id\":38290,\"medicineId\":0,"
                    + "\"planId\":16166,\"medicineName\":\"波依定\",\"takeTime\":1495670400000,\"count\":2000,\"status\":0,\"source\":1,"
                    + "\"confirmedAt\":null,\"dosage\":20000},{\"id\":38305,\"medicineId\":0,\"planId\":15968,"
                    + "\"medicineName\":\"阿司匹林肠溶片\",\"takeTime\":1495663200000,"
                    + "\"count\":1000,\"status\":0,\"source\":1,\"confirmedAt\":null,\"dosage\":20000},{\"id\":38375,\"medicineId\":0,"
                    + "\"planId\":15967,\"medicineName\":\"阿司匹林肠溶片\",\"takeTime\":1495659600000,\"count\":1000,\"status\":0,\"source\":1,"
                    + "\"confirmedAt\":null,\"dosage\":20000},{\"id\":38289,\"medicineId\":0,\"planId\":16165,\"medicineName\":\"波依定\","
                    + "\"takeTime\":1495659600000,\"count\":1000,\"status\":0,\"source\":1,\"confirmedAt\":null,\"dosage\":20000},"
                    + "{\"id\":38106,\"medicineId\":0,\"planId\":16195,\"medicineName\":\"替米沙坦\",\"takeTime\":1495622700000,"
                    + "\"count\":1000,\"status\":2,\"source\":1,\"confirmedAt\":null,\"dosage\":20000},{\"id\":38097,\"medicineId\":0,"
                    + "\"planId\":16194,\"medicineName\":\"替米沙坦\",\"takeTime\":1495621800000,\"count\":1000,\"status\":2,\"source\":1,"
                    + "\"confirmedAt\":null,\"dosage\":20000},{\"id\":38107,\"medicineId\":0,\"planId\":16193,\"medicineName\":\"替米沙坦\","
                    + "\"takeTime\":1495619100000,\"count\":1000,\"status\":2,\"source\":1,\"confirmedAt\":null,\"dosage\":20000}]}}";
    private XExpandableListView mExpandableListView;
    private List<DateStatusBean> mDataList;
    private StatusExpandAdapter mStatusExpandAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mExpandableListView = (XExpandableListView) findViewById(R.id.exlv_listview);
        mExpandableListView.setPullLoadEnable(true);
        initData();
        setListener();
    }


    private void initData() {
        SucGetRecordBean bean = new IssJsonToBean<SucGetRecordBean>().parseToBean(jsonObj, SucGetRecordBean.class);
        Map<Integer, Map<Integer, List<RecordBean>>> mapData = map(bean.data.records);
        initListData(mapData);
        if (!mDataList.isEmpty()) {
            mStatusExpandAdapter = new StatusExpandAdapter(MainActivity.this, mDataList);
            mExpandableListView.setAdapter(mStatusExpandAdapter);
            mExpandableListView.setGroupIndicator(null); // 去掉默认带的箭头
            for (int i = 0; i < mStatusExpandAdapter.getGroupCount(); i++) {
                mExpandableListView.expandGroup(i);
            }
        }
        mExpandableListView.stopLoadMore();
    }

    private static Map<Integer, Map<Integer, List<RecordBean>>> map(List<RecordBean> dataList) {
        List<RecordBean> records = dataList;
        Map<Integer, Map<Integer, List<RecordBean>>> structured = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer lhs, Integer rhs) {
                return -1 * lhs.compareTo(rhs);
            }
        });

        for (RecordBean record : records) {
            Integer date = getDate(record); // 根据getTakeTime得到日期 key
            Integer time = getTime(record); // 根据getTakeTime得到小时分钟 key
            Map<Integer, List<RecordBean>> recordsInDay = structured.get(date);
            if (recordsInDay == null) {
                recordsInDay = new TreeMap<>();
                structured.put(date, recordsInDay);
            }

            List<RecordBean> listAtTime = recordsInDay.get(time);
            if (listAtTime == null) {
                listAtTime = new ArrayList<>();
                recordsInDay.put(time, listAtTime);
            }

            listAtTime.add(record);
        }
        return structured;
    }

    private void initListData(Map<Integer, Map<Integer, List<RecordBean>>> mapData) {
        mDataList = new ArrayList<>();
        for (Map.Entry<Integer, Map<Integer, List<RecordBean>>> entry : mapData.entrySet()) {
            int dateTime = entry.getKey();
            DateStatusBean mDateStatusBean = new DateStatusBean();
            mDateStatusBean.setStatusName(dateTime);

            Map<Integer, List<RecordBean>> dataMap = entry.getValue();
            List<TimeContentStatusBean> twoDataList = new ArrayList<>();
            for (Entry<Integer, List<RecordBean>> dataEntry : dataMap.entrySet()) {
                int timeContent = dataEntry.getKey();
                TimeContentStatusBean mTimeContentStatusBean = new TimeContentStatusBean();
                mTimeContentStatusBean.setActionTime(timeContent);
                mTimeContentStatusBean.setmRecordBeans(dataEntry.getValue());
                twoDataList.add(mTimeContentStatusBean);
            }
            mDateStatusBean.setTwoList(twoDataList);
            mDataList.add(mDateStatusBean);
        }
    }

    private void setListener(){
        mExpandableListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                return true;
            }
        });

        mExpandableListView.setXListViewListener(new IXListViewListener() {
            @Override
            public void onRefresh() {
                mExpandableListView.stopRefresh();
            }

            @Override
            public void onLoadMore() {
                mExpandableListView.stopLoadMore();
            }
        });
    }


    private static final Integer getTime(RecordBean record) {
        Date date = new Date(record.getTakeTime());
        return date.getHours() * 60 + date.getMinutes();
    }

    /*
     * getMonth()的返回值是0-11也就是从0开始而不是从1开始。所以一般都需要加一个1 才能达到1-12月。否则就成了0-11月了
     */
    private static final Integer getDate(RecordBean record) {
        Date date = new Date(record.getTakeTime());
        int data = date.getYear() * 10000 + (date.getMonth() + 1) * 100 + date.getDate();
        return data;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
