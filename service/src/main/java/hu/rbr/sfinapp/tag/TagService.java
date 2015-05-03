package hu.rbr.sfinapp.tag;

import hu.rbr.sfinapp.core.service.BaseService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class TagService extends BaseService {

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

    public Tag create(Tag tag) {
        return tagDao.create(tag);
    }

    public Tag update(int id, Tag tag) {
        return tagDao.update(id, tag);
    }

    public void delete(int id) {
        tagDao.delete(id);
    }

}
