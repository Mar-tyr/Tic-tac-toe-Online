package RMITest;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by 90465 on 2018/5/22.
 */
public interface RemoteHelloWorld extends Remote {
    public String sayHello() throws RemoteException;
}
