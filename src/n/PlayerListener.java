package n;

import org.bukkit.event.*;
import java.util.*;
import org.bukkit.entity.*;
import org.bukkit.*;

public class PlayerListener implements Listener
{
    public static boolean chat1;
    public static ArrayList<Player> adm;
    
    static {
        PlayerListener.chat1 = true;
        PlayerListener.adm = new ArrayList<Player>();
    }
    
    public static void setAdminMode(final Player p, final boolean adminmode) {
        if (adminmode) {
            if (!Vanish.vanished.contains(p)) {
                Vanish.vanished.add(p);
            }
            Player[] arrayOfPlayer;
            for (int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length, i = 0; i < j; ++i) {
                final Player gamers = arrayOfPlayer[i];
                gamers.hidePlayer(p);
            }
            p.setGameMode(GameMode.CREATIVE);
            p.sendMessage("§7Voce entrou em modo §cAdmin.");
            Vanish.updateVanished();
            if (!PlayerListener.adm.contains(p)) {
                PlayerListener.adm.add(p);
            }
        }
        else {
            if (Vanish.vanished.contains(p)) {
                Vanish.vanished.remove(p);
            }
            Player[] arrayOfPlayer;
            for (int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length, i = 0; i < j; ++i) {
                final Player gamers = arrayOfPlayer[i];
                gamers.showPlayer(p);
            }
            p.setGameMode(GameMode.SURVIVAL);
            p.sendMessage("§7Voce saiu do §cAdmin");
            Vanish.updateVanished();
            if (PlayerListener.adm.contains(p)) {
                PlayerListener.adm.remove(p);
            }
        }
    }
}
