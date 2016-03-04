package br.com.livro.rest;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/*
 * Classe anotada com anotação @Provider, o que indica que ela deve ser utilizadaj como 
 * provedora de conteúdo. Nese caso, ela vai conseguir seguir consumir e retornar JSON
 * usando o framework do google (Gson) para retornar e consumir dados em JSON.
 * 
 * */

@Provider
@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON+";charset=uft-8")
public class GsonMessageBodyHandler implements MessageBodyWriter<Object>, MessageBodyReader<Object> {
	
	private static final String UTF_8 = "UTF-8";
	private Gson gson;
	
	private Gson getGson(){
		if(gson == null) {
			gson = new GsonBuilder().setPrettyPrinting().create();
		}
		
		return gson;
	}
	
	@Override
	public long getSize(Object object, Class<?> type, Type genericType,
			Annotation[] annotions, MediaType mediaType) {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotions,
			MediaType mediaType) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void writeTo(Object object, Class<?> type, Type genericType,
			Annotation[] annotions, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
		
		OutputStreamWriter writer = new OutputStreamWriter(entityStream, UTF_8);
		
		try{
			Type jsonType;
			
			if(type.equals(genericType)) {
				jsonType = type;
			} else {
				jsonType = genericType;
			}
			
			getGson().toJson(object, jsonType, writer);
			
		} finally {
			writer.close();
		}
		
	}

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotation,
			MediaType mediType) {

		return true;
	}

	@Override
	public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations,
			MediaType mediaType, MultivaluedMap<String, String> httpHeadrs,
			InputStream entityStream) throws IOException, WebApplicationException {
		
		InputStreamReader streamReader = new InputStreamReader(entityStream, UTF_8);
		
		try {
			Type jsonType;
			
			if(type.equals(genericType)) {
				jsonType = type;
			} else {
				jsonType = genericType;
			}
			
			return getGson().fromJson(streamReader, jsonType);//*****
			
		} finally {
			streamReader.close();
		}
		
	}
	

}
