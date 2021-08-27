package Nodus.Kits3;

import org.bukkit.command.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.entity.*;
import org.bukkit.potion.*;
import java.util.concurrent.*;
import java.util.*;
import org.bukkit.event.*;

public class Lobisomem implements Listener, CommandExecutor
{
    public static Main plugin;
    public static HashMap<String, Long> cooldown;
    
    static {
        Lobisomem.cooldown = new HashMap<String, Long>();
    }
    
    public Lobisomem(final Main main) {
        Lobisomem.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("lobisomem")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.lobisomem")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + "Lobisomem");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.lobisomem.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    Habilidade.setAbility(p, "Lobisomem");
                    p.getInventory().addItem(new ItemStack[] { espada });
                    p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MONSTER_EGG) });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    final World w = Bukkit.getServer().getWorld(Lobisomem.plugin.getConfig().getString("adafafadfafafafd.world"));
                    final double x = Lobisomem.plugin.getConfig().getDouble("adafafadfafafafd.x");
                    final double y = Lobisomem.plugin.getConfig().getDouble("adafafadfafafafd.y");
                    final double z = Lobisomem.plugin.getConfig().getDouble("adafafadfafafafd.z");
                    final Location lobby = new Location(w, x, y, z);
                    lobby.setPitch((float)Lobisomem.plugin.getConfig().getDouble("adafafadfafafafd.pitch"));
                    lobby.setYaw((float)Lobisomem.plugin.getConfig().getDouble("adafafadfafafafd.yaw"));
                    p.getWorld().playEffect(lobby, Effect.ENDER_SIGNAL, 5);
                    p.getPlayer().playSound(p.getLocation(), Sound.ZOMBIE_REMEDY, 5.0f, 5.0f);
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Lobisomem.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Lobisomem.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void interact(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && Main.lobisomem.contains(p.getName()) && p.getItemInHand().getType() == Material.MONSTER_EGG) {
            if (!Lobisomem.cooldown.containsKey(p.getName()) || Lobisomem.cooldown.get(p.getName()) <= System.currentTimeMillis()) {
                e.setCancelled(true);
                p.updateInventory();
                final Wolf w = (Wolf)p.getWorld().spawnEntity(new Location(p.getWorld(), p.getLocation().getX() + 2.0, p.getLocation().getY(), p.getLocation().getZ()), EntityType.WOLF);
                final Wolf w2 = (Wolf)p.getWorld().spawnEntity(new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ() + 2.0), EntityType.WOLF);
                final Wolf w3 = (Wolf)p.getWorld().spawnEntity(new Location(p.getWorld(), p.getLocation().getX() + 2.0, p.getLocation().getY(), p.getLocation().getZ() + 2.0), EntityType.WOLF);
                w.setAngry(true);
                w.setOwner((AnimalTamer)p);
                w2.setAngry(true);
                w2.setOwner((AnimalTamer)p);
                w3.setAngry(true);
                w3.setOwner((AnimalTamer)p);
                final List<Entity> nearby = (List<Entity>)p.getNearbyEntities(5.0, 5.0, 5.0);
                for (final Entity en : nearby) {
                    if (en instanceof Player) {
                        final Player s = (Player)en;
                        w.setTarget((LivingEntity)s);
                        w2.setTarget((LivingEntity)s);
                        w3.setTarget((LivingEntity)s);
                    }
                }
                p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 500, 0));
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 500, 0));
                p.playSound(p.getLocation(), Sound.WOLF_HOWL, 1.0f, 1.0f);
                Lobisomem.cooldown.put(p.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(40L));
                return;
            }
            p.sendMessage(ChatColor.RED + "Faltam " + TimeUnit.MILLISECONDS.toSeconds(Lobisomem.cooldown.get(p.getName()) - System.currentTimeMillis()) + " segundos para poder usar novamente.");
        }
    }
}
