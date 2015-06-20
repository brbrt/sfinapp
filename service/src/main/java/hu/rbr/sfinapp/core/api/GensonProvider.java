package hu.rbr.sfinapp.core.api;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import java.text.SimpleDateFormat;


@Provider
public class GensonProvider implements ContextResolver<Genson> {

    private final Genson genson;

    public GensonProvider() {
        genson = new GensonBuilder()
                .useIndentation(true)
                .useDateFormat(new SimpleDateFormat(Dates.DATE_FORMAT))
                .setSkipNull(true)
                .create();
    }

    @Override
    public Genson getContext(Class<?> type) {
        return genson;
    }
}