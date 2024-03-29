/**
 * 
 */
package com.ehb.samproject;

import com.loopj.android.http.*;

/**
 * @author stofke http://10.0.1.2:8080/
 */
public class RestClient {

	private static final String BASE_URL = "http://questiontime.stofke72.cloudbees.net/rest/";

	static AsyncHttpClient client = new AsyncHttpClient();

	public static void get(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		client.get(getAbsoluteUrl(url), params, responseHandler);
	}

	public static void post(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		client.post(getAbsoluteUrl(url), params, responseHandler);
	}

	public static void put(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		client.put(getAbsoluteUrl(url), params, responseHandler);
	}

	public static void delete(String url,
			AsyncHttpResponseHandler responseHandler) {
		client.delete(getAbsoluteUrl(url), responseHandler);
	}

	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}
}
