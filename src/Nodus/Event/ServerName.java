package Nodus.Event;

import Nodus.Main.*;

public class ServerName
{
    public static String nomedokit;
    public static String semperm;
    
    static {
        ServerName.nomedokit = Main.getInstance().getConfig().getString("MensagemPegarKits").replaceAll("&", "§");
        ServerName.semperm = "Voc\u00ea n\u00e3o tem permiss\u00e3o";
    }
}
