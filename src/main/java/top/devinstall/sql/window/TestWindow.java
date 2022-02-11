package top.devinstall.sql.window;

import com.intellij.uiDesigner.core.GridConstraints;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

@Data
public class TestWindow {

    private JPanel mainPanel;
    private JRadioButton radioSqlFormat;
    private JRadioButton radioMybatis;
    private List<String> tes = Arrays.asList(
            StringUtils.EMPTY,
            "Mybatis",
            "SQL",
            StringUtils.EMPTY,
            StringUtils.EMPTY
    );

    public TestWindow() {
        radioSqlFormat = new JRadioButton();
        radioMybatis = new JRadioButton();
        mainPanel = new JPanel(new GridLayout(1, 5));
// 创建 3 行 3 列 的网格布局
        GridConstraints gcs = new GridConstraints();
        gcs.setColumn(2);
        gcs.setRow(1);
        gcs.setFill(GridConstraints.FILL_HORIZONTAL);
        int index = 0;
        for (String te : tes) {
            if (StringUtils.EMPTY.equals(te)) {
                mainPanel.add(new JLabel(index + ""));
            } else {
                JLabel jLabel = new JLabel();
                jLabel.setText(te);
                mainPanel.add(jLabel);
            }
            index++;
        }
    }

    public static void main(String[] args) {


        JFrame frame = new JFrame("TestWindow");
        frame.setContentPane(new TestWindow().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
