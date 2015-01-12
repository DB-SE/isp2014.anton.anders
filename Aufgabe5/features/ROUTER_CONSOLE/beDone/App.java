package beDone;

import beDone.router.*;
/**
 * TODO description
 */
public class App {
	
	public void init(){
		
		original();
		System.out.println("INIT CONSOLE ROUTER");
		this.addRouter(new TodoConsoleRouter());
		
	}
}