package Nodus.Commands;

import org.bukkit.event.*;
import org.bukkit.command.*;
import org.bukkit.*;
import Nodus.Main.*;
import org.bukkit.inventory.*;
import org.bukkit.entity.*;

public class Arena implements CommandExecutor, Listener
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String arg2, final String[] args) {
        final Player p = (Player)sender;
        if (p.hasPermission("nodus.arena") && cmd.getName().equalsIgnoreCase("arena")) {
            if (args.length == 0) {
                p.sendMessage("§aUse: /arena <Jogador>");
            }
            if (args.length == 1) {
                final Player o = Bukkit.getPlayer(args[0]);
                if (o != null) {
                    o.getLocation().add(0.0, 13.0, 0.0).getBlock().setType(Material.BEDROCK);
                    o.getLocation().add(0.0, 11.0, 1.0).getBlock().setType(Material.BEDROCK);
                    o.getLocation().add(1.0, 11.0, 0.0).getBlock().setType(Material.BEDROCK);
                    o.getLocation().add(0.0, 11.0, -1.0).getBlock().setType(Material.BEDROCK);
                    o.getLocation().add(-1.0, 11.0, 0.0).getBlock().setType(Material.BEDROCK);
                    o.getLocation().add(0.0, 10.0, 0.0).getBlock().setType(Material.BEDROCK);
                    o.teleport(o.getLocation().add(0.0, 11.0, -0.05));
                    p.sendMessage("§cArena criada");
                    Player[] onlinePlayers;
                    for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
                        final Player p2 = onlinePlayers[i];
                        if (p2.hasPermission("nodus.arena")) {
                            p2.sendMessage(ChatColor.GRAY + sender.getName() + " Criou Uma Arena Com " + o.getDisplayName());
                        }
                    }
                }
                else {
                    p.sendMessage("§cJogador offline");
                }
            }
        }
        return false;
    }
    
    public boolean onCommand56789(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        final Player p = (Player)sender;
        if (commandLabel.equalsIgnoreCase("mute")) {
            if (sender.hasPermission("nodus.mute")) {
                if (args.length == 0) {
                    p.sendMessage(ChatColor.RED + "Use /Mute [Jogador]");
                }
                else if (args.length == 1) {
                    final Player player = Bukkit.getPlayer(args[0]);
                    if (player == null) {
                        p.sendMessage(ChatColor.RED + "Esse Jogador Nao Esta Online!.");
                        return true;
                    }
                    p.sendMessage(ChatColor.RED + "O Jogador Foi Mutado Com Sucesso!.");
                    player.sendMessage(ChatColor.RED + "Voce Foi Mutado Por Um Admin!.");
                    Main.mute.add(player.getName());
                }
            }
            else {
                p.sendMessage(ChatColor.RED + "Voce Nao Tem Permissao!.");
            }
        }
        if (commandLabel.equalsIgnoreCase("desmute")) {
            if (sender.hasPermission("nodus.mute")) {
                if (args.length == 0) {
                    p.sendMessage(ChatColor.RED + "Use /Desmute [Jogador]");
                }
                else if (args.length == 1) {
                    final Player player = Bukkit.getPlayer(args[0]);
                    if (player == null) {
                        p.sendMessage(ChatColor.RED + "Esse Jogador Nao Esta Online!.");
                        return true;
                    }
                    p.sendMessage(ChatColor.RED + "O Jogador Foi Desmutado Com Sucesso!.");
                    Main.mute.remove(player.getName());
                }
            }
            else {
                p.sendMessage(ChatColor.RED + "Voce Nao Tem Permissao!.");
            }
        }
        return false;
    }
    
    public static int getAmount(final Player p, final Material m) {
        int amount = 0;
        ItemStack[] contents;
        for (int length = (contents = p.getInventory().getContents()).length, i = 0; i < length; ++i) {
            final ItemStack item = contents[i];
            if (item != null && item.getType() == m) {
                if (item.getAmount() > 0) {
                    amount += item.getAmount();
                }
            }
        }
        return amount;
    }
    
    public boolean onCommand09876(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Voce nao e um jogador!");
            return false;
        }
        final Player p = (Player)sender;
        if (commandLabel.equalsIgnoreCase("check") && p.hasPermission("nodus.check") && args.length == 1) {
            final Player target = p.getServer().getPlayer(args[0]);
            if (target != null) {
                if (!target.isOp()) {
                    final int killst = Main.plugin.getConfig().getInt("jogador." + p.getName().toLowerCase() + ".kill-streak");
                    final Damageable hp = (Damageable)target;
                    p.sendMessage("§7Informa\u00e7\u00f5es do player §6" + target.getName());
                    p.sendMessage("§7Vida: §6" + (int)hp.getHealth());
                    p.sendMessage("§7Sopas: §6" + getAmount(target, Material.MUSHROOM_SOUP));
                    p.sendMessage("§7Potions: §6" + getAmount(target, Material.POTION));
                    p.sendMessage("§7Gamemode: §6" + target.getGameMode());
                    p.sendMessage(ChatColor.GRAY + "IP : " + ChatColor.GOLD + target.getAddress().getHostString());
                    p.sendMessage("§7KillStreak: §6" + killst);
                }
                else {
                    p.sendMessage(ChatColor.RED + "Jogador " + args[0] + " inexistente!");
                }
            }
            else {
                p.sendMessage(ChatColor.RED + "Errado! Use /" + commandLabel + " <jogador>");
            }
        }
        return true;
    }
}
