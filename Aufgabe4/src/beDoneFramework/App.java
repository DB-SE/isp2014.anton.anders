package beDoneFramework;

import beDone.core.TodoEntry;
import beDone.core.TodoEntryFactory;
import beDone.core.TodoList;
import beDone.core.addons.TodoEntryTagAddon;
import beDone.input.TodoConsoleInput;
import beDone.input.TodoInputInterface;
import beDone.output.TodoConsoleOutput;
import beDone.output.TodoOutputInterface;
import beDone.router.RouterContainer;
import beDone.router.TodoConsoleRouter;
import beDone.router.TodoRouterInterface;
import beDone.storage.PersistentStorageInterface;
import beDone.storage.PersistentTextStorage;

/**
 * Erste Implementierung der Todo-Applikation mit Laufzeitparametern
 * 
 * Inhalt:
 * 	todo-core
 * 	todo-input
 * 		console			#Feature1 - Eingabe von Todos per Konsole
 * 	todo-storage
 * 		txt-file		#Feature2 - Sichern der Todos in eine Datei oder nur zur Laufzeit verfügar
 * 	todo-output
 * 		console			#Feature3 - Ausgabe der TODOs erlauben oder nicht
 * 		
 * 
 * @author Anton Anders
 *
 */

public class App {
	
	protected PersistentStorageInterface 	storage = null;
	protected TodoInputInterface			input	= null;
	protected TodoOutputInterface			output	= null;
	protected RouterContainer			    router	= null;
	
	protected TodoList	todos = null;
	
	public static void main(String[] args) {
		
		App app = new App();
		
		app.init();
		
		app.router.startAll();
	}
	
	public App(){
		this.todos = new TodoList();
	}
	
	public void init(){
		
		TodoEntry t = new TodoEntry();
		t.addAddon(new TodoEntryTagAddon());
		TodoEntryFactory.setPrototype(t);
		
		/*
		 * Wenn kein Input und Output-Feature gewählt, brauchen wir einen Standard-Router
		 */
		
		this.router = new RouterContainer();
		
		/*
		 * Konsolen-Eingabe-Dialog für Todos
		 * Console-Input => Console-Router
		 */
		this.addConsoleInput();
		
		/*
		 * Ausgabe der ToDos erfolgt auf der Konsole
		 * Console-Output => Console-Router
		 */

		this.addConsoleOutput();
		
		this.setStorage(new PersistentTextStorage());
		
	}
	
	
	private void addConsoleInput(){
		TodoRouterInterface r;
		if((r = this.router.getRouter("TodoConsoleRouter")) == null){
			r = new TodoConsoleRouter();
			this.router.addRouter(r);
		}
		TodoConsoleInput input = new TodoConsoleInput();
		input.setTodoList(this.todos);
		r.setInput(input);
	}
	
	private void addConsoleOutput(){
		TodoRouterInterface r;
		if((r = this.router.getRouter("TodoConsoleRouter")) == null){
			r = new TodoConsoleRouter();
			this.router.addRouter(r);
		}
		TodoConsoleOutput input = new TodoConsoleOutput();
		input.setTodoList(this.todos);
		r.setOutput(input);
	}
	
	private void setStorage(PersistentStorageInterface storage){
		storage.setTodoList(this.todos);
		for(TodoRouterInterface r :this.router.getRouter()){
			r.setStorage(storage);
		}
	}
}
