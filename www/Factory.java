import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Factory extends Remote {
    public IProduct newProduct() throws RemoteException ;

}
