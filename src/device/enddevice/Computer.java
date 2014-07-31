package device.enddevice;

import java.util.ArrayList;

import NetworkData.Frame;
import protocol.MACGenerator;
import device.Device;
import device.elements.ARPrecord;
import device.elements.AwaitingFrame;
import device.elements.AwaitingReply;

public class Computer extends Device {
	private ArrayList<ARPrecord> arpCache = null;
	private int age;
	
	public Computer(String name, int age) {
		super(name, 1, TYPE.COMPUTER);
		arpCache = new ArrayList<ARPrecord>();
		this.age = age;
	}
	
	public void addCache(String ip, String mac)
	{ arpCache.add(new ARPrecord(mac, ip, 1800, true)); }
	
	public String getMACforIP(String ip)
	{
		for (ARPrecord r : arpCache)
			if (r.getIp().equals(ip))
				return r.getMac();
		return MACGenerator.BROADCAST;
	}
	
	public String getIPforMAC(String mac)
	{
		for (ARPrecord r: arpCache)
			if (r.getMac().equals(mac))
				return r.getIp();
		return null;
	}
	
	public void addAwaitingReply(Frame f)
	{
		super.addAwaitingReply(new AwaitingReply(f, System.currentTimeMillis()));
	}

	@Override
	public int NextExit() { return 0; }

	@Override
	public int NextExit(String destinationMAC) { return 0; }
}
