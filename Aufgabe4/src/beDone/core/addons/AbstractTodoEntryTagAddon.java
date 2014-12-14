package beDone.core.addons;

public abstract class AbstractTodoEntryTagAddon implements
		TodoEntryAddonInterface, Cloneable {

	public AbstractTodoEntryTagAddon clone() throws CloneNotSupportedException{
		return (AbstractTodoEntryTagAddon) super.clone();
	}
	
}
