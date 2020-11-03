package apiEngine;

import io.restassured.response.Response;

public class RestResponse <T> implements IRestResponse<T> {
	private T data;
	private Response response;
	private Response Datas;
	private Exception e;
//	@SuppressWarnings("deprecation")
	public RestResponse(Class<T> t, Response response) {
		this.response = response;
//		this.Datas = datas;
//		this.data = t.newInstance();
		System.out.println("POJO DATA IS:" + response.asString());
		try{
//			this.data = t.newInstance();
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}
	public Response getData() {
		return Datas;
	}
	public String getContent() {
		return response.getBody().asString();
	}
	public int getStatusCode() {
		return response.getStatusCode();
	}
	public boolean isSuccessful() {
		int code = getStatusCode();
		if( code == 200 || code == 201 || code == 202 || code == 203 || code == 204 || code == 205) {
			return true;
		}
		else {
			return false;
		}
		
	}
	public String getStatusDescription() {
		return response.getStatusLine();
	}
	public Response getResponse() {
		return response;
	}
	public T getBody() {
		try {
			data = (T) response.getBody().as(data.getClass());
		}catch (Exception e) {
			this.e=e;
		}
		return data;
	}
	public Exception getException() {
		return e;
	}
}
