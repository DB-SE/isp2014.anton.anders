package beDone.output;

import java.util.ArrayList;

import beDone.core.Status;
import beDone.core.TodoEntry;
import beDone.core.TodoList;
import beDone.core.addons.TodoEntryAddonInterface;
import beDone.helper.ArrayStringConverter;
import beDone.helper.ConsoleIO;

public class TodoConsoleOutput implements TodoOutputInterface {
	
	public boolean has_edit_status = true;
	
	public void callOutputStatus() {
		
		this.callOutput();
		
		System.out.println("Geben Sie die Zahl des Eintrages ein, dessen Status verändert werden soll: ");
		System.out.println("(Alle ungültigen Zahlen, z.B. 0 oder -1, bedeuten Abbruch)");
		int i = ConsoleIO.getInstance().readInt();
		//System.out.println(i);
		//System.out.println(this.todolist.getTodos().size());
		int c = 1;
		
		if( i < 1 || i > this.todolist.getTodos().size() ){
			return;
		}
		System.out.println("Neuer Status: ");
		System.out.print("Status (Neu = 1, Offen = 2, Erledigt = 3):");

		String status = this.getTodoStatus(ConsoleIO.getInstance().readInt()) ;
		
		for(TodoEntry t : this.todolist.getTodos()){
			//System.out.println(t.getID());
			if(c++ == i){
				t.setStatus(status);
				System.out.println("Eintrag '"+t.getTitle() + "' wurde verändert.");
				return;
			}
		}
	}
	private String getTodoStatus(int in){
		switch(in){
		case 1: return Status.NEW;
		case 2: return Status.OPEN;
		case 3: return Status.DONE;
		default: return Status.NEW;
		}
	}
}
