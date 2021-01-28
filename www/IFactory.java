import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class IFactory extends UnicastRemoteObject implements Factory {
    public IFactory() throws RemoteException {
    }

    @Override
    public IProduct newProduct() throws RemoteException {
        return new Product();
    }
}
