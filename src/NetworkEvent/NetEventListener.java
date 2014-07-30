package NetworkEvent;

public interface NetEventListener {
	public void ReceiveEvent(NetEvent e);
	public void DropEvent(NetEvent e);
	public void SendEvent(NetEvent e);
}
