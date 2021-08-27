package Nodus.Kits;

import java.util.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.potion.*;
import java.util.concurrent.*;
import org.bukkit.event.*;

public class Urgal implements Listener, CommandExecutor
{
    public static Main plugin;
    public static HashMap<String, Long> cooldown;
    
    static {
        Urgal.cooldown = new HashMap<String, Long>();
    }
    
    public Urgal(final Main main) {
        Urgal.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("Urgal")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.Urgal")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + "Urgal");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.urgal.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    Habilidade.setAbility(p, "Urgal");
                    p.getInventory().addItem(new ItemStack[] { espada });
                    final ItemStack bow = new ItemStack(Material.REDSTONE);
                    final ItemMeta bowmeta = bow.getItemMeta();
                    bowmeta.setDisplayName("§6Urgal Strength");
                    bow.setItemMeta(bowmeta);
                    p.getInventory().addItem(new ItemStack[] { bow });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Urgal.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Urgal.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void interact(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (e.getPlayer().getItemInHand().getType() == Material.REDSTONE && Main.urgal.contains(e.getPlayer().getName()) && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            e.setCancelled(true);
            if (!Urgal.cooldown.containsKey(p.getName()) || Urgal.cooldown.get(p.getName()) <= System.currentTimeMillis()) {
                e.setCancelled(true);
                p.updateInventory();
                p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 500, 1));
                p.playSound(p.getLocation(), Sound.WITHER_DEATH, 1.0f, 1.0f);
                Urgal.cooldown.put(p.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(60L));
                return;
            }
            p.sendMessage(ChatColor.RED + "Cooldown");
        }
    }
}
