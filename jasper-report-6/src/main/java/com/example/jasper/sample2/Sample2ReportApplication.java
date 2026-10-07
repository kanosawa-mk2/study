package com.example.jasper.sample2;

import com.example.jasper.sample2.model.PurchaseHistoryItem;
import com.example.jasper.sample2.model.Sample2ReportData;
import com.example.jasper.sample2.model.Sample2ReportItem;
import net.sf.jasperreports.engine.JRDataSource;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Generates the sales statement and purchase history shown in sample2-report.xlsx. */
public class Sample2ReportApplication {
    private static final String TEMPLATE = "/reports/sample2/sample2-report.jrxml";

    public static void main(String[] args) throws JRException, IOException {
        Sample2ReportData sales = createSalesData();
        List<PurchaseHistoryItem> history = createPurchaseHistory();

        JasperReport report;
        try (InputStream template = Sample2ReportApplication.class.getResourceAsStream(TEMPLATE)) {
            if (template == null) {
                throw new IllegalStateException("JRXML resource not found: " + TEMPLATE);
            }
            report = JasperCompileManager.compileReport(template);
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ISSUE_DATE", sales.getIssueDate());
        parameters.put("CUSTOMER_NAME", sales.getCustomerName());
        parameters.put("TOTAL_AMOUNT", sales.getTotalAmount());
        JRDataSource historyDataSource = new JRBeanCollectionDataSource(history);
        parameters.put("HISTORY_DATA_SOURCE", historyDataSource);

        JRBeanCollectionDataSource salesDataSource =
                new JRBeanCollectionDataSource(sales.getItems());
        JasperPrint print = JasperFillManager.fillReport(report, parameters, salesDataSource);

        Path output = Path.of("target", "sample2-report.pdf");
        Files.createDirectories(output.getParent());
        JasperExportManager.exportReportToPdfFile(print, output.toString());
        System.out.println("PDF generated: " + output.toAbsolutePath());
    }

    private static Sample2ReportData createSalesData() {
        return new Sample2ReportData(LocalDate.of(2026, 10, 5), "株式会社サンプル商事", Arrays.asList(
                new Sample2ReportItem("P-001", "ノート", 3, new BigDecimal("250.00")),
                new Sample2ReportItem("P-002", "ボールペン", 10, new BigDecimal("120.00")),
                new Sample2ReportItem("P-003", "ファイル", 5, new BigDecimal("180.00"))
        ));
    }

    private static List<PurchaseHistoryItem> createPurchaseHistory() {
        return Arrays.asList(
                new PurchaseHistoryItem(LocalDate.of(2026, 10, 5), "P-001", "ノート", 3),
                new PurchaseHistoryItem(LocalDate.of(2026, 10, 4), "P-002", "ボールペン", 10),
                new PurchaseHistoryItem(LocalDate.of(2026, 10, 3), "P-003", "ファイル", 5)
        );
    }
}
