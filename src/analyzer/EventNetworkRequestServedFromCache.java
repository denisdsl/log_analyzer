package analyzer;

import net.sf.json.JSONObject;

public class EventNetworkRequestServedFromCache extends Event {
	
	EventNetworkRequestServedFromCache() {
		
	}
	
	EventNetworkRequestServedFromCache(String str) {
		JSONObject root = JSONObject.fromObject(str);
		method = root.getString("method");
		
		JSONObject params = root.getJSONObject("params");
		requestId = params.getString("requestId");
		
	}

}
