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
import org.bukkit.event.entity.*;
import org.bukkit.potion.*;

public class Berserker implements Listener, CommandExecutor
{
    public static Main plugin;
    
    public Berserker(final Main main) {
        Berserker.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("berserker")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.berserker")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + "Berserker");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.berserker.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    Habilidade.setAbility(p, "Berserker");
                    p.getInventory().addItem(new ItemStack[] { espada });
                    Main.giveSoup(p, 35);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Berserker.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Berserker.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    public void forca(final PlayerDeathEvent e) {
        if (e.getEntity().getKiller() instanceof Player) {
            final Player p = e.getEntity().getKiller();
            if (Main.berserker.contains(p.getName())) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 400, 0));
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 0));
                p.sendMessage(ChatColor.AQUA + "Um Monstro encarnou em voce!");
                p.playSound(p.getLocation(), Sound.AMBIENCE_THUNDER, 1.0f, 1.0f);
            }
        }
    }
}
