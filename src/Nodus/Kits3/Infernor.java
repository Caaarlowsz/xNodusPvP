package Nodus.Kits3;

import org.bukkit.event.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;

public class Infernor implements Listener, CommandExecutor
{
    public static Main plugin;
    
    public Infernor(final Main main) {
        Infernor.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("infernor")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.infernor")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + "Infernor");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.infernor.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    Habilidade.setAbility(p, "Infernor");
                    espadameta.setDisplayName("§cSword");
                    final ItemStack bow = new ItemStack(Material.NETHER_FENCE);
                    final ItemMeta bowmeta = bow.getItemMeta();
                    bowmeta.setDisplayName("§4Infernor");
                    bow.setItemMeta(bowmeta);
                    p.getInventory().addItem(new ItemStack[] { espada });
                    p.getInventory().addItem(new ItemStack[] { bow });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Infernor.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Infernor.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
}
