package Nodus.Commands;

import java.util.*;
import org.bukkit.command.*;
import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class PlayerL implements Listener
{
    public static boolean chat1;
    public static ArrayList<Player> cc;
    public static ArrayList<Player> NaChl;
    public static ArrayList<Player> adm;
    public static ArrayList<String> jump;
    public static ArrayList<Player> parado;
    public static ArrayList<Player> to;
    public static ArrayList<Player> bo;
    public static ArrayList<Player> rc;
    public static HashMap<Player, Player> clog;
    public static boolean pvp;
    public static HashMap<CommandSender, CommandSender> respostas;
    public static HashMap<String, String> fighting;
    
    static {
        PlayerL.chat1 = true;
        PlayerL.cc = new ArrayList<Player>();
        PlayerL.NaChl = new ArrayList<Player>();
        PlayerL.adm = new ArrayList<Player>();
        PlayerL.jump = new ArrayList<String>();
        PlayerL.parado = new ArrayList<Player>();
        PlayerL.to = new ArrayList<Player>();
        PlayerL.bo = new ArrayList<Player>();
        PlayerL.rc = new ArrayList<Player>();
        PlayerL.clog = new HashMap<Player, Player>();
        PlayerL.pvp = true;
        PlayerL.respostas = new HashMap<CommandSender, CommandSender>();
        PlayerL.fighting = new HashMap<String, String>();
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onDamage(final EntityDamageEvent event) {
        if ((event.getEntity() instanceof Player || (event.getEntity() instanceof Tameable && ((Tameable)event.getEntity()).isTamed())) && event.getEntity() instanceof Player && (!PlayerL.pvp || PlayerL.jump.contains(((Player)event.getEntity()).getName()))) {
            event.setCancelled(true);
        }
    }
}
