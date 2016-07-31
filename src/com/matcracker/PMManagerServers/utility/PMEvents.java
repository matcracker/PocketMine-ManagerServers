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

public class PMEvents{
	
	public final String[] blockEvents = new String[]{
		"BlockBreakEvent",
		"BlockEvent",
		"BlockFormEvent",
		"BlockGrowEvent",
		"BlockPlaceEvent",
		"BlockSpreadEvent",
		"BlockUpdateEvent",
		"LeavesDecayEvent"
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
		"ProjectileLaunchEvent",
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
		"InventoryTransactionEvent",
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
	
	protected Parameter toParameter(int type){	
		switch(type+1){
			case 1:
				return Parameter.GET_PLAYER;
			case 2:
				return Parameter.GET_BLOCK;
		default:
			return Parameter.NOTHING;
		}
	}
	
	public enum Parameter{
		NOTHING(""),
		GET_PLAYER("getPlayer"),
		GET_BLOCK("getBlock");
		
		private String name;
		
		Parameter(String type){
			this.name = type;
		}
		
		public String getName(){
			return this.name;
		}
	}
}