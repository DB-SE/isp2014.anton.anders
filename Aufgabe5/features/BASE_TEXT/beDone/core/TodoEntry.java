package beDone.core;

/**
 * TODO description
 */
public class TodoEntry {
	protected String title	= "";
	protected String text	= "";
	
	public void setText(String text){
		this.text = text;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	
	public String getText(){
		return this.text;
	}
	
	public String getTitle(){
		return this.title;
	}
}