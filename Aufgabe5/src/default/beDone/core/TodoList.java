package beDone.core; 

import java.util.Collection; 
import java.util.LinkedHashMap; 
import java.util.Map; 

public  class  TodoList {
	

	protected Map<String, TodoEntry> todos;

	
	
	public TodoList(){
		this.todos = new LinkedHashMap<String, TodoEntry>();
	}

	
	
	public void addTodo(TodoEntry t){
		this.todos.put(t.getID(), t);
	}

	
	
	public TodoEntry getTodo(String id){
		TodoEntry t = (TodoEntry)this.todos.get(id);
		return t;
	}

	
	
	public void deleteTodo(String id){
		this.todos.remove(id);
	}

	
	
	public Collection<TodoEntry> getTodos(){
		return this.todos.values();
	}


}
