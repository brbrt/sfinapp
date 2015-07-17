package hu.rbr.sfinapp.core.version;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.inject.Inject;
import java.lang.annotation.*;

public class VersionedOperationInterceptor implements MethodInterceptor {

    @Inject
    private VersionStore versionStore;

    @Override
    public Object invoke(MethodInvocation ctx) throws Throwable {
        Object result = ctx.proceed();

        String versionKey = getVersionKey(ctx);
        if (versionKey != null) {
            versionStore.incrementVersion(versionKey);
        }

        return result;
    }

    private String getVersionKey(MethodInvocation ctx) {
        for (Annotation ann : ctx.getMethod().getDeclaredAnnotations()) {
            if (ann instanceof VersionedOperation) {
                VersionedOperation voAnn = (VersionedOperation)ann;
                return voAnn.value();
            }
        }

        return null;
    }

}
