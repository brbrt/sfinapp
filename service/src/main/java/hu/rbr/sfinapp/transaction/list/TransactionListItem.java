package hu.rbr.sfinapp.transaction.list;

import com.google.common.base.MoreObjects;
import hu.rbr.sfinapp.transaction.Transaction;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class TransactionListItem extends Transaction implements Serializable {

    public String accountName;
    public String toAccountName;
    public String tagNames;

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
                .add("accountName", accountName)
                .add("toAccountName", toAccountName)
                .add("tagNames", tagNames)
                .toString();
    }
}
