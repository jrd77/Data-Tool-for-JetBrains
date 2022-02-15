package top.devinstall.sql.util;

import org.apache.commons.lang3.StringUtils;
import top.devinstall.sql.common.SqlConstant;

public class StrUtil {


    public static boolean isMybatis(String beforeTxt) {

        return StringUtils.contains(beforeTxt, SqlConstant.SQL_Parameters) || StringUtils.contains(beforeTxt, SqlConstant.SQL_Preparing);
    }
}
