package Nodus.Event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import Nodus.Main.Main;

public class PlacaColorida implements Listener {
	public PlacaColorida(final Main instance) {
	}

	@EventHandler
	public void placacoloridinha(final SignChangeEvent event) {
		final String l0 = event.getLine(0);
		final String l2 = event.getLine(1);
		final String l3 = event.getLine(2);
		final String l4 = event.getLine(3);
		event.setLine(0, l0.replaceAll("(&([a-f0-9]))", "§$2").replaceAll("&k", "§k").replaceAll("&l", "§l")
				.replaceAll("&m", "§m").replaceAll("&n", "§n").replaceAll("&o", "§o").replaceAll("&r", "§r"));
		event.setLine(1, l2.replaceAll("(&([a-f0-9]))", "§$2").replaceAll("&k", "§k").replaceAll("&l", "§l")
				.replaceAll("&m", "§m").replaceAll("&n", "§n").replaceAll("&o", "§o").replaceAll("&r", "§r"));
		event.setLine(2, l3.replaceAll("(&([a-f0-9]))", "§$2").replaceAll("&k", "§k").replaceAll("&l", "§l")
				.replaceAll("&m", "§m").replaceAll("&n", "§n").replaceAll("&o", "§o").replaceAll("&r", "§r"));
		event.setLine(3, l4.replaceAll("(&([a-f0-9]))", "§$2").replaceAll("&k", "§k").replaceAll("&l", "§l")
				.replaceAll("&m", "§m").replaceAll("&n", "§n").replaceAll("&o", "§o").replaceAll("&r", "§r"));
	}
}
