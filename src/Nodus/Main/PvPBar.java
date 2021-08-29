package Nodus.Main;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class PvPBar {
	public static Map<String, String> powerMap;

	static {
		PvPBar.powerMap = new HashMap<String, String>();
	}

	public static String NomeCombat(final String original) {
		if (original.length() == 0) {
			return original;
		}
		return String.valueOf(original.substring(0, 1).toUpperCase()) + original.substring(1);
	}

	public static String getAbility(final Player player) {
		if (!PvPBar.powerMap.containsKey(player.getName())) {
			PvPBar.powerMap.put(player.getName(), "Nao");
		}
		return PvPBar.powerMap.get(player.getName());
	}

	public static void setAbility(final Player player, final String ability) {
		PvPBar.powerMap.put(player.getName(), ability);
	}

	public static void removeAbility(final Player p) {
		PvPBar.powerMap.remove(p.getName());
	}

	public static void strikeLightning(final Entity p) {
		final Location coords = p.getLocation();
		coords.getWorld().strikeLightningEffect(coords);
		final Block block = coords.getBlock();
		block.setType(Material.AIR);
	}
}
