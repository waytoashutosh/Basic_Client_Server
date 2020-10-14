import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try{
            InetAddress address  = InetAddress.getLocalHost();
            DatagramSocket datagramSocket = new DatagramSocket();

            Scanner scan = new Scanner(System.in);
            String toSend;
            while(true){
                System.out.print("Enter something to send : ");
                toSend = scan.nextLine();

                if(toSend.equals("end")){
                    break;
                }
                byte[] buffer = toSend.getBytes();

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 5000);
                datagramSocket.send(packet);

                byte[] toReceive = new byte[50];
                DatagramPacket receivedPacket = new DatagramPacket(toReceive, toReceive.length);
                datagramSocket.receive(receivedPacket);
                System.out.println(new String(toReceive));

            }
        }catch(IOException e){
            System.out.println("Client Error ");
        }
    }
}
