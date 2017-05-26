package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.modle.DateStatusBean;
import com.example.administrator.myapplication.modle.RecordBean;
import com.example.administrator.myapplication.modle.TimeContentStatusBean;
import com.example.administrator.myapplication.utils.TimeUtils;
import java.util.ArrayList;
import java.util.List;


public class StatusExpandAdapter extends BaseExpandableListAdapter {
    private LayoutInflater inflater = null;
    private List<DateStatusBean> oneList;
    private Context mContext;

    public StatusExpandAdapter(Context mContext, List<DateStatusBean> oneList) {
        this.oneList = oneList;
        this.mContext = mContext;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setDataStatusExpandAdapter(List<DateStatusBean> oneList) {
        this.oneList = oneList;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return oneList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return (oneList.get(groupPosition).getTwoList() == null ? 0 : oneList.get(groupPosition).getTwoList().size());
    }

    @Override
    public DateStatusBean getGroup(int groupPosition) {
        return oneList.get(groupPosition);
    }

    @Override
    public TimeContentStatusBean getChild(int groupPosition, int childPosition) {
        return oneList.get(groupPosition).getTwoList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder = new GroupViewHolder();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.one_status_item, null);
        }
        // 设置第一级月份
        holder.groupName = (TextView) convertView.findViewById(R.id.one_status_time);
        holder.one_status_week = (TextView) convertView.findViewById(R.id.one_status_week);
        holder.groupName.setText(TimeUtils.getIntegerToMMDD(oneList.get(groupPosition).getStatusName()));
        holder.one_status_week.setText(TimeUtils.formatLongToWeek(oneList.get(groupPosition).getTwoList().get(0).getmRecordBeans().get(0).getTakeTime()));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        List<RecordBean> mRecordBeans = new ArrayList<>();
        ChildViewHolder viewHolder = null;
        TimeContentStatusBean entity = getChild(groupPosition, childPosition);
        if (convertView != null) {
            viewHolder = (ChildViewHolder) convertView.getTag();
        } else {
            viewHolder = new ChildViewHolder();
            convertView = inflater.inflate(R.layout.two_status_item, null);
            viewHolder.iv_list_time_icon = (ImageView) convertView.findViewById(R.id.iv_list_time_icon);
            viewHolder.ll_medic_history_content = (LinearLayout) convertView.findViewById(R.id.ll_medic_history_content);
            viewHolder.actionTime = (TextView) convertView.findViewById(R.id.action_time);
            convertView.setTag(viewHolder);
        }
        // 设置第二级时间和事件名称
        ColorStateList csl_c5 = mContext.getResources().getColorStateList(R.color.c_c5c5c5);
        ColorStateList csl_db = mContext.getResources().getColorStateList(R.color.c_db7831);
        int recordBeansSize = entity.getmRecordBeans().size();
        viewHolder.ll_medic_history_content.removeAllViews();
        for (int i = 0; i < recordBeansSize; i++) {
            MedicineHistoryContentHolder mhchView = null;
            mhchView = new MedicineHistoryContentHolder(viewHolder.ll_medic_history_content);
            mhchView.itemContentView.setTag(mhchView);
            viewHolder.ll_medic_history_content.addView(mhchView.itemContentView);
            mhchView.tv_medic_history_content.setText(entity.getmRecordBeans().get(i).getMedicineName());

            if (entity.getmRecordBeans().get(i).getStatus() == 2 || entity.getmRecordBeans().get(i).getStatus() == 0) {
                // 未服用
                mRecordBeans.add(entity.getmRecordBeans().get(i));
                mhchView.tv_medic_history_content.setTextColor(csl_db);
            } else if (entity.getmRecordBeans().get(i).getStatus() == 1) {
                //服用
                mhchView.tv_medic_history_content.setTextColor(csl_c5);
            }
        }

        if (!mRecordBeans.isEmpty()) {
            viewHolder.iv_list_time_icon.setBackgroundResource(R.mipmap.expand_list_time_error_icon);
        } else if (mRecordBeans.size() == 0) {
            viewHolder.iv_list_time_icon.setBackgroundResource(R.mipmap.expand_list_time_right_icon);
        }
        viewHolder.actionTime.setText(TimeUtils.secToHMXXXTime(entity.getActionTime()));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private class GroupViewHolder {
        TextView groupName, one_status_week;
    }

    private class ChildViewHolder {
        public TextView actionTime;
        public ImageView iv_list_time_icon;
        public LinearLayout ll_medic_history_content;
    }

    class MedicineHistoryContentHolder {
        TextView tv_medic_history_content;
        View itemContentView;

        public MedicineHistoryContentHolder(ViewGroup parent) {
            itemContentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medic_history_content, parent, false);
            tv_medic_history_content = (TextView) itemContentView.findViewById(R.id.tv_medic_history_content);
        }
    }

}
