package me.pogostick29dev.bloodbath.cmds;

import me.pogostick29dev.bloodbath.Arena;
import me.pogostick29dev.bloodbath.ArenaManager;
import me.pogostick29dev.bloodbath.CommandInfo;
import me.pogostick29dev.bloodbath.GameCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * @author Matthijs Logemann
 * @version 1.0
 * @since 01-09-14
 */
@CommandInfo(description = "Starts a game.", usage = "<arenaName>", aliases = { "start", "s" }, op = true)
public class ForceStart extends GameCommand {

    public void onCommand(Player p, String[] args) {

        if (args.length == 0) {
            p.sendMessage(ChatColor.RED + "You must specify the arena to start.");
            return;
        }

        Arena a = ArenaManager.getInstance().getArena(args[0]);

        if (a == null) {
            p.sendMessage(ChatColor.RED + "An arena by that name does not exist.");
            return;
        }

        a.start();
    }
}