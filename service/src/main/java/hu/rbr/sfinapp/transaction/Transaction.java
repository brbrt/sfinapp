package hu.rbr.sfinapp.transaction;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@XmlRootElement
public class Transaction implements Serializable {

    public Integer id;
    public Date date;
    public Double amount;
    public String description;
    public TransactionType type;
    public Integer accountId;
    public String comment;
    public Collection<Integer> tags;

}
