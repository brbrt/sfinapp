package hu.brbrt.transaction;

import com.google.common.base.MoreObjects;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public class Transaction {

    private Integer id;
    @NotNull(message = "Date is required")
    private LocalDate date;
    @NotNull(message = "Amount is required")
    private Double amount;
    @NotBlank(message = "Description is required")
    private String description;
    @NotNull(message = "Type is required")
    private TransactionType type;
    @NotNull(message = "Account is required")
    private Integer accountId;
    private Integer toAccountId;
    private String comment;
    @NotNull(message = "You must specify one or more tags")
    @Size(min = 1, message = "You must specify one or more tags")
    private List<Integer> tagIds;

    public Integer getId() {
        return id;
    }

    public Transaction setId(Integer id) {
        this.id = id;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public Transaction setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public Transaction setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Transaction setDescription(String description) {
        this.description = description;
        return this;
    }

    public TransactionType getType() {
        return type;
    }

    public Transaction setType(TransactionType type) {
        this.type = type;
        return this;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public Transaction setAccountId(Integer accountId) {
        this.accountId = accountId;
        return this;
    }

    public Integer getToAccountId() {
        return toAccountId;
    }

    public Transaction setToAccountId(Integer toAccountId) {
        this.toAccountId = toAccountId;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Transaction setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public List<Integer> getTagIds() {
        return tagIds;
    }

    public Transaction setTagIds(List<Integer> tagIds) {
        this.tagIds = tagIds;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("date", date)
                .add("amount", amount)
                .add("description", description)
                .add("type", type)
                .add("accountId", accountId)
                .add("toAccountId", toAccountId)
                .add("comment", comment)
                .add("tagIds", tagIds)
                .toString();
    }
}
