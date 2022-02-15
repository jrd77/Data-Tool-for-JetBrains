package top.devinstall.sql.util;

import top.devinstall.sql.fact.MybatisFormatService;
import top.devinstall.sql.fact.SqlFormatService;

import java.util.logging.Logger;

public class InitUtil {

    private static final Logger logger = Logger.getLogger(InitUtil.class.getName());



    public static void initBaseData() {

        logger.info("init base data start");
        SingleTonUtil.put(new MybatisFormatService());
        SingleTonUtil.put(new SqlFormatService());
        logger.info("init base data finished");
    }

}
