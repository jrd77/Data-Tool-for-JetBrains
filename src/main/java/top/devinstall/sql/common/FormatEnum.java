package top.devinstall.sql.common;

import top.devinstall.sql.fact.JsonFormatService;
import top.devinstall.sql.fact.MybatisFormatService;
import top.devinstall.sql.fact.SqlFormatService;

/**
 * @Classname FormatEnum
 * @Description 需要转化的类型
 * @Date 2022/1/27 16:48
 * @Author W.Z
 */
public enum FormatEnum {

    LONG_SQL, MYBATIS, JSON;

    public static Class<?> getFormatService(FormatEnum formatEnum) {

        Class<?> formatServiceClass;
        if (FormatEnum.MYBATIS.equals(formatEnum)) {
            formatServiceClass = MybatisFormatService.class;
        } else if (FormatEnum.LONG_SQL.equals(formatEnum)) {
            formatServiceClass = SqlFormatService.class;
        } else {
            formatServiceClass = JsonFormatService.class;
        }
        return formatServiceClass;
    }
}
