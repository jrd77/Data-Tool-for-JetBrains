package top.devinstall.sql.window;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.util.ReflectionUtil;
import org.jetbrains.annotations.NotNull;
import top.devinstall.sql.util.InterUtil;

import javax.swing.*;
import java.util.Objects;

/**
 * @author zhen.wang
 * @description TODO
 * @date 2022/2/9 11:54
 */
public class SqlWindowFactory implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        SqlWindow myToolWindow = new SqlWindow(project, toolWindow);
        Icon icon = IconLoader.getIcon("META-INF/pluginIcon.svg", Objects.requireNonNull(ReflectionUtil.getGrandCallerClass()));
        toolWindow.setIcon(icon);
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(myToolWindow.getMainPanel(), InterUtil.getValue("plugin.name"), false);
        toolWindow.getContentManager().addContent(content);

    }
}
