import java.io.DataInputStream;
import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	public static int cno=0;

public static void main(String[] args) throws IOException {
	
			try {
				ServerSocket ss=new ServerSocket(3333);;

				Responder r=new Responder();
				while(true)
				{
			Socket s=ss.accept();
			
			Server.cno++;
			Thread t=new Thread(new ThreadManager(r,s));
			t.start();
		
		
				}
		
	}
	catch(Exception e)
	 {			
e.printStackTrace();}
	
		
		
}	
			
	
	
}
