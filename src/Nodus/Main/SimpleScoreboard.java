package Nodus.Main;

import org.bukkit.event.*;
import com.google.common.collect.*;
import com.google.common.base.*;
import java.util.*;
import org.bukkit.scoreboard.*;
import org.bukkit.*;
import org.bukkit.entity.*;

public class SimpleScoreboard implements Listener
{
    public Scoreboard scoreboard;
    private String title;
    private Map<String, Integer> scores;
    private List<Team> teams;
    
    public SimpleScoreboard(final String title) {
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.title = title;
        this.scores = (Map<String, Integer>)Maps.newLinkedHashMap();
        this.teams = (List<Team>)Lists.newArrayList();
    }
    
    public void blankLine() {
        this.add(" ");
    }
    
    public void add(final String text) {
        this.add(text, null);
    }
    
    public void add(String text, final Integer score) {
        Preconditions.checkArgument(text.length() < 48, (Object)"text cannot be over 48 characters in length");
        text = this.fixDuplicates(text);
        this.scores.put(text, score);
    }
    
    private String fixDuplicates(String text) {
        while (this.scores.containsKey(text)) {
            text = String.valueOf(text) + "\u00ef¿½r";
        }
        if (text.length() > 48) {
            text = text.substring(0, 47);
        }
        return text;
    }
    
    private Map.Entry<Team, String> createTeam(final String text) {
        String result = "";
        if (text.length() <= 16) {
            return new AbstractMap.SimpleEntry<Team, String>(null, text);
        }
        final Team team = this.scoreboard.registerNewTeam("text-" + this.scoreboard.getTeams().size());
        final Iterator<String> iterator = Splitter.fixedLength(16).split((CharSequence)text).iterator();
        team.setPrefix((String)iterator.next());
        result = iterator.next();
        if (text.length() > 32) {
            team.setSuffix((String)iterator.next());
        }
        this.teams.add(team);
        return new AbstractMap.SimpleEntry<Team, String>(team, result);
    }
    
    public void build() {
        final Objective obj = this.scoreboard.registerNewObjective((this.title.length() > 16) ? this.title.substring(0, 15) : this.title, "dummy");
        obj.setDisplayName(this.title);
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        int index = this.scores.size();
        for (final Map.Entry<String, Integer> text : this.scores.entrySet()) {
            final Map.Entry<Team, String> team = this.createTeam(text.getKey());
            final Integer score = (text.getValue() != null) ? text.getValue() : index;
            final OfflinePlayer player = Bukkit.getOfflinePlayer((String)team.getValue());
            if (team.getKey() != null) {
                team.getKey().addPlayer(player);
            }
            obj.getScore(player).setScore((int)score);
            --index;
        }
    }
    
    public void reset() {
        this.title = null;
        this.scores.clear();
        for (final Team t : this.teams) {
            t.unregister();
        }
        this.teams.clear();
    }
    
    public Scoreboard getScoreboard() {
        return this.scoreboard;
    }
    
    public void send(final Player... players) {
        final Player[] arrayOfPlayer = players;
        for (int j = players.length, i = 0; i < j; ++i) {
            final Player p = arrayOfPlayer[i];
            p.setScoreboard(this.scoreboard);
        }
    }
}
