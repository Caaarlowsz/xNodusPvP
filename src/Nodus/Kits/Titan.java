package Nodus.Kits;

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

public class Titan implements Listener, CommandExecutor
{
    public static Main plugin;
    public static HashMap<String, Long> cooldown;
    
    static {
        Titan.cooldown = new HashMap<String, Long>();
    }
    
    public Titan(final Main main) {
        Titan.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("titan")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.titan")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + "Titan");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.titan.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    Habilidade.setAbility(p, "Titan");
                    p.getInventory().addItem(new ItemStack[] { espada });
                    p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.BEDROCK) });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Titan.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Titan.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void interact(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && Main.titan.contains(p.getName()) && p.getItemInHand().getType() == Material.BEDROCK) {
            if (!Titan.cooldown.containsKey(p.getName()) || Titan.cooldown.get(p.getName()) <= System.currentTimeMillis()) {
                e.setCancelled(true);
                p.updateInventory();
                p.sendMessage(ChatColor.RED + "§lArmadura em §a100%");
                p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 500, 3));
                p.playSound(p.getLocation(), Sound.IRONGOLEM_DEATH, 1.0f, 1.0f);
                Titan.cooldown.put(p.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(40L));
                return;
            }
            p.sendMessage(ChatColor.RED + "Armadura em §a0%");
            p.sendMessage(ChatColor.RED + "Faltam " + TimeUnit.MILLISECONDS.toSeconds(Titan.cooldown.get(p.getName()) - System.currentTimeMillis()) + " segundos para poder usar novamente.");
        }
    }
}
