package protocol.IPv4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPv4 {
	private Address address, subnet;
	
	public IPv4(Address address, int subnet) // new Address(192,168,1,1), 24
	{
		this.address = address;
		this.subnet = new Address(Address.CIDRtoInt(subnet));
	}

	public int getIPv4() { return address.toInt(); }
	public int getSubnet() { return subnet.toInt(); }
	/*
	 *  IPv4 myIP = new IPv4(new Address(192,168,1,1), 24); == 192.168.1.1/24
	 *  myIP.isInRange(new Address(192,168,7,2).toInt); // false
	 *  myIP.isInRange(new Address(192,168,1,253).toInt); // true
	 *  
	 */
	public boolean isInRange(int address) {
        int diff = address - getNetworkAddress();
        return (diff >= 0 && (diff <= (getBroadcastAddress() - getNetworkAddress())));
    }
	
	public int getNetworkAddress(){ return (address.toInt() & subnet.toInt()); }
	public int getBroadcastAddress(){ return (address.toInt() | ~(subnet.toInt())); }
	
}