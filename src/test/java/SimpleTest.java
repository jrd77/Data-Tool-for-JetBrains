import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import top.devinstall.sql.fact.MybatisFormatService;
import top.devinstall.sql.vo.SqlFormatReqVO;
import top.devinstall.sql.vo.SqlFormatResVO;

/**
 * @author zhen.wang
 * @description TODO
 * @date 2022/1/27 16:52
 */
public class SimpleTest {

    /**
     * sql解析测试
     */
    @Test
    public void sqlTest() {
        MybatisFormatService sqlFormatService = new MybatisFormatService();
        SqlFormatReqVO reqVO = new SqlFormatReqVO();
        reqVO.setParamStr("==>  Preparing: update send_sms_mq set status = ?, update_time = ? where id = ? and is_delete = 0 \n" +
                "==> Parameters: 2(Integer), 2022-01-27 17:39:51.853(Timestamp), 1234(Long)\n" +
                "<==    Updates: 1");
        SqlFormatResVO format = sqlFormatService.format(reqVO);
        System.out.println(format.getResult());
    }

    /**
     * str测试
     */
    @Test
    public void strTest() {
        String trim = StringUtils.trim(" s21 d3 ");
        System.out.println(trim);
    }
}
