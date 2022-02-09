package top.devinstall.sql.vo;

import top.devinstall.sql.common.FormatEnum;

/**
 * @author zhen.wang
 * @description TODO
 * @date 2022/1/27 16:42
 */

public class SqlFormatReqVO {

    private String paramStr;

    private FormatEnum formatEnum;

    public String getParamStr() {
        return paramStr;
    }

    public SqlFormatReqVO setParamStr(String paramStr) {
        this.paramStr = paramStr;
        return this;
    }

    public FormatEnum getFormatEnum() {
        return formatEnum;
    }

    public SqlFormatReqVO setFormatEnum(FormatEnum formatEnum) {
        this.formatEnum = formatEnum;
        return this;
    }


}
