package Nodus.Kits2;

import org.bukkit.command.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.player.*;
import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.*;
import org.bukkit.util.*;
import org.bukkit.event.*;

public class Vaccum implements Listener, CommandExecutor
{
    public static Main plugin;
    public static HashMap<String, Long> cooldown;
    public long cooldownLenght;
    
    static {
        Vaccum.cooldown = new HashMap<String, Long>();
    }
    
    public Vaccum(final Main main) {
        this.cooldownLenght = 0L;
        Vaccum.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("vacuum")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.vacuum")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + " Vacuum");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.vacuum.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    Habilidade.setAbility(p, "Vacuum");
                    espadameta.setDisplayName("§cSword");
                    final ItemStack iron = new ItemStack(Material.COAL_BLOCK);
                    final ItemMeta ironm = iron.getItemMeta();
                    ironm.setDisplayName("§5Vacuum!");
                    iron.setItemMeta(ironm);
                    p.getInventory().addItem(new ItemStack[] { espada });
                    p.getInventory().addItem(new ItemStack[] { iron });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Vaccum.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Vaccum.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void onVacuum(final PlayerInteractEvent event) {
        final Player p = event.getPlayer();
        if (Main.vacuum.contains(p.getName()) && p.getItemInHand().getType().equals((Object)Material.COAL_BLOCK)) {
            for (final Entity targetplayer : p.getNearbyEntities(15.0, 15.0, 15.0)) {
                final Entity nearby = targetplayer;
                if (!(targetplayer instanceof Player)) {
                    return;
                }
                if (this.hasCooldown3()) {
                    p.sendMessage("§cFaltam " + this.cooldownTimeRemaining3() + " para o cooldown acabar!");
                    return;
                }
                final Location lc = targetplayer.getLocation();
                final Location to = p.getLocation();
                lc.setY(lc.getY() + 0.5);
                final double g = -0.08;
                final double t;
                final double d = t = to.distance(lc);
                final double v_x = (1.0 + 0.17 * t) * (to.getX() - lc.getX()) / t;
                final double v_y = (1.0 + 0.03 * t) * (to.getY() - lc.getY()) / t - 0.5 * g * t;
                final double v_z = (1.0 + 0.17 * t) * (to.getZ() - lc.getZ()) / t;
                final Vector v = p.getVelocity();
                v.setX(v_x);
                v.setY(v_y);
                v.setZ(v_z);
                targetplayer.setVelocity(v);
                p.sendMessage("§3§lVoce esta puxando!");
                this.addCooldown3(p, 10);
            }
        }
    }
    
    public void addCooldown3(final Player player, final int seconds) {
        this.cooldownLenght = System.currentTimeMillis() + seconds * 1000;
    }
    
    public String cooldownTimeRemaining3() {
        final long faltam = (this.cooldownLenght - System.currentTimeMillis()) / 1000L;
        if (faltam < 60L) {
            return String.valueOf(faltam) + " segundos restantes";
        }
        return String.valueOf(faltam) + " minutos restantes";
    }
    
    public boolean hasCooldown3() {
        return this.cooldownLenght > System.currentTimeMillis();
    }
}
