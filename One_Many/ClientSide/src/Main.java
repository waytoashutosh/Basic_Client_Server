import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //local host is an alias for 127.0.0.1  = address of the host we want to connect to
        //the port number is kept same as the server because that's where the server is listening on
        //if you are writing both server and client then you know;
        //if you are just writing the client side, then the server should tell you; may be through some API
        try(Socket socket = new Socket("localhost", 5000)){

            BufferedReader readFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter sendToServer = new PrintWriter(socket.getOutputStream(), true);

            Scanner scan = new Scanner(System.in);
            while(true){
                System.out.println("Enter something to send to server");
                String temp = scan.nextLine();
                sendToServer.println(temp); //to send the string to the server


                if(temp.equals("end")){
                    break;
                }

                System.out.println(readFromServer.readLine());
            }
        }
        catch (IOException e){
            System.out.println("Client Error");
        }
    }
}
