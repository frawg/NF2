package device.enddevice;

import java.util.ArrayList;

import device.Device;
import device.elements.ARPrecord;

public class Computer extends Device {
	private ArrayList<ARPrecord> arpCache = null;
	
	public Computer(String name) {
		super(name, 1, TYPE.COMPUTER);
		arpCache = new ArrayList<ARPrecord>();
		
	}

}
