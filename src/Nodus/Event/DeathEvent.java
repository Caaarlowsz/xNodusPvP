package Nodus.Event;

import org.bukkit.plugin.*;
import java.util.*;
import org.bukkit.event.player.*;
import Nodus.Warp.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import Nodus.Main.*;
import n.*;

public class DeathEvent implements Listener
{
    public Plugin plugin;
    public static List<String> Sair;
    public static List<String> combate;
    
    static {
        DeathEvent.Sair = new ArrayList<String>();
        DeathEvent.combate = new ArrayList<String>();
    }
    
    public DeathEvent(final Main plugin) {
        this.plugin = (Plugin)plugin;
    }
    
    @EventHandler
    public void onDeathPlayer(final PlayerRespawnEvent e) {
        final Player p = e.getPlayer();
        SpawnTeleport.TpSpawn(p);
    }
    
    @EventHandler
    public void Morrer(final PlayerDeathEvent e) {
        if (e.getEntity().getKiller() instanceof Player) {
            final Player p = e.getEntity();
            e.setDeathMessage((String)null);
            e.getDrops().clear();
            final Player matou = e.getEntity().getKiller();
            final Player morreu = e.getEntity().getPlayer();
            final int bv = Main.dinheirorandom(60, 80);
            final int cv = Main.dinheirorandom(140, 180);
            morreu.setExp(0.0f);
            matou.sendMessage("§63+ " + cv + " §f§lCoins por matar:§9§l " + morreu.getDisplayName() + " §7(§3§l" + Habilidade.getAbility(morreu) + "§7)");
            morreu.sendMessage("§7Morto por: §9§l" + matou.getDisplayName() + "§7 (§9§l" + Habilidade.getAbility(matou) + "§7)");
            matou.giveExpLevels(1);
            Main.economy.depositPlayer(matou.getName(), (double)cv);
            Main.economy.bankWithdraw(e.getEntity().getName(), (double)bv);
        }
        else {
            e.getEntity().sendMessage(d.get("MensagemMorrerSemSerMorto"));
            e.setDeathMessage((String)null);
            e.setDroppedExp(0);
        }
    }
}
