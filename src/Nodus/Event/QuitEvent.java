package Nodus.Event;

import org.bukkit.event.player.*;
import org.bukkit.*;
import Nodus.Main.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class QuitEvent implements Listener
{
    @EventHandler
    public void Quit(final PlayerQuitEvent e) {
        final Player p = e.getPlayer();
        Main.removeAbility(p);
        Bukkit.broadcastMessage(Main.getInstance().getConfig().getString("SairMensagem").replaceAll("&", "§").replaceAll("%p", p.getDisplayName()));
        p.getInventory().clear();
        e.setQuitMessage((String)null);
        Habilidade.setAbility(p, "None");
        p.teleport(p.getWorld().getSpawnLocation());
    }
}
