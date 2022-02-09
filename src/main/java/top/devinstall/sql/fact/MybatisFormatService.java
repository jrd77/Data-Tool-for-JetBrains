package top.devinstall.sql.fact;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import top.devinstall.sql.common.SqlConstant;
import top.devinstall.sql.vo.SqlFormatReqVO;
import top.devinstall.sql.vo.SqlFormatResVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author zhen.wang
 * @description mybatis日志转换
 * @date 2022/1/27 17:18
 */
public class MybatisFormatService implements FormatService {
    @Override
    public SqlFormatResVO format(SqlFormatReqVO reqVO) {

//        String sql="==>  Preparing: update send_sms_mq set status = ?, update_time = ? where id = ? and is_delete = 0 \n" +
//                "==> Parameters: 2(Integer), 2022-01-27 17:39:51.853(Timestamp), 1234(Long)\n" +
//                "<==    Updates: 1";
        String beforeSql = reqVO.getParamStr();
        String lineSeparator = SqlConstant.SQL_N;
        if (beforeSql.contains(SqlConstant.SQL_RN)) {
            lineSeparator = SqlConstant.SQL_RN;
        }
        String[] split = reqVO.getParamStr().split(lineSeparator);
        String sqlTemplate = null;
        List<String> sqlParams = null;
        List<String> logs = new ArrayList<>();
        for (String s : split) {
            if (Objects.isNull(sqlTemplate) && s.contains(SqlConstant.SQL_Preparing)) {
                int index = s.indexOf(SqlConstant.SQL_Preparing);
                sqlTemplate = s.substring(index + SqlConstant.SQL_Preparing.length());
            }
            if (Objects.isNull(sqlParams) && s.contains(SqlConstant.SQL_Parameters)) {
                int index = s.indexOf(SqlConstant.SQL_Parameters);
                String sqlPramLog = s.substring(index + SqlConstant.SQL_Parameters.length());
                sqlParams = Arrays.asList(sqlPramLog.split(","));
            }
            if (StringUtils.isNotBlank(sqlTemplate) && Objects.nonNull(sqlParams)) {
                break;
            }
        }
        if (CollectionUtils.isNotEmpty(sqlParams)) {
            for (String sqlParam : sqlParams) {
                String[] param = sqlParam.split("\\(");
                if (param.length != 2) {
                    continue;
                }
                String value = param[0].trim();
                String typeStr = param[1].trim();
                String type = typeStr.substring(0, typeStr.length() - 1);
                String logParam;
                if (SqlConstant.NUM_TYPE_LIST.contains(type)) {
                    logParam = value;
                } else {
                    logParam = "'" + value + "'";
                }
                logs.add(logParam);
            }
        }

        if (Objects.nonNull(sqlTemplate) && sqlTemplate.contains("?")) {
            sqlTemplate = sqlTemplate.replace("?", "%s");
            Object[] objects = logs.toArray();
            String format = String.format(sqlTemplate, objects);
            SqlFormatResVO resVO = new SqlFormatResVO();
            resVO.setResult(format);
            return resVO;
        }

        return null;
    }
}
