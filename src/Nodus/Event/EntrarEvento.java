package Nodus.Event;

import Nodus.Main.*;
import org.bukkit.event.player.*;
import me.confuser.barapi.*;
import org.bukkit.*;
import Nodus.Warp.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class EntrarEvento implements Listener
{
    public static Main plugin;
    
    public EntrarEvento(final Main main) {
        EntrarEvento.plugin = main;
    }
    
    @EventHandler
    public void Entrar(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        e.setJoinMessage((String)null);
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("�b�l============================");
        p.sendMessage("");
        p.sendMessage("           �b  Bem-Vindo!");
        p.sendMessage("");
        p.sendMessage("�b�l=============================");
        BarAPI.setMessage(p, ChatColor.translateAlternateColorCodes('&', EntrarEvento.plugin.getConfig().getString("barramensagemjoin")), 10);
        Bukkit.broadcastMessage(Main.getInstance().getConfig().getString("EntrarMensagem").replaceAll("&", "�").replaceAll("%p", p.getDisplayName()));
        SpawnTeleport.TpSpawn(p);
        p.setLevel(0);
    }
}
