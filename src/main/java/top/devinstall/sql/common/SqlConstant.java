package top.devinstall.sql.common;

import com.intellij.ui.Gray;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhen.wang
 * @date 2022/1/27 17:42
 */
public interface SqlConstant {

    String SQL_Preparing = "Preparing: ";
    String SQL_Parameters = "Parameters: ";
    String[] SQL_START = {"select", "update", "delete", "insert", "create", "alter"};

    String SQL_RN = "\r\n";
    String SQL_N = "\n";
    String MICROSOFT_YAHEI = "Microsoft YaHei UI";
    Color GRAY = Gray._62;
    Color CYAN = new Color(60, 232, 88);
    Font DEFAULT_FONT_16 = new Font(Font.SERIF, Font.PLAIN, 16);
    Font YAHEI_FONT_16 = new Font(MICROSOFT_YAHEI, Font.PLAIN, 16);
    Font YAHEI_FONT_BOLD_16 = new Font(MICROSOFT_YAHEI, Font.BOLD, 16);
    Font SERIF_FONT_16 = new Font(Font.SERIF, Font.PLAIN, 16);

    List<String> NUM_TYPE_LIST = Arrays.asList(Integer.class.getSimpleName(),
            Long.class.getSimpleName(),
            Byte.class.getSimpleName(),
            Short.class.getSimpleName());

    GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
    String[] FONT_NAMES = e.getAvailableFontFamilyNames();
    List<String> SYSTEM_FONT_LIST = Arrays.asList(FONT_NAMES);
}
