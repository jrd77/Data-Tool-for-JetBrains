
import com.cedarsoftware.util.io.JsonWriter;
import org.junit.Test;
import top.devinstall.sql.fact.FormatService;
import top.devinstall.sql.fact.MybatisFormatService;
import top.devinstall.sql.fact.SqlFormatService;
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
     * longSql测试
     */
    @Test
    public void longSqlTest(){
        String longSql="";
        FormatService formatService=new SqlFormatService();
        SqlFormatReqVO sqlFormatReqVO=new SqlFormatReqVO();
        sqlFormatReqVO.setParamStr(longSql);
        SqlFormatResVO format = formatService.format(sqlFormatReqVO);
        System.out.println(format.getResult());
    }

//    /**
//     * str测试
//     */
//    @Test
//    public void strTest() {
//        String trim = StringUtils.trim(" s21 d3 ");
//        System.out.println(trim);
//    }

    /**
     * json测试
     */
    public void jsonTest() {
        String json = JsonWriter.formatJson("{\"resCode\":\"000000\",\"resMsg\":\"success\",\"data\":{\"pageNum\":1,\"pageSize\":10,\"total\":582,\"result\":[{\"orderNo\":\"46550101202299\",\"reqTime\":\"2022-02-09 16:41:26\",\"ownerName\":\"刘文迪\",\"renterName\":\"张飞\",\"rentTime\":\"2022-02-11 09:00:00\",\"revertTime\":\"2022-02-13 09:00:00\",\"reqAcceptTime\":\"2022-02-09 16:41:29\",\"isDeal\":\"0\",\"rentAmt\":\"76\",\"status\":\"结束\",\"reqAddr\":\"上海市浦东新区世纪大道1\",\"userCarCityName\":\"上海\",\"insureTotalPrices\":\"-119\",\"carbrandTxt\":\"宝骏\",\"carTypeTxt\":\"310\",\"year\":\"2020\",\"riskStatus\":\"2\"},{\"orderNo\":\"31170201202299\",\"reqTime\":\"2022-02-09 11:27:30\",\"ownerName\":\"刘文迪\",\"renterName\":\"张飞\",\"rentTime\":\"2022-02-11 09:00:00\",\"revertTime\":\"2022-02-13 09:00:00\",\"reqAcceptTime\":\"2022-02-09 11:27:31\",\"isDeal\":\"0\",\"rentAmt\":\"76\",\"status\":\"结束\",\"reqAddr\":\"上海市浦东新区世纪大道1\",\"userCarCityName\":\"上海\",\"insureTotalPrices\":\"-119\",\"carbrandTxt\":\"宝骏\",\"carTypeTxt\":\"310\",\"year\":\"2020\",\"riskStatus\":\"2\"},{\"orderNo\":\"12720301202299\",\"reqTime\":\"2022-02-09 10:23:00\",\"ownerName\":\"刘文迪\",\"renterName\":\"张飞\",\"rentTime\":\"2022-02-11 09:00:00\",\"revertTime\":\"2022-02-13 09:00:00\",\"reqAcceptTime\":\"2022-02-09 10:23:01\",\"isDeal\":\"0\",\"rentAmt\":\"76\",\"status\":\"结束\",\"reqAddr\":\"上海市浦东新区世纪大道1\",\"userCarCityName\":\"上海\",\"insureTotalPrices\":\"-119\",\"carbrandTxt\":\"宝骏\",\"carTypeTxt\":\"310\",\"year\":\"2020\",\"riskStatus\":\"2\"},{\"orderNo\":\"54824301202299\",\"reqTime\":\"2022-02-09 11:24:40\",\"ownerName\":\"刘文迪\",\"renterName\":\"张飞\",\"rentTime\":\"2022-02-11 09:00:00\",\"revertTime\":\"2022-02-14 19:00:00\",\"reqAcceptTime\":\"2022-02-09 11:24:43\",\"isDeal\":\"0\",\"rentAmt\":\"132\",\"status\":\"结束\",\"reqAddr\":\"上海市浦东新区世纪大道1\",\"userCarCityName\":\"上海\",\"insureTotalPrices\":\"-238\",\"carbrandTxt\":\"宝骏\",\"carTypeTxt\":\"310\",\"year\":\"2020\",\"riskStatus\":\"2\"},{\"orderNo\":\"91707290202299\",\"reqTime\":\"2022-02-08 18:47:20\",\"ownerName\":\"刘文迪\",\"renterName\":\"张飞\",\"rentTime\":\"2022-02-10 09:00:00\",\"revertTime\":\"2022-02-13 18:00:00\",\"reqAcceptTime\":\"2022-02-08 18:47:20\",\"isDeal\":\"0\",\"rentAmt\":\"116\",\"status\":\"结束\",\"reqAddr\":\"上海市浦东新区世纪大道1\",\"userCarCityName\":\"上海\",\"insureTotalPrices\":\"-238\",\"carbrandTxt\":\"宝骏\",\"carTypeTxt\":\"310\",\"year\":\"2020\",\"riskStatus\":\"2\"},{\"orderNo\":\"97601390202299\",\"reqTime\":\"2022-02-08 18:53:05\",\"ownerName\":\"刘文迪\",\"renterName\":\"张飞\",\"rentTime\":\"2022-02-10 09:00:00\",\"revertTime\":\"2022-02-14 16:00:00\",\"reqAcceptTime\":\"2022-02-08 18:53:06\",\"isDeal\":\"0\",\"rentAmt\":\"127\",\"status\":\"结束\",\"reqAddr\":\"上海市浦东新区世纪大道1\",\"userCarCityName\":\"上海\",\"insureTotalPrices\":\"-290\",\"carbrandTxt\":\"宝骏\",\"carTypeTxt\":\"310\",\"year\":\"2020\",\"riskStatus\":\"2\"},{\"orderNo\":\"44256390202299\",\"reqTime\":\"2022-02-08 16:23:52\",\"ownerName\":\"刘文迪\",\"renterName\":\"张飞\",\"rentTime\":\"2022-02-10 09:00:00\",\"revertTime\":\"2022-02-13 18:00:00\",\"reqAcceptTime\":\"2022-02-08 16:23:52\",\"isDeal\":\"0\",\"rentAmt\":\"116\",\"status\":\"结束\",\"reqAddr\":\"上海市浦东新区世纪大道1\",\"userCarCityName\":\"上海\",\"insureTotalPrices\":\"-238\",\"carbrandTxt\":\"宝骏\",\"carTypeTxt\":\"310\",\"year\":\"2020\",\"riskStatus\":\"2\"},{\"orderNo\":\"76042290202299\",\"reqTime\":\"2022-02-08 18:04:44\",\"ownerName\":\"刘文迪\",\"renterName\":\"张飞\",\"rentTime\":\"2022-02-10 09:00:00\",\"revertTime\":\"2022-02-13 18:00:00\",\"reqAcceptTime\":\"2022-02-08 18:04:46\",\"isDeal\":\"0\",\"rentAmt\":\"116\",\"status\":\"结束\",\"reqAddr\":\"上海市浦东新区世纪大道1\",\"userCarCityName\":\"上海\",\"insureTotalPrices\":\"-238\",\"carbrandTxt\":\"宝骏\",\"carTypeTxt\":\"310\",\"year\":\"2020\",\"riskStatus\":\"2\"},{\"orderNo\":\"15413332211299\",\"reqTime\":\"2021-12-22 19:41:33\",\"ownerName\":\"张飞\",\"renterName\":\"张飞\",\"rentTime\":\"2022-02-01 09:00:00\",\"revertTime\":\"2022-02-05 09:00:00\",\"reqAcceptTime\":\"2021-12-22 19:41:34\",\"isDeal\":\"0\",\"rentAmt\":\"1508\",\"status\":\"结束\",\"reqAddr\":\"上海市浦东新区世纪大道1\",\"userCarCityName\":\"上海\",\"insureTotalPrices\":\"-20\",\"carbrandTxt\":\"奥迪\",\"carTypeTxt\":\"A1(进口)\",\"year\":\"2020\",\"riskStatus\":\"2\"},{\"orderNo\":\"67667232211299\",\"reqTime\":\"2021-12-22 20:51:15\",\"ownerName\":\"张飞\",\"renterName\":\"张飞\",\"rentTime\":\"2022-02-01 09:00:00\",\"revertTime\":\"2022-02-05 09:00:00\",\"reqAcceptTime\":\"2021-12-22 20:51:18\",\"isDeal\":\"0\",\"rentAmt\":\"1508\",\"status\":\"结束\",\"reqAddr\":\"上海市浦东新区世纪大道1\",\"userCarCityName\":\"上海\",\"insureTotalPrices\":\"-20\",\"carbrandTxt\":\"奥迪\",\"carTypeTxt\":\"A1(进口)\",\"year\":\"2020\",\"riskStatus\":\"2\"}]}}");
        System.out.println(json);
    }
}
