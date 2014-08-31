package me.pogostick29dev.bloodbath.cmds;

import me.pogostick29dev.bloodbath.Arena;
import me.pogostick29dev.bloodbath.ArenaManager;
import me.pogostick29dev.bloodbath.CommandInfo;
import me.pogostick29dev.bloodbath.GameCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@CommandInfo(description = "Leave a game.", usage = "", aliases = { "leave", "l" }, op = false)
public class Leave extends GameCommand {

	public void onCommand(Player p, String[] args) {
		Arena a = ArenaManager.getInstance().getArena(p);
		
		if (a == null) {
			p.sendMessage(ChatColor.RED + "You are not in a game.");
			return;
		}
		
		a.removePlayer(p);
	}
}