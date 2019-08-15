package com.example.demo.recv;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.validate.Validator;

/**
 * ReceiveDataValidator
 *
 * @author gexc
 * @date 2019-08-11
 */
public class ReceiveDataValidator extends Validator {

    @Override
    protected void validate(Controller c) {
        setShortCircuit(true);
        setRet(Ret.fail());
        JSONObject json = JSON.parseObject(c.getRawData());
        if (json == null) {
            addError("msg", "参数格式错误，请传递application/json");
        }
        if (StrKit.isBlank(json.getString("shopCode"))) {
            addError("msg", "商场代码不能为空");
        }
        if (StrKit.isBlank(json.getString("tableName"))) {
            addError("msg", "表名不能为空");
        }
        JSONArray data = json.getJSONArray("data");
        if (data == null || data.size() == 0) {
            addError("msg", "数据不能为空");
        }
    }

    @Override
    protected void handleError(Controller c) {
        c.renderJson(getRet());
    }
}
