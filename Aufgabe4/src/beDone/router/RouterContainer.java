package beDone.router;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RouterContainer {
	
	protected Map<String, TodoRouterInterface> router;
	
	public RouterContainer() {
		this.router = new HashMap<String, TodoRouterInterface>();
	}

	
	public RouterContainer addRouter(TodoRouterInterface r){
		this.router.put(r.getName(), r);
		return this;
	}
	
	public TodoRouterInterface getRouter(String name){
		return this.router.get(name);
	}
	
	public void startAll(){
		for(TodoRouterInterface r : this.router.values()){
			if(!r.isRunning())
				r.start();
		}
	}
	
	public void stopAll(){
		for(TodoRouterInterface r : this.router.values()){
			if(r.isRunning())
				r.stop();
		}
	}


	public Collection<TodoRouterInterface> getRouter() {
		return this.router.values();
	}
}
