package Nodus.Kits3;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import org.bukkit.enchantments.*;
import Nodus.Warp.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.player.*;
import org.bukkit.event.*;

public class Pao implements Listener, CommandExecutor
{
    public static Main plugin;
    
    public Pao(final Main main) {
        Pao.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("pao")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.pao")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + "Cacetinho huehue");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.spao.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    Habilidade.setAbility(p, "Pao");
                    final ItemStack bow = new ItemStack(Material.BREAD);
                    final ItemMeta bowmeta = bow.getItemMeta();
                    bowmeta.setDisplayName("§6Meu Cacetinho <3");
                    bowmeta.addEnchant(Enchantment.KNOCKBACK, 1, true);
                    bowmeta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
                    bow.setItemMeta(bowmeta);
                    p.getInventory().addItem(new ItemStack[] { bow });
                    Main.giveSoup(p, 35);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Pao.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Pao.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void onConsume(final PlayerItemConsumeEvent event) {
        final ItemStack item = event.getItem();
        final Player p = event.getPlayer();
        if (Main.spao.contains(p.getName())) {
            event.setCancelled(true);
        }
    }
}
