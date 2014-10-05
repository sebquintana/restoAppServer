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

import com.google.gson.GsonBuilder;
import com.model.carta.ItemCarta;
import com.model.pedido.Pedido;

public class PedidoServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger();
	private GsonBuilder gson;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		LOGGER.debug("PedidoServlet: Http Server request recibido!");
		Pedido pedido = null;
		gson = new GsonBuilder();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String json = "";
			if (br != null) {
				json = br.readLine();
			}
			LOGGER.info("PedidoServlet: Pedido.Json recibido: " + json);
			//TODO: Error aca pasando de json a java - terminar
			pedido = gson.enableComplexMapKeySerialization().create().fromJson(json, Pedido.class);
			LOGGER.info("TEST");
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
