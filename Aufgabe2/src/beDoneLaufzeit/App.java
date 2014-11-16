package beDoneLaufzeit;

import beDone.core.TodoList;
import beDone.input.TodoConsoleInput;
import beDone.input.TodoInputInterface;
import beDone.output.TodoConsoleOutput;
import beDone.output.TodoOutputInterface;
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
	protected TodoRouterInterface			router	= null;
	
	protected TodoList	todos = null;
	
	public static void main(String[] args) {
		
		App app = new App();
		
		app.parseParameters(args);
		
		app.router.start();
	}
	
	public App(){
		this.todos = new TodoList();
	}
	
	/**
	 * Parse Arguments and select Features to use
	 * @param args
	 */
	public void parseParameters(String[] args){
		
		/*
		 * Wenn kein Input und Output-Feature gewählt, brauchen wir einen Standard-Router
		 */
		if(this.router == null){
			this.router = new TodoConsoleRouter();
		}
		
		for(String arg: args){
			
			/*
			 * Konsolen-Eingabe-Dialog für Todos
			 * Console-Input => Console-Router
			 */
			if("-finput=console".equals(arg)){
				this.input = new TodoConsoleInput();
				this.input.setTodoList(this.todos);
				
				if(!(this.router instanceof TodoConsoleRouter)){
					this.router = new TodoConsoleRouter();
				}
				
				this.router.setInput(this.input);

			}
			
			/*
			 * Ausgabe der ToDos erfolgt auf der Konsole
			 * Console-Output => Console-Router
			 */
			if("-foutput=console".equals(arg)){
				this.output = new TodoConsoleOutput();
				this.output.setTodoList(this.todos);
				
				if(!(this.router instanceof TodoConsoleRouter)){
					this.router = new TodoConsoleRouter();
				}
				
				this.router.setOutput(this.output);
			}
			
			/*
			 * 
			 */
			if("-fstorage=txt".equals(arg)){
				this.storage = new PersistentTextStorage();
				this.storage.setTodoList(this.todos);
				this.router.setStorage(this.storage);
			}
			
		}
	}
}
