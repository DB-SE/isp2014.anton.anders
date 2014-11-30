package beDone.storage;

import beDone.core.TodoList;

public class PersistentTextStorage implements PersistentStorageInterface {

	protected String filename = "mylist.todo";
	protected TodoList todolist = null;
	
	public void setFilename(String f){
		this.filename = f;
	}
	

	public boolean load() {
		
		return false;
	}

	public boolean save() {
		
		return false;
	}


	public void setTodoList(TodoList t) {
		this.todolist = t;
	}

}
