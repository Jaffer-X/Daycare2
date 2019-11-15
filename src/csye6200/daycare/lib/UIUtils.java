package csye6200.daycare.lib;

import java.awt.*;
import java.util.HashMap;

public class UIUtils {
    public static final Font FONT_GENERAL_UI = new Font("Segoe UI", Font.PLAIN, 20);
    public static final Font FONT_FORGOT_PASSWORD = new Font("Segoe UI", Font.PLAIN, 12);

    public static final Color COLOR_OUTLINE = new Color(255, 255, 255);
    public static final Color COLOR_BACKGROUND = new Color(43, 36, 66);
    public static final Color COLOR_INTERACTIVE = new Color(247, 148, 30);
    public static final Color COLOR_INTERACTIVE_DARKER = new Color(247, 112, 30);
    public static final Color OFFWHITE = new Color(247, 148, 30);

    public static final String BUTTON_TEXT_LOGIN = "Login";
    public static final String BUTTON_TEXT_FORGOT_PASS = "Forgot your password?";
    public static final String BUTTON_TEXT_REGISTER = "Register";
    public static final String BUTTON_TEXT_TEACHER = "Teacher";
    public static final String BUTTON_TEXT_STUDENT = "Student";
    public static final String BUTTON_TEXT_ADVANCE = "Advance";
    public static final String BUTTON_TEXT_SEARCH = "Search";
    public static final String BUTTON_TEXT_MODIFY = "Modify";
    public static final String BUTTON_TEXT_IMPORT = "Import";
    public static final String BUTTON_TEXT_EXPORT = "Export";
    public static final String BUTTON_TEXT_BACK = "Back";

    public static final String PLACEHOLDER_TEXT_USERNAME = "Username/email";

    public static final int ROUNDNESS = 8;

    public static Graphics2D get2dGraphics(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.addRenderingHints(new HashMap<RenderingHints.Key, Object>() {{
            put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        }});
        return g2;
    }
}