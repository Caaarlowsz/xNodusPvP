package Nodus.Commands;

import org.bukkit.event.*;
import java.util.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import Nodus.Main.*;
import org.bukkit.*;

public class Score implements CommandExecutor, Listener
{
    public static ArrayList<String> score;
    
    static {
        Score.score = new ArrayList<String>();
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("score")) {
            if (args.length == 0) {
                p.sendMessage(ChatColor.RED + "Use: /score on/off");
                return true;
            }
            if (args[0].equalsIgnoreCase("on")) {
                Score.score.add(p.getName());
                Main.config = Main.getPlugin().getConfig();
                p.sendMessage(ChatColor.GREEN + "ScoreBoard Ligado");
                Main.setScoreBoard(p);
                Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        Main.setScoreBoard(p);
                    }
                }, 0L, 20L);
            }
            if (args[0].equalsIgnoreCase("off")) {
                Score.score.remove(p.getName());
                Main.config = Main.getPlugin().getConfig();
                p.sendMessage(ChatColor.GREEN + "Scoreboard Desligado");
                Main.removerbord(p);
            }
        }
        return false;
    }
}
