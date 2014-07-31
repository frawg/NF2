package device.enddevice;

import device.Device;

public class Hub extends Device {

	public Hub(String name) {
		super(name, 10, TYPE.HUB);
	}

	@Override
	public int NextExit() { return ports.length; }

	@Override
	public int NextExit(String str) { return ports.length; }
}
