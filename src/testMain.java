import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;

import NetworkEvent.NetEvent;
import NetworkEvent.NetEventListener;
import device.Device;
import device.Device.TYPE;
import device.elements.AwaitingFrame;
import device.elements.Connection;
import device.enddevice.Computer;
import device.enddevice.Switch;
 
public class testMain {
	public static void main(String []args)
	{
		NetEventListener list = new NetEventListener() {
			
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
		};
		
		boolean receiveFlag = false;
		Device currentIndex;
		ArrayList<Device> devList = new ArrayList<Device>();
		LinkedBlockingDeque<AwaitingFrame> deviceJobQueue = new LinkedBlockingDeque<AwaitingFrame>();
		ArrayList<Connection> conns = new ArrayList<Connection>();
		
		Switch sw1 = new Switch("Switch", Switch.PORTSIZE.SIX);
		sw1.addListener(list);
		
		Computer pc1 = new Computer("PC1", 60);
		pc1.getPortAt(0).setIP("192.168.1.1", 24);
		pc1.getPortAt(0).setConnectionIndex(0);
		
		Computer pc2 = new Computer("PC2", 60);
		pc2.getPortAt(0).setIP("192.168.1.2", 24);
		pc2.getPortAt(0).setConnectionIndex(1);

		conns.add(new Connection(pc1, sw1, 0, 0, pc1.getPortAt(0).getSpeed(), 20));
		conns.add(new Connection(pc2, sw1, 0, 1, pc2.getPortAt(0).getSpeed(), 20));
		
		devList.add(pc1);
		devList.add(sw1);
		devList.add(pc2);

		while(!deviceJobQueue.isEmpty())
		{
			AwaitingFrame curr = null;
			int portIndex;
			try {
				curr = deviceJobQueue.take();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (curr != null)
			{
				portIndex = curr.getPortIndex(); 
				currentIndex = curr.getDevice();
				
				while (!curr.getFrame().getDestinationMAC().equals(currentIndex.getPortAt(portIndex).getMAC()))
				{
									
					if (currentIndex.getType() == TYPE.COMPUTER)
					{
						if (curr.getDevice() == currentIndex)
						{
							currentIndex = conns.get(currentIndex.getPortAt(
									currentIndex.NextExit()).getConnectionIndex()).getOpposite(currentIndex);
							portIndex = conns.get(currentIndex.getPortAt(
									currentIndex.NextExit()).getConnectionIndex()).getOppositePort(currentIndex);
						}
						else
						{
							if (curr.getPortIndex() >= curr.getDevice().getTotalAmountofPort())
							{
								if (!deviceJobQueue.isEmpty() && !deviceJobQueue.peek().isBroadcast())
								{
									curr = deviceJobQueue.take();
									portIndex = curr.getPortIndex();
								}
								else 
								{
									curr = deviceJobQueue.peek();
									portIndex = curr.getPortIndex();
								}
							}
						}
						
					} else if (currentIndex.getType() == TYPE.SWITCH)
					{
						int m = currentIndex.NextExit(curr.getFrame().getSourceMAC());
						if (deviceJobQueue.peek() == curr)
						{
							if (m == portIndex)
							{
								curr.setPortIndex(portIndex + 1);
								portIndex += 1;
								m = portIndex;
							}
							else if (portIndex >= currentIndex.getTotalAmountofPort())
							{
								if (!deviceJobQueue.isEmpty() && !deviceJobQueue.peek().isBroadcast())
								{
									curr = deviceJobQueue.take();
									portIndex = curr.getPortIndex();
								}
								else 
								{
									curr = deviceJobQueue.peek();
									portIndex = curr.getPortIndex();
								}
							}
						}
						
						m = currentIndex.NextExit(curr.getFrame().getDestinationMAC());
						if (m >= currentIndex.getTotalAmountofPort())
						{
							if (currentIndex.NextExit(curr.getFrame().getSourceMAC()) == 0)
								m = 1;
							else
								m = 0;
							deviceJobQueue.offerFirst(new AwaitingFrame(curr.getFrame(), currentIndex, m, true));
							curr = deviceJobQueue.peek();
						}
						currentIndex = conns.get(currentIndex.getPortAt(m).getConnectionIndex()).getOpposite(currentIndex);
						portIndex = conns.get(currentIndex.getPortAt(m).getConnectionIndex()).getOppositePort(currentIndex);
						
					} else if (currentIndex.getType() == TYPE.HUB)
					{
						int m = 0;
						if (deviceJobQueue.peek() == curr)
						{
							if (m == portIndex)
							{
								curr.setPortIndex(portIndex + 1);
								portIndex += 1;
								m = portIndex;
							}
							else if (portIndex >= currentIndex.getTotalAmountofPort())
								if (!deviceJobQueue.isEmpty() && !deviceJobQueue.peek().isBroadcast())
								{
									curr = deviceJobQueue.take();
									portIndex = curr.getPortIndex();
								}
								else 
								{
									curr = deviceJobQueue.peek();
									portIndex = curr.getPortIndex();
								}
							currentIndex = conns.get(currentIndex.getPortAt(m).getConnectionIndex()).getOpposite(currentIndex);
							portIndex = conns.get(currentIndex.getPortAt(m).getConnectionIndex()).getOppositePort(currentIndex);
						}
						else
						{
							if (currentIndex.NextExit(curr.getFrame().getSourceMAC()) == 0)
								m = 1;
							else
								m = 0;
							deviceJobQueue.offerFirst(new AwaitingFrame(curr.getFrame(), currentIndex, m, true));
							curr = deviceJobQueue.peek();
						}
						currentIndex = conns.get(currentIndex.getPortAt(m).getConnectionIndex()).getOpposite(currentIndex);
						portIndex = conns.get(currentIndex.getPortAt(m).getConnectionIndex()).getOppositePort(currentIndex);
					}else if (currentIndex.getType() == TYPE.ROUTER)
					{
						
					}
				}
			}
		}
	}
}
