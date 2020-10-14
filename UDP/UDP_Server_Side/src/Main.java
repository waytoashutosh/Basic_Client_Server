import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Main {
    public static void main(String[] args) {

        try(DatagramSocket socket = new DatagramSocket(5000)){

            while(true){
                byte[] buffer = new byte[50];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                System.out.println("Received From Client : "+ new String(buffer));
                //to get the size of the buffer received (may come handy with images)
                // packet.getLength()
                // new String(buffer, 0, packet.getLength())


                //now to send something back to  client we need to create packet too
                String toReturn = "Perfectly read from client : "+ new String(buffer);
                byte[] toSendByte= toReturn.getBytes();
                InetAddress address = packet.getAddress(); //extracting address from the packet received itself
                int port = packet.getPort(); //extracting port number from packet itself.
                packet = new DatagramPacket(toSendByte, toSendByte.length, address, port);
                socket.send(packet);


            }

        }catch (IOException e){
            System.out.println("Server Error");
        }
    }
}
