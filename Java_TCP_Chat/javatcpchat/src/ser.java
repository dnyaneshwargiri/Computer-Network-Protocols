import java.io.DataOutputStream;
import java.io.DataInputStream;

import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;


public class ser {
public static void main(String[] args) {
	String str;
	ServerSocket ss;
	try {
		ss=new ServerSocket(3333);
		Socket s=ss.accept();
		DataInputStream din=new DataInputStream(s.getInputStream());
		DataOutputStream dout=new DataOutputStream(s.getOutputStream());
		while(true)
		{
			
			str=din.readUTF();
			JOptionPane.showMessageDialog(null, str);
		    str=JOptionPane.showInputDialog("Server:Enter Message");
			dout.writeUTF(str);
			
			
		}
			
			
	} catch (Exception e) {
		// TODO: handle exception
	}
}
}
