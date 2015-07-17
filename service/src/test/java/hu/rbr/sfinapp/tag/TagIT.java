package hu.rbr.sfinapp.tag;

import hu.rbr.sfinapp.IntegrationTestBase;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;

public class TagIT extends IntegrationTestBase {

    private static class TagListType extends GenericType<List<Tag>> {}

    @Test
    public void addAndQuery() throws Exception {
        List<Tag> tags = tagWebTarget()
                .request()
                .get(new TagListType());

        assertThat(tags.size(), equalTo(0));


        Tag t1 = createTag("1-tag", "descr..");
        Tag t2 = createTag("2-tag", "otherrr");


        tags = tagWebTarget()
                .request()
                .get(new TagListType());

        assertThat(tags.size(), equalTo(2));

        assertThat(tags, hasItems(
                new TagMatcher(t1),
                new TagMatcher(t2)
        ));


        Integer id = tags.get(0).id;

        Tag tag = tagWebTarget()
                .path(id.toString())
                .request()
                .get(Tag.class);

        assertThat(tag.id, equalTo(id));
        assertThat(tag, new TagMatcher(t2));
    }

    private WebTarget tagWebTarget() {
        return webTarget("tags");
    }

    private Tag createTag(String name, String description) {
        Tag tag = new Tag();
        tag.name = name;
        tag.description = description;

        tagWebTarget()
                .request()
                .post(Entity.json(tag));

        return tag;
    }

}
