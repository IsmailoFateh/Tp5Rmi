import java.rmi.*;
// >> "rmiregistry" <<
public class Server {

   public static void main(String[] args) {
      try {
         StockInterface stock = new Stock();
         Naming.rebind("Stock", stock);
         System.out.println("*********************************************************************************");
         System.out.println("Le serveur est en service.");
         System.out.println("*********************************************************************************");
      } catch(Exception e) {
         System.out.println("Erreur de démarrage du serveur : " + e.getMessage());
      }
   }
}