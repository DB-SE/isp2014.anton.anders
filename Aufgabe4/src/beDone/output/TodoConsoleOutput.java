package beDone.output;

import java.util.ArrayList;

import beDone.core.TodoEntry;
import beDone.core.TodoEntryFactory;
import beDone.core.TodoList;
import beDone.core.addons.TodoEntryAddonInterface;
import beDone.helper.ArrayStringConverter;
import beDone.helper.ConsoleIO;

public class TodoConsoleOutput implements TodoOutputInterface {

	protected TodoList todolist = null;
	
	public void setTodoList(TodoList t) {
		this.todolist = t;
	}

	public void callOutput() {
		ConsoleIO c = ConsoleIO.getInstance();
		
		ArrayList<String> out = new ArrayList<String>();
		out.add("#");
		out.add("Status");
		out.add("Titel");
		for(TodoEntryAddonInterface a: TodoEntryFactory.newInstance().getAddons()){
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
		this.callOutput();
		
		System.out.println("Geben Sie die Zahl des eintrages ein, der gelöscht werden soll: ");
		System.out.println("(Alle ungültigen Zahlen, z.B. 0 oder -1, bedeuten Abbruch)");
		int i = ConsoleIO.getInstance().readInt();
		//System.out.println(i);
		//System.out.println(this.todolist.getTodos().size());
		int c = 1;
		
		if( i < 1 || i > this.todolist.getTodos().size() ){
			return;
		}
		
		for(TodoEntry t : this.todolist.getTodos()){
			//System.out.println(t.getID());
			if(c++ == i){
				this.todolist.deleteTodo(t.getID());
				System.out.println("Eintrag '"+t.getTitle() + "' gelöscht");
				return;
			}
		}
	}

}
