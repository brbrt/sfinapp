package hu.brbrt.account;

import com.google.common.base.MoreObjects;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Objects;

public class Account {

    public Integer id;
    @NotBlank(message = "Name is required!")
    public String name;
    public String description;
    public boolean technical;

    public Integer getId() {
        return id;
    }

    public Account setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Account setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Account setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isTechnical() {
        return technical;
    }

    public Account setTechnical(boolean technical) {
        this.technical = technical;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return technical == account.technical &&
                Objects.equals(id, account.id) &&
                Objects.equals(name, account.name) &&
                Objects.equals(description, account.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, technical);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("description", description)
                .add("technical", technical)
                .toString();
    }

}
