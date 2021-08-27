package Nodus.Kits3;

import org.bukkit.event.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import org.bukkit.enchantments.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;

public class PvP implements Listener, CommandExecutor
{
    public static Main plugin;
    
    public PvP(final Main main) {
        PvP.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("pvp")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.pvp")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + "PvP");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.hg.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                    espada.setItemMeta(espadameta);
                    espadameta.setDisplayName("§cSword");
                    Habilidade.setAbility(p, "PvP");
                    p.getInventory().addItem(new ItemStack[] { espada });
                    Main.giveSoup(p, 35);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', PvP.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', PvP.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
}
