/* _____           _        _   __  __ _                   __  __                                   _____                              
 *|  __ \         | |      | | |  \/  (_)                 |  \/  |                                 / ____|                             
 *| |__) |__   ___| | _____| |_| \  / |_ _ __   ___ ______| \  / | __ _ _ __   __ _  __ _  ___ _ _| (___   ___ _ ____   _____ _ __ ___ 
 *|  ___/ _ \ / __| |/ / _ \ __| |\/| | | '_ \ / _ \______| |\/| |/ _` | '_ \ / _` |/ _` |/ _ \ '__\___ \ / _ \ '__\ \ / / _ \ '__/ __|
 *| |  | (_) | (__|   <  __/ |_| |  | | | | | |  __/      | |  | | (_| | | | | (_| | (_| |  __/ |  ____) |  __/ |   \ V /  __/ |  \__ \
 *|_|   \___/ \___|_|\_\___|\__|_|  |_|_|_| |_|\___|      |_|  |_|\__,_|_| |_|\__,_|\__, |\___|_| |_____/ \___|_|    \_/ \___|_|  |___/
 *                                                                                   __/ |                                             
 *                                                                                  |___/                                              
 *Copyright (C) 2015-2016 @author matcracker
 *
 *This program is free software: you can redistribute it and/or modify 
 *it under the terms of the GNU Lesser General Public License as published by 
 *the Free Software Foundation, either version 3 of the License, or 
 *(at your option) any later version.
*/

package com.matcracker.PMManagerServers.utility;

public class PocketMineAPI{
	
	public final String[] blockEvents = new String[]{
		"BlockBreakEvent",
		"BlockEvent",
		"BlockFormEvent",
		"BlockGrowEvent",
		"BlockPlaceEvent",
		"BlockSpreadEvent",
		"BlockUpdateEvent",
		"LeavesDecayEvent",
		"SignChangeEvent"
	};
	
	public final String[] entityEvents = new String[]{
		"EntityArmorChangeEvent",
		"EntityBlockChangeEvent",
		"EntityCombustByBlockEvent",
		"EntityCombustByEntityEvent",
		"EntityCombustEvent",
		"EntityDamageByBlockEvent",
		"EntityDamageByChildEntityEvent",
		"EntityDamageByEntityEvent",
		"EntityDamageEvent",
		"EntityDeathEvent",
		"EntityDespawnEvent",
		"EntityEvent",
		"EntityExplodeEvent",
		"EntityInventoryChangeEvent",
		"EntityLevelChangeEvent",
		"EntityMotionEvent",
		"EntityMoveEvent",
		"EntityRegainHealthEvent",
		"EntityShootBowEvent",
		"EntitySpawnEvent",
		"EntityTeleportEvent",
		"ExplosionPrimeEvent",
		"ItemDespawnEvent",
		"ProjectileHitEvent",
		"ProjectileLaunchEvent"
	};
	
	public final String[] inventoryEvents = new String[]{
		"CraftItemEvent",
		"FurnaceBurnEvent",
		"FurnaceSmeltEvent",
		"InventoryCloseEvent",
		"InventoryEvent",
		"InventoryOpenEvent",
		"InventoryPickupArrowEvent",
		"InventoryPickupItemEvent",
		"InventoryTransactionEvent"
	};
	
	public final String[] levelEvents = new String[]{
		"ChunkEvent",
		"ChunkLoadEvent",
		"ChunkPopulateEvent",
		"ChunkUnloadEvent",
		"LevelEvent",
		"LevelInitEvent",
		"LevelLoadEvent",
		"LevelSaveEvent",
		"LevelUnloadEvent",
		"SpawnChangeEvent"
	};

