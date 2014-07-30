package device;

public class Port {
	private int connectionIndex, index;
	private String mac = null;
	private String ip, gateway, subnet = null;
	private long speed; // in Bytes per second
	
	public Port(int index, long speed)
	{
		this.index = index;
		this.speed = speed;
		//this.mac = MACGenerate.generateMAC();
	}
	
	public String getIP() { return ip; }
	public String getGateway() { return gateway; }
	public String getSubnet() { return subnet; }
	public long getSpeed() { return speed; }
	public String getMAC() { return mac; }
	public int getIndex() { return index; }
	public int getConnectionIndex() { return connectionIndex; }
	
	public void setIndex(int index) { this.index = index; }
	public void setConnectionIndex(int index) { this.connectionIndex = index; }
	public void setSpeed(long speed) { this.speed = speed; }
	public void setIP(String ip, String gateway, String subnet) { this.ip = ip;
		this.gateway = gateway; this.subnet = subnet; }
	
	public double getSpeedinMBps() { return ((double)speed / 1000); }
	public double getSpeedinGBps() { return ((double)speed / 1000 / 1000); }
	public void setSpeedinMBps(double speed) { this.speed = (long)(speed * 1000 * 1000); }
	public void setSpeedinGBps(double speed) { this.speed = (long)(speed * 1000 * 1000 * 1000); }
	
	public double getTimeTakenSeconds(int sizeInBytes) { return ( ((double)(sizeInBytes)) / (double)speed ); }	
}