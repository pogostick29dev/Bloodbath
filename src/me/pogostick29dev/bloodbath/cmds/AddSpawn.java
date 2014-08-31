package me.pogostick29dev.bloodbath.cmds;

import me.pogostick29dev.bloodbath.*;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

@CommandInfo(description = "Add a spawn to an arena.", usage = "<arenaName>", aliases = { "addspawn", "as" }, op = true)
public class AddSpawn extends GameCommand {

	@Override
	public void onCommand(Player p, String[] args) {
		if (args.length == 0) {
			p.sendMessage(ChatColor.RED + "You must specify the arena to which the spawn will be added.");
			return;
		}
		
		Arena a = ArenaManager.getInstance().getArena(args[0]);
		
		if (a == null) {
			p.sendMessage(ChatColor.RED + "An arena with that name does not exist.");
			return;
		}
		
		if (!a.getBounds().contains(p.getLocation())) {
			p.sendMessage(ChatColor.RED + "That location is outside of the bounds of the arena.");
			return;
		}
		
		a.addSpawn(p.getLocation());
		
		if (!SettingsManager.getArenas().contains(a.getID() + ".spawns")) {
			SettingsManager.getArenas().createSection(a.getID() + ".spawns");
		}
		
		Main.saveLocation(p.getLocation(), SettingsManager.getArenas().createSection(a.getID() + ".spawns" + "." + SettingsManager.getArenas().<ConfigurationSection>get(a.getID() + ".spawns").getKeys(false).size()));
		SettingsManager.getArenas().save();
		
		p.sendMessage(ChatColor.GREEN + "Added spawn.");
	}
}