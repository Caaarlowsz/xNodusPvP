package Nodus.Kits4;

import java.util.*;
import org.bukkit.command.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.util.*;
import org.bukkit.event.entity.*;

public class Metheor implements Listener, CommandExecutor
{
    public static Main plugin;
    private ArrayList<Player> cooldown;
    
    public Metheor(final Main main) {
        this.cooldown = new ArrayList<Player>();
        Metheor.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("Metheor")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.Metheor")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + "Metheor");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.Metheor.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    final ItemStack espada2 = new ItemStack(Material.BEDROCK);
                    final ItemMeta espadameta2 = espada2.getItemMeta();
                    espadameta2.setDisplayName("§cMeteoro");
                    espada2.setItemMeta(espadameta2);
                    espadameta.setDisplayName("§cSword");
                    Habilidade.setAbility(p, "Metheor");
                    p.getInventory().addItem(new ItemStack[] { espada });
                    p.getInventory().addItem(new ItemStack[] { espada2 });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Metheor.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Metheor.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void meteoro(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (p.getItemInHand().getType() != null && p.getItemInHand().getItemMeta() != null && p.getItemInHand().getItemMeta().getDisplayName() != null && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§cMeteoro") && e.getAction() == Action.RIGHT_CLICK_BLOCK && p.hasPermission("kit.metheor") && e.getClickedBlock().getLocation().add(0.0, 16.0, 0.0).getBlock().getType() == Material.AIR && e.getClickedBlock().getLocation().add(0.0, 1.0, 0.0).getBlock().getType() == Material.AIR && e.getClickedBlock().getLocation().add(0.0, 2.0, 0.0).getBlock().getType() == Material.AIR && e.getClickedBlock().getLocation().add(0.0, 3.0, 0.0).getBlock().getType() == Material.AIR && e.getClickedBlock().getLocation().add(0.0, 4.0, 0.0).getBlock().getType() == Material.AIR && e.getClickedBlock().getLocation().add(0.0, 5.0, 0.0).getBlock().getType() == Material.AIR && e.getClickedBlock().getLocation().add(0.0, 6.0, 0.0).getBlock().getType() == Material.AIR && e.getClickedBlock().getLocation().add(0.0, 7.0, 0.0).getBlock().getType() == Material.AIR && e.getClickedBlock().getLocation().add(0.0, 8.0, 0.0).getBlock().getType() == Material.AIR && e.getClickedBlock().getLocation().add(0.0, 9.0, 0.0).getBlock().getType() == Material.AIR && e.getClickedBlock().getLocation().add(0.0, 10.0, 0.0).getBlock().getType() == Material.AIR && e.getClickedBlock().getLocation().add(0.0, 11.0, 0.0).getBlock().getType() == Material.AIR && e.getClickedBlock().getLocation().add(0.0, 12.0, 0.0).getBlock().getType() == Material.AIR && e.getClickedBlock().getLocation().add(0.0, 13.0, 0.0).getBlock().getType() == Material.AIR && e.getClickedBlock().getLocation().add(0.0, 14.0, 0.0).getBlock().getType() == Material.AIR && e.getClickedBlock().getLocation().add(0.0, 15.0, 0.0).getBlock().getType() == Material.AIR && !this.cooldown.contains(p)) {
            e.setCancelled(true);
            this.cooldown.add(p);
            p.sendMessage("Coordenadas mandadas para os aliens");
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(0.0, 1.0, 0.0), Effect.STEP_SOUND, 152);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(1.0, 1.0, 0.0), Effect.STEP_SOUND, 155);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(-1.0, 1.0, 0.0), Effect.STEP_SOUND, 155);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(0.0, 1.0, 1.0), Effect.STEP_SOUND, 155);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(0.0, 0.0, -1.0), Effect.STEP_SOUND, 155);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(2.0, 0.0, -1.0), Effect.STEP_SOUND, 152);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(2.0, 0.0, 1.0), Effect.STEP_SOUND, 152);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(1.0, 0.0, 2.0), Effect.STEP_SOUND, 152);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(-1.0, 0.0, 2.0), Effect.STEP_SOUND, 152);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(-2.0, 0.0, -1.0), Effect.STEP_SOUND, 152);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(-2.0, 0.0, 1.0), Effect.STEP_SOUND, 152);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(-2.0, 0.0, 0.0), Effect.STEP_SOUND, 152);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(0.0, 0.0, 2.0), Effect.STEP_SOUND, 152);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(2.0, 0.0, 0.0), Effect.STEP_SOUND, 152);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(0.0, 0.0, -2.0), Effect.STEP_SOUND, 152);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(2.0, 0.0, 2.0), Effect.STEP_SOUND, 155);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(-2.0, 0.0, -2.0), Effect.STEP_SOUND, 155);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(2.0, 0.0, -2.0), Effect.STEP_SOUND, 155);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(-2.0, 0.0, 2.0), Effect.STEP_SOUND, 155);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(-3.0, 0.0, 0.0), Effect.STEP_SOUND, 155);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(3.0, 0.0, 0.0), Effect.STEP_SOUND, 155);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(0.0, 0.0, 3.0), Effect.STEP_SOUND, 155);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(0.0, 0.0, -3.0), Effect.STEP_SOUND, 155);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(3.0, 0.0, 2.0), Effect.STEP_SOUND, 155);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(-3.0, 0.0, 2.0), Effect.STEP_SOUND, 155);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(-3.0, 0.0, -2.0), Effect.STEP_SOUND, 155);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(3.0, 0.0, -2.0), Effect.STEP_SOUND, 155);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(-2.0, 0.0, 3.0), Effect.STEP_SOUND, 155);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(-2.0, 0.0, -3.0), Effect.STEP_SOUND, 155);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(2.0, 0.0, -3.0), Effect.STEP_SOUND, 155);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(2.0, 0.0, 3.0), Effect.STEP_SOUND, 155);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(3.0, 0.0, 1.0), Effect.STEP_SOUND, 155);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(-3.0, 0.0, 1.0), Effect.STEP_SOUND, 155);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(3.0, 0.0, -1.0), Effect.STEP_SOUND, 155);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(3.0, 0.0, -1.0), Effect.STEP_SOUND, 155);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(1.0, 0.0, 3.0), Effect.STEP_SOUND, 155);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(-1.0, 0.0, -3.0), Effect.STEP_SOUND, 155);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(-1.0, 0.0, 3.0), Effect.STEP_SOUND, 155);
            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation().add(1.0, 0.0, -3.0), Effect.STEP_SOUND, 155);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
                @Override
                public void run() {
                    final FallingBlock fb = Bukkit.getServer().getWorld("world").spawnFallingBlock(e.getClickedBlock().getLocation().add(0.0, 15.0, 0.0), Material.TNT, (byte)0);
                    fb.setDropItem(false);
                    p.sendMessage("Aliens mandaram o meteoro!");
                }
            }, 100L);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
                @Override
                public void run() {
                    e.getClickedBlock().getLocation().getWorld().createExplosion(e.getClickedBlock().getLocation().add(0.0, 1.0, 0.0), 4.5f);
                }
            }, 130L);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
                @Override
                public void run() {
                    Metheor.this.cooldown.remove(p);
                    p.sendMessage("§aVoce nao esta mais em cooldown!");
                }
            }, 800L);
        }
    }
    
    @EventHandler
    public void bldk(final EntityChangeBlockEvent e) {
        if (e.getEntity() instanceof FallingBlock) {
            e.setCancelled(true);
            final FallingBlock fb = Bukkit.getWorld("world").spawnFallingBlock(e.getEntity().getLocation(), Material.AIR, (byte)0);
            fb.setVelocity(new Vector(0, 1, 0));
        }
    }
    
    @EventHandler
    public void damage(final EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            final Player p = (Player)e.getEntity();
            if (Main.Metheor.contains(p.getName()) && e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
                e.setCancelled(true);
            }
        }
    }
    
    @EventHandler
    public void damag2e(final EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            final Player p = (Player)e.getEntity();
            if (Main.Metheor.contains(p.getName()) && e.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) {
                e.setCancelled(true);
            }
        }
    }
}
