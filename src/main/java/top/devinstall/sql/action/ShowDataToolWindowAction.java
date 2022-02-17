package top.devinstall.sql.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.wm.ToolWindowManager;

import java.util.Objects;
import java.util.logging.Logger;

public class ShowDataToolWindowAction extends AnAction {

    private static final Logger logger = Logger.getLogger(ShowDataToolWindowAction.class.getName());

    @Override
    public void actionPerformed(AnActionEvent e) {

        ToolWindowManager.getInstance(Objects.requireNonNull(e.getProject())).getToolWindow("DataTool").show(() -> {
            logger.info("start Data-Tool plugin");
        });
    }
}
