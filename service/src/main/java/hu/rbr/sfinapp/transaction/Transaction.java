package hu.rbr.sfinapp.transaction;

import com.google.common.base.MoreObjects;
import hu.rbr.sfinapp.transaction.validator.ValidTransferType;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@XmlRootElement
@ValidTransferType
public class Transaction implements Serializable {

    public Integer id;
    public Date date;
    public Double amount;
    public String description;
    public TransactionType type;
    public Integer accountId;
    public Integer toAccountId;
    public String comment;
    public Collection<Integer> tagIds;

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
