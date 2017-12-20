package com.qlink.common.utils.neo.http;

import java.io.IOException;
import java.net.URI;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.qlink.common.utils.neo.http.exceptions.HttpNeoSessionException;


public class HttpNeoSession {
	private static final String JSON_CONTENT_TYPE = "application/json";
	private static final String POST_CONTENT_TYPE = "text/plain";

	private HttpClient client = null;
	private URI uri = null;
	private Credentials credentials = null;

	public HttpNeoSession(URI uri, Credentials credentials) {
		this.uri = uri;
		this.credentials = credentials;
	}

	public JSONObject sendAndReceive(JSONObject message) {
		PostMethod method = new PostMethod(uri.toString());

		try {
			method.setRequestHeader("Content-Type", POST_CONTENT_TYPE);

			RequestEntity requestEntity = new StringRequestEntity(message.toString(), JSON_CONTENT_TYPE, null);
			method.setRequestEntity(requestEntity);

			getHttpClient().executeMethod(method);
			int statusCode = method.getStatusCode();

			if (statusCode != HttpStatus.SC_OK) {
				throw new HttpNeoSessionException(
						"HTTP Status - " + HttpStatus.getStatusText(statusCode) + " (" + statusCode + ")");
			}

			/*JSONTokener tokener = new JSONTokener(method.getResponseBodyAsString());
			Object rawResponseMessage = tokener.nextValue();
			JSONObject response = (JSONObject) rawResponseMessage;*/
			JSONObject response = JSONObject.parseObject(method.getResponseBodyAsString());

			if (response == null) {
				throw new HttpNeoSessionException("Invalid response type");
			}

			return response;
		} catch (HttpException e) {
			throw new HttpNeoSessionException(e);
		} catch (IOException e) {
			throw new HttpNeoSessionException(e);
		} catch (JSONException e) {
			throw new HttpNeoSessionException(e);
		} finally {
			method.releaseConnection();
		}
	}

	private HttpClient getHttpClient() {
		if (client == null) {
			client = new HttpClient();
			client.getState().setCredentials(AuthScope.ANY, credentials);
		}

		return client;
	}
}
