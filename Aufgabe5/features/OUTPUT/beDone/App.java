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
		this.addConsoleOutput();
		
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
}