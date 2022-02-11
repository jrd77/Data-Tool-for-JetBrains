package top.devinstall.sql.util;

import top.devinstall.sql.fact.MybatisFormatService;
import top.devinstall.sql.fact.SqlFormatService;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class InitUtil {

    private static final Logger logger = Logger.getLogger(InitUtil.class.getName());

    public void initUI(JPanel mainPanel) {

        GridBagLayout gbl = new GridBagLayout();
        mainPanel.setLayout(gbl);
        Dimension panelSize = mainPanel.getSize();
        if (panelSize.height == 0 && panelSize.width == 0) {
            panelSize = new Dimension(200, 500);
        }
        int width = (int) (panelSize.width * 0.1);
        int height = (int) (panelSize.height * 0.1);

        GridBagConstraints gbc = new GridBagConstraints();
        JRadioButton mybatisRadio = new JRadioButton();
        mybatisRadio.setText("Mybatis");
        mybatisRadio.setSelected(true);

        JRadioButton sqlRadio = new JRadioButton();
        sqlRadio.setText("SQL");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(mybatisRadio);
        buttonGroup.add(sqlRadio);

        //单选框 布局
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        mainPanel.add(mybatisRadio, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        mainPanel.add(sqlRadio, gbc);

        //标签 Before 布局
        JLabel jLabel = new JLabel();
        jLabel.setText("Before:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 5;
        gbc.gridheight = 1;
        mainPanel.add(jLabel, gbc);

        //输入框 布局
        JTextArea textAreaBefore = new JTextArea();
        textAreaBefore.setToolTipText("the before code");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 5;
        gbc.gridheight = 1;

        textAreaBefore.setPreferredSize(new Dimension(width * 7, height * 2));
        mainPanel.add(textAreaBefore, gbc);
        JLabel afterLabel = new JLabel();

        //标签 result 布局
        afterLabel.setText("Result:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 5;
        gbc.gridheight = 1;
        mainPanel.add(afterLabel, gbc);

        //输出框 布局
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 5;
        gbc.gridheight = 1;
        JTextArea textAreaResult = new JTextArea();
        textAreaResult.setPreferredSize(new Dimension(width * 7, height * 2));
        mainPanel.add(textAreaResult, gbc);

        // 复制按钮
        JButton btnCopy = new JButton("Copy");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        btnCopy.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        mainPanel.add(btnCopy, gbc);


    }

    private static void initBaseData() {

        logger.info("init base data start");
        SingleTonUtil.put(new MybatisFormatService());
        SingleTonUtil.put(new SqlFormatService());
        logger.info("init base data finished");
    }
}
