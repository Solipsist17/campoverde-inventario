package controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.ConexionDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/GananciasDelMesServlet")
public class GananciasDelMesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/pdf");
        Document document = new Document();
        PdfWriter writer = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            writer = PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // Definir fuentes en negrita
            Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK);

            document.add(new Paragraph("Ganancias del mes:", titleFont));
            document.add(new Paragraph()); // Añadir salto de línea

            // Obtener datos de la base de datos
            connection = ConexionDB.getConnection();
            String sql = "SELECT m.id_movimiento, m.precio, m.cantidad, m.fecha_movimiento, m.id_producto, " +
                         "DATE_FORMAT(m.fecha_movimiento, '%Y/%m') AS fecha_agrupada " +
                         "FROM movimientos m " +
                         "JOIN contactos c ON m.id_contacto = c.id_contacto " +
                         "WHERE c.tipo_contacto = 'CLIENTE' " +
                         "AND m.tipo_movimiento = 'VENTA' " +
                         "ORDER BY fecha_agrupada, m.fecha_movimiento";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            Map<String, PdfPTable> tablasPorFecha = new HashMap<>();

            while (resultSet.next()) {
                String fechaAgrupada = resultSet.getString("fecha_agrupada");
                PdfPTable table = tablasPorFecha.get(fechaAgrupada);
                if (table == null) {
                    table = new PdfPTable(5);
                    table.setWidthPercentage(100);
                    table.setSpacingBefore(10f);
                    table.setSpacingAfter(10f);

                    // Configurar las celdas de encabezado
                    PdfPCell cell1 = new PdfPCell(new Phrase("ID Movimiento", boldFont));
                    PdfPCell cell2 = new PdfPCell(new Phrase("Precio", boldFont));
                    PdfPCell cell3 = new PdfPCell(new Phrase("Cantidad", boldFont));
                    PdfPCell cell4 = new PdfPCell(new Phrase("Fecha de Venta", boldFont));
                    PdfPCell cell5 = new PdfPCell(new Phrase("ID Producto", boldFont));

                    cell1.setBorderWidth(2);
                    cell2.setBorderWidth(2);
                    cell3.setBorderWidth(2);
                    cell4.setBorderWidth(2);
                    cell5.setBorderWidth(2);

                    table.addCell(cell1);
                    table.addCell(cell2);
                    table.addCell(cell3);
                    table.addCell(cell4);
                    table.addCell(cell5);

                    tablasPorFecha.put(fechaAgrupada, table);
                }

                table.addCell(String.valueOf(resultSet.getInt("id_movimiento")));
                table.addCell(String.valueOf(resultSet.getDouble("precio")));
                table.addCell(String.valueOf(resultSet.getInt("cantidad")));
                table.addCell(new SimpleDateFormat("yyyy-MM-dd").format(resultSet.getDate("fecha_movimiento")));
                table.addCell(String.valueOf(resultSet.getInt("id_producto")));
            }

            if (tablasPorFecha.isEmpty()) {
                document.add(new Phrase("No hay ventas registradas para el mes actual."));
            } else {
                for (Map.Entry<String, PdfPTable> entry : tablasPorFecha.entrySet()) {
                    document.add(new Paragraph("Fecha: " + entry.getKey(), boldFont));
                    document.add(new Paragraph()); // Añadir salto de línea
                    PdfPTable table = entry.getValue();

                    // Establecer bordes más gruesos para las celdas de la tabla
                    for (PdfPCell cell : table.getRow(0).getCells()) {
                        cell.setBorderWidth(2);
                    }
                    for (int i = 1; i < table.getRows().size(); i++) {
                        for (PdfPCell cell : table.getRow(i).getCells()) {
                            cell.setBorderWidth(1.5f);
                        }
                    }

                    document.add(table);

                    // Calcular y añadir el total
                    double total = 0;
                    for (int i = 1; i < table.getRows().size(); i++) {
                        total += Double.parseDouble(table.getRow(i).getCells()[1].getPhrase().getContent());
                    }
                    document.add(new Paragraph("Total: " + total, boldFont));
                    document.add(new Paragraph()); // Añadir salto de línea después de cada tabla
                    document.add(new Phrase(" "));
                }
            }

        } catch (DocumentException e) {
            e.printStackTrace();
            throw new IOException("Error al generar el documento PDF.", e);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IOException("Error al obtener datos de la base de datos.", e);
        } finally {
            if (document.isOpen()) {
                document.close();
            }
            if (writer != null) {
                writer.close();
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                ConexionDB.closeConnection(connection);
            }
        }
    }
}

