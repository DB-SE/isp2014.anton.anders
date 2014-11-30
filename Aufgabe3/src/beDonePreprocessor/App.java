package beDonePreprocessor;

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
//#define OUTPUT_CONSOLE
//#define INPUT_CONSOLE
//#define STORAGE_TEXT

public class App {
	
	protected PersistentStorageInterface 	storage = null;
	protected TodoInputInterface			input	= null;
	protected TodoOutputInterface			output	= null;
	protected TodoRouterInterface			router	= null;
	
	protected TodoList	todos = null;
	
	public static void main(String[] args) {
		
		App app = new App();
		
		app.init();
		
		app.router.start();
	}
	
	public App(){
		this.todos = new TodoList();
	}
	
	public void init(){
		/*
		 * Wenn kein Input und Output-Feature gewählt, brauchen wir einen Standard-Router
		 */
		
		//#ifndef INPUT_CONSOLE
//@			this.router = new TodoConsoleRouter();
		//#endif
		
		/*
		 * Konsolen-Eingabe-Dialog für Todos
		 * Console-Input => Console-Router
		 */
			
		//#ifdef INPUT_CONSOLE
			this.input = new TodoConsoleInput();
			this.input.setTodoList(this.todos);
			
			if(!(this.router instanceof TodoConsoleRouter)){
				this.router = new TodoConsoleRouter();
			}
			
			this.router.setInput(this.input);
		//#endif
		
		/*
		 * Ausgabe der ToDos erfolgt auf der Konsole
		 * Console-Output => Console-Router
		 */
		//#ifdef OUTPUT_CONSOLE
			this.output = new TodoConsoleOutput();
			this.output.setTodoList(this.todos);
			
			if(!(this.router instanceof TodoConsoleRouter)){
				this.router = new TodoConsoleRouter();
			}
			
			this.router.setOutput(this.output);
		//#endif
		
		//#ifdef STORAGE_TEXT
			this.storage = new PersistentTextStorage();
			this.storage.setTodoList(this.todos);
			this.router.setStorage(this.storage);
		//#endif

	}
	
}
