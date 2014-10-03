package com.http.server.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.model.carta.Carta;

public class CartaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		LOGGER.debug("CartaServlet: Http Server request recibido!");

		// WebApplicationContext applicationContext =
		// ContextLoader.getCurrentWebApplicationContext();

		Carta carta = new Carta("Resto del cubo");

		LOGGER.debug("Restaurant: " + carta.getRestaurant());

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD><TITLE>La concha de tu madre all boys!</TITLE></HEAD>");
		out.println("<BODY>");
		out.println("<BIG>Hello World</BIG>");
		out.println("</BODY></HTML>");
		
		out.write(carta.getRestaurant());
		out.close();
	}

}