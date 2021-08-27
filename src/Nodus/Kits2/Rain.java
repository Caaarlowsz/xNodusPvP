package Nodus.Kits2;

import java.util.*;
import org.bukkit.command.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.*;
import org.bukkit.entity.*;

public class Rain implements Listener, CommandExecutor
{
    public static Main plugin;
    public static List<Player> hab;
    
    static {
        Rain.hab = new ArrayList<Player>();
    }
    
    public Rain(final Main main) {
        Rain.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("Rain")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.Rain")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + " Rain");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.rain.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    final ItemStack Rain = new ItemStack(Material.FLINT);
                    Habilidade.setAbility(p, "Rain");
                    final ItemMeta bow = Rain.getItemMeta();
                    bow.setDisplayName("§3Chuvinha :3! ");
                    Rain.setItemMeta(bow);
                    p.getInventory().addItem(new ItemStack[] { espada });
                    p.getInventory().addItem(new ItemStack[] { Rain });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Rain.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Rain.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void b(final EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Arrow) {
            e.setDamage(7.0);
        }
    }
    
    @EventHandler
    public void a(final PlayerInteractEntityEvent event) {
        if (!(event.getRightClicked() instanceof Player)) {
            return;
        }
        final Player p1 = event.getPlayer();
        final Player r = (Player)event.getRightClicked();
        if (p1.getItemInHand().getType() == Material.FLINT) {
            if (Main.rain.contains(p1.getName()) && Main.cooldownm.contains(p1)) {
                p1.sendMessage("§cCriando flechas, Acalme-se");
                return;
            }
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    final Location loc = r.getLocation();
                    loc.setY(loc.getY() + 3.0);
                    final Entity arrow2 = Bukkit.getServer().getWorld(r.getLocation().getWorld().getName()).spawnEntity(loc, EntityType.ARROW);
                }
            }, 40L);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    final Location loc2 = r.getLocation();
                    loc2.setY(loc2.getY() + 3.0);
                    final Entity arrow3 = Bukkit.getServer().getWorld(r.getLocation().getWorld().getName()).spawnEntity(loc2, EntityType.ARROW);
                }
            }, 35L);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    final Location loc3 = r.getLocation();
                    loc3.setY(loc3.getY() + 3.0);
                    final Entity arrow4 = Bukkit.getServer().getWorld(r.getLocation().getWorld().getName()).spawnEntity(loc3, EntityType.ARROW);
                }
            }, 30L);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    final Location loc3 = r.getLocation();
                    loc3.setY(loc3.getY() + 3.0);
                    final Entity arrow4 = Bukkit.getServer().getWorld(r.getLocation().getWorld().getName()).spawnEntity(loc3, EntityType.ARROW);
                }
            }, 25L);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    final Location loc3 = r.getLocation();
                    loc3.setY(loc3.getY() + 3.0);
                    final Entity arrow4 = Bukkit.getServer().getWorld(r.getLocation().getWorld().getName()).spawnEntity(loc3, EntityType.ARROW);
                }
            }, 20L);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    final Location loc4 = r.getLocation();
                    loc4.setY(loc4.getY() + 3.0);
                    final Entity arrow5 = Bukkit.getServer().getWorld(r.getLocation().getWorld().getName()).spawnEntity(loc4, EntityType.ARROW);
                }
            }, 5L);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    final Location loc6 = r.getLocation();
                    loc6.setY(loc6.getY() + 3.0);
                    final Entity arrow6 = Bukkit.getServer().getWorld(r.getLocation().getWorld().getName()).spawnEntity(loc6, EntityType.ARROW);
                }
            }, 3L);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    final Location loc5 = r.getLocation();
                    loc5.setY(loc5.getY() + 3.0);
                }
            }, 2L);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    Rain.hab.remove(p1.getName());
                }
            }, 50L);
            Main.cooldownm.add(p1);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    Main.cooldownm.remove(p1);
                    p1.sendMessage("§aSuas flechas estao prontas!");
                    p1.getWorld().playSound(p1.getLocation(), Sound.BURP, 5.0f, 5.0f);
                }
            }, 700L);
        }
    }
}
