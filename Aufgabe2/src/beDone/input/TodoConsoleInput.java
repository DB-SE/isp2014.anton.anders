package beDone.input;

import beDone.core.TodoEntry;
import beDone.core.TodoList;
import beDone.helper.ConsoleIO;

public class TodoConsoleInput implements TodoInputInterface {

	private TodoList todos;
	
	public void setTodoList(TodoList t) {
		this.todos = t;
	}
	
	public void callDialog() {
		TodoEntry t = new TodoEntry();
		ConsoleIO c = ConsoleIO.getInstance();
		
		System.out.println("Eingabe eines neuen TODO-Eintrages:\n\n");
		System.out.print("Titel:");
		t.setTitle(c.readLine());
		
		System.out.print("Beschreibung:");
		
		t.setText(c.readLine());
		
		System.out.print("Status (Neu = 1, Offen = 2, Erledigt = 3):");

		t.setStatus( this.getTodoStatus(c.readInt()) );
		
		t.generateID();
		this.todos.addTodo(t);
		System.out.println("Eintrag gespeichert.\n\n");
		c.writeNewPage();
		
	}

	
	private TodoEntry.Status getTodoStatus(int in){
		switch(in){
		case 1: return TodoEntry.Status.NEW;
		case 2: return TodoEntry.Status.OPEN;
		case 3: return TodoEntry.Status.DONE;
		default: return TodoEntry.Status.NEW;
		}
	}
	
}
