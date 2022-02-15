package top.devinstall.sql.window;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DataKey;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.openapi.wm.ex.ToolWindowManagerEx;
import com.intellij.openapi.wm.ex.ToolWindowManagerListener;
import com.intellij.util.ui.JBUI;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import top.devinstall.sql.common.FormatEnum;
import top.devinstall.sql.common.MessageConstant;
import top.devinstall.sql.fact.FormatService;
import top.devinstall.sql.fact.MybatisFormatService;
import top.devinstall.sql.fact.SqlFormatService;
import top.devinstall.sql.util.InitUtil;
import top.devinstall.sql.util.InterUtil;
import top.devinstall.sql.util.SingleTonUtil;
import top.devinstall.sql.vo.SqlFormatReqVO;
import top.devinstall.sql.vo.SqlFormatResVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

@Getter
@Setter
public class DataToolWindow implements ToolWindowManagerListener {

    private JPanel mainPanel = new JPanel();
    private JRadioButton mybatisRadio = new JRadioButton();
    private JRadioButton sqlRadio = new JRadioButton();
    private JLabel jLabel = new JLabel();
    private JLabel afterLabel = new JLabel();
    private JTextArea textAreaBefore = new JTextArea();
    private JTextArea textAreaResult = new JTextArea();
    private JButton btnCopy = new JButton();
    private JButton btnFormat = new JButton();


    public DataToolWindow() {
        initUI();
        InitUtil.initBaseData();
    }

    public DataToolWindow(Project project, ToolWindow toolWindow) {
        initUI();
        InitUtil.initBaseData();
    }

    private static void actionPerformed(ActionEvent e, Boolean selectedMybatis, JTextArea textAreaBefore, JTextArea textAreaResult) {


        String beforeTxt = textAreaBefore.getText();
        if (StringUtils.isBlank(beforeTxt)) {
            String value = InterUtil.getValue(MessageConstant.TIP_EMPTY_TXT);
            textAreaResult.setText(value);
        }
        Class<?> className = selectedMybatis ? MybatisFormatService.class : SqlFormatService.class;
        FormatService formatService = (FormatService) SingleTonUtil.get(className);
        SqlFormatReqVO sqlFormatReqVO = new SqlFormatReqVO();
        sqlFormatReqVO.setFormatEnum(selectedMybatis ? FormatEnum.MYBATIS : FormatEnum.LONG_SQL);
        sqlFormatReqVO.setParamStr(beforeTxt);
        SqlFormatResVO result = formatService.format(sqlFormatReqVO);
        textAreaResult.setText(result.getResult());
    }

    @Override
    public void toolWindowRegistered(@NotNull String id) {

        if ("Data Tools".equals(id)) {
            initUI();
        }
    }

    @Override
    public void stateChanged(@NotNull ToolWindowManager toolWindowManager) {

        initDataTool();
    }

    @Override
    public void stateChanged() {

        initDataTool();
    }

    private void initDataTool() {

        DataContext dataContext = DataManager.getInstance().getDataContext();
        Project project = dataContext.getData(DataKey.create(CommonDataKeys.PROJECT.getName()));
        ToolWindowManager toolWindowManager = ToolWindowManagerEx.getInstance(project);
        ToolWindow dataTools = toolWindowManager.getToolWindow("Data Tools");

        ToolWindowFactory sqlWindowFactory = new SqlWindowFactory();
        sqlWindowFactory.createToolWindowContent(project, dataTools);
    }

    public void initUI() {

        if (mainPanel == null) {
            mainPanel = new JPanel();
        }
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        GridBagLayout gbl = new GridBagLayout();
        mainPanel.setLayout(gbl);
        Dimension panelSize = mainPanel.getSize();
        if (panelSize.height == 0 && panelSize.width == 0) {
            mainPanel.setSize(new Dimension(309, 863));
            panelSize = mainPanel.getSize();
        }
        int width = (int) (panelSize.width * 0.1);
        int height = (int) (panelSize.height * 0.1);

        GridBagConstraints gbc = new GridBagConstraints();
//        JRadioButton mybatisRadio = new JRadioButton();
        mybatisRadio.setText("Mybatis");
        mybatisRadio.setSelected(true);

//        JRadioButton sqlRadio = new JRadioButton();
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
        JLabel beforeLabel = new JLabel();
        beforeLabel.setText("Before:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        mainPanel.add(beforeLabel, gbc);

        //输入框 布局
        JTextArea textAreaBefore = new JTextArea();
        textAreaBefore.setToolTipText("the before code");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        textAreaBefore.setLineWrap(true);
        textAreaBefore.setPreferredSize(new Dimension(width * 7, height * 2));
//        textAreaBefore.addlistener
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.add(textAreaBefore);
        jScrollPane.setWheelScrollingEnabled(true);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainPanel.add(jScrollPane, gbc);
        JLabel afterLabel = new JLabel();

        //标签 result 布局
        afterLabel.setText("Result:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        mainPanel.add(afterLabel, gbc);

        //输出框 布局
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        JTextArea textAreaResult = new JTextArea();
        textAreaBefore.setLineWrap(true);
        textAreaResult.setPreferredSize(new Dimension(width * 7, height * 2));
        textAreaResult.setEditable(false);
        JScrollPane jScrollPane1 = new JScrollPane();
        jScrollPane1.add(textAreaResult);
        jScrollPane1.setWheelScrollingEnabled(true);
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainPanel.add(jScrollPane1, gbc);

        btnFormat.setText("Format");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        btnFormat.setMargin(JBUI.insets(1));
        mainPanel.add(btnFormat, gbc);
        btnFormat.addActionListener(e -> {
            DataToolWindow.actionPerformed(e, mybatisRadio.isSelected(), textAreaBefore, textAreaResult);
        });

        // 复制按钮
        btnCopy.setText("Copy Result");
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        btnCopy.setMargin(JBUI.insets(1));
        mainPanel.add(btnCopy, gbc);
        btnCopy.addActionListener(e -> {


        });

    }


}
