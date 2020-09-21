package com.mitocode.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
@Order(-1)
public class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler{

	public GlobalErrorWebExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties,
			ApplicationContext applicationContext,ServerCodecConfigurer configurer) {
		super(errorAttributes, resourceProperties, applicationContext);
		this.setMessageWriters(configurer.getWriters());
	}

	@Override
	protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
		return RouterFunctions.route(RequestPredicates.all(),this::renderErrorResponse);
	}

	private Mono<ServerResponse> renderErrorResponse(ServerRequest request){
		//Map<String, Object> errorDefaultMap = getErrorAttributes(request, false);	
		Map<String, Object> errorDefaultMap = getErrorAttributes(request, ErrorAttributeOptions.defaults());
		final Map<String, Object> mapException = new HashMap<>();
			
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		String status = String.valueOf(errorDefaultMap.get("status"));
		
		switch (status) {
			case "500":
				mapException.put("status", "500");
				mapException.put("exception", "Error Interno");
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				break;
			case "400":
				mapException.put("status", "400");
				mapException.put("exception", "Bad Request");
				httpStatus = HttpStatus.BAD_REQUEST;
				break;
			case "401" :
				mapException.put("status", "401");
				mapException.put("exception", "UNAUTHORIZED");
				httpStatus = HttpStatus.UNAUTHORIZED;
				
			default:
				break;
		}	
		System.err.println(errorDefaultMap.toString());
		
		mapException.put("mensaje", errorDefaultMap.get("message"));
		mapException.put("ruta", request.uri());
		mapException.put("fecha-error", LocalDateTime.now().toString());
		
		return ServerResponse.status(httpStatus)
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(mapException));
	}
	
}
