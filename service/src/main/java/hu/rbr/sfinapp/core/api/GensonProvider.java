package hu.rbr.sfinapp.core.api;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import java.text.SimpleDateFormat;


@Provider
public class GensonProvider implements ContextResolver<Genson> {

    public static final String API_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    private final Genson genson;

    public GensonProvider() {
        genson = new GensonBuilder()
                .useIndentation(true)
                .useDateFormat(new SimpleDateFormat(API_DATE_FORMAT))
                .setSkipNull(true)
                .create();
    }

    @Override
    public Genson getContext(Class<?> type) {
        return genson;
    }
}