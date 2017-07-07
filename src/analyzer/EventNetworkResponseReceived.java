package analyzer;

import net.sf.json.JSONObject;

public class EventNetworkResponseReceived extends Event {
	
	//public String method;
	
	//public String requestId;
	public String frameId;
	public String loaderId;
	public String timestamp;
	public String type;
	
	public String response_url;
	public String response_status;
	public String response_statusText;
	public String response_mimeType;
	public String response_connectionReused;
	public String response_connectionId;
	public String response_encodedDataLength;
	public String response_fromDiskCache;
	public String response_remoteIPAddress;
	public String response_remotePort;
	
	public String response_headers_Date;
	public String response_headers_ContentEncoding;
	public String response_headers_Server;
	public String response_headers_TransferEncoding;
	public String response_headers_XVia;
	public String response_headers_AccessControlAllowOrigin;
	public String response_headers_Connection;
	public String response_headers_ContentType;
	
	public String response_timing_requestTime;
	public String response_timing_proxyStart;
	public String response_timing_proxyEnd;
	public String response_timing_dnsStart;
	public String response_timing_dnsEnd;
	public String response_timing_sslStart;
	public String response_timing_sslEnd;
	public String response_timing_sendStart;
	public String response_timing_sendEnd;
	public String response_timing_receiveHeadersEnd;	
       
	EventNetworkResponseReceived(){
		
	}
	
	EventNetworkResponseReceived(String str){
		
		JSONObject root = JSONObject.fromObject(str);
		method = root.getString("method");
		
		JSONObject params = root.getJSONObject("params");
		requestId = params.getString("requestId");
		frameId = params.getString("frameId");
		loaderId = params.getString("loaderId");
		timestamp = params.getString("timestamp");
		type = params.getString("type");
		
		
		JSONObject response = params.getJSONObject("response");
		try{
		response_url = response.getString("url");
		response_status = response.getString("status");
		response_statusText = response.getString("statusText");
		response_mimeType = response.getString("mimeType");
		response_connectionReused = response.getString("connectionReused");
		response_connectionId = response.getString("connectionId");
		response_encodedDataLength = response.getString("encodedDataLength");
	    response_fromDiskCache = response.getString("fromDiskCache");
		response_remoteIPAddress = response.getString("remoteIPAddress");
		response_remotePort = response.getString("remotePort");
		}catch(Exception e){}
		
		try{
		JSONObject response_headers = response.getJSONObject("headers");
		response_headers_Date = response_headers.getString("Date");
		response_headers_ContentEncoding = response_headers.getString("Content-Encoding");
		response_headers_Server = response_headers.getString("Server");
		response_headers_TransferEncoding = response_headers.getString("Transfer-Encoding");
		response_headers_XVia = response_headers.getString("X-Via");
		response_headers_AccessControlAllowOrigin = response_headers.getString("Access-Control-Allow-Origin");
		response_headers_Connection = response_headers.getString("Connection");
		response_headers_ContentType = response_headers.getString("Content-Type");
		JSONObject response_timing = response.getJSONObject("timing");
		response_timing_requestTime = response_timing.getString("requestTime");
		response_timing_proxyStart = response_timing.getString("proxyStart");
		response_timing_proxyEnd = response_timing.getString("proxyEnd");
		response_timing_dnsStart = response_timing.getString("dnsStart");
		response_timing_dnsEnd = response_timing.getString("dnsEnd");
		response_timing_sslStart = response_timing.getString("sslStart");
		response_timing_sslEnd = response_timing.getString("sslEnd");
		response_timing_sendStart = response_timing.getString("sendStart");
		response_timing_sendEnd = response_timing.getString("sendEnd");
		response_timing_receiveHeadersEnd = response_timing.getString("receiveHeadersEnd");
		}catch(Exception e){}
		
		
	}
	
	public static void main(String[] args){
		//String j="{\"method\":\"Network.responseReceived\",\"params\":{\"requestId\":\"7974.88\",\"frameId\":\"7974.2\",\"loaderId\":\"7974.3\",\"timestamp\":1491351097.20533,\"type\":\"XHR\",\"response\":{\"url\":\"https://dotcounter.douyucdn.cn/deliver/fish2\",\"status\":200,\"statusText\":\"OK\",\"headers\":{\"Date\":\"Wed, 05 Apr 2017 01:43:46 GMT\",\"Content-Encoding\":\"gzip\",\"Server\":\"openresty\",\"Transfer-Encoding\":\"chunked\",\"X-Via\":\"1.1 swt42:0 (Cdn Cache Server V2.0)\",\"Access-Control-Allow-Origin\":\"*\",\"Connection\":\"keep-alive\",\"Content-Type\":\"text/plain; charset=utf-8\"},\"mimeType\":\"text/plain\",\"connectionReused\":true,\"connectionId\":155,\"encodedDataLength\":-1,\"fromDiskCache\":false,\"timing\":{\"requestTime\":1491351097.17699,\"proxyStart\":-1,\"proxyEnd\":-1,\"dnsStart\":-1,\"dnsEnd\":-1,\"connectStart\":-1,\"connectEnd\":-1,\"sslStart\":-1,\"sslEnd\":-1,\"sendStart\":1.23500000000831,\"sendEnd\":1.56499999999937,\"receiveHeadersEnd\":16.3859999999829},\"remoteIPAddress\":\"211.94.114.66\",\"remotePort\":443}}}{\"method\":\"Network.dataReceived\",\"params\":{\"requestId\":\"7974.88\",\"timestamp\":1491351097.20575,\"dataLength\":23,\"encodedDataLength\":322}}{\"method\":\"Network.loadingFinished\",\"params\":{\"requestId\":\"7974.88\",\"timestamp\":1491351097.19544,\"encodedDataLength\":322}}";
		//EventNetworkResponseReceived a = new EventNetworkResponseReceived(j);
		//System.out.println(a.response_timing_receiveHeadersEnd);
	}

}
