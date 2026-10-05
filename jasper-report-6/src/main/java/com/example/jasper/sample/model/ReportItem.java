package com.example.jasper.sample.model;

import java.math.BigDecimal;

/** One detail row. JasperReports reads these JavaBean properties by getter. */
public class ReportItem {
    private final String productCode;
    private final String productName;
    private final int quantity;
    private final BigDecimal unitPrice;

    public ReportItem(String productCode, String productName, int quantity, BigDecimal unitPrice) {
        this.productCode = productCode;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getProductCode() { return productCode; }
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public BigDecimal getAmount() { return unitPrice.multiply(BigDecimal.valueOf(quantity)); }
}
