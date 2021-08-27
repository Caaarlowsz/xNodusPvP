package Nodus.Kits3;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.player.*;
import org.bukkit.util.*;
import org.bukkit.event.*;

public class Launcher implements Listener, CommandExecutor
{
    public static Main plugin;
    
    public Launcher(final Main main) {
        Launcher.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("launcher")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.launcher")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + "Launcher");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.launcher.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    Habilidade.setAbility(p, "Launcher");
                    p.getInventory().addItem(new ItemStack[] { espada });
                    p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.FISHING_ROD) });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Launcher.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Launcher.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void onPlayerHitFishingrodThrower(final PlayerFishEvent event) {
        final Player player = event.getPlayer();
        if (Main.launcher.contains(player.getName()) && event.getCaught() instanceof Player) {
            final Player caught = (Player)event.getCaught();
            if (player.getItemInHand().getType() == Material.FISHING_ROD) {
                caught.setVelocity(new Vector(0, 2, 0));
            }
        }
    }
}
