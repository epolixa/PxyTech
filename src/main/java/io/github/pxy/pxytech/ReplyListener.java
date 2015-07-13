package io.github.pxy.pxytech;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ReplyListener implements Listener
{
	private final Reply reply;

	public ReplyListener(Reply reply)
	{
		this.reply = reply;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		reply.setReply(event.getPlayer().getUniqueId(), event.getPlayer().getName());
	}
	
	@EventHandler
	public void onPlayerCommand(PlayerCommandPreprocessEvent event)
	{
		String[] cmd = event.getMessage().split(" ");
		event.getPlayer().sendMessage(event.getMessage());
		if (cmd.length >= 3) {
			if (cmd[0].equals("/tell"))
			{
				reply.setReply(event.getPlayer().getUniqueId(), cmd[1]);
			}
		}
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		reply.removeReply(event.getPlayer().getUniqueId());
	}

	@EventHandler
	public void onPlayerKicked(PlayerKickEvent event)
	{
		reply.removeReply(event.getPlayer().getUniqueId());
	}

}
