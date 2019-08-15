package com.example.demo.recv;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;

/**
 * ReceiveDataService
 *
 * @author gexc
 * @date 2019-08-11
 */
public class ReceiveDataService {

    public Ret syncData(JSONObject json) {
        String tableName = json.getString("shopCode");
        SqlPara sql = Db.getSqlPara(tableName, json);
        if (sql == null) {
            return Ret.fail("msg", "<" + tableName + ">表名未找到!");
        }
        try {
            int count = Db.update(sql);
            return Ret.ok();
        } catch (Exception e) {
            return Ret.fail("msg", "<" + tableName + ">表数据接收错误:" + e.toString());
        }
    }

}
