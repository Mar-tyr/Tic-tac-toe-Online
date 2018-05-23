package gameserver;

import model.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by 90465 on 2018/5/23.
 */
public class GameServerImpl extends UnicastRemoteObject implements GameServer {
    private static final long serialVersionUID=1L;
    public GameServerImpl() throws RemoteException{
        super();
    }

    @Override
    public boolean registerUser(User user) throws RemoteException {

        return false;
    }

    @Override
    public User signIn(String user, char[] passwd) throws RemoteException {
        return null;
    }
}
