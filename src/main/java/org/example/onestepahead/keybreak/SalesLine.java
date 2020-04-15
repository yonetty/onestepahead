package org.example.onestepahead.keybreak;

import java.time.LocalDate;

public class SalesLine {

    private String salesDate;

    private String productCode;

    private int unitPrice;

    private int quantity;

    private int amount;

    public SalesLine(String salesDate, String productCode, int unitPrice, int quantity, int amount) {
        this.salesDate = salesDate;
        this.productCode = productCode;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.amount = amount;
    }

    public String getSalesDate() {
        return salesDate;
    }

    public String getProductCode() {
        return productCode;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getAmount() {
        return amount;
    }

    private SalesLine() {}
}
