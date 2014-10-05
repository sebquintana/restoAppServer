package com.http.server.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.model.carta.ItemCarta;
import com.model.pedido.Pedido;

public class PedidoServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		LOGGER.debug("PedidoServlet: Http Server request recibido!");
		Pedido pedido = null;
		WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
		Gson gson = (Gson) applicationContext.getBean("gson");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String json = "";
			if (br != null) {
				json = br.readLine();
			}
			LOGGER.info("PedidoServlet: Pedido.Json recibido: " + json);
			pedido = gson.fromJson(json, Pedido.class);
			br.close();
		} catch (Exception e) {
			LOGGER.info("PedidoServlet: Exception: " + e.getMessage());
			e.printStackTrace();
		}
		LOGGER.info("PedidoServlet: Recorriendo el pedido" );
		Iterator<Entry<ItemCarta, Integer>> it = pedido.getPedido().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<ItemCarta, Integer> item = (Map.Entry<ItemCarta, Integer>) it.next();
			LOGGER.info("Nombre " + item.getKey().getNombre() + " Cantidad " + item.getValue());
		}
	}
}
