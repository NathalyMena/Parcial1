package com.example.parcial1android;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TCP extends Thread {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private MainActivity app;

    public TCP (MainActivity app) {
        this.app = app;

    }
    public void run() {
        try {
            this.socket = new Socket("10.0.2.2", 5000);

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

    public void sendMessage(String mensaje) {
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

    public void conection (/*String ip*/) {
        this.start();
    }

    public void recibirMensaje() throws IOException {
        String line = reader.readLine();


    }
}
