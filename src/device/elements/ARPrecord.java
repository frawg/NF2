package device.elements;

import java.util.Date;

public class ARPrecord {
	private String mac, ip = null;
	private Date age = null;
	private int TimeToLive; // time for record to live in seconds
	private boolean dynamic;
	
	public ARPrecord(String mac, String ip, int TimeToLive, boolean isDynamic)
	{
		this.mac = mac;
		this.ip = ip;
		this.TimeToLive = TimeToLive;
		dynamic = isDynamic;
		updateAge();
	}
	/**
	 * @return the mac
	 */
	public String getMac() {
		return mac;
	}
	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	
	public int getTTL() { return TimeToLive; }
	public void setTTL(int TimeToLive) { this.TimeToLive = TimeToLive; }
	
	public void updateAge(){ this.age = new Date(System.currentTimeMillis() + (TimeToLive * 1000)); }
	public boolean isExpired() { return (age.before(new Date(System.currentTimeMillis())) && dynamic); }
	public boolean isDynamic() { return dynamic; }
}
