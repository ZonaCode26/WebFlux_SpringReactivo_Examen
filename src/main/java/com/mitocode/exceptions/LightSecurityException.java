package com.mitocode.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class LightSecurityException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LightSecurityException(String message) {
	    super(message);
	 }
	
//	private Integer httpStatus;
//    private String message;
//    private Integer code;
//    private String developerMessage;
// 
//    private static final long serialVersionUID = -528134378438377740L;
// 
//    public LightSecurityException(Integer httpStatus, String message, Integer code,String developerMessage) {
//        this.httpStatus = httpStatus;
//        this.message = message;
//        this.code = code;
//        this.developerMessage=developerMessage;
//    }
// 
//    public LightSecurityException(Integer httpStatus, String message, Integer code) {
//        this.httpStatus = httpStatus;
//        this.message = message;
//        this.code = code;
//    }
// 
////    public LightSecurityException(ErrorMessage errorMessage){
////        this.httpStatus = errorMessage.getHttpStatus();
////        this.message = errorMessage.getMessage();
////        this.code = errorMessage.getCode();
////        this.developerMessage=errorMessage.getDeveloperMessage();
////    }
// 
//
//    @Override
//    public String getMessage() {
//    	// TODO Auto-generated method stub
//    	return message;
//    }
//    
////	/**
////	 * 
////	 */
////	private static final long serialVersionUID = 1L;
////
////	@Autowired
////	private  HttpStatus status;
////	
////	private String mensaje;	
////	
////	@Nullable
////	private String reason;
////	
////	
////	
////	
////	public LightSecurityException(HttpStatus status, String mensaje) {
////		super();
////		this.status = status;
////		this.mensaje = mensaje;
////	}
////
////
////
////
////	@Override
////	public String getMessage() {
////		// TODO Auto-generated method stub
////		return super.getMessage();
////	}
////	
////	
//////	@ExceptionHandler(value = {ExpiredJwtException.class})
//////    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//////    public Mono<ServerResponse> handle(ExpiredJwtException ex) {
//////        return ServerResponse.status(HttpStatus.UNAUTHORIZED)
//////				.build();
//////    }
////	
}