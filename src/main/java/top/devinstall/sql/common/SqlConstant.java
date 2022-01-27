package top.devinstall.sql.common;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhen.wang
 * @description TODO
 * @date 2022/1/27 17:42
 */
public interface SqlConstant {

    String SQL_Preparing = "Preparing: ";
    String SQL_Parameters = "Parameters: ";
    String SQL_RN = "\r\n";
    String SQL_N = "\n";

    List<String> NUM_TYPE_LIST = Arrays.asList(Integer.class.getSimpleName(),
            Long.class.getSimpleName(),
            Byte.class.getSimpleName(),
            Short.class.getSimpleName());
}
