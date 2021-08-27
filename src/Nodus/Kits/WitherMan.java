package Nodus.Kits;

import java.util.*;
import org.bukkit.command.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.entity.*;
import java.util.concurrent.*;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;

public class WitherMan implements Listener, CommandExecutor
{
    public static Main plugin;
    public static HashMap<String, Long> cooldown;
    public static HashMap<String, Egg> teias;
    
    static {
        WitherMan.cooldown = new HashMap<String, Long>();
        WitherMan.teias = new HashMap<String, Egg>();
    }
    
    public WitherMan(final Main main) {
        WitherMan.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("witherman")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.witherman")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + " WitherMan");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.witherman.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    Habilidade.setAbility(p, "Wither");
                    espadameta.setDisplayName("§cSword");
                    final ItemStack iron = new ItemStack(Material.SKULL_ITEM);
                    final ItemMeta ironm = iron.getItemMeta();
                    ironm.setDisplayName("§aVai monstro");
                    iron.setItemMeta(ironm);
                    p.getInventory().addItem(new ItemStack[] { espada });
                    p.getInventory().addItem(new ItemStack[] { iron });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', WitherMan.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', WitherMan.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void lancar(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && Main.witherman.contains(p.getName()) && p.getItemInHand().getType() == Material.SKULL_ITEM) {
            e.setCancelled(true);
            p.updateInventory();
            if (!WitherMan.cooldown.containsKey(p.getName()) || WitherMan.cooldown.get(p.getName()) <= System.currentTimeMillis()) {
                if (Main.SemPvP(p)) {
                    final WitherSkull teia = (WitherSkull)p.launchProjectile((Class)WitherSkull.class);
                    final WitherSkull teia2 = (WitherSkull)p.launchProjectile((Class)WitherSkull.class);
                    final WitherSkull teia3 = (WitherSkull)p.launchProjectile((Class)WitherSkull.class);
                    WitherMan.cooldown.put(p.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(20L));
                    p.playSound(p.getLocation(), Sound.WITHER_SHOOT, 1.0f, 1.0f);
                    p.playEffect(p.getEyeLocation(), Effect.SMOKE, 100);
                    p.playEffect(p.getLocation(), Effect.EXTINGUISH, 100);
                    return;
                }
                p.sendMessage(ChatColor.RED + "Voce pode usar esta habzilidade apenas em areas com PVP.");
            }
            else {
                p.sendMessage(ChatColor.RED + "Faltam " + TimeUnit.MILLISECONDS.toSeconds(WitherMan.cooldown.get(p.getName()) - System.currentTimeMillis()) + " segundos para poder usar novamente.");
            }
        }
    }
    
    @EventHandler
    public void damage(final EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            final Player p = (Player)e.getEntity();
            if (Main.witherman.contains(p.getName()) && e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
                e.setCancelled(true);
            }
        }
    }
}
