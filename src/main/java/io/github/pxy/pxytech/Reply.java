package io.github.pxy.pxytech;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

public final class Reply {
	private final PxyTech plugin;

	private static HashMap<UUID, String> replyMap;

	public Reply(PxyTech plugin) {
		this.plugin = plugin;
	}

	public void start() {
		/* Register commands */
		plugin.getCommand("reply").setExecutor(new ReplyCommandExecutor(this));

		/* Initialize HashMap */
		replyMap = new HashMap<UUID, String>();
		for (Player p : plugin.getServer().getOnlinePlayers())
			replyMap.put(p.getUniqueId(), p.getName());

		plugin.getLogger().info("[Reply] Enabled");
	}

	public void stop() {

		plugin.getLogger().info("[Reply] Disabled");
	}

	public PxyTech getPlugin() {
		return plugin;
	}

	public String getReply(UUID player) {
		return replyMap.get(player);
	}

	public void setReply(UUID player, String recipient) {
		replyMap.put(player, recipient);
	}

	public void removeReply(UUID player) {
		for (Player p : plugin.getServer().getOnlinePlayers())
			if (getReply(p.getUniqueId()) == plugin.getServer().getPlayer(player).getName())
				setReply(p.getUniqueId(), p.getName());
		replyMap.remove(player);
	}
}
