package com.example.administrator.expandapplication.modle;

import java.util.ArrayList;
import java.util.List;

public class SucGetRecordBean extends BaseResponseBean<SucGetRecordBean.GetRecordBean> {
    public class GetRecordBean {

        public List<RecordBean> records = new ArrayList<>();
    }
}
