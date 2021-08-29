package Nodus.Kits;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TimeSecondEvent extends Event {
	private static final HandlerList handlers;

	static {
		handlers = new HandlerList();
	}

	public static HandlerList getHandlerList() {
		return TimeSecondEvent.handlers;
	}

	public HandlerList getHandlers() {
		return TimeSecondEvent.handlers;
	}
}
