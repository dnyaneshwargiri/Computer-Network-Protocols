import java.security.PublicKey;
import java.util.Scanner;


public class subnet {
	Scanner sc = new  Scanner(System.in);
    public String Ipmsb;
	public String Getbin() 
	{
		System.out.println("Enter IP Address in Format.");
		String ip=sc.next();
		String [] OctetArray=ip.split("\\.");
		Ipmsb=OctetArray[0];
		String Binary=null;
		for (String string : OctetArray) {
			
			int Octet=Integer.parseInt(string);
			String BinaryOctet=Integer.toBinaryString(Octet);
			
			if(BinaryOctet.length()<8)
			{
				while(BinaryOctet.length()!=8)
				{
					BinaryOctet="0"+BinaryOctet;
					
				}
				
			}
			Binary+=BinaryOctet;
			System.out.println(BinaryOctet);
		}
		return Binary;
	}
	
	public void GetClassAndSubnetMask(String ipmsb) {
		int ipMsb=Integer.parseInt(ipmsb);
		if(ipMsb <= 127)
		{
			
		System.out.println("IP Class Is - A");	
		System.out.println("Default SubNetMask Is : 255.0.0.0");	

		}
		else if(ipMsb > 127 && ipMsb <=191)
		{
			
		System.out.println("IP Class Is - B");	
		System.out.println("Default SubNetMask Is : 255.255.0.0");	

		}
		else if(ipMsb > 191 && ipMsb <=223)
		{
		System.out.println("IP Class Is - C");	
		System.out.println("Default SubNetMask Is : 255.255.255.0");	

			
		}
		else if(ipMsb > 239)
		{
		System.out.println("IP Class Is - E");	
		System.out.println("NO SubNetMask Reserved ");	

			
		}
		else
		{
		System.out.println("IP Class Is - D");	
		System.out.println("NO SubNetMask Reserved ");	

		}
	}
	public void GetSubnetAndNodes(String Binary) 
	{   int i,Abits,Nabits,NodesToEachSubnets;
		System.out.println("How many Subnets u need.");
        i=sc.nextInt();
        Abits=(int) (Math.log(i) / Math.log(2));
        System.out.println("No of Bits Need to be Altered are ."+Abits);
        Nabits=16-Abits;  /*16 or 32 or 8 Depends on IP class*/
        NodesToEachSubnets=(int) ((Math.pow(2, Nabits))-2);
        System.out.println("No of Nodes(Different IP Address) Possible  To each Subnets are :"+NodesToEachSubnets);
        System.out.println("Total IP Addresses Possibles are :"+(i*NodesToEachSubnets));
        
	}
public static void main(String[] args)
{  
	subnet s=new subnet();
	s.GetSubnetAndNodes(s.Getbin());
	s.GetClassAndSubnetMask(s.Ipmsb);
}
}
