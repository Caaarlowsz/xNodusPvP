package Nodus.Kits3;

import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.command.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.entity.*;
import org.bukkit.*;
import org.bukkit.plugin.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import java.text.*;

public class Ninja implements Listener, CommandExecutor
{
    public static HashMap<Player, Player> a;
    public static HashMap<Player, Long> b;
    public static List<Player> cooldownbk;
    public static Main plugin;
    
    static {
        Ninja.a = new HashMap<Player, Player>();
        Ninja.b = new HashMap<Player, Long>();
        Ninja.cooldownbk = new ArrayList<Player>();
    }
    
    public Ninja(final Main main) {
        Ninja.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("ninja")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.ninja")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + "Ninja");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.ninja.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    Habilidade.setAbility(p, "Ninja");
                    p.getInventory().addItem(new ItemStack[] { espada });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Ninja.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Ninja.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void a(final EntityDamageByEntityEvent paramEntityDamageByEntityEvent) {
        if (paramEntityDamageByEntityEvent.getDamager() instanceof Player && paramEntityDamageByEntityEvent.getEntity() instanceof Player) {
            final Player localPlayer1 = (Player)paramEntityDamageByEntityEvent.getDamager();
            final Player localPlayer2 = (Player)paramEntityDamageByEntityEvent.getEntity();
            if (Main.ninja.contains(localPlayer1.getName())) {
                Ninja.a.put(localPlayer1, localPlayer2);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)Ninja.plugin, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        Ninja.cooldownbk.remove(localPlayer1);
                    }
                }, 200L);
            }
        }
    }
    
    @EventHandler
    public void a(final PlayerToggleSneakEvent paramPlayerToggleSneakEvent) {
        final Player localPlayer1 = paramPlayerToggleSneakEvent.getPlayer();
        if (paramPlayerToggleSneakEvent.isSneaking() && Main.ninja.contains(localPlayer1.getName()) && Ninja.a.containsKey(localPlayer1)) {
            final Player localPlayer2 = Ninja.a.get(localPlayer1);
            if (localPlayer2 != null && !localPlayer2.isDead()) {
                String str = null;
                if (Ninja.b.get(localPlayer1) != null) {
                    final long l = Ninja.b.get(localPlayer1) - System.currentTimeMillis();
                    final DecimalFormat localDecimalFormat = new DecimalFormat("##");
                    final int i = (int)l / 1000;
                    str = localDecimalFormat.format(i);
                }
                if (Ninja.b.get(localPlayer1) == null || Ninja.b.get(localPlayer1) < System.currentTimeMillis()) {
                    if (localPlayer1.getLocation().distance(localPlayer2.getLocation()) < 100.0) {
                        localPlayer1.teleport(localPlayer2.getLocation());
                        localPlayer1.sendMessage(ChatColor.GREEN + "Teleportado");
                        Ninja.b.put(localPlayer1, System.currentTimeMillis() + 10000L);
                    }
                    else {
                        localPlayer1.sendMessage(ChatColor.RED + "O Ultimo jogador hitado esta muito longe!");
                    }
                }
                else {
                    localPlayer1.sendMessage("§9§lCooldown §f> §6" + str + " segundos!");
                }
            }
        }
    }
}
