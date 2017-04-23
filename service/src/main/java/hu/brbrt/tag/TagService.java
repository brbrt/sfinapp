package hu.brbrt.tag;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Validated
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> getAll() {
        return tagRepository.getAll();
    }

    public Tag get(int id) {
        return tagRepository.get(id);
    }

    public int create(@NotNull @Valid Tag tag) {
        return tagRepository.create(tag);
    }

    public void update(@NotNull @Valid Tag tag) {
        tagRepository.update(tag);
    }

    public void delete(int id) {
        tagRepository.delete(id);
    }

}
