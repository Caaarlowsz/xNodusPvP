package Nodus.Kits3;

import org.bukkit.command.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.inventory.meta.*;
import Nodus.Kits.*;
import java.text.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import me.confuser.barapi.*;
import java.util.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;

public class Madman implements Listener, CommandExecutor
{
    public static Main plugin;
    public static HashMap<String, Long> cooldown;
    public static HashMap<String, Egg> teias;
    public int cooldown11;
    public String cooldownMessage;
    ArrayList<String> thor;
    private transient HashMap<String, Long> lastThored;
    public static HashMap<Player, Double> madman;
    
    static {
        Madman.cooldown = new HashMap<String, Long>();
        Madman.teias = new HashMap<String, Egg>();
        Madman.madman = new HashMap<Player, Double>();
    }
    
    public Madman(final Main main) {
        this.cooldown11 = 40;
        this.cooldownMessage = ChatColor.RED + "Aguarde!";
        this.thor = new ArrayList<String>();
        this.lastThored = new HashMap<String, Long>();
        Madman.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("madman")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.madman")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + " Madman");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.MadMan.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    Habilidade.setAbility(p, "Madman");
                    espadameta.setDisplayName("§cSword");
                    p.getInventory().addItem(new ItemStack[] { espada });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Madman.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Madman.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void MadmanList(final TimeSecondEvent event) {
        final DecimalFormat dm = new DecimalFormat("##");
        Player[] arrayOfPlayer;
        for (int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length, i = 0; i < j; ++i) {
            final Player p = arrayOfPlayer[i];
            if (Main.MadMan.contains(p)) {
                for (final Entity e : p.getNearbyEntities(20.0, 20.0, 20.0)) {
                    if (e instanceof Player) {
                        if (Madman.madman.containsKey(e)) {
                            Madman.madman.put((Player)e, Madman.madman.get(e) + 0.01);
                        }
                        else {
                            Madman.madman.put((Player)e, 0.01);
                            ((Player)e).sendMessage(ChatColor.GRAY + "Parece que um Madman esta proximo...");
                        }
                        BarAPI.setMessage((Player)e, "Efeito do Madman: " + ChatColor.AQUA + dm.format(Madman.madman.get(e) * 100.0) + ChatColor.WHITE + "%");
                    }
                }
            }
            if (Madman.madman.containsKey(p)) {
                boolean hasMadMan = false;
                for (final Entity e2 : p.getNearbyEntities(20.0, 20.0, 20.0)) {
                    if (e2 instanceof Player) {
                        final Player mad = (Player)e2;
                        if (Main.MadMan.contains(p)) {
                            hasMadMan = true;
                            break;
                        }
                        continue;
                    }
                }
                if (!hasMadMan) {
                    if (Madman.madman.get(p) - 0.2 <= 0.0) {
                        Madman.madman.remove(p);
                        p.sendMessage(ChatColor.GRAY + "O efeito de Madman passou!");
                        BarAPI.removeBar(p);
                    }
                    else {
                        Madman.madman.put(p, Madman.madman.get(p) - 0.2);
                        BarAPI.setMessage(p, "§6Efeito do Madman: " + ChatColor.AQUA + dm.format(Madman.madman.get(p) * 100.0) + ChatColor.WHITE + "%");
                    }
                }
            }
        }
    }
    
    @EventHandler
    public void DanoMadman(final EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            final Player p = (Player)event.getEntity();
            if (Madman.madman.containsKey(p)) {
                event.setDamage(event.getDamage() + event.getDamage() * Madman.madman.get(p));
            }
        }
    }
}
