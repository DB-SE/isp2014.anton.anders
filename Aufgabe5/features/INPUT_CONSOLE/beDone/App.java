package beDone;

import beDone.router.*;
/**
 * TODO description
 */
public class App {
	public void init(){
		original();
		
		/*
		 * Konsolen-Eingabe-Dialog für Todos
		 * Console-Input => Console-Router
		 */
		this.addConsoleInput();
		
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
}