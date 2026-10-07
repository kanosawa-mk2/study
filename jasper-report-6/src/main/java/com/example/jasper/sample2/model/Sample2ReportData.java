package com.example.jasper.sample2.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/** Header and detail data for sample report 2. */
public class Sample2ReportData {
    private final LocalDate issueDate;
    private final String customerName;
    private final List<Sample2ReportItem> items;

    public Sample2ReportData(LocalDate issueDate, String customerName, List<Sample2ReportItem> items) {
        this.issueDate = issueDate;
        this.customerName = customerName;
        this.items = items;
    }

    public LocalDate getIssueDate() { return issueDate; }
    public String getCustomerName() { return customerName; }
    public List<Sample2ReportItem> getItems() { return items; }

    public BigDecimal getTotalAmount() {
        return items.stream().map(Sample2ReportItem::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
