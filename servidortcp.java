import java.net.*;
import java.io.*;
import java.util.HashMap;

public class servidortcp {
	public static void main(String argv[]) {
		ServerSocket socketEscolta;
		boolean fi = false;
		String missatge = "";
		
		Producto refresc = new Producto("REF", 2, "Refresc");
		Producto patates = new Producto("PAT", 3, "Patates");
		Producto croissant = new Producto("CRO", 1, "Croissant");
		Producto bocata = new Producto("BOC", 2, "Bocata");
		Producto ensaladilla = new Producto("ENS", 4, "Ensaladilla");
		Producto cafe = new Producto("CAF", 1, "Cafe");
		
		HashMap<String, Integer> productos = new HashMap<String, Integer>();
		productos.put(refresc.codigo, refresc.precio);
		productos.put(patates.codigo, patates.precio);
		productos.put(croissant.codigo, croissant.precio);
		productos.put(bocata.codigo, bocata.precio);
		productos.put(ensaladilla.codigo, ensaladilla.precio);
		productos.put(cafe.codigo, cafe.precio);
		
		try {
			socketEscolta = new ServerSocket(7000);
			while(1>0) {
				Socket socketClient = socketEscolta.accept();
				DataInputStream in = new DataInputStream(socketClient.getInputStream());
				DataOutputStream out = new DataOutputStream(socketClient.getOutputStream());	
				fi = false;

				while (!fi) {
					missatge = in.readUTF();
					
					Integer precio = productos.get(missatge);
					if (missatge.startsWith("fi")) {
						System.out.println(missatge);
						out.writeUTF(missatge);
						in.close();
						out.close();
						socketClient.close();
						fi = true;
					}
					else if (missatge == "HELLO") {
						out.writeUTF("HELLO");
					}
					else if (precio != null) {
						System.out.println(missatge + " " + precio.toString());
						out.writeUTF(missatge + " " + precio.toString());
					}
					out.writeUTF("There is no product for this key");
					
				}
				fi = false;
			}
		} 
		catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}
