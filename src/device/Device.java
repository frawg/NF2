package device;

import java.util.ArrayList;

import NetworkEvent.NetEvent;
import NetworkEvent.NetEventListener;

public class Device {
	public enum TYPE{ SWITCH, COMPUTER, HUB, ROUTER }
	protected Port[] ports = null;
	protected String name = null;
	protected TYPE type = null;
	protected ArrayList<NetEventListener> listener = null;
	
	
	public Device(String name, int portnums, TYPE t){
		ports = new Port[portnums];
		this.name = name;
		this.type = t;
		listener = new ArrayList<NetEventListener>();
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
	
	public void addListener(NetEventListener l) { listener.add(l); }
	
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
