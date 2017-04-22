package hu.brbrt.tag;

import hu.brbrt.TestBase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TagTest extends TestBase {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    private TagController tagController;

    @Test
    public void crud() {
        tagController.create(new Tag()
                .setName("T1")
                .setDescription("T1D")
        );

        List<Tag> tags = tagController.getAll();
        assertThat(tags, hasSize(1));
        Tag tag = tags.get(0);
        assertThat(tag.getId(), notNullValue());
        assertThat(tag.getName(), is("T1"));
        assertThat(tag.getDescription(), is("T1D"));

        tagController.update(tag
                .setName("T1-UPD")
                .setDescription("T1D-UPD")
        );

        Tag updatedTag = tagController.get(tag.getId());
        assertThat(updatedTag.getId(), is(tag.getId()));
        assertThat(updatedTag.getName(), is("T1-UPD"));
        assertThat(updatedTag.getDescription(), is("T1D-UPD"));

        tagController.delete(tag.getId());

        assertThat(tagController.getAll(), empty());
    }

    @Test
    public void validation() {
        exception.expect(ConstraintViolationException.class);
        tagController.create(new Tag().setDescription("XYZ"));
    }

}
