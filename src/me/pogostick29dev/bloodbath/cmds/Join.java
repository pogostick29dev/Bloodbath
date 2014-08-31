package me.pogostick29dev.bloodbath.cmds;

import me.pogostick29dev.bloodbath.Arena;
import me.pogostick29dev.bloodbath.ArenaManager;
import me.pogostick29dev.bloodbath.CommandInfo;
import me.pogostick29dev.bloodbath.GameCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@CommandInfo(description = "Join a game.", usage = "<arenaName>", aliases = { "join", "j" }, op = false)
public class Join extends GameCommand {

	public void onCommand(Player p, String[] args) {
		if (ArenaManager.getInstance().getArena(p) != null) {
			p.sendMessage(ChatColor.RED + "You are already in a game.");
			return;
		}
		
		if (args.length == 0) {
			p.sendMessage(ChatColor.RED + "You must specify the arena to join.");
			return;
		}
		
		Arena a = ArenaManager.getInstance().getArena(args[0]);
		
		if (a == null) {
			p.sendMessage(ChatColor.RED + "An arena by that name does not exist.");
			return;
		}
		
		a.addPlayer(p);
	}
}