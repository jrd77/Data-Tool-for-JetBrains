package top.devinstall.sql.util;

import top.devinstall.sql.common.SqlConstant;

import java.util.Locale;

public class StrUtil {

    public static final String EMPTY = "";


    public static boolean isMybatis(String beforeTxt) {

        return contains(beforeTxt, SqlConstant.SQL_Parameters) || contains(beforeTxt, SqlConstant.SQL_Preparing);
    }

    public static boolean isSql(String beforeTxt) {

        String trim = beforeTxt.trim();
        String s = trim.toLowerCase(Locale.ROOT);
        boolean isSql = Boolean.FALSE;
        for (String start : SqlConstant.SQL_START) {
            isSql = s.startsWith(start);
            if (isSql) {
                return isSql;
            }
        }
        return isSql;
    }

    public static boolean isBlank(String str) {

        return str == null || str.isEmpty() || str.trim().isEmpty();
    }

    public static boolean isNotBlank(String str) {

        return !isBlank(str);
    }

    public static boolean isJson(String str) {

        if (isBlank(str)) {
            return false;
        }
        if (str.startsWith("{") && str.endsWith("}")) {
            return true;
        }
        if (str.startsWith("[") && str.endsWith("]")) {
            return true;
        }
        return false;
    }

    public static boolean contains(CharSequence seq, CharSequence searchSeq) {
        if (seq != null && searchSeq != null) {
            String s = seq.toString();
            return s.contains(searchSeq.toString());
        } else {
            return false;
        }
    }


}
