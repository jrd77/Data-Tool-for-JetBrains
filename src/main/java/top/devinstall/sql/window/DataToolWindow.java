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
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class DataToolWindow implements ToolWindowManagerListener {
    private JPanel mainPanel = new JPanel();

    public DataToolWindow() {

    }

    public DataToolWindow(Project project, ToolWindow toolWindow) {
        initUI();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DataToolWindow");
        frame.setContentPane(new DataToolWindow().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
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
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        mainPanel.add(jLabel, gbc);

        //输入框 布局
        JTextArea textAreaBefore = new JTextArea();
        textAreaBefore.setToolTipText("the before code");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;

        textAreaBefore.setPreferredSize(new Dimension(width * 7, height * 2));
//        textAreaBefore.setMaximumSize(new Dimension(width * 7, height * 2));
//        textAreaBefore.setMinimumSize(new Dimension(width * 7, height * 2));
//        textAreaBefore.setSize(new Dimension(width * 7, height * 2));
        mainPanel.add(textAreaBefore, gbc);
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
        textAreaResult.setPreferredSize(new Dimension(width * 7, height * 2));
//        textAreaResult.setMaximumSize(new Dimension(width * 7, height * 2));
//        textAreaResult.setMinimumSize(new Dimension(width * 7, height * 2));
        mainPanel.add(textAreaResult, gbc);

        // 复制按钮
        JButton btnCopy = new JButton("Copy Result");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        btnCopy.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        mainPanel.add(btnCopy, gbc);
        btnCopy.addActionListener(e -> {

            Dimension panelSize1 = mainPanel.getSize();
            int height1 = panelSize1.height;
            int width1 = panelSize1.width;
            System.out.println(height1);
            System.out.println(width1);

        });
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
//        DataToolWindow myToolWindow = new DataToolWindow(project, dataTools);
//        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
//        Content content = contentFactory.createContent(myToolWindow.getMainPanel(), InterUtil.getValue(MessageConstant.PLUGIN_NAME), false);
//        dataTools.getContentManager().setSelectedContent(content);
    }
}
