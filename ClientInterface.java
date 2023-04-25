import java.rmi.*;

public interface ClientInterface extends Remote {

   // méthode de callback pour être alerté en temps réel lorsqu'une modification est apportée au stock
   public void notify(String message) throws RemoteException;
}
