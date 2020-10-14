import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class One_Of_Many_Client_Connection implements Runnable{
    Socket socket;
    public One_Of_Many_Client_Connection(Socket socket){
        this.socket=socket;
    }
    @Override
    public void run(){

        try{
            BufferedReader ReadFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter WriteToClient = new PrintWriter(socket.getOutputStream(), true);

            String temp ;
            while(true){

                temp = ReadFromClient.readLine();

                if(temp.equals("end")){
                    break;
                }
                System.out.println("Successfully Read From Client: "+temp);
                WriteToClient.println("Server Read this Successfully: "+ temp);


            }
        }
        catch(IOException e){
            System.out.println("Server Error");
        }
        finally {
           try{

               socket.close();
               System.out.println("Connection Closed");
           }catch (IOException e){
               System.out.println("Error Closing the connection");
           }

        }
    }
}
