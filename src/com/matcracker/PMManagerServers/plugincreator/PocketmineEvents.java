package com.matcracker.PMManagerServers.plugincreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.matcracker.PMManagerServers.plugincreator.PocketMineAPI.EventsParameter;
import com.matcracker.PMManagerServers.utility.Utility;

public class PocketmineEvents{
	protected PocketMineAPI pmev = new PocketMineAPI();
	
	/**
	 * Events
	 */
	public List<String> events = new ArrayList<>();
	private String event_temp = "";
	private String context = "";;
	private String final_context_events = "";
	private boolean eventSetted = false;

	/**
	 * Other
	 */
	private String variable;
	
	
	/**
	 * @return instance of PocketMineEvents
	 */
	public PocketMineAPI getPocketMineEvents(){
		return pmev;
	}
	
	/**
	 * @param listener
	 */
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
		
		PocketminePluginCreator.addImport("event\\" + adjustEventImport(event) + "\\" + event);
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
	
	/**
	 * Save the event
	 */
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
		for(String event : events)
			 s += event;
		return s;
	}
	
	private void mergeEvents(){
		if(events.isEmpty()) return;
		
		for(String event : events)
			final_context_events += event;
	}
	
	public void sendDataToCreator(){
		mergeEvents();
		PocketminePluginCreator.final_events = final_context_events;
	}
	
	/**
	 * @param type of event (Example: getPlayer is type 5, getBlock is type 0). Check type in enum {@link EventsParameter}
	 * @param content it's what the event do.
	 */
	public void addEventContext(int type, boolean custom, String content){
		String cont = "";
				
		if(custom)
			cont += "\n\t\t" + content + "\n";
		else{
			cont += "\n\t\t";
		
			if(type != 8)
				cont +=	variable + " = $event->" + EventsParameter.values()[type].getName() + ";\n";
			else
				cont +=	"$event->" + EventsParameter.values()[type].getName() + ";\n";
		}
		
		this.context += cont;
	}
	
	public void setVariable(String variable){

		if(Utility.is_numeric(variable)) return;

		if(variable == null || variable.equalsIgnoreCase(""))
			variable = "vb" + new Random().nextInt(20);
		
		if(!variable.startsWith("$"))
			variable = "$" + variable;
		
		this.variable = variable;
	}
	
	public String getVariable(){
		return variable;
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
