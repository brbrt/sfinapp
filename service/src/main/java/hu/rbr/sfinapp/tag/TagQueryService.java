package hu.rbr.sfinapp.tag;

import hu.rbr.sfinapp.core.service.BaseService;
import hu.rbr.sfinapp.core.service.Versioned;
import hu.rbr.sfinapp.core.version.VersionStore;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class TagQueryService extends BaseService implements Versioned {

    private final TagDao tagDao;
    private final VersionStore versionStore;

    @Inject
    public TagQueryService(TagDao tagDao, VersionStore versionStore) {
        this.tagDao = tagDao;
        this.versionStore = versionStore;
    }

    public List<Tag> getAll() {
        return tagDao.getAll();
    }

    public Tag get(int id) {
        return tagDao.get(id);
    }

    @Override
    public long getVersion() {
        return versionStore.getVersion(TagCommandService.VERSION_KEY);
    }

}
