package com.http.server.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QueuePeekServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		LOGGER.debug("QueuePeek: Http Server request received");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		LOGGER.info("HOLAAAAAAAAAAAAAAAAAAAAAA ===============");
	}
}