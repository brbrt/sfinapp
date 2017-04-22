package hu.brbrt.tag;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(path = "api/tag")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @RequestMapping(method = GET)
    public List<Tag> getAll() {
        return tagService.getAll();
    }

    @RequestMapping(method = GET, path = "/{id}")
    public Tag get(@PathVariable("id") int id) {
        return tagService.get(id);
    }

    @RequestMapping(method = POST)
    public void create(@RequestBody Tag tag) {
        tagService.create(tag);
    }

    @RequestMapping(method = PUT)
    public void update(@RequestBody Tag tag) {
        tagService.update(tag);
    }

    @RequestMapping(method = DELETE, path = "/{id}")
    public void delete(@PathVariable("id") int id) {
        tagService.delete(id);
    }

}
