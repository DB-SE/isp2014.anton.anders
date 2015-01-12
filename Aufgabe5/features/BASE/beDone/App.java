package beDone;

import beDone.core.TodoEntry;
import beDone.core.TodoList;
import beDone.input.TodoConsoleInput;
import beDone.input.TodoInputInterface;
import beDone.output.TodoConsoleOutput;
import beDone.output.TodoOutputInterface;
import beDone.router.RouterContainer;
import beDone.router.TodoConsoleRouter;
import beDone.router.TodoRouterInterface;
import beDone.storage.PersistentStorageInterface;

/**
 * Erste Implementierung der Todo-Applikation mit FOP
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
 * 
 * FEATURES
 * 	INPUT 	=> CONSOLE oder NICHTS
 *  OUTPUT	=> CONSOLE oder NICHTS
 *  STORAGE => SQL oder TXT oder NICHTS
 *  ROUTER  => CONSOLE oder NICHTS		
 *  CORE	=> TAGS oder NICHTS				=> ADDONS für ToDo-Entry
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
		
		
		app.router.startAll();
	}
	
	public App(){
		this.todos = new TodoList();
		this.init();
	}
	
	public void init(){
		/*
		 * Ein Router-Container für verschiedene router (Konsole, HTTP, ...)
		 */
		this.router = new RouterContainer();
	}
	
	public void addRouter(TodoRouterInterface router){
		System.out.println("ADD ROUTER");
		this.router.addRouter(router);
	}
	
}
