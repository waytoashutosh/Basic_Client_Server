import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args){

        //the port can be from 0 to 6000 approx

        try(ServerSocket serverSocket = new ServerSocket(5000)) {

            while(true){
                //we are starting a new thread everytime we get a new connection
                new Thread(new One_Of_Many_Client_Connection(serverSocket.accept())).start();

            }
        }
        catch (IOException e){

        }
    }
}
