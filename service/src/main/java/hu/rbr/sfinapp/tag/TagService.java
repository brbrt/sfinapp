package hu.rbr.sfinapp.tag;

import hu.rbr.sfinapp.core.service.BaseService;

import java.util.List;


public class TagService extends BaseService {

    private final TagDao tagDao = new TagDao();

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
