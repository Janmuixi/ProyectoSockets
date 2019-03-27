import java.net.*;
import java.io.*;

public class clientetcp {

   public static void main(String argv[]) {
      
      BufferedReader teclat = new BufferedReader(new InputStreamReader(System.in));

      Socket socket;
      InetAddress address;
      String missatge="";
      String missatgeServidor="";

      try {
    	  
         address=InetAddress.getByName(argv[0]);
         socket = new Socket(address,7000);
         DataInputStream in = new DataInputStream(socket.getInputStream());
         DataOutputStream out = new DataOutputStream(socket.getOutputStream());
         
         out.writeUTF("HELLO");
         missatgeServidor = in.readUTF();
        
         do {
            missatge = teclat.readLine();
           	out.writeUTF(missatge);
        	
            missatgeServidor = in.readUTF();
            System.out.println(missatgeServidor);
        } while (!missatge.startsWith("fi"));

	
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
