package me.vik1395.GListHider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.md_5.bungee.Util;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

/*

Author: Vik1395
Project: GListHider

Copyright 2015

Licensed under Creative CommonsAttribution-ShareAlike 4.0 International Public License (the "License");
You may not use this file except in compliance with the License.

You may obtain a copy of the License at http://creativecommons.org/licenses/by-sa/4.0/legalcode

You may find an abridged version of the License at http://creativecommons.org/licenses/by-sa/4.0/
 */

public class Main extends Plugin
{
	@Override
    public void onEnable()
    {
		getProxy().getPluginManager().registerListener(this, new PlayerChatListener());
    }
	
    public void execute(CommandSender sender)
    {
    	if(sender instanceof ProxiedPlayer)
		{
			ProxiedPlayer p = (ProxiedPlayer)sender;
			if(p.hasPermission("glist.normal"))
	        {
				int hplt = 0; //Hidden Players Total
				for (ServerInfo server:ProxyServer.getInstance().getServers().values())
		        {
		            if (!server.canAccess(sender))
		            {
		                continue;
		            }

		            List<String> players = new ArrayList<>();
		            int hpl = 0; //Hidden Players
		            
		            for (ProxiedPlayer player:server.getPlayers())
		            {
		            	if(player.hasPermission("glist.hide"))
		            	{
		            		hpl++;
		            		hplt++;
		            	}
		            	
		            	else
		            	{
		            		players.add(player.getDisplayName());
		            	}
		            }
		            Collections.sort(players, String.CASE_INSENSITIVE_ORDER);
		            
		            sender.sendMessage(new TextComponent(ProxyServer.getInstance().getTranslation("command_list", server.getName(), server.getPlayers().size()-hpl, Util.format(players, ChatColor.RESET + ", "))));
		        }
				sender.sendMessage(new TextComponent(ProxyServer.getInstance().getTranslation("total_players", ProxyServer.getInstance().getOnlineCount()-hplt)));
	        }
			
			else if(p.hasPermission("glist.admin"))
			{
				for (ServerInfo server:ProxyServer.getInstance().getServers().values())
		        {
		            if (!server.canAccess(sender))
		            {
		                continue;
		            }

		            List<String> players = new ArrayList<>();
		            for (ProxiedPlayer player:server.getPlayers())
		            {
		                players.add(player.getDisplayName());
		            }
		            Collections.sort(players, String.CASE_INSENSITIVE_ORDER);
		            
		            sender.sendMessage(new TextComponent(ProxyServer.getInstance().getTranslation("command_list", server.getName(), server.getPlayers().size(), Util.format(players, ChatColor.RESET + ", "))));
		        }
				sender.sendMessage(new TextComponent(ProxyServer.getInstance().getTranslation("total_players", ProxyServer.getInstance().getOnlineCount())));
			}
			else
			{
				p.sendMessage(new TextComponent(ChatColor.RED + "You are not allowed to use this command."));
			}
		}
    	
    	else
    	{
    		for (ServerInfo server:ProxyServer.getInstance().getServers().values())
	        {
	            if (!server.canAccess(sender))
	            {
	                continue;
	            }

	            List<String> players = new ArrayList<>();
	            for (ProxiedPlayer player:server.getPlayers())
	            {
	                players.add(player.getDisplayName());
	            }
	            Collections.sort(players, String.CASE_INSENSITIVE_ORDER);
	            
	            sender.sendMessage(new TextComponent(ProxyServer.getInstance().getTranslation("command_list", server.getName(), server.getPlayers().size(), Util.format(players, ChatColor.RESET + ", "))));
	        }
			sender.sendMessage(new TextComponent(ProxyServer.getInstance().getTranslation("total_players", ProxyServer.getInstance().getOnlineCount())));
    	}
    }
}
