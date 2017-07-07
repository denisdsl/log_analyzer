package analyzer;

import net.sf.json.JSONObject;

public class Event {
	
	public String method;
	public String requestId;
	
	Event() {
		
	}
	
	Event(String str) {
		JSONObject root = JSONObject.fromObject(str);
		try{method = root.getString("method");}catch(Exception e){}
		
		try{
			JSONObject params = root.getJSONObject("params");
		    requestId = params.getString("requestId");
		}catch(Exception e){}
	}

}
