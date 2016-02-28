package com.horadatosa.service.client;

import java.util.List;

import javax.ws.rs.core.MediaType;

import com.pet.mongo.morphia.entities.Usuario;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

public class RestClientCaller {

		public static final String CRUDSERVICEBASEURI = "http://localhost:8080/pet-service";
		
		
		public static void delete(String id) throws Exception{
			Client client = Client.create();
			WebResource service = client.resource(CRUDSERVICEBASEURI);
			
			ClientResponse resp = service.path(CRUDSERVICEBASEURI).path("delete").path(id).type(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
			System.out.println(resp.getStatusInfo().getReasonPhrase());
		}
		
		public static void post(Usuario usuario) throws Exception{
			Client client = Client.create();
			WebResource service = client.resource(CRUDSERVICEBASEURI);
			
			ClientResponse resp = service.path("usuariosService").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, usuario);
			System.out.println(resp.getStatusInfo().getReasonPhrase() + " " + resp.getEntity(String.class));
		}
		public static ClientResponse post(String URI,String mediaType,Object object, String... paths) throws Exception{
			Client client = Client.create();
			WebResource service = client.resource(URI);
			
			for(String path : paths){
				service = service.path(path);
			}
			Builder postResponseBuilder = service.type(mediaType);
			return postResponseBuilder.post(ClientResponse.class, object);
		}
		
		public static List<?> get(String URI, MediaType mediaType, String...paths ) throws Exception{
			Client client = Client.create();
			WebResource service = client.resource(URI);
			
			for(String path : paths){
				service = service.path(path);
			}
			Builder postResponseBuilder = service.accept(mediaType);
			return postResponseBuilder.get(List.class);
	}
}
