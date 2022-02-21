package top.devinstall.sql.handle;

import com.intellij.ui.JBColor;
import top.devinstall.sql.common.FormatEnum;
import top.devinstall.sql.common.MessageConstant;
import top.devinstall.sql.common.SqlConstant;
import top.devinstall.sql.fact.FormatService;
import top.devinstall.sql.util.InterUtil;
import top.devinstall.sql.util.SingleTonUtil;
import top.devinstall.sql.util.StrUtil;
import top.devinstall.sql.vo.SqlFormatReqVO;
import top.devinstall.sql.vo.SqlFormatResVO;
import top.devinstall.sql.window.DataToolWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventHandleService {

    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

    private static FormatEnum formStr(String str) {

        if (StrUtil.isBlank(str)) {
            return FormatEnum.JSON;
        } else if (StrUtil.isJson(str)) {
            return FormatEnum.JSON;
        } else if (StrUtil.isMybatis(str)) {
            return FormatEnum.MYBATIS;
        } else {
            return FormatEnum.LONG_SQL;
        }
    }

    /**
     * copy按钮点击事件
     *
     * @param dataToolWindow
     */
    public void copyEvent(DataToolWindow dataToolWindow) {

        //获取结果文本域
        JTextArea textAfter = dataToolWindow.getTextAfter();
        //获取提示域
        JLabel tipLabel = dataToolWindow.getTipLabel();
        String result = textAfter.getText();
        if (StrUtil.isBlank(result)) {
            return;
        }
        // 获取系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 将result封装
        Transferable trans = new StringSelection(result);
        // 把文本内容设置到系统剪贴板
        clipboard.setContents(trans, null);
        tipLabel.setText("Copy Success!");
        tipLabel.setFont(SqlConstant.YAHEI_FONT_BOLD_16);
        tipLabel.setForeground(SqlConstant.CYAN);
        wait2Second(tipLabel);
    }


    private SqlFormatReqVO buildReqVO(String param, FormatEnum formatEnum) {

        SqlFormatReqVO reqVO = new SqlFormatReqVO();
        reqVO.setFormatEnum(formatEnum);
        reqVO.setParamStr(param);
        return reqVO;
    }

    private FormatService getFormatService(FormatEnum formatEnum) {

        Object o = SingleTonUtil.get(FormatEnum.getFormatService(formatEnum));
        return (FormatService) o;
    }


    private void wait2Second(JLabel tip) {

        executorService.submit(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tip.setText(StrUtil.EMPTY);
        });
    }

    /**
     * Format按钮点击事件
     *
     * @param dataToolWindow
     */
    public void formatEvent(DataToolWindow dataToolWindow) {

        //获取提示标签
        JLabel tipLabel = dataToolWindow.getTipLabel();
        //获取待格式化文本域
        JTextArea textBefore = dataToolWindow.getTextBefore();
        //获取格式化后文本域
        JTextArea textAfter = dataToolWindow.getTextAfter();
        //单选按钮
        JRadioButton radioSql = dataToolWindow.getRadioSql();
        //自动辨认类型
        JRadioButton radioAuto = dataToolWindow.getRadioAuto();
        //重置提示标签
        tipLabel.setText(StrUtil.EMPTY);
        String beforeText = textBefore.getText();
        //待格式化文本域为空则提示
        if (StrUtil.isBlank(beforeText)) {
            tipLabel.setText(InterUtil.getValue(MessageConstant.TIPS_LABEL_1));
            tipLabel.setFont(SqlConstant.YAHEI_FONT_BOLD_16);
            tipLabel.setForeground(JBColor.RED);
            wait2Second(tipLabel);
            return;
        }
        //根据文本类型不同,选择不同service进行格式化 -start
        JRadioButton radioMybatis = dataToolWindow.getRadioMybatis();
        FormatEnum formatEnum;
        //选择文本类型
        if (radioAuto.isSelected()) {
            formatEnum = formStr(beforeText);
        } else if (radioMybatis.isSelected()) {
            formatEnum = FormatEnum.MYBATIS;
        } else if (radioSql.isSelected()) {
            formatEnum = FormatEnum.LONG_SQL;
        } else {
            formatEnum = FormatEnum.JSON;
        }
        FormatService formatService = getFormatService(formatEnum);
        //构造请求参数
        SqlFormatReqVO sqlFormatReqVO = buildReqVO(beforeText, formatEnum);
        SqlFormatResVO resVO = formatService.format(sqlFormatReqVO);
        //根据文本类型不同,选择不同service进行格式化 -end
        String result = resVO == null ? StrUtil.EMPTY : resVO.getResult();
        //设置到格式化后文本域显示
        textAfter.setText(result);
    }
}
