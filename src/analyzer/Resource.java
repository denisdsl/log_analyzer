package analyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Resource {
	
	public String requestId="";
	public Vector<Event> eventList = new Vector<Event>();
	
	public String contentpath="";
	public String md5="";
	public String url="";
	public String mimetype="";
	public String fromcache="false";
	public long length = 0;
	
	public static String byteArrayToHex(byte[] b) {
	     String hs = "";   
	     String stmp = "";   
	     for (int n = 0; n < b.length; n++) {   
	            stmp = (Integer.toHexString(b[n] & 0XFF));   
	            if (stmp.length() == 1) {   
	                hs = hs + "0" + stmp;   
	            } else {   
	                hs = hs + stmp;   
	            }   
	            if (n < b.length - 1) {   
	                hs = hs + "";   
	            }   
	        }   
	        // return hs.toUpperCase();   
	        return hs;
	}
	
	void getinfo() throws IOException, NoSuchAlgorithmException {
		for(int i=0; i<eventList.size(); ++i) {
			if(eventList.get(i).method.equals("Network.responseReceived")) {
				url = ((EventNetworkResponseReceived)eventList.get(i)).response_url;
				mimetype = ((EventNetworkResponseReceived)eventList.get(i)).response_mimeType;
				if(((EventNetworkResponseReceived)eventList.get(i)).response_fromDiskCache.equals("true"))
					fromcache = "true";
			}
			if(eventList.get(i).method.equals("Network.requestServedFromCache")) {
				fromcache = "true";
			}
		}
		File f = new File(contentpath);
		if(f.exists()) {
			length = f.length();
			
		    int bufferSize = 256 * 1024;
		    FileInputStream fileInputStream = null;
		    DigestInputStream digestInputStream = null;
		    MessageDigest messageDigest =MessageDigest.getInstance("MD5");
		    fileInputStream = new FileInputStream(f);
		    digestInputStream = new DigestInputStream(fileInputStream,messageDigest);
		    byte[] buffer =new byte[bufferSize];
		    while (digestInputStream.read(buffer) > 0);
		    messageDigest= digestInputStream.getMessageDigest();
		    byte[] resultByteArray = messageDigest.digest();	    
		    md5 = byteArrayToHex(resultByteArray);
		    digestInputStream.close();
		    fileInputStream.close();
		}
			
		
	}
	
}
