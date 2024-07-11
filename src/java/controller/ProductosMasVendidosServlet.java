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

@WebServlet("/ProductosMasVendidosServlet")
public class ProductosMasVendidosServlet extends HttpServlet {
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

            // Top 5 productos más vendidos
            document.add(new Paragraph("Productos más vendidos:", titleFont));
            document.add(new Paragraph(" "));
            
            connection = ConexionDB.getConnection();
            String sql = "SELECT p.nombre_producto, SUM(m.cantidad) AS total_vendido " +
                         "FROM movimientos m " +
                         "JOIN productos p ON m.id_producto = p.id_producto " +
                         "WHERE m.tipo_movimiento = 'VENTA' " +
                         "GROUP BY p.nombre_producto " +
                         "ORDER BY total_vendido DESC " +
                         "LIMIT 5";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Configurar las celdas de encabezado
            PdfPCell cell1 = new PdfPCell(new Phrase("Producto", boldFont));
            PdfPCell cell2 = new PdfPCell(new Phrase("Cantidad Vendida", boldFont));

            cell1.setBorderWidth(2);
            cell2.setBorderWidth(2);

            table.addCell(cell1);
            table.addCell(cell2);

            while (resultSet.next()) {
                PdfPCell productoCell = new PdfPCell(new Phrase(resultSet.getString("nombre_producto")));
                PdfPCell cantidadCell = new PdfPCell(new Phrase(String.valueOf(resultSet.getInt("total_vendido"))));

                productoCell.setBorderWidth(2);
                cantidadCell.setBorderWidth(2);

                table.addCell(productoCell);
                table.addCell(cantidadCell);
            }

            document.add(table);

            // Top 5 productos menos vendidos
            document.add(new Paragraph("Productos menos vendidos:", titleFont));
            document.add(new Paragraph(" "));

            String sqlMenosVendidos = "SELECT p.nombre_producto, SUM(m.cantidad) AS total_vendido " +
                                      "FROM movimientos m " +
                                      "JOIN productos p ON m.id_producto = p.id_producto " +
                                      "WHERE m.tipo_movimiento = 'VENTA' " +
                                      "GROUP BY p.nombre_producto " +
                                      "ORDER BY total_vendido ASC " +
                                      "LIMIT 5";
            statement = connection.prepareStatement(sqlMenosVendidos);
            resultSet = statement.executeQuery();

            PdfPTable tableMenosVendidos = new PdfPTable(2);
            tableMenosVendidos.setWidthPercentage(100);
            tableMenosVendidos.setSpacingBefore(10f);
            tableMenosVendidos.setSpacingAfter(10f);

            // Configurar las celdas de encabezado
            PdfPCell cellMenos1 = new PdfPCell(new Phrase("Producto", boldFont));
            PdfPCell cellMenos2 = new PdfPCell(new Phrase("Cantidad Vendida", boldFont));

            cellMenos1.setBorderWidth(2);
            cellMenos2.setBorderWidth(2);

            tableMenosVendidos.addCell(cellMenos1);
            tableMenosVendidos.addCell(cellMenos2);

            while (resultSet.next()) {
                PdfPCell productoCellMenos = new PdfPCell(new Phrase(resultSet.getString("nombre_producto")));
                PdfPCell cantidadCellMenos = new PdfPCell(new Phrase(String.valueOf(resultSet.getInt("total_vendido"))));

                productoCellMenos.setBorderWidth(2);
                cantidadCellMenos.setBorderWidth(2);

                tableMenosVendidos.addCell(productoCellMenos);
                tableMenosVendidos.addCell(cantidadCellMenos);
            }

            document.add(tableMenosVendidos);

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
