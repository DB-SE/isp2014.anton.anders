package beDone.output;

import beDone.core.TodoList;

public interface TodoOutputInterface {

	public boolean hasDelete();
	public boolean hasEditStatus();
	
	public void setTodoList(TodoList t);
	
	public void callOutput();
	
	public void callOutputDelete();
	public void callOutputStatus();
	
}
