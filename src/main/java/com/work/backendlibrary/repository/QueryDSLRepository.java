package com.work.backendlibrary.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.work.backendlibrary.entity.QBloqueFolio;
import com.work.backendlibrary.entity.QCuentasPorCobrar;
import com.work.backendlibrary.entity.QEscuela;
import com.work.backendlibrary.entity.QHistorialVenta;
import com.work.backendlibrary.entity.QLibro;
import com.work.backendlibrary.entity.QProfesor;
import com.work.backendlibrary.entity.QVendedor;
import com.work.backendlibrary.entity.QVenta;
import com.work.backendlibrary.entity.QZona;
import com.work.backendlibrary.entity.Vendedor;
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
			predicate = QHistorialVenta.historialVenta.tipoMovimiento.eq("SALIDA");
			predicateBuilder.and(predicate);
		}else{
			if(tipoPedido<0){
				predicate = QHistorialVenta.historialVenta.tipoMovimiento.eq("ENTRADA");
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
	
	public List<Vendedor> findVendedorByClaveEscuelaProfesor(String clave,String escuela,int profesor,Date fechaInicial,Date fechaFinal){
		JPAQuery<Vendedor> query = new JPAQuery<Vendedor>(em);
		BooleanBuilder predicateBuilder = new BooleanBuilder();
		//BooleanBuilder predicateBuilder2 = new BooleanBuilder();
		Predicate predicate=null;
		//Filtro vendedor
		if(!clave.isEmpty()){
			predicate = QVendedor.vendedor.clave.eq(clave);
			predicateBuilder.and(predicate);
			System.out.println("Filtro de vendedor");
		}
		if(!escuela.isEmpty()){
			predicate = QVenta.venta.escuela.clave.eq(escuela);
			predicateBuilder.and(predicate);
			System.out.println("Filtro de escuela");
		}
		if(profesor!=0){
			predicate = QVenta.venta.profesor.idprofesor.eq(profesor);
			predicateBuilder.and(predicate);
			System.out.println("Filtro de profesor");
		}
		if(fechaInicial!=null && fechaFinal!=null){
			predicate = QCuentasPorCobrar.cuentasPorCobrar.fecha.between(fechaInicial, fechaFinal);
			predicateBuilder.and(predicate);
			System.out.println("Filtro de fechas");
		}
		
		return query.select(QVendedor.vendedor)
				.distinct()
				.from(QVendedor.vendedor)
				.innerJoin(QVendedor.vendedor.bloqueFolios,QBloqueFolio.bloqueFolio)
				.fetchJoin()
				.innerJoin(QBloqueFolio.bloqueFolio.ventas,QVenta.venta)
				.fetchJoin()
				.innerJoin(QVenta.venta.pedidos,QHistorialVenta.historialVenta)
				.fetchJoin()
				.innerJoin(QHistorialVenta.historialVenta.cuentas,QCuentasPorCobrar.cuentasPorCobrar)
				.fetchJoin()
				.where(predicateBuilder)
				.orderBy(QVendedor.vendedor.clave.asc(),QVenta.venta.escuela.clave.asc(),QVenta.venta.profesor.idprofesor.asc())
				.fetch();
	}
}
