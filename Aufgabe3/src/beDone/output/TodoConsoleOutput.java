package beDone.output;

import beDone.core.TodoEntry;
import beDone.core.TodoList;
import beDone.helper.ConsoleIO;

public class TodoConsoleOutput implements TodoOutputInterface {

	protected TodoList todolist = null;
	
	public void setTodoList(TodoList t) {
		this.todolist = t;
	}

	public void callOutput() {
		ConsoleIO c = ConsoleIO.getInstance();
		
		String[] out = {"#", 
				"Status", 
				"Titel",
				//#ifdef Tag
				"Tags",
				//#endif
				"Beschreibung"
				};
		
		c.writeTable(out);
		int i = 1;
		for(TodoEntry t : this.todolist.getTodos()){
			out[0] = ""+i++;
			out[1] = t.getStatus().toString();
			out[2] = t.getTitle();
			//#ifndef Tag
//@			out[3] = t.getText();
			//#else
			out[3] = t.getTagsString();
			out[4] = t.getText();
			//#endif
			
			c.writeTable(out);
		}
		c.writeNewPage();
	}

}
