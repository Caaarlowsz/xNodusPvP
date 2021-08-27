package n;

import org.bukkit.event.*;
import org.bukkit.entity.*;
import Nodus.Main.*;
import org.bukkit.*;

public class d implements Listener
{
    public static String PegarConfig(final String nomeconfig, final Player p) {
        return ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString(nomeconfig).replaceAll("%p", p.getName()));
    }
    
    public static String get(final String nomeconfig) {
        return ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString(nomeconfig));
    }
}
