package beDone.core;

public class TodoEntryFactory {
	protected static TodoEntry prototype;
	
	public static void setPrototype(TodoEntry t){
		TodoEntryFactory.prototype = t;
	}
	
	public static TodoEntry newInstance(){
		try {
			return (TodoEntry) TodoEntryFactory.prototype.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new TodoEntry();
	}
	
	
}
