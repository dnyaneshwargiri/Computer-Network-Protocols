import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;


public class Responder 
{
	String str;

	/*synchronized */ public boolean responseMethod(Socket s) throws IOException
	{
		
		while(true)
		{
			DataInputStream din=new DataInputStream(s.getInputStream());
			DataOutputStream dout=new DataOutputStream(s.getOutputStream());
			
			
			str=din.readUTF();
			JOptionPane.showMessageDialog(null,"Server : Client  No-"+Server.cno+"says :"+ str);
			str=JOptionPane.showInputDialog("Server : Enter Message for"+Server.cno);
			dout.writeUTF(str);
			
			if(1==2)
			{
				break;

			}
			
		}
			
			
		
		return false;


		}
		
	}

