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

import beDone.router.*; 

import beDone.storage.*; 
/**
 * TODO description
 */
public   class  App {
	
	
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

	
	
	 private void  init__wrappee__BASE  (){
		/*
		 * Ein Router-Container für verschiedene router (Konsole, HTTP, ...)
		 */
		this.router = new RouterContainer();
	}

	
	 private void  init__wrappee__ROUTER  (){
		System.out.println("INIT ROUTER CONTAINER");
		this.router = new RouterContainer();
		init__wrappee__BASE();
	}

	
	
	 private void  init__wrappee__ROUTER_CONSOLE  (){
		
		init__wrappee__ROUTER();
		System.out.println("INIT CONSOLE ROUTER");
		this.addRouter(new TodoConsoleRouter());
		
	}

	
	 private void  init__wrappee__INPUT_CONSOLE  (){
		init__wrappee__ROUTER_CONSOLE();
		
		/*
		 * Konsolen-Eingabe-Dialog für Todos
		 * Console-Input => Console-Router
		 */
		this.addConsoleInput();
		
	}

	
	 private void  init__wrappee__OUTPUT  (){
		init__wrappee__INPUT_CONSOLE();
		
		/*
		 * Konsolen-Eingabe-Dialog für Todos
		 * Console-Input => Console-Router
		 */
		this.addConsoleOutput();
		
	}

	
	public void init(){
		init__wrappee__OUTPUT();
		
		this.setStorage(new PersistentSQLITEStorage());	
	}

	
	
	public void addRouter(TodoRouterInterface router){
		System.out.println("ADD ROUTER");
		this.router.addRouter(router);
	}

	
	
	private void addConsoleInput(){
		System.out.println("INIT CONSOLE INPUT");
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
