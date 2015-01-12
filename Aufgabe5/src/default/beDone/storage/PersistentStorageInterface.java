package beDone.storage; 

import beDone.core.TodoList; 

public  interface  PersistentStorageInterface {
	

	public boolean save();

	
	
	public boolean load();

	
	
	public void setTodoList(TodoList t);


}
