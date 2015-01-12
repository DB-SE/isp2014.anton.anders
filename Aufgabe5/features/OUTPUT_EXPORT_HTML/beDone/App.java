package beDone;

import beDone.output.TodoOutputInterface;
import beDone.router.*;
import beDone.output.TodoHTMLExport;
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
		this.addHTMLExport();
		
	}
	
	private void addHTMLExport(){
		TodoRouterInterface r;
		if((r = this.router.getRouter("TodoConsoleRouter")) == null){
			r = new TodoConsoleRouter();
			this.router.addRouter(r);
		}
		
		TodoHTMLExport input = new TodoHTMLExport();
		input.setTodoList(this.todos);
		r.setExport(input);
	}
}