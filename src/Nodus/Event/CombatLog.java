package Nodus.Event;

import org.bukkit.plugin.*;
import java.util.*;
import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import Nodus.Main.*;
import org.bukkit.event.*;
import org.bukkit.*;
import org.bukkit.event.player.*;
import me.confuser.barapi.*;
import Nodus.Commands.*;

public class CombatLog implements Listener
{
    public Plugin plugin;
    public static List<String> Sair;
    public static List<String> combate;
    Config config;
    
    static {
        CombatLog.Sair = new ArrayList<String>();
        CombatLog.combate = new ArrayList<String>();
    }
    
    public CombatLog(final Main plugin) {
        this.config = Config.getInstance();
        this.plugin = (Plugin)plugin;
    }
    
    @EventHandler
    public void onEntityDamage(final EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
            final Player p = (Player)e.getEntity();
            final Player hitter = (Player)e.getDamager();
            if (!CombatLog.Sair.contains(p.getName()) && !CombatLog.Sair.contains(hitter.getName())) {
                CombatLog.Sair.add(p.getName());
                CombatLog.Sair.add(hitter.getName());
                CombatLog.combate.add(p.getName());
                CombatLog.combate.add(hitter.getName());
                PvPBar.setAbility(p, "Sim");
                PvPBar.setAbility(hitter, "Sim");
                hitter.sendMessage("§3Voce entrou em combate com: §7" + p.getDisplayName());
                Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        CombatLog.Sair.remove(p.getName());
                        CombatLog.Sair.remove(hitter.getName());
                        PvPBar.setAbility(p, "Nao");
                        PvPBar.setAbility(hitter, "Nao");
                        hitter.sendMessage("§7§l Voce esta fora de combate!");
                        p.sendMessage("§7§l Voce esta fora de combate!");
                        CombatLog.combate.remove(p.getName());
                        CombatLog.combate.remove(hitter.getName());
                    }
                }, 350L);
            }
        }
    }
    
    @EventHandler
    public void onPlayerExit(final PlayerQuitEvent e) {
        final Player p = e.getPlayer();
        if (CombatLog.Sair.contains(p.getName())) {
            p.setHealth(0.0);
            final World w = Bukkit.getServer().getWorld(this.config.getData().getString("SPAWN.World"));
            final double x = this.config.getData().getDouble("SPAWN.X");
            final double y = this.config.getData().getDouble("SPAWN.Y");
            final double z = this.config.getData().getDouble("SPAWN.Z");
            p.teleport(new Location(w, x, y, z));
            Bukkit.broadcastMessage("§a" + p.getName() + "§7 Deslogou no PvP !");
        }
    }
    
    @EventHandler
    public void onMe2(final PlayerCommandPreprocessEvent event) {
        final Player p = event.getPlayer();
        if (Main.SemPvP(p) && CombatLog.Sair.contains(p.getName())) {
            event.setCancelled(true);
            BarAPI.setMessage(p, "§cSem comandos durante o PvP!", 5);
        }
    }
    
    @EventHandler
    public void onM4e2(final PlayerCommandPreprocessEvent event) {
        final Player p = event.getPlayer();
        if (Admin.adm.contains(p)) {
            event.setCancelled(false);
        }
    }
    
    @EventHandler
    public void onMe26(final PlayerCommandPreprocessEvent event) {
        final Player p = event.getPlayer();
        if (event.getMessage().toLowerCase().startsWith("/version") || event.getMessage().toLowerCase().startsWith("/bukkit") || event.getMessage().toLowerCase().startsWith("/plugin") || event.getMessage().toLowerCase().startsWith("/plugins") || event.getMessage().toLowerCase().startsWith("/ver") || event.getMessage().toLowerCase().startsWith("/?") || event.getMessage().toLowerCase().startsWith("/pl")) {
            event.setCancelled(true);
        }
    }
}
