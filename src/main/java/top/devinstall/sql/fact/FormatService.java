package top.devinstall.sql.fact;

import top.devinstall.sql.vo.SqlFormatReqVO;
import top.devinstall.sql.vo.SqlFormatResVO;

/**
 * @author zhen.wang
 * @description 格式化
 * @date 2022/1/27 16:41
 */
public interface FormatService {


    /**
     * 格式化
     *
     * @param reqVO
     * @return
     */
    SqlFormatResVO format(SqlFormatReqVO reqVO);
}
