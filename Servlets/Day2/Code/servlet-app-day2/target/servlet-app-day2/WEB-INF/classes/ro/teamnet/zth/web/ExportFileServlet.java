package ro.teamnet.zth.web;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * TODO Write javadoc
 */
public class ExportFileServlet extends HttpServlet {

    private static final String REPORT_TYPE_PDF = "pdf";
    private static final String REPORT_TYPE_XLS = "xlsx";
    private static final String APPLICATION_TYPE_XLS = "application/vnd.ms-excel";
    private static final String APPLICATION_TYPE_PDF = "application/pdf";

    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HashMap paramsMap = new HashMap();
        //  1: Iterate through Enumeration of parameter names from the request object and put inside paramsMap each parameter send from request
        // TIP: Use lambda expression
        // TIP: Each entry of the map should be <parameterName, parameterValue>

        Enumeration<String> parameters = request.getParameterNames();
        while(parameters.hasMoreElements()) {
            String parameter = parameters.nextElement();
            paramsMap.put(parameter, request.getParameter(parameter));
        }

        String passedTemplate = (String) (paramsMap.get("template"));  // the parameter sent from URL (exportFile.jsp)
        String passedFileType = (String) (paramsMap.get("fileType"));  // the parameter sent from URL (exportFile.jsp)

        JasperPrint jasperPrint = null;
        String jasperPath = "/reports/" + passedTemplate + ".jasper";

        System.out.println(jasperPath);

        try (InputStream reportStream = getClass().getResourceAsStream(jasperPath)) {
            System.out.println(reportStream);
            if(REPORT_TYPE_PDF.equals(passedFileType)) {
                response.setContentType(APPLICATION_TYPE_PDF);
            } else if(REPORT_TYPE_XLS.equals(passedFileType)) {
                response.setContentType(APPLICATION_TYPE_XLS);
            }
            System.out.println("intra");
            jasperPrint = JasperFillManager.fillReport(reportStream, paramsMap);

            //  2: Set the filename on the response header, based on the request parameters defined above

            System.out.println("ceva");

            response.setHeader("template", passedTemplate);
            response.setHeader("fileType", passedFileType);

            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());

            exporter.exportReport();

        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
