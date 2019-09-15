import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.print.DocFlavor.STRING;
import javax.swing.JOptionPane;


public class ThreadManager implements Runnable
{

	   
	Responder r;
	Socket s;
	
		public ThreadManager( Responder r,Socket s)
		{
this.r=r;
this.s=s;
}
		
		
		public void run()
		{
			
			try {
				while(r.responseMethod(s))
			    {
					//r.responseMethod(s);
				} 
			}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			    /*try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			  /*  try {
					s.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			    
				
				
				
			
			
			
			
		
			/*s.close();
			ss.close();*/

	
}





