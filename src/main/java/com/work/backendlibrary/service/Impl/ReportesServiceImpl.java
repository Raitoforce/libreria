package com.work.backendlibrary.service.Impl;

import com.work.backendlibrary.converter.InventarioConverterRConverter;
import com.work.backendlibrary.entity.Director;
import com.work.backendlibrary.entity.LideresComisiones;
import com.work.backendlibrary.entity.Vendedor;
import com.work.backendlibrary.entity.Venta;
import com.work.backendlibrary.repository.*;
import com.work.backendlibrary.service.ComisionService;
import com.work.backendlibrary.service.ReportesService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("reportesService")
public class ReportesServiceImpl implements ReportesService {

    @Autowired
    @Qualifier("escuelaJPARepository")
    EscuelaJPARepository eJPA;

    @Autowired
    @Qualifier("directorJPARepository")
    DirectorJPARepository dJPA;

    @Autowired
    @Qualifier("lideresComisionesJPARepository")
    LideresComisionesJPARepository lcJPA;

    @Autowired
    @Qualifier("vendedorRepository")
    VendedorRepository vJPA;

    @Autowired
    @Qualifier("zonaJPARepository")
    ZonaJPARepository zJPA;

    @Autowired
    @Qualifier("inventarioConverterRConverter")
    InventarioConverterRConverter icrc;

    @Autowired
    @Qualifier("ventaJPARepository")
    VentaJPARepository vRepository;

    @Autowired
    @Qualifier("queryDSLRepository")
    QueryDSLRepository qDSLR;

    @Autowired
    @Qualifier("comisionService")
    ComisionService cService;

    @Value("classpath:/RZona.jrxml")
    private Resource reporte_model;

    String path = null;
    String masterReportFileName = null;
    String subReportFileName = null;
    String subEscuela = null;
    String destFile = null;
    String jasperMaster = null;
    String subsubReportFileName = null;
    String reporte;
    String subFinalReportFileName;

