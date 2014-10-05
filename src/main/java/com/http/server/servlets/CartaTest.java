package com.http.server.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.carta.Carta;
import com.model.carta.ItemCarta;

public class CartaTest {

	private static String cartaFilePath = "/home/seba/dev/Workspace/restoAppServer/src/main/resources/test.json";

	public static void main(String[] args) {
		deJavaAJson();
		deJsonAJava();
		deArchivoAJson();
	}

	private static void deArchivoAJson() {
		Gson gson = new Gson();

		String cartaString = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(cartaFilePath ));
			Carta carta = gson.fromJson(br, Carta.class);
			cartaString = gson.toJson(carta);
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("CARTA: " + cartaString);
	}

	private static void deJsonAJava() {
		Gson gson = new Gson();
		 
		try {
	 
			BufferedReader br = new BufferedReader(
				new FileReader(cartaFilePath));
	 
			Carta obj = gson.fromJson(br, Carta.class);
			for (ItemCarta item : obj.getCarta()) {
				
				System.out.println("Nombre " + item.getNombre());
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	 
	}

	private static void deJavaAJson() {
		ItemCarta item = new ItemCarta("Coca cola", "1 Litro", 20);
		ItemCarta item2 = new ItemCarta("ensalada mixta", "tomate y  lechuga", 20);
		Carta carta = new Carta("kubo");
		carta.agregarItem(item);
		carta.agregarItem(item2);
		GsonBuilder gbuilder = new GsonBuilder();
		gbuilder.setPrettyPrinting();
		String json = gbuilder.create().toJson(carta);
		try {
			FileWriter writer = new FileWriter(cartaFilePath);
			writer.write(json);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(json);
	}

}
