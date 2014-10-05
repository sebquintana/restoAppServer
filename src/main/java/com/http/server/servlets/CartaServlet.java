package com.http.server.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.model.carta.Carta;

public class CartaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger();
	private String cartaFilePath = "/home/seba/dev/Workspace/restoAppServer/src/main/resources/test.json";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		LOGGER.debug("CartaServlet: Http Server request recibido!");
		String carta = "";
		WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
		Gson gson = (Gson) applicationContext.getBean("gson");
		try {
			BufferedReader br = new BufferedReader(new FileReader(cartaFilePath));
			Carta aux = gson.fromJson(br, Carta.class);
			carta = gson.toJson(aux);
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.debug("CartaServlet: Carta enviada!");
		PrintWriter out = response.getWriter();
		out.write(carta);
		out.close();
	}
	
}