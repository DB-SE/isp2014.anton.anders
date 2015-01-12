package beDone.storage; 

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 
import java.util.Vector; 

import beDone.core.TodoEntry; 
import beDone.core.TodoList; 
import beDone.core.addons.TodoEntryAddonInterface; 
import beDone.helper.ArrayStringConverter; 

public  class  PersistentSQLITEStorage  implements PersistentStorageInterface {
	

	private TodoList todos ;

	
	
	private final static String filename = "tododb.sqlite";

	
	private Connection c = null;

	
	
	public PersistentSQLITEStorage() {
		this.init();
	}

	
	
	public boolean init(){
		try {
		      Class.forName("org.sqlite.JDBC");
		      this.c = DriverManager.getConnection("jdbc:sqlite:"+filename);
		      this.addTODOTable();
		      this.checkAndAddCollumn();
		      return true;
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		return false;
	}

	
	
	protected void addTODOTable(){
		 Statement stmt;
		try {
			stmt = c.createStatement();
		
			String sql = "CREATE TABLE IF NOT EXISTS todo " +
	                   "(id 			  char(32) PRIMARY KEY     NOT NULL," +
	                   " status           Chat(10) NOT NULL, " + 
	                   " titel            INT     NOT NULL, " + 
	                   " beschreibung     TEXT " + 
	                   " )"; 
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	public void checkAndAddCollumn(){
		String sql = "";
		Statement stmt;
		try {
			stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery("PRAGMA table_info('todo')");
			
			Vector<String> s = new Vector<String>();
			
			while(rs.next()){
				s.add(rs.getString("name"));
			}
			
			for(TodoEntryAddonInterface a : new TodoEntry().getAddons()){
				if(!s.contains("addon_"+a.getName().toLowerCase() )){
					sql =  "ALTER TABLE todo ADD addon_"+a.getName().toLowerCase() + " TEXT";
					stmt.execute(sql);
				}
			}
			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	
	
	private String generateColumnnames(){
		String sql = "`id`, `status`, `titel`, `beschreibung`";
		for(TodoEntryAddonInterface a : new TodoEntry().getAddons() ){
			sql += ", `addon_"+a.getName().toLowerCase()+"`";
		}
		return sql;//.substring(0, sql.length()-1);
	}

	
	
	private String generateInsertValue(TodoEntry t){
		String sql = "(";
		sql += "'" + t.getID() 		+"'"+ ",";
		sql += "'" + t.getStatus() 	+"'"+ ",";
		sql += "'" + t.getTitle() 	+"'"+ ",";
		sql += "'" + t.getText() 	+"'"+ ",";
		for(TodoEntryAddonInterface a : t.getAddons()){
			sql += "'" + ArrayStringConverter.implode( a.getValues(), ",") 	+"'"+ ",";
		}
		return sql.substring(0, sql.length() -1)+ "),";
	}

	
	
	public boolean save() {
		if(this.c == null)
			return false;
		
		Statement stmt;
		try {
			stmt 		= c.createStatement();
			String sql 	= "DELETE FROM todo;";
			stmt.execute(sql);
			
			sql 	= "INSERT or IGNORE INTO todo (" + this.generateColumnnames() + ") VALUES ";
			for(TodoEntry t : this.todos.getTodos()){
				sql += this.generateInsertValue(t);
			}
			sql = sql.substring(0, sql.length()-1)+";";
			//System.out.println(sql);
			stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
		return true;
	}

	

	public boolean load() {
		Statement stmt;
		try {
			stmt = c.createStatement();
		
		    ResultSet rs = stmt.executeQuery( "SELECT * FROM todo;" );
		    while ( rs.next() ) {
		    	TodoEntry t = new TodoEntry();
		    	t.setID( 	rs.getString( "id" ));
		    	t.setTitle( rs.getString( "titel" ));
		    	t.setText(  rs.getString( "beschreibung" ));
		    	t.setStatus(rs.getString( "status"));
		    	
		    	for(TodoEntryAddonInterface a : t.getAddons()){
		    		a.setValues( ArrayStringConverter.explode(rs.getString("addon_"+a.getName().toLowerCase()), ","));
		    	}
		    	this.todos.addTodo(t);
		    }
		    return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	

	public void setTodoList(TodoList t) {
		this.todos = t;
	}


}
