package ukma.eCommerce.util.repository;

import java.util.Collection;

/**
 * <p>
 *     Contains repository utils
 * </p>
 * Created by Максим on 11/3/2016.
 */
public final class RepoUtils {

    private RepoUtils() {
        throw new RuntimeException("shouldn't be invoked");
    }

    public static <T> T tryGetFirst(Collection<T> src) {
        return src == null || src.isEmpty() ? null : src.iterator().next();
    }

}
