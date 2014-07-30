package NetworkEvent;

import NetworkData.Frame;
import device.Device;

public class NetEvent {
	public enum ACTION { RECEIVE, SEND, DROPPED }
	
	private Device source = null;
	private ACTION action = null;
	private Frame item = null;
	private int portSourceIndex;
	private long timeTaken;
	
	public NetEvent(Device source, int portSourceIndex, ACTION action, Frame item, long timeTaken)
	{
		this.source = source;
		this.portSourceIndex = portSourceIndex;
		this.action = action;
		this.item = item;
		this.timeTaken = timeTaken;
	}

	public Device getSource() { return source; }
	public ACTION getAction() {	return action; }
	public Frame getItem() { return item; }
	public long getTimeTaken() { return timeTaken; }
}
