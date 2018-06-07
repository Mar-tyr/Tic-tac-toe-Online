package client;

import controller.UserAction;

import java.rmi.Naming;

/**
 * Created by 90465 on 2018/6/1.
 */
public class Client {
    private static UserAction action;

    static {
        try {
            String location = "rmi://47.100.115.158:1099/UserAction";
            action = (UserAction) Naming.lookup(location);                  //初始化控制器
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UserAction getAction() {
        return action;
    }           //返回控制器


}
