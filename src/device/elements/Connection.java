package device.elements;

import device.Port;

public class Connection {
	private int deviceStart, deviceEnd, portStart, portEnd, distance; // distance in Km
	private long speed; // in bytes
	private Port start, end = null;
	
	public Connection(int deviceStart, int deviceEnd, int portStart, int portEnd,
			Port start, Port end, long speed, int distance)
	{
		this.speed = speed;
		this.deviceStart = deviceStart;
		this.deviceEnd = deviceEnd;
		this.portStart = portStart;
		this.portEnd = portEnd;
		this.start = start;
		this.end = end;
		this.distance = distance;
	}
	
	public int getPortEnd() { return portEnd; }
	public int getPortStart() { return portStart; }
	public int getDeviceStart() { return deviceStart; }
	public int getDeviceEnd() { return deviceEnd; }
	public int getDistance() { return distance; }
	
	public long getLinkSpeed() { return speed; }
	public long getSpeed() { return Math.min(start.getSpeed(), end.getSpeed()); }
	public double getTimeTakenSeconds(int byteSize) {
		return ((double)(byteSize) / (double)(getSpeed())) + ((double)(distance) / (double)(getSpeed()));
	}
}
