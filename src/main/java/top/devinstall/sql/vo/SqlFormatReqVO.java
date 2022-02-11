package top.devinstall.sql.vo;

import lombok.Data;
import top.devinstall.sql.common.FormatEnum;

/**
 * @author zhen.wang
 * @description TODO
 * @date 2022/1/27 16:42
 */
@Data
public class SqlFormatReqVO {

    private String paramStr;

    private FormatEnum formatEnum;
}
