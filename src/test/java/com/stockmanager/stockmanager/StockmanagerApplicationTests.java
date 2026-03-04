package com.stockmanager.stockmanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockmanager.stockmanager.model.Categoria;
import com.stockmanager.stockmanager.model.Producto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StockmanagerApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	// TEST 1 - GET /categorias devuelve status 200
	@Test
	void listarCategorias_debeRetornar200() throws Exception {
		mockMvc.perform(get("/categorias"))
				.andExpect(status().isOk());
	}

	// TEST 2 - POST /categorias crea una categoria correctamente
	@Test
	void crearCategoria_debeRetornar200YNombre() throws Exception {
		Categoria categoria = new Categoria("Test Categoria", "Descripcion test");

		mockMvc.perform(post("/categorias")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(categoria)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nombre").value("Test Categoria"));
	}

	// TEST 3 - GET /productos devuelve status 200
	@Test
	void listarProductos_debeRetornar200() throws Exception {
		mockMvc.perform(get("/productos"))
				.andExpect(status().isOk());
	}

	// TEST 4 - POST /productos crea un producto correctamente
	@Test
	void crearProducto_debeRetornar200YNombre() throws Exception {
		// Primero creamos una categoria
		Categoria categoria = new Categoria("Categoria Test", "Descripcion");
		String catResponse = mockMvc.perform(post("/categorias")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(categoria)))
				.andReturn().getResponse().getContentAsString();

		Categoria catCreada = objectMapper.readValue(catResponse, Categoria.class);

		// Despues creamos el producto con esa categoria
		Producto producto = new Producto("Producto Test", 100.0, 10, catCreada);

		mockMvc.perform(post("/productos")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(producto)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nombre").value("Producto Test"))
				.andExpect(jsonPath("$.precio").value(100.0));
	}

	// TEST 5 - DELETE /productos/{id} elimina correctamente
	@Test
	void eliminarProducto_debeRetornar200() throws Exception {
		// Creamos categoria
		Categoria categoria = new Categoria("Cat Eliminar", "Desc");
		String catResponse = mockMvc.perform(post("/categorias")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(categoria)))
				.andReturn().getResponse().getContentAsString();
		Categoria catCreada = objectMapper.readValue(catResponse, Categoria.class);

		// Creamos producto
		Producto producto = new Producto("A Eliminar", 50.0, 5, catCreada);
		String prodResponse = mockMvc.perform(post("/productos")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(producto)))
				.andReturn().getResponse().getContentAsString();
		Producto prodCreado = objectMapper.readValue(prodResponse, Producto.class);

		// Lo eliminamos
		mockMvc.perform(delete("/productos/" + prodCreado.getId()))
				.andExpect(status().isOk());
	}
}
