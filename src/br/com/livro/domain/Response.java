package br.com.livro.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Response {
	
	private String status;
	private String msg;
	
	public Response() { }
	
	public static Response Ok(String string){
		Response response = new Response();
		
		response.setStatus("Ok");
		response.setMsg(string);

		return response;
	}
	
	public static Response Error(String string) {
		Response response = new Response();
		
		response.setStatus("ERROR");
		response.setMsg(string);

		return response;

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
