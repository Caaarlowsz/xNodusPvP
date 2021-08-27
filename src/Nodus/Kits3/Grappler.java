package Nodus.Kits3;

import org.bukkit.entity.*;
import Nodus.Kits.*;
import java.util.*;
import org.bukkit.command.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import org.bukkit.util.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.craftbukkit.v1_7_R1.entity.*;
import net.minecraft.server.v1_7_R1.*;

public class Grappler implements Listener, CommandExecutor
{
    public static Main plugin;
    Map<Player, Cordinha> hooks;
    
    public Grappler(final Main main) {
        this.hooks = new HashMap<Player, Cordinha>();
        Grappler.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("grappler")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.grappler")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + "Grappler");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.grappler.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    Habilidade.setAbility(p, "Grappler");
                    p.getInventory().addItem(new ItemStack[] { espada });
                    p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.LEASH) });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Grappler.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Grappler.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void onSlot(final PlayerItemHeldEvent e) {
        if (this.hooks.containsKey(e.getPlayer())) {
            this.hooks.get(e.getPlayer()).remove();
            this.hooks.remove(e.getPlayer());
        }
    }
    
    @EventHandler
    public void grapplerDamageNoLeash(final EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        final Player player = (Player)event.getEntity();
        if (event.getCause() != EntityDamageEvent.DamageCause.FALL) {
            return;
        }
        if (this.hooks.containsKey(player) && this.hooks.get(player).isHooked() && event.getDamage() > 3.0) {
            event.setDamage(3.0);
        }
    }
    
    @EventHandler
    public void onMove(final PlayerMoveEvent e) {
        if (this.hooks.containsKey(e.getPlayer()) && !e.getPlayer().getItemInHand().getType().equals((Object)Material.LEASH)) {
            this.hooks.get(e.getPlayer()).remove();
            this.hooks.remove(e.getPlayer());
        }
    }
    
    @EventHandler
    public void onLeash(final PlayerLeashEntityEvent e) {
        final Player p = e.getPlayer();
        if (e.getPlayer().getItemInHand().getType().equals((Object)Material.LEASH)) {
            e.setCancelled(true);
            e.getPlayer().updateInventory();
            e.setCancelled(true);
            if (!this.hooks.containsKey(p)) {
                return;
            }
            if (!this.hooks.get(p).isHooked()) {
                return;
            }
            final double t;
            final double d = t = this.hooks.get(p).getBukkitEntity().getLocation().distance(p.getLocation());
            final double v_x = (1.0 + 0.07 * t) * (this.hooks.get(p).getBukkitEntity().getLocation().getX() - p.getLocation().getX()) / t;
            final double v_y = (1.0 + 0.03 * t) * (this.hooks.get(p).getBukkitEntity().getLocation().getY() - p.getLocation().getY()) / t;
            final double v_z = (1.0 + 0.07 * t) * (this.hooks.get(p).getBukkitEntity().getLocation().getZ() - p.getLocation().getZ()) / t;
            final Vector v = p.getVelocity();
            v.setX(v_x);
            v.setY(v_y);
            v.setZ(v_z);
            p.setVelocity(v);
        }
    }
    
    @EventHandler
    public void onClick(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (e.getPlayer().getItemInHand().getType().equals((Object)Material.LEASH)) {
            e.setCancelled(true);
            if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
                if (this.hooks.containsKey(p)) {
                    this.hooks.get(p).remove();
                }
                final Cordinha nmsHook = new Cordinha(p.getWorld(), (EntityHuman)((CraftPlayer)p).getHandle());
                nmsHook.spawn(p.getEyeLocation().add(p.getLocation().getDirection().getX(), p.getLocation().getDirection().getY(), p.getLocation().getDirection().getZ()));
                nmsHook.move(p.getLocation().getDirection().getX() * 5.0, p.getLocation().getDirection().getY() * 5.0, p.getLocation().getDirection().getZ() * 5.0);
                this.hooks.put(p, nmsHook);
            }
            else {
                if (!this.hooks.containsKey(p)) {
                    return;
                }
                if (!this.hooks.get(p).isHooked()) {
                    return;
                }
                final double t;
                final double d = t = this.hooks.get(p).getBukkitEntity().getLocation().distance(p.getLocation());
                final double v_x = (1.0 + 0.2 * t) * (this.hooks.get(p).getBukkitEntity().getLocation().getX() - p.getLocation().getX()) / t;
                final double v_y = (1.0 + 0.03 * t) * (this.hooks.get(p).getBukkitEntity().getLocation().getY() - p.getLocation().getY()) / t;
                final double v_z = (1.0 + 0.2 * t) * (this.hooks.get(p).getBukkitEntity().getLocation().getZ() - p.getLocation().getZ()) / t;
                final Vector v = p.getVelocity();
                v.setX(v_x);
                v.setY(v_y);
                v.setZ(v_z);
                p.setVelocity(v);
            }
        }
    }
}
