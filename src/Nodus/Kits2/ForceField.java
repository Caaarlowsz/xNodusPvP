package Nodus.Kits2;

import org.bukkit.command.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.event.player.*;
import org.bukkit.*;
import org.bukkit.event.*;

public class ForceField implements Listener, CommandExecutor
{
    public static Main plugin;
    public static ArrayList<String> Forcefild;
    public static ArrayList<Player> Cooldown;
    public static ArrayList<Player> NoFF;
    
    static {
        ForceField.Forcefild = new ArrayList<String>();
        ForceField.Cooldown = new ArrayList<Player>();
        ForceField.NoFF = new ArrayList<Player>();
    }
    
    public ForceField(final Main main) {
        ForceField.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("forcefield")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.forcefield")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + " ForceField");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.forcefield.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    Habilidade.setAbility(p, "Forcefield");
                    final ItemStack bow = new ItemStack(Material.NETHER_FENCE);
                    final ItemMeta bowmeta = bow.getItemMeta();
                    bowmeta.setDisplayName("§5Huzuni");
                    bow.setItemMeta(bowmeta);
                    p.getInventory().addItem(new ItemStack[] { espada });
                    p.getInventory().addItem(new ItemStack[] { bow });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', ForceField.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', ForceField.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    public static void gFF(final Player p) {
        final Location Local = p.getLocation();
        final List<Entity> Inimigos = (List<Entity>)Local.getWorld().getEntities();
        for (final Entity e : Inimigos) {
            if (e.getLocation().distance(Local) < 5.0 && e instanceof Player) {
                final Player d = (Player)e;
                if (d == p) {
                    continue;
                }
                d.damage(3.0);
            }
        }
    }
    
    @EventHandler
    public void InteractFF(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final ItemStack i = p.getItemInHand();
        if (i.getType() == Material.NETHER_FENCE && i.hasItemMeta() && i.getItemMeta().getDisplayName().equalsIgnoreCase("§5Huzuni") && p.hasPermission("kit.forcefield")) {
            e.setCancelled(true);
            ForceField.Forcefild.add(p.getName());
            if (ForceField.Cooldown.contains(p)) {
                p.sendMessage(ChatColor.DARK_RED + "O kit esta em cooldown!");
                return;
            }
            ForceField.Cooldown.add(p);
            ForceField.NoFF.add(p);
            p.sendMessage(ChatColor.GOLD + "Voce ativou o Forcefield!");
            gFF(p);
            Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(Main.getPlugin(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    if (ForceField.NoFF.contains(p)) {
                        ForceField.gFF(p);
                    }
                }
            }, 1L, 1L);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    ForceField.NoFF.remove(p);
                }
            }, 100L);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    ForceField.Cooldown.remove(p);
                }
            }, 800L);
        }
    }
}
