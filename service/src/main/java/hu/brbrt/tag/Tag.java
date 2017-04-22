package hu.brbrt.tag;

import com.google.common.base.MoreObjects;
import org.hibernate.validator.constraints.NotBlank;

public class Tag {

    private Integer id;
    @NotBlank(message = "Name is required!")
    private String name;
    private String description;

    public Integer getId() {
        return id;
    }

    public Tag setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Tag setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Tag setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("description", description)
                .toString();
    }

}
