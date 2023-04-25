import java.rmi.*;
import java.rmi.server.*;
import java.util.Scanner;

public class Client_2 extends UnicastRemoteObject implements ClientInterface {

   public Client_2() throws RemoteException {
      super();
   }
   // implementation of the callback method notify
   public void notify(String message) throws RemoteException {
      System.out.println("Notification : " + message);
   }
    
   // method to connect to the server and register to receive notifications
   public void connectToServer() {
      try {
         StockInterface stock = (StockInterface) Naming.lookup("//localhost/Stock");
         stock.registerClient(this);
         System.out.println("Connexion au serveur.");
      } catch(Exception e) {
         System.out.println("Erreur de connexion au serveur: " + e.getMessage());
      }
   }
    
   // method to disconnect from the server and unregister
   public void disconnectFromServer() {
      try {
         StockInterface stock = (StockInterface) Naming.lookup("//localhost/Stock");
         stock.unregisterClient(this);
         System.out.println("Déconnectée du serveur.");
      }catch(Exception e) {
         System.out.println("Erreur de déconnexion au serveur : " + e.getMessage());
      }
   }

   // method to display the available products and their quantities
   public void displayStock(StockInterface stock) {
      try {
         String[] products = stock.getProducts();
         System.out.println("LES PRODUITS DISPONIBLES:");
         System.out.println("-------------------------:");
         for (String product : products) {
            int quantity = stock.getQuantity(product);
            System.out.println(product + " : " + quantity);
         }
      } catch (Exception e) {
         System.out.println("Erreur dans l'obtention des informations sur les stocks : " + e.getMessage());
      }
   }

   // method to update the stock with a new quantity for a given product
   public void updateStock(StockInterface stock) {
      Scanner scanner = new Scanner(System.in);
      System.out.print("Entrez le nom du produit >> ");
      String product = scanner.nextLine();
      System.out.print("Saisissez la quantité que vous souhaitez ajouter >> ");
      int quantity = scanner.nextInt();
      try {
         stock.updateQuantity(product, quantity);
         System.out.println("*********************************************************************************");
         System.out.println("Stock ajouté avec succès.");
      } catch (Exception e) {
         System.out.println("Erreur de mise à jour du stock: " + e.getMessage());
      }
   }

   // method to update the stock with a - quantity for a given product
   public void removeStock(StockInterface stock) {
      Scanner scanner = new Scanner(System.in);
      System.out.print("Entrez le nom du produit >> ");
      String product = scanner.nextLine();
      System.out.print("Saisissez la quantité que vous voulez retirer >>");
      int quantity = scanner.nextInt();
      try {
         stock.removeQuantity(product, quantity);
         System.out.println("*********************************************************************************");
         System.out.println("Mise à jour du stock réussie.");
         System.out.println("*********************************************************************************");
      } catch (Exception e) {
         System.out.println("Erreur de mise à jour du stock: " + e.getMessage());
      }
   }
   

   // method to handle user input and perform the appropriate action
   public void handleInput(StockInterface stock) {
      Scanner scanner = new Scanner(System.in);
      boolean exit = false;
      while (!exit) {
         System.out.println("*********************************************************************************");
         System.out.println("MENU:");
         System.out.println("-----");
         System.out.println("1. Affichage de stock");
         System.out.println("2. Ajouter Quantité");
         System.out.println("3. Diminuer Quantité");
         System.out.println("4. Quitté");
         System.out.print("Sélectionnez >> ");
         int option = scanner.nextInt();
         System.out.println("*********************************************************************************");
         switch (option) {
            case 1:
               displayStock(stock);
               break;
            case 2:
               updateStock(stock);
               break;
               case 3:
                removeStock(stock);
                break;
            case 4:
               exit = true;
               break;
            default:
               System.out.println("Option invalide. Veuillez réessayer!");
         }
      }
   }

   public static void main(String[] args) {
      Client client = null;
      try {
         client = new Client();
         client.connectToServer();
         StockInterface stock = (StockInterface) Naming.lookup("//localhost/Stock");
         client.handleInput(stock);
      } catch (Exception e) {
         System.out.println("Erreur: " + e.getMessage());
      } finally {
         if (client != null) {
            client.disconnectFromServer();
         }
      }
   }
}
