package com.example.demo;

import com.example.demo.recv.ReceiveDataController;
import com.jfinal.config.*;
import com.jfinal.json.MixedJsonFactory;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;

/**
 * MainConfig
 *
 * @author gexc
 * @date 2019-08-10
 */
public class MainConfig extends JFinalConfig {

    /**
     * 启动入口
     */
    public static void main(String[] args) {
        UndertowServer.start(MainConfig.class, 8000, true);
    }

    @Override
    public void configConstant(Constants me) {
        me.setDevMode(true);
        me.setInjectDependency(true);
        me.setJsonFactory(new MixedJsonFactory());
    }

    @Override
    public void configRoute(Routes me) {
        me.add("/api/receiveData", ReceiveDataController.class);
    }

    @Override
    public void configEngine(Engine me) {}

    @Override
    public void configPlugin(Plugins me) {
        DruidPlugin dp = new DruidPlugin(
                "jdbc:mysql://127.0.0.1:3306/test?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false",
                "root",
                "",
                "com.mysql.cj.jdbc.Driver");
        me.add(dp);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        arp.setDialect(new MysqlDialect());
//        _MappingKit.mapping(arp);
        arp.addSqlTemplate("all.sql");
        me.add(arp);
    }

    @Override
    public void configInterceptor(Interceptors me) {}

    @Override
    public void configHandler(Handlers me) {}
}
