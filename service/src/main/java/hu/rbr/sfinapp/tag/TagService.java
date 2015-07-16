package hu.rbr.sfinapp.tag;

import hu.rbr.sfinapp.core.service.BaseService;
import hu.rbr.sfinapp.core.service.Versioned;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Singleton
public class TagService extends BaseService implements Versioned {

    private final AtomicInteger version = new AtomicInteger();

    private final TagDao tagDao;

    @Inject
    public TagService(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    public List<Tag> getAll() {
        return tagDao.getAll();
    }

    public Tag get(int id) {
        return tagDao.get(id);
    }

    public Tag create(@Valid @NotNull Tag tag) {
        Tag created = tagDao.create(tag);
        incrementVersion();
        return created;
    }

    public Tag update(int id, @Valid @NotNull Tag tag) {
        Tag updated = tagDao.update(id, tag);
        incrementVersion();
        return updated;
    }

    public void delete(int id) {
        tagDao.delete(id);
        incrementVersion();
    }

    @Override
    public long getVersion() {
        return version.get();
    }

    private void incrementVersion() {
        version.incrementAndGet();
    }

}
