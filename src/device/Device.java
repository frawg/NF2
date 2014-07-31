package device;

import java.util.ArrayList;

import device.elements.AwaitingFrame;
import device.elements.AwaitingReply;
import NetworkData.Frame;
import NetworkEvent.NetEvent;
import NetworkEvent.NetEventListener;

public abstract class Device {
	public enum TYPE{ SWITCH, COMPUTER, HUB, ROUTER }
	protected Port[] ports = null;
	protected String name = null;
	protected TYPE type = null;
	protected ArrayList<NetEventListener> listener = null;
	protected ArrayList<AwaitingReply> awaiting = null;
	
	public Device(String name, int portnums, TYPE t){
		ports = new Port[portnums];
		this.name = name;
		this.type = t;
		listener = new ArrayList<NetEventListener>();
		awaiting = new ArrayList<AwaitingReply>();
		initPorts();
	}
	
	protected void initPorts()
	{
		for (int i = 0; i < ports.length; i ++)
		{ ports[i] = new Port(i, 50 * 1000 * 1000); } // mega to kilo to byte
	}
	
	public String getName() { return name; }
	public int getTotalAmountofPort() { return ports.length; }
	public TYPE getType(){ return type; }
	public Port getPortAt(int index)
	{
		if (index >= ports.length)
			return null;
		else
			return ports[index];
	}
	
	public abstract int NextExit();
	public abstract int NextExit(String str);
	public boolean isBroadcast(int index) { return (index < ports.length); }
	
	public void addListener(NetEventListener l) { listener.add(l); }

	protected void addAwaitingReply(AwaitingReply f) { awaiting.add(f); }
	
	public boolean isReplyValid(Frame f)
	{
		for (AwaitingReply ar : awaiting)
			if (ar.getFrame() == f)
				return true;
		return false;
	}
	
	public void setString(String name) { this.name = name; }
	
	public void TriggerReceive(NetEvent e)
	{
		for (NetEventListener l : listener)
		{ l.ReceiveEvent(e); }
	}
	public void TriggerDrop(NetEvent e)
	{
		for (NetEventListener l : listener)
		{ l.DropEvent(e); }
	}
	public void TriggerSend(NetEvent e)
	{
		for (NetEventListener l : listener)
			l.SendEvent(e);
	}
}
