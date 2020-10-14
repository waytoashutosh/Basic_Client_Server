import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args){

        //the port can be from 0 to 6000 approx
        try(ServerSocket serverSocket = new ServerSocket(5000)){

            //want the sever to listen to the clients on the port that we have assigned
            //every client that connects to the server will do so using the same port
            //but through its own socket

            Socket socket = serverSocket.accept();  //this accept() is gonna block till a client connects to the server
            System.out.println("Client connected");

            //when a client does connect the server, we use input and output streams
            //to send data to the client and recieve data from the client
            //socket.getInputStream() and socket.getOutputStream()
            //return the input and output streams associated with the ServerSocket instance

            BufferedReader readFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writeToClient = new PrintWriter(socket.getOutputStream(), true);
            //second argument to automatically flush output stream
            //otherwise we would have to mannually flush to ensure that data is sent

            while(true){
                String temp = readFromClient.readLine(); //waiting to recieve some input from the client
                if(temp.equals("end")){
                    break;
                }
                writeToClient.println("Server Successfully Read this : "+temp);
                System.out.println("Client sent this: "+temp);
            }



            //now we need to read data from the client

        }
        catch (Exception e){
            System.out.println("-> Server Error -> ");
            e.printStackTrace();
        }
    }
}
