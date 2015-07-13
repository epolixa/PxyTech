package io.github.pxy.pxytech;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class ReplyCommandExecutor implements CommandExecutor {
	private final Reply reply;

	public ReplyCommandExecutor(Reply reply) {
		this.reply = reply;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("reply") && args.length >= 0) {
			if (!(sender instanceof Player)) 
			{
				sender.sendMessage("'/reply' can only be used by a player.");
				return true;
			}

			if (reply.getReply(((Player)sender).getUniqueId()).equals(((Player)sender).getName())) 
			{
				sender.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "There was no one to reply to.");
				return true;
			}
			
			reply.getPlugin().getServer().dispatchCommand(sender, "/tell " + reply.getReply(((Player)sender).getUniqueId()) + args.toString());
			return true;
		}
		return false;
	}
}
