package hu.brbrt.tag;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(path = "tag")
@Validated
public class TagController {

    private final TagRepository tagRepository;

    public TagController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @RequestMapping(method = GET)
    public List<Tag> getAll() {
        return tagRepository.getAll();
    }

    @RequestMapping(method = GET, path = "/{id}")
    public Tag get(@PathVariable("id") int id) {
        return tagRepository.get(id);
    }

    @RequestMapping(method = POST)
    public void create(@RequestBody @NotNull @Valid Tag tag) {
        tagRepository.create(tag);
    }

    @RequestMapping(method = PUT)
    public void update(@RequestBody @NotNull @Valid Tag tag) {
        tagRepository.update(tag);
    }

    @RequestMapping(method = DELETE, path = "/{id}")
    public void delete(@PathVariable("id") int id) {
        tagRepository.delete(id);
    }

}
