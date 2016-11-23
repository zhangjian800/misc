package com.myprojects.ci.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.SSLProtocolSocketFactory;


public class OAuth2HttpClient {
	
	private OAuth2HttpClient() {
		
	}
	
	private static int DEFAULT_HTTPCLIENT_CONNECT_TIMEOUT = 5 * 1000;
	private static int DEFAULT_HTTPCLIENT_SCOKET_TIMEOUT = 3 * 1000;

	public static MultiThreadedHttpConnectionManager manager = new MultiThreadedHttpConnectionManager();  
	static {
		
		int connectionTimeout = DEFAULT_HTTPCLIENT_CONNECT_TIMEOUT;
		int socketTimeout = DEFAULT_HTTPCLIENT_SCOKET_TIMEOUT;
		
		HttpConnectionManagerParams params = manager.getParams();
		params.setConnectionTimeout(connectionTimeout);
		params.setSoTimeout(socketTimeout);
		params.setStaleCheckingEnabled(true);
		manager.setParams(params); 		
	 } 
	
	private static  OAuth2HttpClient oauth2HttpClient = new OAuth2HttpClient(); 
	public static OAuth2HttpClient getInstance() {
		return oauth2HttpClient;
	}
	
	/**
	 * Get http client by the request URL. Note, after finish http call, need to call releaseConnection
	 * @param urlStr
	 * @return
	 */
	public HttpClient getHttpClientByURL(String apiURL) throws Exception{
		try {
			HttpClient client = new HttpClient(manager);
			URL url = new URL(apiURL);
			if ("HTTPS".equalsIgnoreCase(url.getProtocol())) {
				int port = url.getPort();
				if (port == -1) {
					port = url.getDefaultPort();
				}
				Protocol oauth2https = new Protocol("https", new SSLProtocolSocketFactory(), port);
				Protocol.registerProtocol("https", oauth2https);
			} 

			return client;
		} catch (Exception e) {
			System.out.println("OAuth2HttpClient.getHttpClientByURL error, apiURL is:" + apiURL + ", error is:");
			throw e;
		}
	}
	
	public APIResponse httpPostCall(String postUrl,  Map<String, String> headers, String postBody) throws Exception {
		HttpClient client = getHttpClientByURL(postUrl);
		PostMethod post = new PostMethod(postUrl);
		
		if (headers != null && headers.size() > 0) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				post.addRequestHeader(entry.getKey(), entry.getValue());
			}
		}
		APIResponse response = new APIResponse();
		InputStream in = null;
		ByteArrayOutputStream output = null;
		try {
			StringRequestEntity requestEntity = new StringRequestEntity(postBody, "application/json", "UTF-8");
			post.setRequestEntity(requestEntity);
			
			int statusCode = client.executeMethod(post);
			in = post.getResponseBodyAsStream();
			output = new ByteArrayOutputStream();
			byte[] byteArray = new byte[1024];
			int count = 0;
			while((count = in.read(byteArray, 0, byteArray.length)) > 0) {
				output.write(byteArray, 0, count);
			}
			String responseBody = new String(output.toByteArray(), "UTF-8");
			response.setStatus(statusCode);
			response.setResponse(responseBody);

		} catch (IOException e) {
			System.out.println("OAuth2HttpClient.httpPostCall() error, PostURL is:" + postUrl + ", error:");
			throw e;
		} finally {			
			try {
				if (in != null) {
					in.close();
				}
				if (output != null) {
					output.close();
				}
			} catch (IOException e) {
				System.out.println("OAuth2HttpClient.httpPostCall() error, PostURL is:" + postUrl + ", error:");

			}			
			post.releaseConnection();
		}
		
		return response;
	}
	
	
	public class APIResponse {
		private int status;
		private String response;
		public APIResponse() {
			
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public String getResponse() {
			return response;
		}
		public void setResponse(String response) {
			this.response = response;
		}
	}

}
