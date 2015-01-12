package beDone.core;

import java.util.UUID;
import java.util.Vector;

import beDone.core.TodoEntry;
import beDone.core.addons.AbstractTodoEntryTagAddon;
import beDone.core.addons.TodoEntryAddonInterface;

public class TodoEntry implements Cloneable{
	
	
	protected String id 	= "";
	
	protected String status = Status.NEW;
	
	protected Vector<TodoEntryAddonInterface> addons;
	
	/**
	 * Wird per original() erweitert um addons zu laden
	 */
	public TodoEntry() {
		this.addons = new Vector<TodoEntryAddonInterface>();
		this.init();
	}
	
	public void init(){
		
	}
	
	public void generateID(){
		this.id = UUID.randomUUID().toString();
	}
	
	public void setID(String id){
		this.id = id;
	}
	
	public void setStatus(String s){
		
		this.status = s;
	}
	
	public String getID(){
		return this.id;
	}
	
	public String getStatus(){
		return this.status;
	}
	
	public Vector<TodoEntryAddonInterface> getAddons(){
		return this.addons;
	}
}
