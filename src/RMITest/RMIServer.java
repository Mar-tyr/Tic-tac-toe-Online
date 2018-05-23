package RMITest;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by 90465 on 2018/5/22.
 */
public class RMIServer {
    static RemoteHelloWorld helloWorld;
    public static void main(String[] args){
        System.setProperty("java.rmi.server.hostname","47.100.115.158");
        try {
            LocateRegistry.createRegistry(1099);
            helloWorld=new RemoteHelloWorldImpl();
            Naming.rebind("rmi://127.0.0.1:1099/helloworld",helloWorld);
            System.out.println("Bind Successfully!");
        }catch (RemoteException e) {
            e.printStackTrace();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }

    }
}
