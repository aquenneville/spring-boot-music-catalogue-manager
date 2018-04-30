package github.aq.musiccataloguemanager.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpClientService {

	
	public static synchronized String doGetRequest(String url) {
		ReentrantLock lock = new ReentrantLock();
		lock.lock();
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);

		// add request header throttling 1 req/sec
		request.addHeader("User-Agent", "music-catalogue-manager/0.1 (alainquenneville@gmail.com)");
		StringBuffer result = new StringBuffer();
		try {
			HttpResponse response = client.execute(request);
	
			System.out.println("Response Code : " 
		                + response.getStatusLine().getStatusCode());
	
			BufferedReader rd = new BufferedReader(
				new InputStreamReader(response.getEntity().getContent()));
	
			
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			Thread.sleep(1500);
		} catch (InterruptedException exc) { 
			exc.printStackTrace();
		} catch(ClientProtocolException exc) {
			exc.printStackTrace();
		} catch(IOException exc) {
			exc.printStackTrace();
		} finally { 
			lock.unlock();
		}
		return result.toString();
	}
}
