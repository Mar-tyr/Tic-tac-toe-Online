package controller;

import entity.Game;
import entity.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserAction extends Remote {
    public void regist(User user) throws RemoteException;                    //注册服务

    public User login(String user_username, String user_password) throws RemoteException;        //登录服务

    public User findUserById(int user_id) throws RemoteException;            //id找用户数据服务

    public Game findGameByUserId(int user_id) throws RemoteException;//用户id查询棋局

    public void matchUsers(User one, User another) throws RemoteException;      //匹配选手

    public void updateGame(int user_id, String boardcode) throws RemoteException;   //更新棋局
}
