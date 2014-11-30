package beDone.core;

import java.util.UUID;

public class TodoEntry {
	
	
	protected String id 	= "";
	protected String title	= "";
	protected String text	= "";
	protected String status = Status.NEW;
	
	public void generateID(){
		this.id = UUID.randomUUID().toString();
	}
	
	public void setID(String id){
		this.id = id;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setStatus(String s){
		
		this.status = s;
	}
	
	public String getID(){
		return this.id;
	}
	
	public String getText(){
		return this.text;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getStatus(){
		return this.status;
	}
	
}
