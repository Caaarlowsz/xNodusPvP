package Nodus.Kits2;

import java.util.*;
import org.bukkit.command.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.entity.*;
import java.util.concurrent.*;
import org.bukkit.util.*;
import org.bukkit.*;
import org.bukkit.event.*;

public class Pyro implements Listener, CommandExecutor
{
    public static Main plugin;
    public static HashMap<String, Long> cooldown;
    
    static {
        Pyro.cooldown = new HashMap<String, Long>();
    }
    
    public Pyro(final Main main) {
        Pyro.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("Pyro")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.Pyro")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + " Pyro");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.pyro.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    final ItemStack bow = new ItemStack(Material.FIREBALL);
                    final ItemMeta bowmeta = bow.getItemMeta();
                    bowmeta.setDisplayName("§6§oPyro!");
                    Habilidade.setAbility(p, "Pyro");
                    bow.setItemMeta(bowmeta);
                    p.getInventory().addItem(new ItemStack[] { espada });
                    p.getInventory().addItem(new ItemStack[] { bow });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Pyro.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Pyro.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void interact(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && p.getItemInHand().getType() == Material.FIREBALL && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§6§oPyro!")) {
            e.setCancelled(true);
            if (!Pyro.cooldown.containsKey(p.getName()) || Pyro.cooldown.get(p.getName()) <= System.currentTimeMillis()) {
                e.setCancelled(true);
                p.updateInventory();
                final Vector velo1 = p.getLocation().getDirection().normalize().multiply(2);
                final Fireball boladenve = (Fireball)p.launchProjectile((Class)Fireball.class);
                boladenve.setVelocity(velo1);
                boladenve.setFireTicks(0);
                final Location location = p.getEyeLocation();
                final BlockIterator blocksToAdd = new BlockIterator(location, 0.0, 30);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        boladenve.remove();
                    }
                }, 10L);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        p.getWorld().createExplosion(boladenve.getLocation().add(0.0, 1.0, 0.0), 2.5f);
                    }
                }, 15L);
                while (blocksToAdd.hasNext()) {
                    final Location blockToAdd = blocksToAdd.next().getLocation();
                    final Effect a = Effect.MOBSPAWNER_FLAMES;
                    p.getWorld().playEffect(blockToAdd, a, 50);
                }
                Pyro.cooldown.put(p.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(25L));
                return;
            }
            p.sendMessage("§cAguarde mais:" + TimeUnit.MILLISECONDS.toSeconds(Pyro.cooldown.get(p.getName()) - System.currentTimeMillis()));
        }
    }
}
