package me.pogostick29dev.bloodbath.cmds;

import me.pogostick29dev.bloodbath.*;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

@CommandInfo(description = "Add a lobby sign.", usage = "<arenaName>", aliases = { "addlobbysign", "addsign", "als" }, op = true)
public class AddLobbySign extends GameCommand {

	@Override
	public void onCommand(Player p, String[] args) {
		if (args.length == 0) {
			p.sendMessage(ChatColor.RED + "You must specify the arena to which you want to bind this sign.");
			return;
		}
		
		Arena a = ArenaManager.getInstance().getArena(args[0]);
		
		if (a == null) {
			p.sendMessage(ChatColor.RED + "An arena with that name does not exist.");
			return;
		}
		
		@SuppressWarnings("deprecation")
		Block target = p.getTargetBlock(null, 10);
		
		if (target == null) {
			p.sendMessage(ChatColor.RED + "You are not looking at a block.");
			return;
		}
		
		if (!(target.getState() instanceof Sign)) {
			p.sendMessage(ChatColor.RED + "You are not looking at a sign.");
			return;
		}
		
		new LobbySign(target.getLocation(), a).save(SettingsManager.getSigns().createSection(String.valueOf(SettingsManager.getSigns().getKeys().size())));
		SettingsManager.getSigns().save();
		
		// New sign would not be in SignManager.
		
		p.sendMessage(ChatColor.GREEN + "Added lobby sign.");
	}
}