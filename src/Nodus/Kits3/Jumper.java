package Nodus.Kits3;

import java.util.*;
import org.bukkit.command.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.entity.*;
import java.util.concurrent.*;
import org.bukkit.event.*;

public class Jumper implements Listener, CommandExecutor
{
    public static HashMap<String, Long> cooldown;
    public static Main plugin;
    
    static {
        Jumper.cooldown = new HashMap<String, Long>();
    }
    
    public Jumper(final Main main) {
        Jumper.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("jumper")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.jumper")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + " Jumper");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.jp.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    Habilidade.setAbility(p, "Jumper");
                    espadameta.setDisplayName("§cSword");
                    final ItemStack bow = new ItemStack(Material.EYE_OF_ENDER);
                    final ItemMeta bowmeta = bow.getItemMeta();
                    bowmeta.setDisplayName("§aOlho que tudo teleporta");
                    bow.setItemMeta(bowmeta);
                    p.getInventory().addItem(new ItemStack[] { espada });
                    p.getInventory().addItem(new ItemStack[] { bow });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Jumper.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Jumper.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void interact(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_AIR) && Main.jp.contains(p.getName()) && p.getItemInHand().getType() == Material.EYE_OF_ENDER) {
            if (!Jumper.cooldown.containsKey(p.getName()) || Jumper.cooldown.get(p.getName()) <= System.currentTimeMillis()) {
                e.setCancelled(true);
                p.launchProjectile((Class)EnderPearl.class);
                Jumper.cooldown.put(p.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(10L));
                return;
            }
            p.sendMessage(ChatColor.RED + "Faltam " + TimeUnit.MILLISECONDS.toSeconds(Jumper.cooldown.get(p.getName()) - System.currentTimeMillis()) + " segundos para poder usar novamente.");
        }
    }
}
