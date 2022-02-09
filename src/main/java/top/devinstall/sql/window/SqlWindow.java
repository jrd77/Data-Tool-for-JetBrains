package top.devinstall.sql.window;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import top.devinstall.sql.util.InitUtil;

import javax.swing.*;

/**
 * @author zhen.wang
 * @description TODO
 * @date 2022/1/27 16:16
 */
public class SqlWindow {

    private JPanel mainPanel;
    private JTextArea textAreaBefore;
    private JTextArea textAreaAfter;
    private JRadioButton radioSqlFormat;
    private JRadioButton radioMybatis;
    private JRadioButton radioHibernate;


    public SqlWindow(Project project, ToolWindow toolWindow) {

        //初始化部分数据
        InitUtil.init();
    }


    //    -----------------------get&set
    public SqlWindow() {

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public SqlWindow setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
        return this;
    }

    public JTextArea getTextAreaBefore() {
        return textAreaBefore;
    }

    public SqlWindow setTextAreaBefore(JTextArea textAreaBefore) {
        this.textAreaBefore = textAreaBefore;
        return this;
    }

    public JTextArea getTextAreaAfter() {
        return textAreaAfter;
    }

    public SqlWindow setTextAreaAfter(JTextArea textAreaAfter) {
        this.textAreaAfter = textAreaAfter;
        return this;
    }

    public JRadioButton getRadioSqlFormat() {
        return radioSqlFormat;
    }

    public SqlWindow setRadioSqlFormat(JRadioButton radioSqlFormat) {
        this.radioSqlFormat = radioSqlFormat;
        return this;
    }

    public JRadioButton getRadioMybatis() {
        return radioMybatis;
    }

    public SqlWindow setRadioMybatis(JRadioButton radioMybatis) {
        this.radioMybatis = radioMybatis;
        return this;
    }

    public JRadioButton getRadioHibernate() {
        return radioHibernate;
    }

    public SqlWindow setRadioHibernate(JRadioButton radioHibernate) {
        this.radioHibernate = radioHibernate;
        return this;
    }
    //    -----------------------get&set
}