	public final String[] playerEvents = new String[]{
		"PlayerAchievementAwardedEvent",
		"PlayerAnimationEvent",
		"PlayerBedEnterEvent",
		"PlayerBedLeaveEvent",
		"PlayerBucketEmptyEvent",
		"PlayerBucketEvent",
		"PlayerBucketFillEvent",
		"PlayerChatEvent",
		"PlayerCommandPreprocessEvent",
		"PlayerDeathEvent",
		"PlayerDropItemEvent",
		"PlayerEvent",
		"PlayerGameModeChangeEvent",
		"PlayerInteractEvent",
		"PlayerItemConsumeEvent",
		"PlayerItemHeldEvent",
		"PlayerJoinEvent",
		"PlayerKickEvent",
		"PlayerLoginEvent",
		"PlayerMoveEvent",
		"PlayerPreLoginEvent",
		"PlayerQuitEvent",
		"PlayerRespawnEvent"
	};
	
	public final String[] pluginEvents = new String[]{
		"PluginDisableEvent",
		"PluginEnableEvent",
		"PluginEvent"
	};
	
	/**
	 * 
	 * @param index
	 * @param events
	 * @return event name
	 */
	public String getEvent(int index, String[] events){
		return events[index];
	}
	
	/**
	 * @param event this reduce an event in short way (BlockBreakEvent -> onBlockBreak)
	 * @return reduced event
	 */
	public String getReducedEvents(String event){
		String edited = event;
		edited = edited.replaceAll("Event", "");
		edited = "on" + edited;
		return edited;
	}
	
	/** 
	 * @param event
	 * @return boolean array
	 */
	public boolean[] filterParam(String event){
		boolean[] denied = new boolean[]{false, false, false, false, false, false, false, false, true, true, true, false};
		String ev = event.toLowerCase();
		
		if(ev.contains("block") || ev.contains("leaves") || ev.contains("signchange")){
			denied[0] = true;
			if(ev.contains("break") || ev.contains("place") || ev.contains("sign"))
				denied[5] = true;
			if(ev.contains("break") || ev.contains("place"))
				denied[7] = true;
		}
		
		if(ev.contains("entity") || ev.contains("item") || ev.contains("projectile")){
			denied[1] = true;
			if(ev.contains("block"))
				denied[0] = true;
		}
		
		if(ev.contains("inventory") || ev.contains("furnace") || ev.contains("craft")){
			denied[2] = true;
			denied[3] = true;
			
			if(ev.contains("smelt") || ev.contains("burn"))
				denied[0] = true;
			if(ev.contains("open") || ev.contains("close"))
				denied[5] = true;
			if(ev.contains("pickupitem"))
				denied[7] = true;
		}
		
		if(ev.contains("level") || ev.contains("chunk") || ev.contains("spawn"))
			denied[4] = true;
		
		if(ev.contains("player")){
			denied[5] = true;
			
			if(ev.contains("bucket") || ev.contains("dropitem") || ev.contains("interact") || ev.contains("item"))
				denied[7] = true;
			
			if(ev.contains("death"))
				denied[1] = true;
			
			if(ev.contains("interact"))
				denied[0] = true;
		}
		
		if(ev.contains("plugin"))
			denied[6] = true;
		
		return denied;
	}

	public enum CommandsParameter{
		GET_SENDER("getSender()"),
		SEND_MESSAGE("sendMessage(message)"),
		GET_PLAYER("getPlayer()"),
		GET_COMMAND("getCommand()"),
		NOTHING("");
		private String name;
		
		CommandsParameter(String type){
			this.name = type;
		}
		
		public String getName(){
			return this.name;
		}
	}
	
	public enum EventsParameter{
		GET_BLOCK("getBlock()"), //BlockEvent 0
		GET_ENTITY("getEntity()"), //EntityEvent 1
		GET_INVENTORY("getInvetory()"), //InventoryEvent 2
		GET_VIEWERS("getViewers()"), //InventoryEvent 3
		GET_LEVEL("getLevel()"), //LevelEvent 4 
		GET_PLAYER("getPlayer()"), //PlayerEvent 5
		GET_PLUGIN("getPlugin()"), //PluginEvent 6
		GET_ITEM("getItem()"), //Used 7 
		SET_CANCELLED("setCancelled(true)"), //All events 8
		GET_EVENTNAME("getEventName()"), //All events 9
		GET_HANDLERS("getHandlers()"), //All events 10
		NOTHING(""); //11
		
		private String name;
		
		EventsParameter(String type){
			this.name = type;
		}
		
		public String getName(){
			return this.name;
		}
	}
}