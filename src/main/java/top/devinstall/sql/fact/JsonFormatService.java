package top.devinstall.sql.fact;

import com.cedarsoftware.util.io.JsonWriter;
import top.devinstall.sql.util.StrUtil;
import top.devinstall.sql.vo.SqlFormatReqVO;
import top.devinstall.sql.vo.SqlFormatResVO;

public class JsonFormatService implements FormatService {

    @Override
    public SqlFormatResVO format(SqlFormatReqVO reqVO) {

        if (!StrUtil.isJson(reqVO.getParamStr())) {
            return new SqlFormatResVO(StrUtil.EMPTY);
        }
        return new SqlFormatResVO(JsonWriter.formatJson(reqVO.getParamStr()));
    }
}
