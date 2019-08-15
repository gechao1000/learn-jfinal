package com.example.demo.home;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import org.apache.log4j.Logger;

/**
 * HomeController
 *
 * @author gexc
 * @date 2019-08-10
 */
public class HomeController extends Controller {

    private static final Logger log = Logger.getLogger(HomeController.class);

    public void index() {
        JSONObject json = JSON.parseObject(getRawData());

        SqlPara sql = Db.getSqlPara("demo", json);
        System.out.println(sql);
        if (sql != null) {
            int count = Db.update(sql);
            System.out.println(count);
        }

        renderJson();
    }
}
