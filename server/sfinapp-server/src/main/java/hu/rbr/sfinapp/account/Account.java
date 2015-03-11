package hu.rbr.sfinapp.account;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	public Integer id;
    public String name;
    public String description;
	
}
