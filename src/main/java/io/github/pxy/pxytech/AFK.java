package io.github.pxy.pxytech;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;



public final class AFK
{
	private final PxyTech plugin;

	private static ScheduledExecutorService executor;
	private static HashMap<UUID, Boolean> afkStatusMap;
	private static HashMap<UUID, AtomicInteger> idleTimeMap;


	public AFK(PxyTech plugin)
	{
		this.plugin = plugin;
	}


	public void start()
	{
		/* Register commands */
		plugin.getCommand("afk").setExecutor(new AFKCommandExecutor(this));

		/* Register event listeners */
		plugin.getServer().getPluginManager().registerEvents(new AFKListener(this), plugin);

		/* Initialize idleTimeCheck executor */
		executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(new AFKIdleTimer(this), 0, 1, TimeUnit.SECONDS);

		/* Initialize HashMaps */
		afkStatusMap = new HashMap<UUID, Boolean>();
		for (Player p : plugin.getServer().getOnlinePlayers())
			setAFKstatus(p.getUniqueId(), false);
		idleTimeMap = new HashMap<UUID, AtomicInteger>();
		for (Player p : plugin.getServer().getOnlinePlayers())
			setIdleTime(p.getUniqueId(), 0);
		
		plugin.getLogger().info("[AFK] Enabled");
	}


	public void stop()
	{
		executor.shutdown();

		for (Player p : plugin.getServer().getOnlinePlayers())
			p.setPlayerListName(p.getName());
		
		plugin.getLogger().info("[AFK] Disabled");
	}

	public PxyTech getPlugin() {return plugin;}

	public boolean getAFKstatus(UUID uuid) {return afkStatusMap.get(uuid);}
	public void setAFKstatus(UUID uuid, boolean status) {afkStatusMap.put(uuid, status);}
	public void removeAFKstatus(UUID uuid) {afkStatusMap.remove(uuid);}

	public int getIdleTime(UUID uuid) {return idleTimeMap.get(uuid).get();}
	public void setIdleTime(UUID uuid, int seconds) {idleTimeMap.put(uuid, new AtomicInteger(seconds));}
	public void removeIdleTime(UUID uuid) {idleTimeMap.remove(uuid);}


	public void goAFK(Player player, String reason)
	{
		if (!afkStatusMap.get(player.getUniqueId()))
		{
			afkStatusMap.put(player.getUniqueId(), true);
			if (!reason.equals(""))
			{Bukkit.broadcastMessage(ChatColor.GRAY + "* " + player.getName() + " is now AFK: \"" + reason + "\"");}
			else {Bukkit.broadcastMessage(ChatColor.GRAY + "* " + player.getName() + " is now AFK.");}
			refreshListName(player);
		}
	}

	public void leaveAFK(Player player)
	{
		if (afkStatusMap.get(player.getUniqueId()))
		{
			afkStatusMap.put(player.getUniqueId(), false);
			Bukkit.broadcastMessage(ChatColor.GRAY + "* " + player.getName() + " is no longer AFK.");
			refreshListName(player);
		}
	}

	public void refreshListName(Player player)
	{
		if (afkStatusMap.get(player.getUniqueId()))
		{
			player.setPlayerListName(ChatColor.GRAY + player.getName() + " (AFK)");
		} else
		{
			player.setPlayerListName(player.getName());
		}
	}

	public void refreshListNames()
	{
		for (Player p : plugin.getServer().getOnlinePlayers())
			refreshListName(p);
	}
}
