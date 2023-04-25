import java.rmi.*;

public interface StockInterface extends Remote {

   // méthode pour consulter la quantité d'un produit donné
   public int getQuantity(String productName) throws RemoteException;

   // méthode pour consulter la liste de tous les produits disponibles
   public String[] getProducts() throws RemoteException;

   // méthode pour ajouter une quantité d'un produit donné
   public void updateQuantity(String productName, int quantity) throws RemoteException;

   // méthode pour retirer une quantité d'un produit donné
   public void removeQuantity(String productName, int quantity) throws RemoteException;

   
   // méthode pour enregistrer un client
   public void registerClient(ClientInterface client) throws RemoteException;

   // méthode pour désenregistrer un client
   public void unregisterClient(ClientInterface client) throws RemoteException;
}
