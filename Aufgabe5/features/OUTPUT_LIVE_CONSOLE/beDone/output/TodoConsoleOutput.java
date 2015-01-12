package beDone.output;

import java.util.ArrayList;

import beDone.core.TodoEntry;
import beDone.core.TodoList;
import beDone.core.addons.TodoEntryAddonInterface;
import beDone.helper.ArrayStringConverter;
import beDone.helper.ConsoleIO;

public class TodoConsoleOutput implements TodoOutputInterface {

	protected TodoList todolist = null;
	public boolean has_delete;
	public boolean has_edit_status;
	
	public void setTodoList(TodoList t) {
		this.todolist = t;
	}

	public void callOutput(){

		ConsoleIO c = ConsoleIO.getInstance();
		
		ArrayList<String> out = new ArrayList<String>();
		out.add("#");
		out.add("Status");
		out.add("Titel");
		for(TodoEntryAddonInterface a: new TodoEntry().getAddons()){
			out.add(a.getName());
		}
		out.add("Beschreibung");
		
		String[] a = {};
		c.writeTable(out.toArray(a));
		
		int i = 1;
		for(TodoEntry t : this.todolist.getTodos()){
			out.clear();
			out.add("" + i++);
			out.add(t.getStatus().toString());
			out.add(t.getTitle());
			for(TodoEntryAddonInterface a1: t.getAddons()){
				out.add( ArrayStringConverter.implode(a1.getValues(), ", "));
			}
			out.add(t.getText());
			
			c.writeTable(out.toArray(a));
		}
		c.writeNewPage();
	}
	
	public void callOutputDelete() {
	}
	public void callOutputStatus() {
	}
	
	public boolean hasDelete(){
		return this.has_delete;
	}
	
	public boolean hasEditStatus(){
		return this.has_edit_status;
	}


}
