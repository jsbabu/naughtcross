package com.moj.noughtcross.application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.internal.JerseyResourceContext;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import com.moj.noughtcross.controller.NoughtCrossController;
import com.moj.noughtcross.dao.GameDaoImpl;
import com.moj.noughtcross.service.BoardServiceImpl;
import com.moj.noughtcross.service.GameServiceImpl;

public class NoughtCrossApplication extends ResourceConfig {

	public NoughtCrossApplication(){
		 register(RequestContextFilter.class);
		 register(JerseyResourceContext.class);
		 register(NoughtCrossController.class);
		 register(GameServiceImpl.class);
		 register(GameDaoImpl.class);
		 register(BoardServiceImpl.class);
	}
	
}
