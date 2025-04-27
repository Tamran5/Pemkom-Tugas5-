import java.io.*;
import java.net.*;
import java.util.Scanner;

public class FileClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 5000;

        // Membaca input dari pengguna
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan path file yang ingin dikirim (contoh: C:/Users/Nama/file.txt): ");
        String filePath = scanner.nextLine();

        // Validasi file
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            System.out.println("Error: File tidak ditemukan atau bukan file yang valid.");
            scanner.close();
            return;
        }

        try {
            Socket socket = new Socket(serverAddress, port);
            System.out.println("Connected to server");

            // Mengirim file
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            // Mengirim nama file dan ukuran
            dos.writeUTF(file.getName());
            dos.writeLong(file.length());

            // Mengirim isi file
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) > 0) {
                dos.write(buffer, 0, bytesRead);
            }

            System.out.println("File sent: " + file.getName());
            fis.close();
            dos.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}