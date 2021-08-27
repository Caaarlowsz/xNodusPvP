package Nodus.Kits;

import org.bukkit.event.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;

public class C4 implements Listener, CommandExecutor
{
    public static Main plugin;
    
    public C4(final Main main) {
        C4.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("c4")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.c4")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + " C4");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.c4.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    Habilidade.setAbility(p, "C4");
                    p.getInventory().addItem(new ItemStack[] { espada });
                    final ItemStack bow = new ItemStack(Material.STONE_BUTTON);
                    final ItemMeta bowmeta = bow.getItemMeta();
                    bowmeta.setDisplayName("§aC4");
                    bow.setItemMeta(bowmeta);
                    p.getInventory().addItem(new ItemStack[] { bow });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', C4.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', C4.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
}
