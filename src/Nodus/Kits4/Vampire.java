package Nodus.Kits4;

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

public class Vampire implements Listener, CommandExecutor
{
    public static Main plugin;
    
    public Vampire(final Main main) {
        Vampire.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("Vampire")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.Vampire")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + "Vampire");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.Vampire.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    Habilidade.setAbility(p, "Vampire");
                    p.getInventory().addItem(new ItemStack[] { espada });
                    Main.giveSoup(p, 35);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Vampire.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Vampire.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void onVampire(final EntityDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            final Player killed = (Player)event.getEntity();
            if (killed.getKiller() instanceof Player) {
                final Player player = event.getEntity().getKiller();
                if (Main.Vampire.contains(player.getName())) {
                    player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.POTION, 1, (short)16396) });
                    player.sendMessage("§a§oVoce ganhou experiencia por matar§c§o " + killed.getName());
                }
            }
        }
    }
}
