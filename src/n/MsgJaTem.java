package n;

import org.bukkit.ChatColor;

import Nodus.Main.Main;

public abstract class MsgJaTem {
	public static String TEMMSG;

	static {
		MsgJaTem.TEMMSG = ChatColor.translateAlternateColorCodes('&', "Voce ja tem Esse Kit");
	}

	public MsgJaTem(final Main plugin) {
	}
}
