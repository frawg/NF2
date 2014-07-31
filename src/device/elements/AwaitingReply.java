package device.elements;

import java.util.Date;

import NetworkData.Frame;

public class AwaitingReply {
	private Frame f = null;
	private Date expiry = null;
	
	public AwaitingReply(Frame f, long date)
	{
		this.f = f;
		this.expiry = new Date(date);
	}
	
	public Frame getFrame() { return f; }
	public boolean isExpired() 
	{ return expiry.before(new Date(System.currentTimeMillis())); }
}
