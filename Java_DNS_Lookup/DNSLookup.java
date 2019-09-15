// Print out DNS Record for an Internet Address
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class DNSLookup
{
    public static void main(String args[])
    {
        // explain what program does and how to use it 
        if (args.length != 1)
        {
            System.err.println("Print out DNS Record for an Internet Address");
            System.err.println("USAGE: java DNSLookup domainName|domainAddress");
            System.exit(-1);
        }
        try
        {
            InetAddress inetAddress;
            // if first character is a digit then assume is an address
            if (Character.isDigit(args[0].charAt(0)))
            {   // convert address from string representation to byte array
                byte[] b = new byte[4];
                String[] bytes = args[0].split("[.]");
                for (int i = 0; i < bytes.length; i++)
                {
                    b[i] = new Integer(bytes[i]).byteValue();
                }
                // get Internet Address of this host address
                inetAddress = InetAddress.getByAddress(b);
            }
            else
            {   // get Internet Address of this host name
                inetAddress = InetAddress.getByName(args[0]);
            }
            // show the Internet Address as name/address
            System.out.println(inetAddress.getHostName() + "/" + inetAddress.getHostAddress());
            // get the default initial Directory Context
            InitialDirContext iDirC = new InitialDirContext();
            // get the DNS records for inetAddress
            Attributes attributes = iDirC.getAttributes("dns:/" + inetAddress.getHostName());
            // get an enumeration of the attributes and print them out
            NamingEnumeration attributeEnumeration = attributes.getAll();
            System.out.println("-- DNS INFORMATION --");
            while (attributeEnumeration.hasMore())
            {
                System.out.println("" + attributeEnumeration.next());
            }
            attributeEnumeration.close();
        }
        catch (UnknownHostException exception)
        {
            System.err.println("ERROR: No Internet Address for '" + args[0] + "'");
        }
        catch (NamingException exception)
        {
            System.err.println("ERROR: No DNS record for '" + args[0] + "'");
        }
    }
}
