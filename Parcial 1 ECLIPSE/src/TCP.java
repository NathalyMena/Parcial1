import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP extends Thread {
	
	private BufferedWriter writer;
	private Socket socket;
	private BufferedReader reader;
	
	
	private Main main;
	
	public TCP(Main main) {
		this.main = main;
	}
	
	public void run() {
		try {
			ServerSocket server = new ServerSocket(5000);
			System.out.println("Esperando conexión...");
			this.socket = server.accept();
			System.out.println("Conexión aceptada");

			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			this.reader = new BufferedReader(isr);

			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			this.writer = new BufferedWriter(osw);

			while (true) {
				recibirMensaje();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void conection() {
		this.start();
	}

	public void mandarMensaje(String mensaje) {
		new Thread(

				() -> {
					try {
						writer.write(mensaje + "\n");
						writer.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

		).start();

	}
	
	public void recibirMensaje() throws IOException {
		String line = reader.readLine();
		main.setArregloRec(line.split(","));
		float x = Float.parseFloat(main.getArregloRec()[0]);
		float y = Float.parseFloat(main.getArregloRec()[1]);
		String recordatorio = main.getArregloRec()[2];
		String importancia = main.getArregloRec()[3];
		
		main.getList().add(new Recordatorio(x, y, recordatorio, importancia));
	
	}

	public void endConection() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}