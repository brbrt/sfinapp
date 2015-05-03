package hu.rbr.sfinapp.tag;

import org.hibernate.validator.constraints.NotBlank;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	public Integer id;

    @NotBlank(message = "Name is required!")
    public String name;

    public String description;

    public Integer parentId;
	
}
