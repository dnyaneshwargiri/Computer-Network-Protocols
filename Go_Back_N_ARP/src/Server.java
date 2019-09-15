import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;




public class Server {
	public static void main(String[] args) {
		String str;
		ServerSocket ss;
		int [] arr=new int[7];
		int i,j;
		for (i = 0; i < arr.length; i++) {
			arr[i]=i;
		}
		/*Error Packet*/
		arr[2]=-1;
		try {
			ss=new ServerSocket(3333);
			Socket s=ss.accept();
			DataInputStream din=new DataInputStream(s.getInputStream());
			DataOutputStream dout=new DataOutputStream(s.getOutputStream());
			dout.writeUTF("Server : Client Requested Packets  ");
			
			for  (i = 0; i < arr.length; i++) 
			{
				dout.writeInt(arr[i]);

			}

				i=din.readInt();
				/*Corrected Packet*/

				arr[i]=2;
				
				
				/*Resending  Packets*/
				
				for  (j = i; j < arr.length; j++) 
				{
					dout.writeInt(arr[j]);

				}
				
			
				
				
		} catch (Exception e) {
			// TODO: handle exception
		}
	}



}