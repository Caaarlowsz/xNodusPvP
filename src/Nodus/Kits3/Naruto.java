package Nodus.Kits3;

import java.util.*;
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
import org.bukkit.potion.*;
import java.util.concurrent.*;
import org.bukkit.event.*;

public class Naruto implements Listener, CommandExecutor
{
    public static Main plugin;
    public static HashMap<String, Long> cooldown;
    
    static {
        Naruto.cooldown = new HashMap<String, Long>();
    }
    
    public Naruto(final Main main) {
        Naruto.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("naruto")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.naruto")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + "Naruto");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.naruto.add(sender.getName());
                    p.getInventory().clear();
                    p.getInventory().setHelmet(new ItemStack(Material.REDSTONE_BLOCK));
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    Habilidade.setAbility(p, "Naruto");
                    p.getInventory().addItem(new ItemStack[] { espada });
                    p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.NETHER_STAR) });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Naruto.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Naruto.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void interact(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && Main.naruto.contains(p.getName()) && p.getItemInHand().getType() == Material.NETHER_STAR) {
            if (!Naruto.cooldown.containsKey(p.getName()) || Naruto.cooldown.get(p.getName()) <= System.currentTimeMillis()) {
                e.setCancelled(true);
                p.updateInventory();
                p.sendMessage(ChatColor.RED + "§lSinto meu corpo Mudando....!!!");
                p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 500, 0));
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 500, 1));
                p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 500, 0));
                p.playSound(p.getLocation(), Sound.BLAZE_DEATH, 1.0f, 1.0f);
                Naruto.cooldown.put(p.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(40L));
                return;
            }
            p.sendMessage(ChatColor.RED + "Faltam " + TimeUnit.MILLISECONDS.toSeconds(Naruto.cooldown.get(p.getName()) - System.currentTimeMillis()) + " segundos para poder usar novamente.");
        }
    }
}
