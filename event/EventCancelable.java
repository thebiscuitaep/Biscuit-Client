package biscuit.event;

public class EventCancelable extends Event{

	private boolean cancalled = false;
	
	public boolean isCancalled() {
		return cancalled;
	}
	
	public void setCancalled(boolean cancalled) {
		this.cancalled = cancalled;
	}
	
}
