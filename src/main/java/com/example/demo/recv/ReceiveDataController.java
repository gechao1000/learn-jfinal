package com.example.demo.recv;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;

/**
 * ReceiveDataController
 *
 * @author gexc
 * @date 2019-08-11
 */
public class ReceiveDataController extends Controller {

    @Inject
    ReceiveDataService service;

    @Before(ReceiveDataValidator.class)
    public void index() {
        renderText("render.index()");
    }

    public void syncData() {
        JSONObject json = JSON.parseObject(getRawData());
        Ret ret = service.syncData(json);

        renderJson(ret);
    }
}
