package hu.rbr.sfinapp.transaction;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@XmlRootElement
public class Transaction implements Serializable {

    public Integer id;

    @NotNull(message = "Date is required!")
    public Date date;

    @NotNull(message = "Amount is required!")
    public Double amount;

    @NotBlank(message = "Description is required!")
    public String description;

    @NotNull(message = "Type is required!")
    public TransactionType type;

    @NotNull(message = "Account is required!")
    public Integer accountId;

    public Integer toAccountId;

    public String comment;

    @NotNull(message = "You must specify one or more tags!")
    @Size(min = 1, message = "You must specify one or more tags!")
    public Collection<Integer> tagIds;

}
