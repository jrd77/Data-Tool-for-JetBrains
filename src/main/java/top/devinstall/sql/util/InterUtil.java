package top.devinstall.sql.util;

import org.junit.Assert;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * InternationalUtil
 * 国际化语言工具
 */
public class InterUtil {

    private static final String DEFAULT_LANGUAGE_ZH = "language/zh";
    private static final String DEFAULT_LANGUAGE_EN = "language/en";
    private static final String DEFAULT_LANGUAGE = "zh";
    private static Logger logger = Logger.getLogger(InterUtil.class.getName());
    private static ResourceBundle bundle = null;

    static {
        Locale aDefault = Locale.getDefault();
        if (aDefault.getLanguage().equals(DEFAULT_LANGUAGE)) {
            bundle = ResourceBundle.getBundle(DEFAULT_LANGUAGE_ZH);
        } else {
            bundle = ResourceBundle.getBundle(DEFAULT_LANGUAGE_EN);
        }
    }

    /**
     * 获取国际化提示
     *
     * @param name
     * @return
     */
    public static String getValue(String name) {

        Assert.assertTrue("can't get language tips file", Objects.nonNull(bundle));
        return bundle.getString(name);
    }
}
