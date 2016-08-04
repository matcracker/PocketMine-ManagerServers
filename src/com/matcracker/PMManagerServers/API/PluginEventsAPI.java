package com.matcracker.PMManagerServers.API;

import java.util.ArrayList;
import java.util.List;

import com.matcracker.PMManagerServers.utility.PocketMineAPI;
import com.matcracker.PMManagerServers.utility.PocketMineAPI.EventsParameter;

public class PluginEventsAPI{
	protected PocketMineAPI pmev = new PocketMineAPI();
	
	protected PluginCreatorAPI api;
	
	public PluginEventsAPI(PluginCreatorAPI api){
		this.api = api;
	}
	
	/**
	 * Events
	 */
	public List<String> events = new ArrayList<>();
	protected String event_temp = "";
	protected String context = "";;
	protected String final_context_events = "";
	private boolean eventSetted = false;

	/**
	 * @return instance of PocketMineEvents
	 */
	public PocketMineAPI getPocketMineEvents(){
		return pmev;
	}
	
	/**
	 * @param listener
	 */
	public void setListener(boolean listener){
		api.listener = listener;
	}
	
	private String oldEvent = "";

	/**
	 * @param event (Example: BlockBreakEvent)
	 */
	public void addEvent(String event){
		String shortEvent = pmev.getReducedEvents(event);
		String code = 
				"\n" +  "\n" +
				"\tpublic function " + shortEvent + "(" + event + " $event){" + "\n" +
				this.context + "\n" +
				"\t}";
		
		if(oldEvent != event)
			event_temp = event_temp + code;
		else
			event_temp = code;
		
		oldEvent = event;
		
		api.addImport("event\\" + adjustEventImport(event) + "\\" + event);
	}
	
	private String adjustEventImport(String event){
		String e = event.toLowerCase();
		String importz = "";
		
		if(e.contains("block") || e.contains("leaves") || e.contains("signchange"))
			importz = "block";
		else if(e.contains("entity") || e.contains("item") || e.contains("projectile"))
			importz = "entity";
		else if(e.contains("inventory") || e.contains("furnace") || e.contains("craft"))
			importz = "inventory";
		else if(e.contains("level") || e.contains("chunk") || e.contains("spawn"))
			importz = "level";
		else if(e.contains("player"))
			importz = "player";
		else if(e.contains("plugin"))
			importz = "plugin";
		
		return importz;
	}
	
	public void saveEvent(){
		this.events.add(event_temp);
		event_temp = "";
		removeEventsContext();
	}
	
	/**
	 * @return
	 */
	public String getEventContent(){
		return this.event_temp;
	}
	
	/**
	 * @return
	 */
	public String getEventsContent(){	
		String s = "";
		for(int i = 0; i < events.size(); i++)
			 s = s + events.get(i);
		return s;
	}
	
	protected void mergeEvents(){
		if(events.isEmpty()) return;
		
		for(int i = 0; i < events.size(); i++)
			final_context_events = final_context_events + events.get(i); 
	}
	
	/**
	 * @param type of event (Example: getPlayer is type 5, getBlock is type 0). Check type in enum Parameter
	 * @param content it's what the event do.
	 */
	public void addEventContext(int type, boolean custom, String content){
		String cont = "";
				
		if(custom)
			cont = cont + "\n\t\t" + content + "\n";
		else{
			cont = cont + "\n\t\t";
		
			if(type != 8)
			cont = cont + 
				api.variable + " = $event->" + EventsParameter.values()[type].getName() + ";" +
				"\n";
			else
				cont = cont +
				"$event->" + EventsParameter.values()[type].getName() + ";" +
				"\n";
		}
		
		this.context = context + cont;
		api.variable = "$param";
	}
	
	/**
	 * @return true if event is setted
	 */
	public boolean isEventSetted() {
		return eventSetted;
	}
	
	/**
	 * @param eventSetted set if the event is finished
	 */
	public void setEventSetted(boolean eventSetted) {
		this.eventSetted = eventSetted;
	}
	
	/**
	 * Delete the current code
	 */
	public void removeEventsContext() {
		this.context = "";
	}
	
}
