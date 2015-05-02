package hu.rbr.sfinapp.transaction.list;

import hu.rbr.sfinapp.transaction.Transaction;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class TransactionListItem extends Transaction implements Serializable {

    public String accountName;
    public String toAccountName;
    public String tagNames;

}