    @Override
    public void generarReporteZonas(String vendedor) {
        if (path == null) {
            try {
                path = reporte_model.getURL().getPath().replaceAll("%20", " ");
                path = path.replaceAll("RZona.jrxml", "");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {

            masterReportFileName = path + "RZona.jrxml";
            subReportFileName = path + "SubZonas.jrxml";
            subsubReportFileName = path + "REscuelas.jrxml";
            jasperMaster = path + "RZona.jasper";
            destFile = path + "reporte.pdf";

            JRBeanCollectionDataSource sourceVendedor = null;

            if (vendedor.compareTo("") == 0) {
                sourceVendedor = new JRBeanCollectionDataSource(vJPA.findAll());
            } else {
                ArrayList<Vendedor> vendedores = new ArrayList<>();
                vendedores.add(vJPA.findByClave(vendedor));
                sourceVendedor = new JRBeanCollectionDataSource(vendedores);
            }
            /// Compile the master and sub report

            /*
             * JasperCompileManager.compileReportToFile(subsubReportFileName,path+
             * "REscuelas.jasper"); JasperReport jasperSubReport2=
             * JasperCompileManager.compileReport(subReportFileName);
             *
             * JasperCompileManager.compileReportToFile(subReportFileName,path+
             * "SubZonas.jasper"); JasperReport jasperSubReport =
             * JasperCompileManager.compileReport(subsubReportFileName);
             */

            JasperCompileManager.compileReportToFile(masterReportFileName, jasperMaster);

            System.out.println("Llenando...");
            reporte = null;
            reporte = JasperFillManager.fillReportToFile(jasperMaster, null, sourceVendedor);
            if (reporte != null) {
                JasperExportManager.exportReportToPdfFile(reporte, destFile);
            }
        } catch (JRException e) {

            e.printStackTrace();
        }
        System.out.println("Done filling!!! ...");
    }

    @Override
    public void generarReporteInventario() {
        if (path == null) {
            try {
                path = reporte_model.getURL().getPath().replaceAll("%20", " ");
                path = path.replaceAll("RZona.jrxml", "");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {

            masterReportFileName = path + "ReporteInventario.jrxml";
            subReportFileName = path + "StocksR.jrxml";
            jasperMaster = path + "ReporteInventario.jasper";
            destFile = path + "reporte.pdf";

            JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(this.icrc.converterInventarioRModel());

            /// Compile the master and sub report

            /*
             * JasperCompileManager.compileReportToFile(subReportFileName,path+
             * "StocksR.jasper"); JasperReport jasperSubReport =
             * JasperCompileManager.compileReport(subReportFileName);
             */

            JasperCompileManager.compileReportToFile(masterReportFileName, jasperMaster);

            // Map parameters = new HashMap();
            // parameters.put("subreportParameter", jasperSubReport);
            // parameters.put("subreportParameter2",jasperSubReport2);

            System.out.println("Llenando...");
            reporte = null;
            reporte = JasperFillManager.fillReportToFile(jasperMaster, null, source);
            if (reporte != null) {
                JasperExportManager.exportReportToPdfFile(reporte, destFile);
            }
        } catch (JRException e) {

            e.printStackTrace();
        }
        System.out.println("Done filling!!! ...");
    }

    @Override
    public void generarReporteVentas(String vendedor, String libro, Date fechaInicial, Date fechaFinal, int tipoPedido,
                                     int temporada, int hacienda) {
        if (path == null) {
            try {
                path = reporte_model.getURL().getPath().replaceAll("%20", " ");
                path = path.replaceAll("RZona.jrxml", "");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {

            masterReportFileName = path + "ReporteVentas.jrxml";
            subReportFileName = path + "ReportePedidos.jrxml";
            jasperMaster = path + "ReporteVentas.jasper";
            destFile = path + "reporte.pdf";

            JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(
                    this.qDSLR.findVentaByVendedorLibroFechaTemporadaHacienda(vendedor, libro, fechaInicial, fechaFinal,
                            tipoPedido, temporada, hacienda));

            /// Compile the master and sub report

            /*
             * JasperCompileManager.compileReportToFile(subReportFileName,path+
             * "ReportePedidos.jasper"); JasperReport jasperSubReport =
             * JasperCompileManager.compileReport(subReportFileName);
             */

            JasperCompileManager.compileReportToFile(masterReportFileName, jasperMaster);

            System.out.println("Llenando...");
            reporte = null;
            reporte = JasperFillManager.fillReportToFile(jasperMaster, null, source);
            if (reporte != null) {
                JasperExportManager.exportReportToPdfFile(reporte, destFile);
            }
        } catch (JRException e) {

            e.printStackTrace();
        }
        System.out.println("Done filling!!! ...");
    }

    @Override
    public void generarReporteCobranza(String clave, String escuela, int profesor, Date fechaInicial, Date fechaFinal,
                                       int temporada) {
        if (path == null) {
            try {
                path = reporte_model.getURL().getPath().replaceAll("%20", " ");
                path = path.replaceAll("RZona.jrxml", "");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {

            masterReportFileName = path + "ReporteCobranza.jrxml";
            subReportFileName = path + "ReporteEscuelas.jrxml";
            subsubReportFileName = path + "ReporteProfesores.jrxml";
            subFinalReportFileName = path + "ReporteCuentas.jrxml";
            jasperMaster = path + "ReporteCobranza.jasper";
            destFile = path + "reporte.pdf";

            JRBeanCollectionDataSource source = null;
            source = new JRBeanCollectionDataSource(this.qDSLR.findVendedorByClaveEscuelaProfesor(clave, escuela,
                    profesor, fechaInicial, fechaFinal, temporada));
            /// Compile the master and sub report

            /*
             * JasperCompileManager.compileReportToFile(subsubReportFileName,path+"");
             * JasperReport jasperSubReport2=
             * JasperCompileManager.compileReport(subsubReportFileName);
             *
             * JasperCompileManager.compileReportToFile(subReportFileName,path+
             * "ReporteEscuelas.jasper"); JasperReport jasperSubReport =
             * JasperCompileManager.compileReport(subReportFileName);
             *
             * JasperCompileManager.compileReportToFile(masterReportFileName,jasperMaster);
             */
            System.out.println("Llenando...");
            reporte = null;
            reporte = JasperFillManager.fillReportToFile(jasperMaster, null, source);
            if (reporte != null) {
                JasperExportManager.exportReportToPdfFile(reporte, destFile);
            }
        } catch (JRException e) {

            e.printStackTrace();
        }
        System.out.println("Done filling!!! ...");
    }

    @Override
    public void generarReporteComisiones(int tipo, String id, int temporada, int hacienda) {
        if (path == null) {
            try {
                path = reporte_model.getURL().getPath().replaceAll("%20", " ");
                path = path.replaceAll("RZona.jrxml", "");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {

            masterReportFileName = path + "ReporteComisiones.jrxml";
            jasperMaster = path + "ReporteComisiones.jasper";
            destFile = path + "reporte.pdf";

            JRBeanCollectionDataSource source = null;
            List<Venta> temporal = null;

            String titulo = "REPORTE DE COMISIONES";
            // Vendedor
            if (tipo == 1) {
                // por filtro
                if (id.isEmpty()) {
                    temporal = new ArrayList<>();
                    List<Vendedor> vendedores = vJPA.findAll();
                    for (Vendedor vendedor : vendedores) {

                        temporal.addAll(vRepository
                                .findByBloqueFolioVendedorClaveAndBloqueFolioFolioIdtemporadaIdtemporadaAndHacienda(vendedor.getClave(),
                                        temporada, hacienda));
                    }
                } else {
                    temporal = vRepository
                            .findByBloqueFolioVendedorClaveAndBloqueFolioFolioIdtemporadaIdtemporadaAndHacienda(id,
                                    temporada, hacienda);
                }
                titulo += " POR VENDEDOR";
            }
            // Director
            if (tipo == 2) {
                if (id.isEmpty()) {
                    temporal = new ArrayList<>();
                    List<Director> directores = dJPA.findAll();
                    for (Director director : directores) {
                        temporal.addAll(vRepository
                                .findByEscuelaDirectorIddirectorAndBloqueFolioFolioIdtemporadaIdtemporadaAndHacienda(
                                        director.getIddirector(), temporada, hacienda));
                    }
                } else {
                    temporal = vRepository
                            .findByEscuelaDirectorIddirectorAndBloqueFolioFolioIdtemporadaIdtemporadaAndHacienda(
                                    Integer.valueOf(id), temporada, hacienda);
                }
                titulo += " POR DIRECTOR";
            }
            // Lider
            if (tipo == 3) {
                if (id.isEmpty()) {
                    temporal = new ArrayList<>();
                    List<LideresComisiones> lideresComisiones = lcJPA.findAll();
                    for (LideresComisiones liderComision : lideresComisiones) {
                        List<Venta> aux = vRepository
                                .findByLideresLiderIdprofesorAndBloqueFolioFolioIdtemporadaIdtemporadaAndHacienda(
                                        liderComision.getId().getProfesor(), temporada, hacienda);
                        for (Venta venta : aux) {
                            venta.setComisionesLider(venta.getLiderComision(liderComision.getId().getProfesor()));
                        }
                        temporal.addAll(aux);
                    }

                } else {

                    temporal = vRepository
                            .findByLideresLiderIdprofesorAndBloqueFolioFolioIdtemporadaIdtemporadaAndHacienda(
                                    Integer.valueOf(id), temporada, hacienda);
                    for (Venta venta : temporal) {
                        venta.setComisionesLider(venta.getLiderComision(Integer.valueOf(id)));
                    }
                }
                titulo += " POR LIDER";
                // calculamos la comision del lider antes
            }

            source = new JRBeanCollectionDataSource(temporal);
            JasperCompileManager.compileReportToFile(masterReportFileName, jasperMaster);

            Map parameters = new HashMap();
            parameters.put("titulo", titulo);
            parameters.put("tipo", tipo);

            System.out.println("Llenando...");
            reporte = null;
            reporte = JasperFillManager.fillReportToFile(jasperMaster, parameters, source);
            if (reporte != null) {
                JasperExportManager.exportReportToPdfFile(reporte, destFile);
            }
        } catch (JRException e) {

            e.printStackTrace();
        }
        System.out.println("Done filling!!! ...");
    }

    @Override
    public void generarReporteGanancia(String vendedor, String libro, Date fechaInicial, Date fechaFinal,
                                       int tipoPedido, int temporada) {
        if (path == null) {
            try {
                path = reporte_model.getURL().getPath().replaceAll("%20", " ");
                path = path.replaceAll("RZona.jrxml", "");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {

            masterReportFileName = path + "ReporteGanancia.jrxml";
            subReportFileName = path + "SubReporteGanancia.jrxml";
            jasperMaster = path + "ReporteGanancia.jasper";
            destFile = path + "reporte.pdf";

            JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(this.qDSLR
                    .findVentaByVendedorLibroFechaGanancia(vendedor, libro, fechaInicial, fechaFinal, tipoPedido, temporada));

            /// Compile the master and sub report

            /*
             * JasperCompileManager.compileReportToFile(subReportFileName,path+
             * "ReportePedidos.jasper"); JasperReport jasperSubReport =
             * JasperCompileManager.compileReport(subReportFileName);
             */

            JasperCompileManager.compileReportToFile(masterReportFileName, jasperMaster);

            System.out.println("Llenando...");
            reporte = null;
            reporte = JasperFillManager.fillReportToFile(jasperMaster, null, source);
            if (reporte != null) {
                JasperExportManager.exportReportToPdfFile(reporte, destFile);
            }
        } catch (JRException e) {

            e.printStackTrace();
        }
        System.out.println("Done filling!!! ...");
    }

}
