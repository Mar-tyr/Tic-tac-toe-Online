package gameserver;

import model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by 90465 on 2018/5/23.
 */
public interface GameServer extends Remote {
    public boolean registerUser(User user) throws RemoteException;
    public User signIn(String user,char []passwd) throws RemoteException;
}
