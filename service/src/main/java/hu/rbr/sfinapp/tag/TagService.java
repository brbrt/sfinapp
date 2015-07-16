package hu.rbr.sfinapp.tag;

import hu.rbr.sfinapp.core.service.BaseService;
import hu.rbr.sfinapp.core.service.Versioned;
import hu.rbr.sfinapp.core.version.VersionStore;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Singleton
public class TagService extends BaseService implements Versioned {

    public static final String VERSION_KEY = "tag";

    private final TagDao tagDao;
    private final VersionStore versionStore;

    @Inject
    public TagService(TagDao tagDao, VersionStore versionStore) {
        this.tagDao = tagDao;
        this.versionStore = versionStore;
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
        return versionStore.getVersion(VERSION_KEY);
    }

    private void incrementVersion() {
        versionStore.incrementVersion(VERSION_KEY);
    }

}
