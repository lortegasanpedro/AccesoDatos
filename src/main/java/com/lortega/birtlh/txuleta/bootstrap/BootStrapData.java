package com.lortega.birtlh.txuleta.bootstrap;

import java.time.LocalTime;
import java.util.Date;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.lortega.birtlh.txuleta.modelo.Categoria;
import com.lortega.birtlh.txuleta.modelo.EnumCategorias;
import com.lortega.birtlh.txuleta.modelo.Txuleta;
import com.lortega.birtlh.txuleta.repositorios.CategoriaRepositorio;
import com.lortega.birtlh.txuleta.repositorios.TxuletaRepositorio;

@Component 
public class BootStrapData implements CommandLineRunner {

	private static Logger log = LoggerFactory
		      .getLogger(BootStrapData.class);
	
	@Autowired
	private CategoriaRepositorio categoriaRepositorio;
	
	@Autowired
	private TxuletaRepositorio txuletaRepositorio;
	
	@Transactional
	@Override
	public void run(String... args) throws Exception {
		log.info("START Txuleta");
		
		Categoria categoria = new Categoria();
		categoria.setCategoria(EnumCategorias.URLS);
		categoria.setDescripcion("URLs");
		categoria.setFechaMod(new Date());
		
		Txuleta txuleta = new Txuleta();
		txuleta.setUrl("http://www.radarkapildui.com");
		txuleta.setDescripcion("URL Radar Kapildui.com");
		txuleta.setFechaMod(new Date());
		
		txuleta.setCategoria(categoria);
		categoria.getTxuletas().add(txuleta);
		
		categoriaRepositorio.save(categoria);
		txuletaRepositorio.save(txuleta);
		
		//this.createCategorias();
		log.info("END Txuleta");
	}
	
	@Transactional
	private void createCategorias() throws Exception {
		Categoria categoriaURLs = new Categoria();
		categoriaURLs.setCategoria(EnumCategorias.URLS);
		categoriaURLs.setDescripcion("URLs");
		categoriaURLs.setFechaMod(new Date());

		Categoria categoriaServiciosSOA = new Categoria();
		categoriaServiciosSOA.setCategoria(EnumCategorias.SERVICIOS_SOA);
		categoriaServiciosSOA.setDescripcion("Servisios SOA");
		categoriaServiciosSOA.setFechaMod(new Date());

		Categoria categoriaRutasCarpetas = new Categoria();
		categoriaRutasCarpetas.setCategoria(EnumCategorias.RUTAS_CARPETAS);
		categoriaRutasCarpetas.setDescripcion("Rutas Carpetas");
		categoriaRutasCarpetas.setFechaMod(new Date());
		
		categoriaRepositorio.save(categoriaURLs);
		categoriaRepositorio.save(categoriaServiciosSOA);
		categoriaRepositorio.save(categoriaRutasCarpetas);
	}
}