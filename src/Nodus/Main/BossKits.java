package Nodus.Main;

import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import me.confuser.barapi.*;
import org.bukkit.*;
import org.bukkit.plugin.*;
import org.bukkit.event.*;

public class BossKits implements Listener
{
    public Main plugin;
    
    public BossKits(final Main instance) {
        this.plugin = instance;
    }
    
    @EventHandler
    public void JogadorKIT(final EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        if (!(e.getDamager() instanceof Player)) {
            return;
        }
        final Player t = (Player)e.getEntity();
        final Player p = (Player)e.getDamager();
        BarAPI.setMessage(p, " §7 " + t.getDisplayName() + "§b§l >>§f§l " + Habilidade.getAbility(t));
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
            @Override
            public void run() {
                BarAPI.removeBar(p);
            }
        }, 100L);
    }
}
