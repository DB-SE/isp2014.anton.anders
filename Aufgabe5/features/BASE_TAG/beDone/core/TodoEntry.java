package beDone.core;

import beDone.core.addons.*;
/**
 * TODO description
 */
public class TodoEntry {
	
	
	public void init(){
		original();
		this.addons.add(new TodoEntryTagAddon());
	}
}