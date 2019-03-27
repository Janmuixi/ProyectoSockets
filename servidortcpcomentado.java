import java.net.*;
import java.io.*;

public class servidortcpcomentado {
	public static void main(String argv[]) {
		// Crea un servidor de sockets
		ServerSocket socketEscolta;
		boolean fi = false;
		String missatge = "";

		try {
			// Instància el servidor de sockets amb el port 6001
			socketEscolta = new ServerSocket(6001);
			// Bucle infinit
			while(1>0) {
				// Crea un socket client i l'instància com un socket del servidor que hem creat abans
				Socket socketClient = socketEscolta.accept();
				// Crea dos canals, un de entrada i un de sortida de dades
				DataInputStream in = new DataInputStream(socketClient.getInputStream());
				DataOutputStream out = new DataOutputStream(socketClient.getOutputStream());	
				fi = false;
				
				// Mentre el missatge no sigui 'fi' llegira el que hi hagi al canal
				// d'entrada i ho ensenyará per consola, després de llegiro el retornará
				// per el canal de sortida
				while (!fi) {
					missatge = in.readUTF();
					// AVE
					if (missatge.startsWith("A")) {
						System.out.println(missatge);
						out.writeUTF(missatge);
						fi = true;
					}
					else {
						System.out.println("DES 0");
						out.writeUTF("DES 0");
					}
				}

				fi = false;
				while (!fi) {
					missatge = in.readUTF();
					
					// Depenent amb quina serie de caracters començi el missatge
					// retornará per consola i per el canal de sortida un missatge diferent
					if (missatge.startsWith("SEV")) {
						System.out.println("SEV -150");
						out.writeUTF("SEV -150");
					}						
					else if (missatge.startsWith("V")) {
						System.out.println("VAL -60");
						out.writeUTF("VAL -60");
					}
					else if (missatge.startsWith("SEG")) {
						System.out.println("SEG -30");
						out.writeUTF("SEG -30");
					}
					else if (missatge.startsWith("M")) {
						System.out.println("MAD 0");
						out.writeUTF("MAD 0");
					}
					else if (missatge.startsWith("Z")) {
						System.out.println("ZAR 85");
						out.writeUTF("ZAR 85");
					}
					else if (missatge.startsWith("T")) {
						System.out.println("TAR 180");
						out.writeUTF("TAR 180");
					}
					else if (missatge.startsWith("L")) {
						System.out.println("LLE 130");
						out.writeUTF("LLE 130");
					}
					else if (missatge.startsWith("B")) {
						System.out.println("BCN 205");
						out.writeUTF("BCN 205");
					}
					else if (missatge.startsWith("fi")) {
						System.out.println(missatge);
						out.writeUTF(missatge);
						in.close();
						out.close();
						socketClient.close();
						fi = true;
					} 
					else {
						System.out.println(missatge);
						out.writeUTF("DES 0");
					}
				}
			}
		} 
		catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}
