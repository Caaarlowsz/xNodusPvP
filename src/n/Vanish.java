package n;

import java.util.*;
import org.bukkit.entity.*;
import org.bukkit.*;

public class Vanish
{
    public static ArrayList<Player> vanished;
    
    static {
        Vanish.vanished = new ArrayList<Player>();
    }
    
    public static void makeVanished(final Player p) {
        Player[] onlinePlayers;
        for (int length = (onlinePlayers = Bukkit.getServer().getOnlinePlayers()).length, i = 0; i < length; ++i) {
            final Player player = onlinePlayers[i];
            if (!player.getName().equals(p.getName())) {
                player.hidePlayer(p);
            }
        }
        if (!Vanish.vanished.contains(p)) {
            Vanish.vanished.add(p);
        }
    }
    
    public static boolean isVanished(final Player p) {
        return Vanish.vanished.contains(p);
    }
    
    public static void updateVanished() {
        Player[] onlinePlayers;
        for (int length = (onlinePlayers = Bukkit.getServer().getOnlinePlayers()).length, i = 0; i < length; ++i) {
            final Player p = onlinePlayers[i];
            if (isVanished(p)) {
                makeVanished(p);
            }
            else {
                makeVisible(p);
            }
        }
    }
    
    public static void makeVisible(final Player p) {
        Player[] onlinePlayers;
        for (int length = (onlinePlayers = Bukkit.getServer().getOnlinePlayers()).length, i = 0; i < length; ++i) {
            final Player player = onlinePlayers[i];
            player.showPlayer(p);
        }
        if (Vanish.vanished.contains(p)) {
            Vanish.vanished.remove(p);
        }
    }
}
