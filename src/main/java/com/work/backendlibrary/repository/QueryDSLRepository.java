package com.work.backendlibrary.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.work.backendlibrary.entity.QHistorialVenta;
import com.work.backendlibrary.entity.QLibro;
import com.work.backendlibrary.entity.QVenta;
import com.work.backendlibrary.entity.Venta;

@Repository("queryDSLRepository")
public class QueryDSLRepository {
	
	@PersistenceContext
	private EntityManager em;
	private QVenta qVenta = QVenta.venta;

	public List<Venta> findVentaByVendedorLibroFecha(String clave,String libro,Date fechaInicial,Date fechaFinal,int tipoPedido){
		JPAQuery<Venta> query = new JPAQuery<Venta>(em);
		BooleanBuilder predicateBuilder = new BooleanBuilder();
		//BooleanBuilder predicateBuilder2 = new BooleanBuilder();
		Predicate predicate=null;
		//Filtro vendedor
		if(!clave.isEmpty()){
			predicate = qVenta.bloqueFolio.vendedor.clave.eq(clave);
			predicateBuilder.and(predicate);
			System.out.println("Filtro de vendedor");
		}
		//Filtro libro
		if(!libro.isEmpty()){
			predicate = QLibro.libro.claveProducto.eq(libro);
			predicateBuilder.and(predicate);
			System.out.println("Filtro de libro "+predicate.toString());
		}
		//Filtro Fechas
		if(fechaInicial!=null && fechaFinal!=null){
			predicate = qVenta.fecha.between(fechaInicial, fechaFinal);
			predicateBuilder.and(predicate);
			System.out.println("Filtro de fechas");
		}
		
		if(tipoPedido>0){
			predicate = QHistorialVenta.historialVenta.pedidos.gt(0);
			predicateBuilder.and(predicate);
		}else{
			if(tipoPedido<0){
				predicate = QHistorialVenta.historialVenta.pedidos.lt(0);
				predicateBuilder.and(predicate);
			}
		}
		return query.select(qVenta)
				.distinct()
				.from(qVenta)
				.innerJoin(qVenta.pedidos,QHistorialVenta.historialVenta)
				.fetchJoin()
				.innerJoin(QHistorialVenta.historialVenta.libro,QLibro.libro)
				//.on(predicateBuilder2)
				.where(predicateBuilder)
				.orderBy(qVenta.bloqueFolio.vendedor.clave.asc(),qVenta.folio.asc())
				.fetch();
	}
}
