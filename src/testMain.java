import java.util.ArrayList;

import NetworkEvent.NetEvent;
import NetworkEvent.NetEventListener;
import device.Device;
import device.Device.TYPE;
import device.enddevice.Switch;
 
public class testMain {
	public static void main(String []args)
	{
		boolean receiveFlag = false;
		int currentIndex;
		ArrayList<Device> devList = new ArrayList<Device>();
		ArrayList<Device> broadcastDeviceHistory = new ArrayList<Device>();
		
		Switch sw1 = new Switch("Switch", Switch.PORTSIZE.SIX);
		sw1.addListener(new NetEventListener() {
			
			@Override
			public void SendEvent(NetEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getSource().getName() + " " + e.getAction().toString() + " to " 
						+ e.getItem().getPacket().getSourceIP());
			}
			
			@Override
			public void ReceiveEvent(NetEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getSource().getName() + " " + e.getAction().toString() + " from " 
				+ e.getItem().getPacket().getDestinationIP());
			}
			
			@Override
			public void DropEvent(NetEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getSource().getName() + " " + e.getAction().toString() + " frame from "
				+ e.getItem().getPacket().getSourceIP());		
			}
		});
		
		devList.add(sw1);
		
		if (devList.get(currentIndex).getType() == TYPE.COMPUTER)
		{
		} else if (devList.get(currentIndex).getType() == TYPE.HUB)
		{
		} else if (devList.get(currentIndex).getType() == TYPE.SWITCH)
		{
			Switch temp = (Switch)devList.get(currentIndex);
			int i = temp.NextExit(f.getDestinationMAC());
			if (i >= temp.getTotalAmountofPort())
			{
				//broadcast
				broadcastDeviceHistory.add(temp);
			}
			else
			{
				sw1.getPortAt(i).send(f);
				sw1.TriggerSend(e);
			}

		} else if (devList.get(currentIndex).getType() == TYPE.ROUTER)
		{
			
		}
	}
}
