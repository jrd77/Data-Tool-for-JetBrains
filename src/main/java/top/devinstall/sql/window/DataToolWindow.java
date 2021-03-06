package top.devinstall.sql.window;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.util.ui.JBUI;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.devinstall.sql.common.MessageConstant;
import top.devinstall.sql.common.SqlConstant;
import top.devinstall.sql.handle.EventHandleService;
import top.devinstall.sql.util.InterUtil;
import top.devinstall.sql.util.SingleTonUtil;

import javax.swing.*;

@Getter
@Setter
@NoArgsConstructor
public class DataToolWindow {
    public static final String LONG_SQL = "LongSQL";
    public static final String MYBATIS = "Mybatis";
    public static final String BEFORE = "Before: ";
    public static final String RESULT = "Result: ";
    public static final String JSON = "JSON";
    private JPanel mainPanel;
    private JRadioButton radioMybatis;
    private JRadioButton radioSql;
    private JRadioButton radioJson;
    private JTextArea textBefore;
    private JTextArea textAfter;
    private JButton btnFormat;
    private JButton btnCopy;
    private JLabel afterLabel;
    private JLabel beforeLabel;
    private JLabel tipLabel;
    private JComboBox comboBoxSqlType;
    private JPanel btnPanel;


    private Project project;

    public DataToolWindow(Project project, ToolWindow toolWindow) {

        this.project = project;
        initUI();
    }

    private void initUI() {

        initLanguage();
        buildComponent();
        actionEvent();
    }

    private void initLanguage() {

        radioSql.setText(LONG_SQL);
        radioMybatis.setText(MYBATIS);
        radioJson.setText(JSON);
        textBefore.setToolTipText(InterUtil.getValue(MessageConstant.TIP_EMPTY_TXT));
        textAfter.setEditable(Boolean.FALSE);
        btnFormat.setText(InterUtil.getValue(MessageConstant.BTN_FORMAT_NAME));
        btnCopy.setText(InterUtil.getValue(MessageConstant.BTN_COPY_NAME));
        beforeLabel.setText(BEFORE);
        afterLabel.setText(RESULT);
    }

    private void buildComponent() {

        ButtonGroup formatType = new ButtonGroup();
        formatType.add(radioSql);
        formatType.add(radioMybatis);
        formatType.add(radioJson);
        radioMybatis.setSelected(Boolean.TRUE);
        textBefore.setLineWrap(Boolean.TRUE);
        textBefore.setFont(SqlConstant.DEFAULT_FONT_16);
        textBefore.setMargin(JBUI.insets(10));
//        textBefore.setForeground(SqlConstant.GRAY);
        textAfter.setLineWrap(Boolean.TRUE);
        textAfter.setFont(SqlConstant.DEFAULT_FONT_16);
//        textAfter.setForeground(SqlConstant.GRAY);
        textAfter.setMargin(JBUI.insets(10));
        beforeLabel.setFont(SqlConstant.SERIF_FONT_16);
        afterLabel.setFont(SqlConstant.SERIF_FONT_16);
        if (SqlConstant.SYSTEM_FONT_LIST.contains(SqlConstant.MICROSOFT_YAHEI)) {
            textAfter.setFont(SqlConstant.YAHEI_FONT_16);
            textBefore.setFont(SqlConstant.YAHEI_FONT_16);
        }
        comboBoxSqlType.setVisible(false);

    }

    private void comobox(JComboBox comboBoxSqlType) {
        comboBoxSqlType.addItem("MySQL");
        comboBoxSqlType.addItem("Oracle");
        comboBoxSqlType.addItem("PgSQL");
        comboBoxSqlType.addItem("MsSQL");
    }

    private void actionEvent() {
        EventHandleService eventHandleService = SingleTonUtil.get(EventHandleService.class);
        //format
        btnFormat.addActionListener(e -> {
//            boolean underDarcula = UIUtil.isUnderDarcula();

            eventHandleService.formatEvent(this);
        });
        //copy
        btnCopy.addActionListener(e -> {

            eventHandleService.copyEvent(this);
        });
    }


}
