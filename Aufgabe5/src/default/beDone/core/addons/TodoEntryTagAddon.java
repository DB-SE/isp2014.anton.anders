package beDone.core.addons; 

import java.util.Vector; 

public  class  TodoEntryTagAddon  extends AbstractTodoEntryTagAddon {
	

	protected Vector<String> tags = null;

	
	
	public TodoEntryTagAddon() {
		this.tags = new Vector<String>();
	}

	
	
	public String getName() {
		return "Tags";
	}

	

	public String[] getValues() {
		String[] a = {};
		return this.tags.toArray(a);
	}

	

	public void setValues(String[] values) {
		this.tags.clear();
		for(String s : values){
			tags.add(s);
		}
	}

	

	
	public void addValue(String value){
		this.tags.add(value);
	}

	

	public String getFormText() {
		return "Tags (Bitte geben Sie die Tags kommasepariert an):";
	}


}
