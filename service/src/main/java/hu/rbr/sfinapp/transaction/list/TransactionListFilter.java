package hu.rbr.sfinapp.transaction.list;

import java.util.Date;

@SuppressWarnings("UnusedDeclaration")
public class TransactionListFilter {

    public Date from;
    public Date to;
    public String description;
    public String tag;

    public TransactionListFilter() {}

    public TransactionListFilter(Date from, Date to, String description, String tag) {
        this.from = from;
        this.to = to;
        this.description = description;
        this.tag = tag;
    }

}
