package Nodus.Kits3;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.*;

public class Resouper implements Listener, CommandExecutor
{
    public static Main plugin;
    
    public Resouper(final Main main) {
        Resouper.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("resouper")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.resouper")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + "Resouper");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.resouper.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    Habilidade.setAbility(p, "Resouper");
                    p.getInventory().addItem(new ItemStack[] { espada });
                    Main.giveSoup(p, 35);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Resouper.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Resouper.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void onKill(final PlayerDeathEvent e) {
        if (e.getEntity().getKiller() instanceof Player) {
            final Player k = e.getEntity().getKiller();
            if (Main.resouper.contains(k.getName())) {
                k.getInventory().remove(Material.BOWL);
                try {
                    for (int i = 0; i < 34; ++i) {
                        k.getInventory().setItem(k.getInventory().firstEmpty(), new ItemStack(Material.MUSHROOM_SOUP));
                    }
                }
                catch (ArrayIndexOutOfBoundsException ex) {}
                k.sendMessage(ChatColor.GREEN + "Resoup Automatico Concluido!");
                k.playSound(k.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
            }
        }
    }
}
