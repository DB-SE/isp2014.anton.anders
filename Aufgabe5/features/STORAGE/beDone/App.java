package beDone;

import beDone.storage.*;
/**
 * TODO description
 */
public class App {
	
	private void setStorage(PersistentStorageInterface storage){
		storage.setTodoList(this.todos);
		for(TodoRouterInterface r :this.router.getRouter()){
			r.setStorage(storage);
		}
	}
}