import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started. Waiting for client...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket);

                // Menerima nama file dari client
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                String fileName = dis.readUTF();
                long fileSize = dis.readLong();

                // Menyimpan file
                FileOutputStream fos = new FileOutputStream("server_" + fileName);
                byte[] buffer = new byte[4096];
                long bytesRead = 0;
                int count;

                while (bytesRead < fileSize && (count = dis.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                    bytesRead += count;
                }

                System.out.println("File received: " + fileName);
                fos.close();
                dis.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}