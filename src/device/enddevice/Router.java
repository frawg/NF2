package device.enddevice;

import java.util.ArrayList;

import device.Device;
import device.elements.RoutingRecord;

public class Router extends Device {
	private ArrayList<RoutingRecord> routingTable = null;
	
	public Router(String name) {
		super(name, 4, TYPE.ROUTER);
		this.routingTable = new ArrayList<RoutingRecord>();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int NextExit() { return ports.length; }

	@Override
	public int NextExit(String str) {
		for (RoutingRecord rr : routingTable)
			if (rr.isOfThisNetwork(str))
				return rr.getPortIndex();
		return ports.length;
	}
	
	public boolean setPortIP(String ip, int subnet, int port)
	{
		if (port < ports.length)
		{
			ports[port].setIP(ip, subnet);
			return true;
		}
		return false;
	}
}
