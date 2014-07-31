package device.elements;

import protocol.IPv4.Address;
import protocol.IPv4.IPv4;

public class RoutingRecord {
	public enum TYPE
	{
		STATIC,
		CONNECTED,
		RIP,
		EIGRP,
		OSPF
	}
	
	private int portIndex;
	private IPv4 address = null;
	private TYPE t = null;
	
	public RoutingRecord(IPv4 address, int portIndex, TYPE t)
	{
		this.address = address;
		this.portIndex = portIndex;
		this.t = t;
	}
	
	public int getPortIndex(){ return portIndex; }
	public TYPE getType() { return t; }
	public String getNetworkToString() { return Address.IntToString(address.getNetworkAddress()); }
	
	public boolean isOfThisNetwork(String str)
	{ return address.isInRange(new Address(str).toInt()); }
}
