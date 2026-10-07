package com.example.jasper.sample2.model;

import java.math.BigDecimal;

/** One sales detail row for sample report 2. */
public class Sample2ReportItem {
    private final String productCode;
    private final String productName;
    private final int quantity;
    private final BigDecimal unitPrice;

    public Sample2ReportItem(String productCode, String productName, int quantity, BigDecimal unitPrice) {
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
