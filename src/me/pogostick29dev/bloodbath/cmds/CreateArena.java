package me.pogostick29dev.bloodbath.cmds;

import com.sk89q.worldedit.bukkit.selections.Selection;
import me.pogostick29dev.bloodbath.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@CommandInfo(description = "Create an arena.", usage = "<name>", aliases = { "createarena", "ca" }, op = true)
public class CreateArena extends GameCommand {

	public void onCommand(Player p, String[] args) {
		if (args.length == 0) {
			p.sendMessage(ChatColor.RED + "You must specify a name for the arena.");
			return;
		}
		
		String name = args[0];
		
		if (ArenaManager.getInstance().getArena(name) != null) {
			p.sendMessage(ChatColor.RED + "An arena with that name already exists.");
			return;
		}
		
		Selection s = Main.getWorldEdit().getSelection(p);
		
		if (s == null) {
			p.sendMessage(ChatColor.RED + "You must make a WorldEdit selection of the arena.");
			return;
		}
		
		SettingsManager.getArenas().set(name + ".world", s.getWorld().getName());
		
		Main.saveLocation(s.getMinimumPoint(), SettingsManager.getArenas().createSection(name + ".cornerA"));
		Main.saveLocation(s.getMaximumPoint(), SettingsManager.getArenas().createSection(name + ".cornerB"));
		
		SettingsManager.getArenas().save();

        ArenaManager.getInstance().setup();
		p.sendMessage(ChatColor.GREEN + "Created arena " + name + ". Now you must set up the spawns.");
	}
}