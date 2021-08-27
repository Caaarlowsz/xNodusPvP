package Nodus.Kits2;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import me.confuser.barapi.*;
import org.bukkit.potion.*;
import java.util.concurrent.*;
import java.util.*;
import org.bukkit.event.*;

public class Doctor implements Listener, CommandExecutor
{
    public static Main plugin;
    public static HashMap<String, Long> cooldown;
    
    static {
        Doctor.cooldown = new HashMap<String, Long>();
    }
    
    public Doctor(final Main main) {
        Doctor.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("Doctor")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.doctor")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + " Doctor");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.kr.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    final ItemStack bow = new ItemStack(Material.SLIME_BALL);
                    final ItemMeta bowmeta = bow.getItemMeta();
                    Habilidade.setAbility(p, "Doctor");
                    bowmeta.setDisplayName("§bAmbulancia portatil ;-; ");
                    bow.setItemMeta(bowmeta);
                    p.getInventory().addItem(new ItemStack[] { espada });
                    p.getInventory().addItem(new ItemStack[] { bow });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Doctor.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Doctor.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void flash(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && Main.kr.contains(p.getName()) && p.getItemInHand().getType() == Material.SLIME_BALL) {
            if (!Doctor.cooldown.containsKey(p.getName()) || Doctor.cooldown.get(p.getName()) <= System.currentTimeMillis()) {
                e.setCancelled(true);
                p.updateInventory();
                BarAPI.setMessage(p, "§7Voce foi curado!", 6);
                for (final PotionEffect effect : p.getActivePotionEffects()) {
                    p.removePotionEffect(effect.getType());
                }
                Doctor.cooldown.put(p.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(5L));
                return;
            }
            p.sendMessage(ChatColor.GRAY + "Fazendo mais remedio! Tempo restante: " + TimeUnit.MILLISECONDS.toSeconds(Doctor.cooldown.get(p.getName()) - System.currentTimeMillis()) + " segundos.");
        }
    }
}
