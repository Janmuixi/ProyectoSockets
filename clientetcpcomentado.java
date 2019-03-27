import java.net.*;
import java.io.*;

public class clientetcpcomentado {

   public static void main(String argv[]) {
	   
	 // Revisa que els arguments no están buits
      if (argv.length != 1) {
         System.err.println("Us: java clientetcp servidor");
         System.exit(1);
      }
      
      // Llegeix el text que s'ha introduit per teclat
      BufferedReader teclat = new BufferedReader(new InputStreamReader(System.in));

      // Crea el socket i la adreça IP
      Socket socket;
      InetAddress address;
      
      String missatge="";
      String missatgeServidor="";

      try {
    	 // Instància la IP desde els paràmatres
         address=InetAddress.getByName(argv[0]);
         // Instància el socket amb la adreça proveida abans al port 6001
         socket = new Socket(address,6001);
         // Crea dos canalsdd, un per entrada de dades i un altre de sortida
         DataInputStream in = new DataInputStream(socket.getInputStream());
         DataOutputStream out = new DataOutputStream(socket.getOutputStream());
         
         // Escrit per el stream de sortida un string
         out.writeUTF("AVE");
         // Llegeix el missatge provinent en el stream d'entrada i l'imprimeix per consola
         missatgeServidor = in.readUTF();
         System.out.println(missatgeServidor);
        
         do {
        	// Llegeix el string introduit per teclat, 
        	// l'envia per el canal de sortida i imprimeix el que rep per el canal d'entrada
        	// mentre que el missatge no sigui 'fi'
            missatge = teclat.readLine();
           	out.writeUTF(missatge);
        	
            missatgeServidor = in.readUTF();
            System.out.println(missatgeServidor);
        } while (!missatge.startsWith("fi"));

        // Tanca totes les conexions
        in.close();
        out.close();
        socket.close();
      } 
      catch (Exception e) {
         System.err.println(e.getMessage());
         System.exit(1);
      }
   }
}
