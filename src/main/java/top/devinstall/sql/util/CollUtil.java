package top.devinstall.sql.util;

import java.util.Collection;

public class CollUtil {


    public static boolean isNotEmpty(Collection coll) {

        return !isEmpty(coll);
    }

    public static boolean isEmpty(Collection coll) {

        return coll == null || coll.isEmpty();
    }
}
