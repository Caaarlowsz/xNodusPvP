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
import org.bukkit.metadata.*;
import java.util.concurrent.*;
import org.bukkit.*;
import org.bukkit.util.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;

public class Ryu implements Listener, CommandExecutor
{
    public static Main plugin;
    public static HashMap<String, Long> cooldown;
    
    static {
        Ryu.cooldown = new HashMap<String, Long>();
    }
    
    public Ryu(final Main main) {
        Ryu.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("Ryu")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.Ryu")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + " Ryu");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.riu.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    Habilidade.setAbility(p, "Ryu");
                    espadameta.setDisplayName("§cSword");
                    final ItemStack ryu = new ItemStack(Material.DIAMOND_BLOCK);
                    final ItemMeta bow = ryu.getItemMeta();
                    bow.setDisplayName("§3Hadouken! ");
                    ryu.setItemMeta(bow);
                    p.getInventory().addItem(new ItemStack[] { espada });
                    p.getInventory().addItem(new ItemStack[] { ryu });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Ryu.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Ryu.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void hadouken(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && Main.riu.contains(p.getName()) && p.getItemInHand().getType() == Material.DIAMOND_BLOCK) {
            if (!Ryu.cooldown.containsKey(p.getName()) || Ryu.cooldown.get(p.getName()) <= System.currentTimeMillis()) {
                e.setCancelled(true);
                p.updateInventory();
                final Location location = p.getEyeLocation();
                final BlockIterator blocksToAdd = new BlockIterator(location, 0.0, 40);
                while (blocksToAdd.hasNext()) {
                    final Location blockToAdd = blocksToAdd.next().getLocation();
                    p.getWorld().playEffect(blockToAdd, Effect.STEP_SOUND, (Object)Material.DIAMOND_BLOCK, 20);
                    p.playSound(blockToAdd, Sound.IRONGOLEM_THROW, 3.0f, 3.0f);
                }
                final Snowball h = (Snowball)p.launchProjectile((Class)Snowball.class);
                final Vector velo1 = p.getLocation().getDirection().normalize().multiply(10);
                h.setVelocity(velo1);
                h.setMetadata("hadouken", (MetadataValue)new FixedMetadataValue(Main.plugin, (Object)true));
                Ryu.cooldown.put(p.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(40L));
                return;
            }
            p.sendMessage(ChatColor.GRAY + "Voce esta restaurando sua energia " + TimeUnit.MILLISECONDS.toSeconds(Ryu.cooldown.get(p.getName()) - System.currentTimeMillis()) + " segundos.");
        }
    }
    
    @EventHandler
    public void dano(final EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Snowball) {
            final Player p = (Player)e;
            final Snowball s = (Snowball)e.getDamager();
            if (s.hasMetadata("hadouken") && Main.riu.contains(p.getName())) {
                e.setDamage(e.getDamage() + 8.0);
            }
        }
    }
}
