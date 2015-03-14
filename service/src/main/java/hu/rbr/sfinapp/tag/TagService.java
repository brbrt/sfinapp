package hu.rbr.sfinapp.tag;

import hu.rbr.sfinapp.core.db.BaseDao;
import hu.rbr.sfinapp.core.service.BaseService;


public class TagService extends BaseService<Tag> {

    private final TagDao tagDao = new TagDao();

    @Override
    protected BaseDao<Tag> getDao() {
        return tagDao;
    }

}
