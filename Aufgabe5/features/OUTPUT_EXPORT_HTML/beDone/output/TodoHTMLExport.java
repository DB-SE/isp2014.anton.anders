package beDone.output;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import beDone.core.TodoEntry;
import beDone.core.TodoList;
import beDone.core.addons.TodoEntryAddonInterface;
import beDone.helper.ArrayStringConverter;
import beDone.helper.ConsoleIO;

public class TodoHTMLExport implements TodoOutputInterface {

	protected TodoList todolist = null;

	
	public void setTodoList(TodoList t) {
		this.todolist = t;
	}

	public void callOutput(){

		ConsoleIO c = ConsoleIO.getInstance();
		
		System.out.println("Wohin möchten Sie die TODO-Liste als HTML exportieren?");
		String filename = c.readLine();
		String content = 
				"<table>"
				+ "\t<tr>"
				+ "\t\t<td>ID</td>"
				+ "\t\t<td>Titel</td>"
				+ "\t\t<td>Beschreibung</td>"
				+ "\t\t<td>Status</td>";
				for(TodoEntryAddonInterface a : new TodoEntry().getAddons()){
					content+="\t\t<td>" + a.getName() +"<td>";
				}
				content+= "\t<tr>";
		int i = 1;
		for(TodoEntry t : this.todolist.getTodos()){
			
			content +="<h1>TODO-Liste</h1>"
					+ "\t<tr>"
					+ "\t\t<td>"+(i++)+"</td>"
					+ "\t\t<td>"+t.getTitle()+"</td>"
					+ "\t\t<td>"+t.getText()+"</td>"
					+ "\t\t<td>"+t.getStatus()+"</td>";
					for(TodoEntryAddonInterface a : t.getAddons()) {
						content += "\t\t<td>" + ArrayStringConverter.implode(a.getValues(),",") +"<td>";
					}
					content+= "\t<tr>";
		}
		content+="</table>";
		
		this.save(filename, this.generateHTMLPage(content));
		
		System.out.println("Datei wurde gespeichert!");
		c.writeNewPage();
	}

	private String generateHTMLPage(String content){
		return    "<html>"
				+ "\t<head>"
				+ "\t\t<title>TODO-Liste</title>"
				+ "\t</head>"
				+ "\t<body>"
				+ "\t\t"+content
				+ "\t</body>"
				+ "</html>";
		 
	}
	
public boolean clearFile(String filename){
		
		PrintWriter writer;
		try {
			writer = new PrintWriter(filename);
			writer.print("");
			writer.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean save(String filename, String content) {
		this.clearFile(filename);
		
		OutputStream fos;
		try {
			fos = new FileOutputStream(filename);
			OutputStreamWriter out = new OutputStreamWriter(fos, "UTF-8");

			out.write( content );
			out.flush();
			
			fos.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	public boolean hasDelete() {
		// TODO Auto-generated method stub
		return false;
	}



	public boolean hasEditStatus() {
		// TODO Auto-generated method stub
		return false;
	}



	public void callOutputDelete() {
		// TODO Auto-generated method stub
		
	}



	public void callOutputStatus() {
		// TODO Auto-generated method stub
		
	}


}
