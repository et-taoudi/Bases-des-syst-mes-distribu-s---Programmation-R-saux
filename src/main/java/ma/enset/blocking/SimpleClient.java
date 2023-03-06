package ma.enset.blocking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient {
    public static void main(String[] args) throws IOException {
        Socket socket =new Socket("localhost",6000);
        InputStream is = socket.getInputStream();
        OutputStream os=socket.getOutputStream();
        Scanner sc=new Scanner(System.in);
        System.out.println("Nombre : ");
        int nb=sc.nextInt();
        os.write(nb);
        int rep=is.read();
        System.out.println("Réponse : "+rep);

    }
}
