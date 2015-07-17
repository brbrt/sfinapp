package hu.rbr.sfinapp.tag;

import hu.rbr.sfinapp.core.service.BaseService;
import hu.rbr.sfinapp.core.service.Versioned;
import hu.rbr.sfinapp.core.version.VersionStore;
import hu.rbr.sfinapp.core.version.VersionedOperation;

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

    @VersionedOperation(VERSION_KEY)
    public Tag create(@Valid @NotNull Tag tag) {
        return tagDao.create(tag);
    }

    @VersionedOperation(VERSION_KEY)
    public Tag update(int id, @Valid @NotNull Tag tag) {
        return tagDao.update(id, tag);
    }

    @VersionedOperation(VERSION_KEY)
    public void delete(int id) {
        tagDao.delete(id);
    }

    @Override
    public long getVersion() {
        return versionStore.getVersion(VERSION_KEY);
    }

}
