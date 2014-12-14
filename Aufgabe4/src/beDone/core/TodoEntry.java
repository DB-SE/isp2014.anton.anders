package beDone.core;

import java.util.UUID;
import java.util.Vector;

import beDone.core.addons.AbstractTodoEntryTagAddon;
import beDone.core.addons.TodoEntryAddonInterface;

public class TodoEntry implements Cloneable{
	
	
	protected String id 	= "";
	protected String title	= "";
	protected String text	= "";
	protected String status = Status.NEW;
	
	protected Vector<TodoEntryAddonInterface> addons;
	
	public TodoEntry() {
		this.addons = new Vector<TodoEntryAddonInterface>();
	}
	
	public void generateID(){
		this.id = UUID.randomUUID().toString();
	}
	
	public void setID(String id){
		this.id = id;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setStatus(String s){
		
		this.status = s;
	}
	
	public String getID(){
		return this.id;
	}
	
	public String getText(){
		return this.text;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getStatus(){
		return this.status;
	}
	
	
	public TodoEntry addAddon(TodoEntryAddonInterface addon){
		this.addons.add(addon);
		return this;
	}
	
	public Vector<TodoEntryAddonInterface> getAddons(){
		return this.addons;
	}
	
	public void setAddons(Vector<TodoEntryAddonInterface> addons){
		this.addons = addons;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected Object clone() throws CloneNotSupportedException {
		 TodoEntry cloned = (TodoEntry)super.clone();
		 Vector<TodoEntryAddonInterface> cloned_addons = (Vector<TodoEntryAddonInterface>) cloned.getAddons().clone();
		 cloned_addons.clear();
		 for(TodoEntryAddonInterface a: cloned.addons){
			 cloned_addons.add( (TodoEntryAddonInterface) ((AbstractTodoEntryTagAddon)a).clone());
		 }
		 cloned.addons = cloned_addons;
		 return cloned;
	}
}
