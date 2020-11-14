package coursework.frame.menus.menuBar;

import coursework.frame.menus.menuBar.menus.ApplicationMenu;
import coursework.frame.menus.menuBar.menus.ModelMenu;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    public MenuBar() {
        super();
        add(new ModelMenu());
        add(new ApplicationMenu());
    }
}
