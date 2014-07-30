package device.enddevice;

import java.util.ArrayList;

import device.Device;
import device.Device.TYPE;
import device.elements.CAMrecord;

public class Switch extends Device{
	public enum PORTSIZE { 
		SIX(6),
		TWELVE(12),
		TWENTYFOUR(24);
		private int value;
		PORTSIZE(int value) { this.value = value; }
		public int getValue() { return value; }
	}
	private ArrayList<CAMrecord> camTable = null;
	private int TimeToLive;
	
	public Switch(String name, PORTSIZE size)
	{
		super(name, size.getValue(), Device.TYPE.SWITCH);
		camTable = new ArrayList<CAMrecord>();
		TimeToLive = 1800;
	}
	
	public void AddDynamicCAMRecord(String sourceMAC, int portNum)
	{ camTable.add(new CAMrecord(sourceMAC, portNum, TimeToLive, true)); }
	
	public void AddStaticCAMRecord(String sourceMAC, int portNum)
	{ camTable.add(new CAMrecord(sourceMAC, portNum, TimeToLive, false)); }
	
	public int NextExit(String destinationMAC)
	{ 
		for (CAMrecord r : camTable)
		{
			if (r.getMAC().equals(destinationMAC) && !r.isExpired())
			{
				return r.getPortNumber();
			}
			else if (r.isExpired()) camTable.remove(r);
		}
		return ports.length; // if does not contain, then i will return the overflow size of ports
	}
	
	private void cleanRecords()
	{
		for (CAMrecord r : camTable)
		{
			if (r.isExpired())
				camTable.remove(r);
		}
	}
	
	public void removeStaticRecord(String mac)
	{
		for (CAMrecord r : camTable)
			if (r.getMAC().equals(mac))
				camTable.remove(r);
	}
	
	public int getTimeToLive() { return TimeToLive; }
	public void setTimeToLive(int seconds) { this.TimeToLive = seconds; }
}
