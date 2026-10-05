package com.example.jasper.sample;

import com.example.jasper.sample.model.ReportData;
import com.example.jasper.sample.model.ReportItem;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/** Runnable end-to-end example: JavaBeans -> JRXML -> PDF. */
public class SampleReportApplication {
    private static final String TEMPLATE = "/reports/sample/sample-report.jrxml";

    public static void main(String[] args) throws JRException, IOException {
        ReportData data = createSampleData();
        JasperReport report;
        try (InputStream template = SampleReportApplication.class.getResourceAsStream(TEMPLATE)) {
            if (template == null) {
                throw new IllegalStateException("JRXML resource not found: " + TEMPLATE);
            }
            report = JasperCompileManager.compileReport(template);
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ISSUE_DATE", data.getIssueDate());
        parameters.put("CUSTOMER_NAME", data.getCustomerName());
        parameters.put("TOTAL_AMOUNT", data.getTotalAmount());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getItems());
        JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);

        Path output = Path.of("target", "sample-report.pdf");
        Files.createDirectories(output.getParent());
        JasperExportManager.exportReportToPdfFile(print, output.toString());
        System.out.println("PDF generated: " + output.toAbsolutePath());
    }

    private static ReportData createSampleData() {
        return new ReportData(LocalDate.of(2026, 10, 5), "株式会社サンプル商事", java.util.Arrays.asList(
                new ReportItem("P-001", "ノート", 3, new BigDecimal("250.00")),
                new ReportItem("P-002", "ボールペン", 10, new BigDecimal("120.00")),
                new ReportItem("P-003", "ファイル", 5, new BigDecimal("180.00"))
        ));
    }
}
