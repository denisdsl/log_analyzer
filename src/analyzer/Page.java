package analyzer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.util.Vector;

public class Page {
	
	public Vector<Resource> resourceList = new Vector<Resource>(); 
	
	public void addEvent(Event e, String path) {
		for(int i=0; i<resourceList.size(); ++i) {
			if(resourceList.get(i).requestId!= null && resourceList.get(i).requestId.equals(e.requestId)) {
				resourceList.get(i).eventList.add(e);
				return;
			}
		}
		Resource r = new Resource();
		r.requestId = e.requestId;
		r.contentpath = path+'_'+e.requestId;
		resourceList.add(r);
	}
	
	public void getResourceInfo() throws NoSuchAlgorithmException, IOException {
		for(int i=0; i<resourceList.size(); ++i) {
			Resource r = resourceList.get(i);
			r.getinfo();
		}
	}
	
	public void initPage(String path) throws IOException, NoSuchAlgorithmException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path+"_trace"))); 
		String[] traffic_traces = br.readLine().replace("}{\"", "}\n{\"").split("\n");
		for(int i=0;i<traffic_traces.length;++i) {
			String trace = traffic_traces[i];
			if(trace.length()==0)
				continue;
			
			if(trace.contains("Network.responseReceived")) {
				Event e = new EventNetworkResponseReceived(trace);
				addEvent(e, path);
			}
			else if(trace.contains("Network.requestServedFromCache")) {
				Event e = new EventNetworkRequestServedFromCache(trace);
				addEvent(e, path);
			}
			else {
				Event e = new Event(trace);
				addEvent(e, path);
			}
			
		}
		getResourceInfo();
	}
	
	public void getValidResources() {
		Vector<Resource> rt = new Vector<Resource>();
		for(int i=0; i<resourceList.size(); ++i) {
			if(resourceList.get(i).requestId!= null && !resourceList.get(i).requestId.equals("") && !resourceList.get(i).url.equals("") && !resourceList.get(i).md5.equals("")) {
				rt.add(resourceList.get(i));
			}
		}
		resourceList = rt;
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		Page p = new Page();
		p.initPage("/home/liang/Desktop/500app/TrafficTrace/air.tv.douyu.android_tv.douyu.view.activity.SplashActivity__/second");
		
		p.getValidResources();
		System.out.println(p.resourceList.size());
		
	}

}
