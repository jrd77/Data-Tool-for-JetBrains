package top.devinstall.sql.fact;

import com.github.vertical_blank.sqlformatter.SqlFormatter;
import top.devinstall.sql.vo.SqlFormatReqVO;
import top.devinstall.sql.vo.SqlFormatResVO;

/**
 * @author zhen.wang
 * @description TODO
 * @date 2022/1/27 16:45
 */
public class SqlFormatService implements FormatService {
    @Override
    public SqlFormatResVO format(SqlFormatReqVO reqVO) {
        String format = SqlFormatter.format(reqVO.getSql());
        SqlFormatResVO resVO = new SqlFormatResVO();
        resVO.setResult(format);
        return resVO;
    }

}
