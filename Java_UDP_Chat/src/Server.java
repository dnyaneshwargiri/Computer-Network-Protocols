

import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

import org.omg.CORBA.ByteHolder;

public class Server
{
 public static void main(String args[]) throws IOException 
 { @SuppressWarnings("resource")
    DatagramSocket ss =  new DatagramSocket(9990); 
	
	


   
	//JOptionPane.showMessageDialog(null," UDP Server socket is created and waiting for Client");
		
	while(true) 
	 { 	byte[] receiveData = new byte[2048];  
		byte[] sendData  = new byte[2048]; 

		
		DatagramPacket receivePacket =new DatagramPacket(receiveData, receiveData.length); 

	    ss.receive(receivePacket); 

	   String message = new String(receivePacket.getData()); 
	   InetAddress IPAddress = receivePacket.getAddress(); 
	   int port = receivePacket.getPort(); 


	   JOptionPane.showMessageDialog(null,"Server : Client Says - "+message);
		
            message=null;
		  
	   message =  JOptionPane.showInputDialog("Server : Enter Meassage -");
  
	   sendData = message.getBytes(); 
  
	   DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,port); 
  
	   ss.send(sendPacket); //ostream.writeUTF(myoperation);
		  	 	
	  } 	
	// System.out.println("Server Stopped by User program");
  }
}



  
	 
  
		  
		