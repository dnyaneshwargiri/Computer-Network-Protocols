import java.io.DataOutputStream;
import java.io.DataInputStream;

import java.net.Socket;

import javax.swing.JOptionPane;


public class ser {





public static void main(String[] args) {
	Socket s;
	int [] arr=new int[7];
	int i,j = 0;
	try {
		s=new Socket("127.0.0.1",3333);
		String str;

		DataInputStream din=new DataInputStream(s.getInputStream());
		DataOutputStream dout=new DataOutputStream(s.getOutputStream());

		str=din.readUTF();

			for  (i = 0; i < arr.length; i++) 
			{
				arr[i]=din.readInt();
				if(arr[i]==-1)
				j=i; 
				JOptionPane.showMessageDialog(null, "Client : Packet NO  "+i+" value "+arr[i]);


			}
			JOptionPane.showMessageDialog(null, "Client : Error In Packet NO  "+j);

			dout.writeInt(j);

			arr[j]=din.readInt();
			JOptionPane.showMessageDialog(null, "Client : Corrected Packets");
			for  (i = 0; i < arr.length; i++) 
			{
				JOptionPane.showMessageDialog(null, "Client : Packet NO  "+i+" value "+arr[i]);

			}
			

}
			
		
			
			
	catch (Exception e) {
		// TODO: handle exception
	}
}
}