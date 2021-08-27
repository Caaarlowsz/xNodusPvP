package Nodus.Kits;

import org.bukkit.command.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.player.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.*;
import org.bukkit.util.*;
import org.bukkit.event.*;

public class Dwarf implements Listener, CommandExecutor
{
    public static Main plugin;
    public int cooldown;
    private HashMap<Player, Long> cooldownExpires;
    private HashMap<Player, Long> startedSneaking;
    
    public Dwarf(final Main main) {
        this.cooldown = 30;
        this.cooldownExpires = new HashMap<Player, Long>();
        this.startedSneaking = new HashMap<Player, Long>();
        Dwarf.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("dwarf")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.dwarf")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + " Dwarf");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.dwarf.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    Habilidade.setAbility(p, "Dwarf");
                    p.getInventory().addItem(new ItemStack[] { espada });
                    Main.giveSoup(p, 35);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Dwarf.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Dwarf.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void onSneak(final PlayerToggleSneakEvent event) {
        final Player p = event.getPlayer();
        if (Main.dwarf.contains(p.getName())) {
            if (this.cooldownExpires.containsKey(p) && this.cooldownExpires.get(p) < System.currentTimeMillis()) {
                this.cooldownExpires.remove(p);
            }
            if (event.isSneaking()) {
                this.startedSneaking.put(p, System.currentTimeMillis());
                p.sendMessage("§cCarregando Poder...");
            }
            else if (this.startedSneaking.containsKey(p)) {
                this.cooldownExpires.put(p, System.currentTimeMillis() + this.cooldown * 1000);
                final long sneakingTime = System.currentTimeMillis() - this.startedSneaking.remove(p);
                final double knockBack = 0.5 * (sneakingTime / 1000L);
                for (final Entity entity : p.getNearbyEntities(knockBack, knockBack, knockBack)) {
                    p.playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
                    p.playSound(p.getLocation(), Sound.EXPLODE, 10.0f, 1.0f);
                    if (!(entity instanceof Player) || !((Player)entity).isSneaking()) {
                        final Vector vector = entity.getLocation().toVector().subtract(p.getLocation().toVector()).normalize();
                        entity.setVelocity(vector.multiply(knockBack));
                    }
                }
            }
        }
    }
}
