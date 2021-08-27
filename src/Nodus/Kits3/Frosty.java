package Nodus.Kits3;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.player.*;
import org.bukkit.block.*;
import org.bukkit.potion.*;
import org.bukkit.event.*;

public class Frosty implements Listener, CommandExecutor
{
    public static Main plugin;
    
    public Frosty(final Main main) {
        Frosty.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("frosty")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.frosty")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + "Frosty");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.frosty.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("�cSword");
                    Habilidade.setAbility(p, "Frosty");
                    p.getInventory().addItem(new ItemStack[] { espada });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Frosty.plugin.getConfig().getString("Sem_Permiss\u00c3�o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Frosty.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void onPlayerFrosty(final PlayerMoveEvent e) {
        final Player p = e.getPlayer();
        if ((e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SNOW_BLOCK || e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.ICE) && Main.frosty.contains(p.getName())) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 80, 0));
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 80, 0));
        }
    }
}
