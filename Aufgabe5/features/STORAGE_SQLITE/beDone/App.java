package beDone;

import beDone.storage.*;
/**
 * TODO description
 */
public class App {
	public void init(){
		original();
		
		this.setStorage(new PersistentSQLITEStorage());	
	}
}