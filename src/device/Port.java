package device;

import protocol.MACGenerator;
import protocol.IPv4.Address;
import protocol.IPv4.IPv4;

public class Port {
	private int connectionIndex, index;
	private String mac = null;
	private IPv4 ip = null;
	private Address gateway = null;
	private long speed; // in Bytes per second
	
	public Port(int index, long speed)
	{
		this.index = index;
		this.speed = speed;
		this.mac = MACGenerator.generateMAC();
	}
	
	public String getIP() { return ip.getStringIP(); }
	public int getIntIP() { return ip.getIPv4(); }
	public String getGateway() { return gateway.toString(); }
	public int getIntGateway() { return gateway.toInt(); }
	public String getSubnet() { return ip.getStringSubnet(); }
	public int getIntSubnet() { return ip.getSubnet(); }
	public long getSpeed() { return speed; }
	public String getMAC() { return mac; }
	public int getIndex() { return index; }
	public int getConnectionIndex() { return connectionIndex; }
	
	public void setIndex(int index) { this.index = index; }
	public void setConnectionIndex(int index) { this.connectionIndex = index; }
	public void setSpeed(long speed) { this.speed = speed; }
	public void setIP(String ip, int subnet) { this.ip = new IPv4(new Address(ip), subnet);}
	public void setGateWay(String ip) { this.gateway = new Address(ip); } 
	
	public double getSpeedinMBps() { return ((double)speed / 1000); }
	public double getSpeedinGBps() { return ((double)speed / 1000 / 1000); }
	public void setSpeedinMBps(double speed) { this.speed = (long)(speed * 1000 * 1000); }
	public void setSpeedinGBps(double speed) { this.speed = (long)(speed * 1000 * 1000 * 1000); }
	
	public double getTimeTakenSeconds(int sizeInBytes) { return ( ((double)(sizeInBytes)) / (double)speed ); }	
}