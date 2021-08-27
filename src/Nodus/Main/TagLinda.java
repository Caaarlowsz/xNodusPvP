package Nodus.Main;

import java.util.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.block.*;

public class TagLinda
{
    public static Map<String, String> powerMap;
    
    static {
        TagLinda.powerMap = new HashMap<String, String>();
    }
    
    public static String NomeCombat(final String original) {
        if (original.length() == 0) {
            return original;
        }
        return String.valueOf(original.substring(0, 1).toUpperCase()) + original.substring(1);
    }
    
    public static String getAbility(final Player player) {
        if (!TagLinda.powerMap.containsKey(player.getName())) {
            TagLinda.powerMap.put(player.getName(), "§7Default");
        }
        return TagLinda.powerMap.get(player.getName());
    }
    
    public static void setAbility(final Player player, final String ability) {
        TagLinda.powerMap.put(player.getName(), ability);
    }
    
    public static void removeAbility(final Player p) {
        TagLinda.powerMap.remove(p.getName());
    }
    
    public static void strikeLightning(final Entity p) {
        final Location coords = p.getLocation();
        coords.getWorld().strikeLightningEffect(coords);
        final Block block = coords.getBlock();
        block.setType(Material.AIR);
    }
}
