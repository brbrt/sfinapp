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
    public Integer accountId;
    public String accountName;
    public String comment;
    public String tags;

}
