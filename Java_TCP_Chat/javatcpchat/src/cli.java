import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.net.Socket;

import javax.swing.JOptionPane;



public class cli {
	public static void main(String[] args) {
		Socket s;
		try {
			s=new Socket("127.0.0.1",3333);
			String str;

			DataInputStream din=new DataInputStream(s.getInputStream());
			DataOutputStream dout=new DataOutputStream(s.getOutputStream());
			while(true)
			{
				str=JOptionPane.showInputDialog("Client :Enter Message");
				dout.writeUTF(str);

				str=din.readUTF();
				JOptionPane.showMessageDialog(null, str);
				
				
			}
				
				
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

