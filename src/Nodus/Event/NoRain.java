package Nodus.Event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class NoRain implements Listener {
	@EventHandler
	public void nuncaChover(final WeatherChangeEvent evento) {
		evento.setCancelled(true);
	}
}
