package hu.brbrt.overview;


import hu.brbrt.core.TransactionType;

import java.time.LocalDate;

public class OverviewItem {

    private Integer transactionId;
    private LocalDate date;
    private Double amount;
    private String description;
    private TransactionType type;
    private String accountName;
    private String toAccountName;
    private String tagName;

    public Integer getTransactionId() {
        return transactionId;
    }

    public OverviewItem setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public OverviewItem setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public OverviewItem setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OverviewItem setDescription(String description) {
        this.description = description;
        return this;
    }

    public TransactionType getType() {
        return type;
    }

    public OverviewItem setType(TransactionType type) {
        this.type = type;
        return this;
    }

    public String getAccountName() {
        return accountName;
    }

    public OverviewItem setAccountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public String getToAccountName() {
        return toAccountName;
    }

    public OverviewItem setToAccountName(String toAccountName) {
        this.toAccountName = toAccountName;
        return this;
    }

    public String getTagName() {
        return tagName;
    }

    public OverviewItem setTagName(String tagName) {
        this.tagName = tagName;
        return this;
    }

}
