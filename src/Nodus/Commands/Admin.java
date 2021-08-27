package Nodus.Commands;

import java.util.*;
import org.bukkit.inventory.*;
import org.bukkit.command.*;
import n.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.enchantments.*;
import Nodus.Main.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.util.*;
import org.bukkit.event.player.*;

public class Admin implements CommandExecutor, Listener
{
    public String description;
    public static ArrayList<Player> adm;
    public HashMap<Player, ItemStack[]> invsave;
    public HashMap<Player, ItemStack[]> armorsave;
    
    static {
        Admin.adm = PlayerListener.adm;
    }
    
    public Admin() {
        this.description = "Modo admin";
        this.invsave = new HashMap<Player, ItemStack[]>();
        this.armorsave = new HashMap<Player, ItemStack[]>();
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        final Player p = (Player)sender;
        if (!p.hasPermission("nodus.admin")) {
            return true;
        }
        if (!Vanish.vanished.contains(p)) {
            PlayerListener.setAdminMode(p, true);
            Player[] arrayOfPlayer;
            for (int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length, i = 0; i < j; ++i) {
                final Player p2 = arrayOfPlayer[i];
                if (p2.hasPermission("nodus.staffchat")) {
                    p2.sendMessage(ChatColor.GRAY + sender.getName() + " entrou no modo admin.");
                    p.sendMessage("§9Suas mensagens serao mandadas somente para os staffers");
                    p.getInventory().clear();
                    this.invsave.put(p, p.getInventory().getContents());
                    this.armorsave.put(p, p.getInventory().getArmorContents());
                    Admin.adm.add(p);
                    p.setDisplayName("§7[§cAdminMode§7] " + p.getName());
                    p.showPlayer(p2);
                    p2.showPlayer(p);
                    giveadminitens(p);
                }
                p2.sendMessage(Main.getPlugin().getConfig().getString("SairMensagem").replaceAll("&", "§").replaceAll("%p", p.getName()));
            }
            return true;
        }
        PlayerListener.setAdminMode(p, false);
        Player[] arrayOfPlayer;
        for (int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length, i = 0; i < j; ++i) {
            final Player p2 = arrayOfPlayer[i];
            if (p2.hasPermission("nodus.admin")) {
                p2.sendMessage(ChatColor.GRAY + sender.getName() + " saiu do admin.");
                p.getInventory().setContents((ItemStack[])this.invsave.get(p));
                p.getInventory().setArmorContents((ItemStack[])this.armorsave.get(p));
                final ItemStack chest = new ItemStack(Material.BLAZE_ROD);
                final ItemMeta chestm = chest.getItemMeta();
                chestm.setDisplayName("§b§l1v1-Teleport");
                chest.setItemMeta(chestm);
                final ItemStack menu = new ItemStack(Material.PISTON_STICKY_BASE);
                final ItemMeta menum = menu.getItemMeta();
                menum.setDisplayName("§a§lMenu");
                menu.setItemMeta(menum);
                final ItemStack as = new ItemStack(Material.CARPET, 1, (short)14);
                final ItemMeta ass = as.getItemMeta();
                ass.setDisplayName(ChatColor.RED + " ");
                as.setItemMeta(ass);
                final ItemStack vine = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)5);
                final ItemMeta vinew = vine.getItemMeta();
                vinew.setDisplayName(ChatColor.RED + " ");
                vine.setItemMeta(vinew);
                final ItemStack kits = new ItemStack(Material.BOOK);
                final ItemMeta kitsm = kits.getItemMeta();
                kitsm.setDisplayName("§3§lKits");
                kits.setItemMeta(kitsm);
                final ItemStack kits2 = new ItemStack(Material.NETHER_STAR);
                final ItemMeta kitsm2 = kits2.getItemMeta();
                kitsm2.setDisplayName("§bClick-Test");
                kits2.setItemMeta(kitsm2);
                final ItemStack loja = new ItemStack(Material.DIAMOND);
                final ItemMeta kitsm3 = loja.getItemMeta();
                kitsm3.setDisplayName("§b§lNosso Site");
                loja.setItemMeta(kitsm3);
                final ItemStack loja2 = new ItemStack(Material.EMERALD);
                final ItemMeta kitsm4 = loja2.getItemMeta();
                kitsm4.setDisplayName("§9§lLoja Kits");
                loja2.setItemMeta(kitsm4);
                final ItemStack loja3 = new ItemStack(Material.ENDER_CHEST);
                final ItemMeta kitsm5 = loja3.getItemMeta();
                kitsm5.setDisplayName("§7§lWarps");
                loja3.setItemMeta(kitsm5);
                p.getInventory().setItem(0, loja2);
                p.getInventory().setItem(1, kits);
                p.getInventory().setItem(8, loja3);
                p.getInventory().setItem(7, kits2);
                p.getInventory().setItem(3, loja);
                p.getInventory().setItem(4, menu);
                p.getInventory().setItem(5, chest);
                Admin.adm.remove(p);
                p.setDisplayName("§7" + p.getName());
            }
            p2.sendMessage(Main.getPlugin().getConfig().getString("EntrarMensagem").replaceAll("&", "§").replaceAll("%p", p.getName()));
        }
        return true;
    }
    
    public static void giveadminitens(final Player target) {
        final ItemStack fast = new ItemStack(Material.MAGMA_CREAM);
        final ItemMeta fasts = fast.getItemMeta();
        fasts.addEnchant(Enchantment.DURABILITY, 1, true);
        fasts.setDisplayName("§3Fast-Switch");
        fast.setItemMeta(fasts);
        final ItemStack arena = new ItemStack(Material.IRON_FENCE);
        final ItemMeta arenas = arena.getItemMeta();
        arenas.setDisplayName("§3Prender");
        arenas.addEnchant(Enchantment.DURABILITY, 1, true);
        arena.setItemMeta(arenas);
        final ItemStack info = new ItemStack(Material.BLAZE_ROD);
        final ItemMeta infos = info.getItemMeta();
        infos.addEnchant(Enchantment.DURABILITY, 1, true);
        infos.setDisplayName("§3Informacoes do player");
        info.setItemMeta(infos);
        final ItemStack kb = new ItemStack(Material.STICK);
        final ItemMeta kbs = kb.getItemMeta();
        kbs.addEnchant(Enchantment.DURABILITY, 1, true);
        kbs.addEnchant(Enchantment.KNOCKBACK, 3, true);
        kbs.setDisplayName("§3Knockback");
        kb.setItemMeta(kbs);
        final ItemStack nofall = new ItemStack(Material.FEATHER);
        final ItemMeta nofalls = nofall.getItemMeta();
        nofalls.addEnchant(Enchantment.DURABILITY, 1, true);
        nofalls.setDisplayName("§3No-Fall");
        nofall.setItemMeta(nofalls);
        target.getInventory().setItem(1, fast);
        target.getInventory().setItem(2, arena);
        target.getInventory().setItem(3, info);
        target.getInventory().setItem(5, kb);
        target.getInventory().setItem(6, nofall);
    }
    
    @EventHandler
    public void infos(final PlayerInteractEntityEvent e) {
        if (e.getRightClicked() instanceof Player && e.getRightClicked() instanceof Player) {
            final Player p = e.getPlayer();
            final Player r = (Player)e.getRightClicked();
            if (p.getItemInHand().getType() == Material.BLAZE_ROD && Admin.adm.contains(p.getPlayer())) {
                final Damageable hp = (Damageable)r;
                p.sendMessage("§7Infos do Player " + r.getDisplayName());
                p.sendMessage("§7Vida: §a" + (int)hp.getHealth());
                p.sendMessage("§7Sopas: §a" + AutoSoup.getAmount(r, Material.MUSHROOM_SOUP));
                p.sendMessage("§7Gamemode: §a" + r.getGameMode());
                p.sendMessage("§7Kit: §a" + Habilidade.getAbility(r));
                p.sendMessage("§7Ip: §a" + r.getAddress().getHostString());
            }
        }
    }
    
    @EventHandler
    public void flash(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && Admin.adm.contains(p) && p.getItemInHand().getType() == Material.MAGMA_CREAM) {
            p.chat("/admin");
            p.sendMessage("§c ");
            p.sendMessage("§c ");
            p.sendMessage("§c ");
            p.sendMessage("§c ");
            p.sendMessage("§c ");
            p.sendMessage("§c ");
            p.sendMessage("§c ");
            p.sendMessage("§c ");
            p.sendMessage("§c ");
            p.sendMessage("§c ");
            p.sendMessage("§c ");
            p.sendMessage("§c ");
            p.sendMessage("§c ");
            p.sendMessage("§c ");
            p.sendMessage("§c ");
            p.sendMessage("§c ");
            p.sendMessage("§c ");
            p.sendMessage("§c ");
            p.sendMessage("§c ");
            p.sendMessage("§c ");
            p.sendMessage("§c ");
            p.sendMessage("§c ");
            p.sendMessage("§c ");
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
                @Override
                public void run() {
                    p.chat("/admin");
                    p.sendMessage("§c ");
                    p.sendMessage("§c ");
                    p.sendMessage("§c ");
                    p.sendMessage("§c ");
                    p.sendMessage("§c ");
                    p.sendMessage("§c ");
                    p.sendMessage("§c ");
                    p.sendMessage("§c ");
                    p.sendMessage("§c ");
                    p.sendMessage("§c ");
                    p.sendMessage("§c ");
                    p.sendMessage("§c ");
                    p.sendMessage("§c ");
                    p.sendMessage("§c ");
                    p.sendMessage("§c ");
                    p.sendMessage("§c ");
                    p.sendMessage("§c ");
                    p.sendMessage("§c ");
                    p.sendMessage("§c ");
                    p.sendMessage("§c ");
                    p.sendMessage("§c ");
                    p.sendMessage("§c ");
                    p.sendMessage("§c ");
                    p.sendMessage("§c ");
                    p.sendMessage("§c ");
                }
            }, 15L);
        }
    }
    
    @EventHandler
    public void jaular(final PlayerInteractEntityEvent e) {
        if (e.getRightClicked() instanceof Player) {
            final Player p = e.getPlayer();
            final Player r = (Player)e.getRightClicked();
            if (p.getItemInHand().getType() == Material.IRON_FENCE && Admin.adm.contains(p.getPlayer())) {
                r.getLocation().add(0.0, 13.0, 0.0).getBlock().setType(Material.BEDROCK);
                r.getLocation().add(0.0, 11.0, 1.0).getBlock().setType(Material.BEDROCK);
                r.getLocation().add(1.0, 11.0, 0.0).getBlock().setType(Material.BEDROCK);
                r.getLocation().add(0.0, 11.0, -1.0).getBlock().setType(Material.BEDROCK);
                r.getLocation().add(-1.0, 11.0, 0.0).getBlock().setType(Material.BEDROCK);
                r.getLocation().add(0.0, 10.0, 0.0).getBlock().setType(Material.BEDROCK);
                r.teleport(r.getLocation().add(0.0, 11.0, -0.05));
                Player[] arrayOfPlayer;
                for (int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length, i = 0; i < j; ++i) {
                    final Player p2 = arrayOfPlayer[i];
                    if (p2.hasPermission("nodus.admin")) {
                        p2.sendMessage(ChatColor.GRAY + p.getName() + " Criou Uma Arena Com " + r.getDisplayName());
                    }
                }
            }
        }
    }
    
    @EventHandler
    public void nofall(final PlayerInteractEntityEvent e) {
        if (e.getRightClicked() instanceof Player) {
            final Player p = e.getPlayer();
            final Player r = (Player)e.getRightClicked();
            if (p.getItemInHand().getType() == Material.FEATHER && Admin.adm.contains(p.getPlayer())) {
                final Vector sponge = r.getLocation().getDirection().multiply(0).setY(1.4);
                r.setVelocity(sponge);
            }
        }
    }
    
    @EventHandler
    public void adminchat(final PlayerChatEvent e) {
        if (Admin.adm.contains(e.getPlayer())) {
            final Player p = e.getPlayer();
            e.setCancelled(true);
            Player[] arrayOfPlayer;
            for (int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length, i = 0; i < j; ++i) {
                final Player p2 = arrayOfPlayer[i];
                if (p2.hasPermission("nodus.admin")) {
                    p2.sendMessage("§c[SC-ADMIN] " + p.getName() + " §4>> §7" + e.getMessage());
                }
            }
        }
    }
}
