package Nodus.Event;

import org.bukkit.event.weather.*;
import org.bukkit.event.*;

public class NoRain implements Listener
{
    @EventHandler
    public void nuncaChover(final WeatherChangeEvent evento) {
        evento.setCancelled(true);
    }
}
