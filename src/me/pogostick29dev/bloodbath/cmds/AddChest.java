package me.pogostick29dev.bloodbath.cmds;

import me.pogostick29dev.bloodbath.*;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

@CommandInfo(description = "Add a chest to an arena.", usage = "<arenaName> <tier>", aliases = { "addchest", "ac" }, op = true)
public class AddChest extends GameCommand {

	@Override
	public void onCommand(Player p, String[] args) {
		if (args.length < 2) {
			p.sendMessage(ChatColor.RED + "You must specify the arena to which the chest will be added and the tier of the chest.");
			return;
		}
		
		Arena a = ArenaManager.getInstance().getArena(args[0]);
		
		if (a == null) {
			p.sendMessage(ChatColor.RED + "An arena with that name does not exist.");
			return;
		}
		
		int tier;
		
		try {
			tier = Integer.parseInt(args[1]);
		}
		
		catch (Exception e) {
			p.sendMessage(ChatColor.RED + "That is not a number!");
			return;
		}
		
		if (tier != 1 && tier != 2) {
			p.sendMessage(ChatColor.RED + "That is an invalid tier! Valid tiers are 1 and 2");
			return;
		}
		
		@SuppressWarnings("deprecation")
		Block target = p.getTargetBlock(null, 10);
		
		if (target == null) {
			p.sendMessage(ChatColor.RED + "You are not looking at a block.");
			return;
		}
		
		if (!(target.getState() instanceof Chest)) {
			p.sendMessage(ChatColor.RED + "You are not looking at a chest.");
			return;
		}
		
		Chest chest = (Chest) target.getState();
		
		if (!a.getBounds().contains(chest.getLocation())) {
			p.sendMessage(ChatColor.RED + "That chest is outside of the bounds of the arena.");
			return;
		}
		
		a.addChest(chest, tier);
		
		if (!SettingsManager.getArenas().contains(a.getID() + ".chests")) {
			SettingsManager.getArenas().createSection(a.getID() + ".chests");
		}
		
		new GameChest(chest, tier).save(SettingsManager.getArenas().createSection(a.getID() + ".chests." + SettingsManager.getArenas().<ConfigurationSection>get(a.getID() + ".chests").getKeys(false).size()));
		SettingsManager.getArenas().save();
		
		p.sendMessage(ChatColor.GREEN + "Added chest.");
	}
}