package Nodus.Kits2;

import java.util.*;
import org.bukkit.command.*;
import Nodus.Event.*;
import Nodus.Main.*;
import org.bukkit.inventory.*;
import Nodus.Warp.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.util.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import org.bukkit.potion.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.*;
import org.bukkit.entity.*;

public class SubZero implements Listener, CommandExecutor
{
    public static Main plugin;
    public static HashMap<String, Long> cooldown;
    public static HashMap<String, Egg> teias;
    public static List<Player> cooldownm;
    
    static {
        SubZero.cooldown = new HashMap<String, Long>();
        SubZero.teias = new HashMap<String, Egg>();
        SubZero.cooldownm = new ArrayList<Player>();
    }
    
    public SubZero(final Main main) {
        SubZero.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("SubZero")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.SubZero")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + " SubZero");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.SubZeroarrow.add(sender.getName());
                    Habilidade.setAbility(p, "SubZero");
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    final ItemStack iron = new ItemStack(Material.ICE);
                    final ItemMeta ironm = iron.getItemMeta();
                    ironm.setDisplayName("§bGelin");
                    iron.setItemMeta(ironm);
                    p.getInventory().addItem(new ItemStack[] { espada });
                    p.getInventory().addItem(new ItemStack[] { iron });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', SubZero.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', SubZero.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void BloodClick(final PlayerInteractEvent event) {
        final Player p = event.getPlayer();
        if (event.getPlayer().getItemInHand().getType() == Material.ICE && Main.SubZeroarrow.contains(event.getPlayer().getName())) {
            if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                event.setCancelled(true);
            }
            if (SubZero.cooldownm.contains(p)) {
                p.sendMessage("§c§oAguarde o cooldown acabar!");
            }
            else {
                final Vector velo1 = p.getLocation().getDirection().normalize().multiply(30);
                final Snowball boladenve = (Snowball)p.launchProjectile((Class)Snowball.class);
                boladenve.setVelocity(velo1);
                final Location location = p.getEyeLocation();
                final BlockIterator blocksToAdd = new BlockIterator(location, 0.0, 30);
                while (blocksToAdd.hasNext()) {
                    final Location blockToAdd = blocksToAdd.next().getLocation();
                    final Effect a = Effect.STEP_SOUND;
                    p.getWorld().playEffect(blockToAdd, Effect.SMOKE, 5);
                    SubZero.cooldownm.add(p);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            SubZero.cooldownm.remove(p);
                        }
                    }, 200L);
                }
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        p.sendMessage("§a§oVoce pode usar novamente!");
                    }
                }, 200L);
            }
        }
    }
    
    @EventHandler
    public void DanoOstenta$aum(final EntityDamageByEntityEvent e) {
        final Entity ent = e.getEntity();
        final Entity damager = e.getDamager();
        if (ent instanceof Player) {
            final Player hit = (Player)ent;
            if (damager instanceof Snowball) {
                final Snowball snowball = (Snowball)damager;
                if (snowball.getShooter() instanceof Player) {
                    final Player shooter = (Player)snowball.getShooter();
                    if (!Main.SubZeroarrow.contains(shooter.getName())) {
                        return;
                    }
                    final Location ploc = shooter.getLocation();
                    final Location hitloc = hit.getLocation();
                    hit.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 2));
                    hit.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 10));
                    hit.getInventory().setHelmet(new ItemStack(Material.ICE));
                    final ItemStack Peitoral = new ItemStack(Material.LEATHER_CHESTPLATE);
                    final LeatherArmorMeta kPeitoral = (LeatherArmorMeta)Peitoral.getItemMeta();
                    kPeitoral.setColor(Color.BLUE);
                    Peitoral.setItemMeta((ItemMeta)kPeitoral);
                    final ItemStack Calss = new ItemStack(Material.LEATHER_LEGGINGS);
                    final LeatherArmorMeta kCalss = (LeatherArmorMeta)Calss.getItemMeta();
                    kCalss.setColor(Color.BLUE);
                    Calss.setItemMeta((ItemMeta)kCalss);
                    final ItemStack a = new ItemStack(Material.APPLE);
                    hit.getInventory().setChestplate(Peitoral);
                    hit.getInventory().setLeggings(Calss);
                    hit.updateInventory();
                    hitloc.getBlock().setType(Material.ICE);
                    TimeLord.frozenPlayers.add(hit.getName());
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            hit.getInventory().setHelmet(new ItemStack(Material.APPLE));
                            hit.getInventory().setChestplate(a);
                            hit.getInventory().setLeggings(a);
                            TimeLord.frozenPlayers.remove(hit.getName());
                        }
                    }, 200L);
                    Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            hit.getLocation().getWorld().playEffect(hitloc, Effect.STEP_SOUND, (Object)Material.ICE, 20);
                            hitloc.getBlock().setType(Material.APPLE);
                        }
                    }, 200L);
                }
            }
        }
    }
}
