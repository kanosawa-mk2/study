package com.example.jasper.sample2.model;

import java.time.LocalDate;

/** One row in the purchase history section of sample report 2. */
public class PurchaseHistoryItem {
    private final LocalDate purchaseDate;
    private final String productCode;
    private final String productName;
    private final int quantity;

    public PurchaseHistoryItem(LocalDate purchaseDate, String productCode,
                               String productName, int quantity) {
        this.purchaseDate = purchaseDate;
        this.productCode = productCode;
        this.productName = productName;
        this.quantity = quantity;
    }

    public LocalDate getPurchaseDate() { return purchaseDate; }
    public String getProductCode() { return productCode; }
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
}
