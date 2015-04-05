package hu.rbr.sfinapp.transaction.list;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
public class TransactionListItem implements Serializable {

    public Integer id;
    public Date date;
    public Double amount;
    public String description;
    public String accountName;
    public String toAccountName;
    public String comment;
    public String tags;

}
