package me.vik1395.GListHider;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
/*

Author: Vik1395
Project: GList

Copyright 2015

Licensed under Creative CommonsAttribution-ShareAlike 4.0 International Public License (the "License");
You may not use this file except in compliance with the License.

You may obtain a copy of the License at http://creativecommons.org/licenses/by-sa/4.0/legalcode

You may find an abridged version of the License at http://creativecommons.org/licenses/by-sa/4.0/
 */

public class PlayerChatListener implements Listener 
{

    @EventHandler
    public void onPlayerChat(ChatEvent e) 
    {
    	System.out.println("glist \n" + e.getMessage());
        if(e.isCommand())
        {
    		CommandSender cm = (CommandSender) e.getSender();
    		
        	if(e.getMessage().equals("/glist"))
        	{
        		e.setCancelled(true);
        		Main m = new Main();
        		m.execute(cm);
        	}
        	
        	else if(e.getMessage().startsWith("/find"))
        	{
        		e.setCancelled(true);
        		String[] args = null;
        		
        		try
        		{
        			args = e.getMessage().split(" ");
        		}
        		catch(Exception ex)
        		{
        			cm.sendMessage(new TextComponent(ChatColor.RED + "Please follow this command by a user name"));
        			System.out.println();
        		}
        		
        		Main2 m = new Main2();
        		m.execute(cm, args);
        	}
        }
    }
}

