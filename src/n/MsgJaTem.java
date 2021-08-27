package n;

import org.bukkit.*;
import Nodus.Main.*;

public abstract class MsgJaTem
{
    public static String TEMMSG;
    
    static {
        MsgJaTem.TEMMSG = ChatColor.translateAlternateColorCodes('&', "Voce ja tem Esse Kit");
    }
    
    public MsgJaTem(final Main plugin) {
    }
}
