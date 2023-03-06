package ma.enset.blocking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss=new ServerSocket(6000);
        System.out.println("J'attends une nouvelle connexion !!");
        Socket socket = ss.accept();
        InputStream is=socket.getInputStream();
        OutputStream os=socket.getOutputStream();
        System.out.println("J'attends une donnée !!");
        int nb=is.read();
        System.out.println("J'attends une réponse !!");
        int res=nb*23;
        os.write(res);
        socket.close();
    }

}
