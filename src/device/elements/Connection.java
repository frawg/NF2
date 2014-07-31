package device.elements;

import device.Device;
import device.Port;

public class Connection {
	private int portStart, portEnd, distance; // distance in Km
	private long speed; // in bytes
	private Device start, end;
	
	public Connection(Device dstart, Device dend, int portStart, int portEnd, long speed, int distance)
	{
		this.speed = speed;
		this.start = dstart;
		this.end = dend;
		this.portStart = portStart;
		this.portEnd = portEnd;
		this.distance = distance;
	}
	
	public int getPortEnd() { return portEnd; }
	public int getPortStart() { return portStart; }
	public Device getDeviceStart() { return start; }
	public Device getDeviceEnd() { return end; }
	public int getDistance() { return distance; }
	public long getLinkSpeed() { return speed; }
	
	public long getSpeed() { return Math.min(start.getPortAt(portStart).getSpeed(), end.getPortAt(portEnd).getSpeed()); }
	public double getTimeTakenSeconds(int byteSize) {
		return ((double)(byteSize) / (double)(getSpeed())) + ((double)(distance) / (double)(getSpeed()));
	}
	
	public Device getOpposite(Device d)
	{ return (start == d) ? end : start; }
	
	public int getOppositePort(Device d)
	{ return (start == d) ? portEnd : portStart; }
}
