package Nodus.Kits3;

import org.bukkit.command.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.player.*;
import org.bukkit.entity.*;
import org.bukkit.block.*;
import org.bukkit.event.*;

public class Fisherman implements Listener, CommandExecutor
{
    public static Main plugin;
    
    public Fisherman(final Main main) {
        Fisherman.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("fisherman")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.fisherman")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + "Fisherman");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.fisherman.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    Habilidade.setAbility(p, "Fisherman");
                    p.getInventory().addItem(new ItemStack[] { espada });
                    final ItemStack bow = new ItemStack(Material.FISHING_ROD);
                    final ItemMeta bowmeta = bow.getItemMeta();
                    bowmeta.setDisplayName("§5Fishaum");
                    bow.setItemMeta(bowmeta);
                    p.getInventory().addItem(new ItemStack[] { bow });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Fisherman.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Fisherman.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void onPlayerFish(final PlayerFishEvent event) {
        final Entity caught = event.getCaught();
        final Block block = event.getHook().getLocation().getBlock();
        if (caught != null && caught != block && Main.fisherman.contains(event.getPlayer().getName())) {
            caught.teleport(event.getPlayer().getLocation());
        }
    }
}
