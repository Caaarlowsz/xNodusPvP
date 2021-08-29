package Nodus.Event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class NoHunger implements Listener {
	@EventHandler
	public void nuncaFome(final FoodLevelChangeEvent evento) {
		evento.setFoodLevel(20);
	}
}
