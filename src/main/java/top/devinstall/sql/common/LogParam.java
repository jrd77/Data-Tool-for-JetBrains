package top.devinstall.sql.common;

/**
 * @author zhen.wang
 * @date 2022/1/27 17:53
 */
public class LogParam {

    private String value;
    private String type;

    public String getValue() {
        return value;
    }

    public LogParam setValue(String value) {
        this.value = value;
        return this;
    }

    public String getType() {
        return type;
    }

    public LogParam setType(String type) {
        this.type = type;
        return this;
    }

    public LogParam(String value, String type) {
        this.value = value;
        this.type = type;
    }
}
