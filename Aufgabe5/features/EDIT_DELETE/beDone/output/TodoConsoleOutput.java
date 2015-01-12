package beDone.output;

import java.util.ArrayList;

import beDone.core.TodoEntry;
import beDone.core.TodoList;
import beDone.core.addons.TodoEntryAddonInterface;
import beDone.helper.ArrayStringConverter;
import beDone.helper.ConsoleIO;

public class TodoConsoleOutput implements TodoOutputInterface {

	public boolean has_delete = true;
	
	
	
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
