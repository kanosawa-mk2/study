package com.example.jasper.sample.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/** Header and detail data for the sample sales report. */
public class ReportData {
    private final LocalDate issueDate;
    private final String customerName;
    private final List<ReportItem> items;

    public ReportData(LocalDate issueDate, String customerName, List<ReportItem> items) {
        this.issueDate = issueDate;
        this.customerName = customerName;
        this.items = items;
    }

    public LocalDate getIssueDate() { return issueDate; }
    public String getCustomerName() { return customerName; }
    public List<ReportItem> getItems() { return items; }

    public BigDecimal getTotalAmount() {
        return items.stream().map(ReportItem::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
