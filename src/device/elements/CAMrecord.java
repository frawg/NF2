package device.elements;

import java.util.Date;

public class CAMrecord {
	private String mac;
	private int portNumber;
	private Date age = null;
	private int TimeToLive; // time for record to live in seconds
	private boolean dynamic;
	
	public CAMrecord(String mac, int portNumber, int TimeToLive, boolean isDynamic)
	{
		this.mac = mac;
		this.portNumber = portNumber;
		this.TimeToLive = TimeToLive;
		this.dynamic = isDynamic;
		updateAge();
	}
	
	public String getMAC() { return mac; }
	public int getPortNumber() { return portNumber; }
	
	public int getTTL() { return TimeToLive; }
	public void setTTL(int TimeToLive) { this.TimeToLive = TimeToLive; }
	
	public void updateAge(){ this.age = new Date(System.currentTimeMillis() + (TimeToLive * 1000)); }
	public boolean isExpired() { return (age.before(new Date(System.currentTimeMillis())) && dynamic); }
	public boolean isDynamic() { return dynamic; }
}
