package Nodus.Kits2;

import java.util.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import Nodus.Event.*;
import Nodus.Main.*;
import org.bukkit.inventory.*;
import Nodus.Warp.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import java.util.concurrent.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.plugin.*;

public class SpiderMan implements Listener, CommandExecutor
{
    public static Main plugin;
    public static HashMap<String, Long> cooldown;
    public static HashMap<String, Egg> teias;
    
    static {
        SpiderMan.cooldown = new HashMap<String, Long>();
        SpiderMan.teias = new HashMap<String, Egg>();
    }
    
    public SpiderMan(final Main main) {
        SpiderMan.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("spiderman")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.spiderman")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + " SpiderMan");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.spiderman.add(sender.getName());
                    p.getInventory().clear();
                    Habilidade.setAbility(p, "SpiderMan");
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    final ItemStack bow = new ItemStack(Material.STRING);
                    final ItemMeta bowmeta = bow.getItemMeta();
                    bowmeta.setDisplayName("§9§lTeiaaa");
                    bow.setItemMeta(bowmeta);
                    p.getInventory().addItem(new ItemStack[] { espada });
                    p.getInventory().addItem(new ItemStack[] { bow });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', SpiderMan.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', SpiderMan.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void lancar(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && Main.spiderman.contains(p.getName()) && p.getItemInHand().getType() == Material.STRING) {
            e.setCancelled(true);
            p.updateInventory();
            if (!SpiderMan.cooldown.containsKey(p.getName()) || SpiderMan.cooldown.get(p.getName()) <= System.currentTimeMillis()) {
                final Egg teia = (Egg)p.launchProjectile((Class)Egg.class);
                SpiderMan.teias.put(p.getName(), teia);
                SpiderMan.cooldown.put(p.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(10L));
                p.sendMessage(ChatColor.DARK_PURPLE + "Teia lan\u00e7ada!");
                p.playSound(p.getLocation(), Sound.IRONGOLEM_THROW, 1.0f, 1.0f);
                p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 1.0f);
                p.playEffect(p.getLocation(), Effect.STEP_SOUND, 100);
                p.playEffect(p.getLocation(), Effect.SMOKE, 100);
                p.playEffect(p.getLocation(), Effect.EXTINGUISH, 100);
                p.playSound(p.getLocation(), Sound.SPIDER_IDLE, 1.0f, 1.0f);
                return;
            }
            p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
        }
    }
    
    @EventHandler
    public void teia(final ProjectileHitEvent e) {
        if (e.getEntity() instanceof Egg && e.getEntity().getShooter() instanceof Player) {
            final Egg teia = (Egg)e.getEntity();
            final Player p = (Player)e.getEntity().getShooter();
            if (SpiderMan.teias.containsKey(p.getName()) && teia == SpiderMan.teias.get(p.getName())) {
                SpiderMan.teias.remove(p.getName());
                final Block b = teia.getLocation().getBlock();
                b.setType(Material.WEB);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)SpiderMan.plugin, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        if (b.getType() == Material.WEB) {
                            b.setType(Material.AIR);
                        }
                    }
                }, 100L);
            }
        }
    }
}
