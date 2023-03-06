package ma.enset.blocking;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadBlockingServer extends Thread {
    int clientsCount;
    public static void main(String[] args) {
        new MultiThreadBlockingServer().start();
    }
    @Override
    public void run(){
        System.out.println("Le serveur écoute sur le port 6000 !! ");
        try {
            ServerSocket serverSocket=new ServerSocket(6000);
            while (true){
                Socket socket=serverSocket.accept();
                ++clientsCount;
                new Conversation(socket,clientsCount).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //Classe Conversation qui va s'occuper de tous les clients qui veulent se connecter
    class Conversation extends Thread{
        private Socket socket;
        private int clientId;
        public Conversation(Socket socket,int clientId){
            this.socket=socket;
            this.clientId=clientId;
        }
        @Override
        public void run(){
            try {
                InputStream is=socket.getInputStream();
                InputStreamReader isr=new InputStreamReader(is);
                BufferedReader br=new BufferedReader(isr);
                OutputStream os=socket.getOutputStream();
                PrintWriter pw=new PrintWriter(os,true);
                String IP=socket.getRemoteSocketAddress().toString();
                System.out.println("Nouvelle connexion client => "+clientId+" IP = "+IP);
                pw.println("Bienvenue, vous êtes le client n°= "+clientId);
                while(true){
                    String requete=br.readLine();
                    System.out.println("Nouvelle requete => IP = "+IP+" Requête = "+requete);
                    String reponse="Size = "+requete.length();
                    pw.println(reponse);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
