package ma.enset.blocking;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MyTelnetClient {
    public static void main(String[] args) {
        try {
            Socket socket=new Socket("localhost",6000);
            InputStream is=socket.getInputStream();
            InputStreamReader isr=new InputStreamReader(is);
            BufferedReader br=new BufferedReader(isr);
            OutputStream os=socket.getOutputStream();
            PrintWriter pw=new PrintWriter(os,true);
            new Thread(() -> {
                while(true){
                    try {
                        String reponse=br.readLine();
                        System.out.println(reponse);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
            Scanner sc=new Scanner(System.in);
            while(true){
                String requete=sc.nextLine();
                pw.println(requete);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
