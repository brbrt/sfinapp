package hu.rbr.sfinapp.tag;

import com.google.common.base.MoreObjects;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	public Integer id;
    public String name;
    public String description;
    public Integer parentId;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("description", description)
                .add("parentId", parentId)
                .toString();
    }
}
