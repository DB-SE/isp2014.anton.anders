package beDone.core; 

import java.util.UUID; 
import java.util.Vector; 

import beDone.core.TodoEntry; 
import beDone.core.addons.AbstractTodoEntryTagAddon; 
import beDone.core.addons.TodoEntryAddonInterface; 

import beDone.core.addons.*; 

/**
 * TODO description
 */
public   class  TodoEntry  implements Cloneable {
	
	
	
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

	
	
	 private void  init__wrappee__BASE  (){
		
	}

	
	
	
	public void init(){
		init__wrappee__BASE();
		this.addons.add(new TodoEntryTagAddon());
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

	
	protected String title	= "";

	
	protected String text	= "";

	
	
	public void setText(String text){
		this.text = text;
	}

	
	
	public void setTitle(String title){
		this.title = title;
	}

	
	
	
	public String getText(){
		return this.text;
	}

	
	
	public String getTitle(){
		return this.title;
	}


}
