package Nodus.Kits2;

import Nodus.Main.*;
import org.bukkit.entity.*;
import org.bukkit.event.entity.*;
import java.util.*;
import org.bukkit.inventory.*;
import org.bukkit.*;
import org.bukkit.event.*;

public class TrollHAB implements Listener
{
    public static Main plugin;
    public static Main main;
    public static ArrayList<Player> troll;
    
    static {
        TrollHAB.troll = new ArrayList<Player>();
    }
    
    public TrollHAB(final Main main) {
        TrollHAB.plugin = main;
    }
    
    @EventHandler
    public void onEntityDamage(final EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            final Player p = (Player)e.getEntity();
            final Player d = (Player)e.getDamager();
            if (!Main.troll.contains(d)) {
                return;
            }
            final Random r = new Random();
            final int rand = r.nextInt(100);
            if (rand <= 15) {
                boolean lostsoup = false;
                final ItemStack[] item = p.getInventory().getContents();
                for (int slot = 0; slot < 36; ++slot) {
                    final ItemStack soup = item[slot];
                    if (!lostsoup && soup != null && soup.isSimilar(new ItemStack(Material.MUSHROOM_SOUP))) {
                        lostsoup = true;
                        p.getInventory().setItem(slot, new ItemStack(Material.AIR));
                    }
                }
                d.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.chat(ChatColor.RED + "Cade minhas sopas?");
                d.chat(ChatColor.GREEN + "Eu catei =P");
            }
        }
    }
}
