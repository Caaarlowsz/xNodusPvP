package Nodus.Kits2;

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
import org.bukkit.util.*;
import org.bukkit.*;
import org.bukkit.event.*;

public class Bloodgun implements Listener, CommandExecutor
{
    public static Main plugin;
    public static List<Player> cooldownm;
    
    static {
        Bloodgun.cooldownm = new ArrayList<Player>();
    }
    
    public Bloodgun(final Main main) {
        Bloodgun.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("bloodgun")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.Bloodgun")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + " Bloodgun");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.blood.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    final ItemStack bow = new ItemStack(Material.WOOD_HOE);
                    final ItemMeta bowmeta = bow.getItemMeta();
                    bowmeta.setDisplayName("§4§oBloodgun!");
                    Habilidade.setAbility(p, "Bloodgun");
                    bow.setItemMeta(bowmeta);
                    p.getInventory().addItem(new ItemStack[] { espada });
                    p.getInventory().addItem(new ItemStack[] { bow });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Bloodgun.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Bloodgun.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void BloodClick(final PlayerInteractEvent event) {
        final Player p = event.getPlayer();
        if (event.getPlayer().getItemInHand().getType() == Material.WOOD_HOE && Main.blood.contains(event.getPlayer().getName())) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                event.setCancelled(true);
            }
            if (Bloodgun.cooldownm.contains(p)) {
                p.sendMessage("§c§oAguarde o cooldown acabar!");
            }
            else {
                final Vector velo1 = p.getLocation().getDirection().normalize().multiply(10);
                final Fireball boladenve = (Fireball)p.launchProjectile((Class)Fireball.class);
                boladenve.setIsIncendiary(false);
                boladenve.setYield(0.0f);
                boladenve.setVelocity(velo1);
                final Location location = p.getEyeLocation();
                final BlockIterator blocksToAdd = new BlockIterator(location, 0.0, 30);
                while (blocksToAdd.hasNext()) {
                    final Location blockToAdd = blocksToAdd.next().getLocation();
                    final Effect a = Effect.STEP_SOUND;
                    p.getWorld().playEffect(blockToAdd, a, 152);
                    Bloodgun.cooldownm.add(p);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            Bloodgun.cooldownm.remove(p);
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
}
