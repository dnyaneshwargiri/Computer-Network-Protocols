

import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

public class Client
{
  @SuppressWarnings("resource")
public static void main(String args[]) throws IOException
  {
	
		
	// JOptionPane.showMessageDialog(null," UDP Client socket is created and waiting for server");
		
	 String message = null;
	 DatagramSocket cs = null; 
			
	 cs = new DatagramSocket();  
 	
	 InetAddress IPAddress = InetAddress.getByName("127.0.0.1"); 
  
     int port = 9990;
	
     while (true) {
		
    	  
    	 byte[] receiveData = new byte[2048];   

    	 byte[] sendData  = new byte[2048]; 
     
     message =  JOptionPane.showInputDialog("Client : Enter Meassage-");
	 sendData = message.getBytes(); 
  
	 DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,port); 
  
	 cs.send(sendPacket); 
	   
	 DatagramPacket receivePacket =new DatagramPacket(receiveData, receiveData.length); 
  
	 cs.receive(receivePacket); 
     message=null;

	 message = new String(receivePacket.getData());  
	
	 JOptionPane.showMessageDialog(null, "Client :  Server says-"+message);


	}
  }
}