package beDone;

import beDone.router.*;
/**
 * TODO description
 */
public class App {
	public void init(){
		System.out.println("INIT ROUTER CONTAINER");
		this.router = new RouterContainer();
		original();
	}
}