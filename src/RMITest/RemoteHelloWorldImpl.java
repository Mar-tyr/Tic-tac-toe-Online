package RMITest;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by 90465 on 2018/5/22.
 */
public class RemoteHelloWorldImpl extends UnicastRemoteObject implements RemoteHelloWorld{
    private static final long serialVersionUID=1L;
    public RemoteHelloWorldImpl () throws RemoteException{
        super();
    }
    @Override
    public String sayHello() throws RemoteException {
        System.out.println("Called Successfully!");
        return "Hello world!";
    }
}
