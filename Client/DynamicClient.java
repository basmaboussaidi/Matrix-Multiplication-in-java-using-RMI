import java.rmi.RMISecurityManager;
import java.rmi.server.RMIClassLoader;
import java.util.Properties;
import java.lang.reflect.Constructor;
public class DynamicClient {
    public DynamicClient (String[] args) throws Exception {
        System.setProperty("java.rmi.server.codebase","file:../www/");
        Properties p = System.getProperties();
        String url = p.getProperty("java.rmi.server.codebase");
        Class ClasseClient = RMIClassLoader.loadClass(url,"ProductClient");
        Constructor [] C = ClasseClient.getConstructors();
        C[0].newInstance(new Object[]{args});

    }
    public static void main (String [] args)
    {
        System.setSecurityManager(new RMISecurityManager());
        try
        {
            DynamicClient cli = new DynamicClient(args);

        }
        catch (Exception e) {
            System.out.println (e.toString());

        }

    }
}