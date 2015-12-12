package hu.rbr.sfinapp.tag;

import hu.rbr.sfinapp.core.service.BaseService;
import hu.rbr.sfinapp.core.version.VersionedOperation;
import hu.rbr.sfinapp.tag.command.CreateTagCommand;
import hu.rbr.sfinapp.tag.command.DeleteTagCommand;
import hu.rbr.sfinapp.tag.command.UpdateTagCommand;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TagCommandService extends BaseService {

    public static final String VERSION_KEY = "tag";

    private final TagDao tagDao;

    @Inject
    public TagCommandService(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @VersionedOperation(VERSION_KEY)
    public void create(CreateTagCommand command) {
        Tag newTag = new Tag();
        newTag.name = command.name;
        newTag.description = command.description;

        tagDao.create(newTag);
    }

    @VersionedOperation(VERSION_KEY)
    public void update(UpdateTagCommand command) {
        Tag tag = new Tag();
        tag.id = command.id;
        tag.name = command.name;
        tag.description = command.description;

        tagDao.update(command.id, tag);
    }

    @VersionedOperation(VERSION_KEY)
    public void delete(DeleteTagCommand command) {
        tagDao.delete(command.id);
    }

}
