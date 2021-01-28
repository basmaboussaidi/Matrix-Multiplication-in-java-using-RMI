import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClassLoader;
import java.util.Properties;

public class DynamicServer {
    public static void main(String[] args){
        try {
            //set path to server and to policy file
            System.setProperty("java.security.policy", "server.policy");
            System.setProperty("java.rmi.server.codebase", "file:../www/");

            if(System.getSecurityManager() == null){
                System.setSecurityManager(new RMISecurityManager());
            }

            //create rmiregistry
            Registry registry = LocateRegistry.createRegistry(1099);

            //load factory class from the server
            Properties p = System.getProperties();
            String url = p.getProperty("java.rmi.server.codebase");
            Class serverClass = RMIClassLoader.loadClass(url, "IFactory");

            //rebind factory to the registry
            registry.rebind("Factory", (Remote)serverClass.newInstance());

            //wait for clients
            System.out.println("Server Ready");
        } catch(Exception e){
            System.out.println("****Error****");
            System.out.println(e.toString());
        }
    }
}
