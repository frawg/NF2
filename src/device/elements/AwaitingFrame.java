package device.elements;

import device.Device;
import NetworkData.Frame;

public class AwaitingFrame {
	private Frame f = null;
	private Device dev;
	private int port;
	private boolean isBroadcast;
	
	public AwaitingFrame(Frame f, Device dev, int portIndex, boolean isBroadcast)
	{
		this.f = f;
		this.dev = dev;
		this.port = portIndex;
		this.isBroadcast = isBroadcast;
	}

	public Frame getFrame() { return this.f; }
	public boolean isBroadcast() { return isBroadcast; }
	public Device getDevice() { return dev; }
	public int getPortIndex() { return port; }
	public void setPortIndex(int index) { this.port = index; }
}
