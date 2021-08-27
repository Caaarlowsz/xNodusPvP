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

public class Jellyfish implements Listener, CommandExecutor
{
    public static Main plugin;
    
    public Jellyfish(final Main main) {
        Jellyfish.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("jellyfish")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.jellyfish")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + "Jellyfish");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.jellyfish.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    Habilidade.setAbility(p, "Jellyfish");
                    p.getInventory().addItem(new ItemStack[] { espada });
                    final ItemStack bow = new ItemStack(Material.SLIME_BALL);
                    final ItemMeta bowmeta = bow.getItemMeta();
                    bowmeta.setDisplayName("§bJellyfish Kit");
                    bow.setItemMeta(bowmeta);
                    p.getInventory().addItem(new ItemStack[] { bow });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Jellyfish.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Jellyfish.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
}
