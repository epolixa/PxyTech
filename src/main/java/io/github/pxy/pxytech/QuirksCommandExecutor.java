package io.github.pxy.pxytech;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class QuirksCommandExecutor implements CommandExecutor {
	private final Quirks quirks;

	public QuirksCommandExecutor(Quirks quirks) {
		this.quirks = quirks;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("quirks")) {
			sender.sendMessage(" - " + ChatColor.YELLOW + "Amped Up" + ChatColor.RESET + ": amplified terrain");
			sender.sendMessage(" - " + ChatColor.GREEN + "Vegetarian" + ChatColor.RESET + ": eating meat causes indigestion");
			sender.sendMessage(" - " + ChatColor.WHITE + "Spoiled Milk" + ChatColor.RESET + ": drinking milk won't reset potion effects");
			sender.sendMessage(" - " + ChatColor.DARK_GRAY + "Leonty" + ChatColor.RESET + ": Leonty");
			return true;
		}
		return false;
	}

}
