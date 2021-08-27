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
import java.util.concurrent.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class TnTMan implements Listener, CommandExecutor
{
    public static Main plugin;
    public static HashMap<String, Long> cooldown;
    public static HashMap<String, Egg> teias;
    
    static {
        TnTMan.cooldown = new HashMap<String, Long>();
        TnTMan.teias = new HashMap<String, Egg>();
    }
    
    public TnTMan(final Main main) {
        TnTMan.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("exploder")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.exploder")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + "Exploder");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.Exploder.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    final ItemStack iron = new ItemStack(Material.TNT);
                    final ItemMeta ironm = iron.getItemMeta();
                    ironm.setDisplayName("§4Tnt voadora");
                    Habilidade.setAbility(p, "TnTMan");
                    iron.setItemMeta(ironm);
                    p.getInventory().addItem(new ItemStack[] { espada });
                    p.getInventory().addItem(new ItemStack[] { iron });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', TnTMan.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', TnTMan.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void lancar(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && Main.Exploder.contains(p.getName()) && p.getItemInHand().getType() == Material.TNT) {
            e.setCancelled(true);
            p.updateInventory();
            if (!TnTMan.cooldown.containsKey(p.getName()) || TnTMan.cooldown.get(p.getName()) <= System.currentTimeMillis()) {
                if (Main.SemPvP(p)) {
                    final Location loc = p.getLocation();
                    loc.setY(loc.getY() + 1.0);
                    final Entity tnt = Bukkit.getServer().getWorld(p.getLocation().getWorld().getName()).spawnEntity(loc, EntityType.PRIMED_TNT);
                    tnt.setVelocity(p.getEyeLocation().getDirection().multiply(1));
                    TnTMan.cooldown.put(p.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(30L));
                    p.playSound(p.getLocation(), Sound.ENDERDRAGON_HIT, 1.0f, 1.0f);
                    p.playEffect(p.getEyeLocation(), Effect.SMOKE, 100);
                    return;
                }
                p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
            }
            else {
                p.sendMessage(ChatColor.RED + "Faltam " + TimeUnit.MILLISECONDS.toSeconds(TnTMan.cooldown.get(p.getName()) - System.currentTimeMillis()) + " segundos para poder usar novamente.");
            }
        }
    }
}
