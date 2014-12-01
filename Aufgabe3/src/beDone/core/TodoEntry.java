package beDone.core;

import java.util.UUID;
import java.util.Vector;

public class TodoEntry {
	
	
	protected String id 	= "";
	protected String title	= "";
	protected String text	= "";
	//#ifdef Tag
	protected Vector<String> tags = null;
	//#endif
	protected String status = Status.NEW;
	
	public TodoEntry() {
		//#ifdef Tag
		this.tags = new Vector<String>();
		//#endif
	}
	
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
	//#ifdef Tag
	public void addTag(String tag){
		this.tags.add(tag);
	}
	
	public void setTags(String[] tags){
		this.tags.clear();
		for(String s: tags){
			this.tags.add(s);
		}
	}
	
	public void setTags(String tags){
		this.setTags(tags.split(","));
	}
	
	public String[] getTags(){
		String[] a = {};
		return this.tags.toArray(a);
	}
	
	public String getTagsString(){
		String st = "";
		for(String s:this.getTags()){
			st += s + ",";
		}
		if(st.length()>2)
			return st.substring(0, st.length()-1);
		return "";
	}
	//#endif
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
