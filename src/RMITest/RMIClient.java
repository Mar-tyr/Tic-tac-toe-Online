package RMITest;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by 90465 on 2018/5/22.
 */
public class RMIClient {
    public static void main(String[]args){

        try{
            RemoteHelloWorld hello= (RemoteHelloWorld) Naming.lookup("rmi://47.100.115.158:1099/helloworld");
            String ret=hello.sayHello();
            System.out.println(ret);
        }catch (RemoteException e){
            e.printStackTrace();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (NotBoundException e){
            e.printStackTrace();
        }
    }
}