package com.moj.noughtcross.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@SuppressWarnings("serial")
public class NoughtCrossGameException extends WebApplicationException{
	
	public NoughtCrossGameException(){
		//
	}
	 
	public NoughtCrossGameException(String message, Response.Status responseStatus) {
        super(Response.status(responseStatus).
                entity(new ErrorBean(message,  responseStatus.getStatusCode()))
                .build());
    }

}
