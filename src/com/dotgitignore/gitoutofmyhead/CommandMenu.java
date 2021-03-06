/*
 * Copyright (C) 2016 Shakhar Dasgupta<sdasgupt@oswego.edu>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.dotgitignore.gitoutofmyhead;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Shakhar Dasgupta<sdasgupt@oswego.edu>
 */
public class CommandMenu extends JDialog implements Gestures {

    private final Controller controller;
    private final JList<String> list;

    public CommandMenu(Controller controller) {
        this.controller = controller;
        setTitle("Git Command Menu");
        JPanel panel = new JPanel();
        DefaultListModel<String> model = new DefaultListModel<>();
        model.addElement("Add");
        model.addElement("Commit");
        model.addElement("Push");
        list = new JList<>(model);
        list.setFixedCellHeight(50);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        JScrollPane pane = new JScrollPane(list);
        pane.setPreferredSize(new Dimension(250, 250));
        panel.add(pane);
        add(panel);
        pack();
        setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2, (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2);
        setVisible(true);
    }

    public void singleBlink() {
        list.setSelectedIndex((list.getSelectedIndex() + 1) % list.getModel().getSize());
    }

    public void doubleBlink() {
        
    }

    public void singleJawClench() {
        setVisible(false);
        switch(list.getSelectedIndex()) {
            case 0:
                controller.setWindow(new AddMenu(controller));
                break;
            case 1:
                controller.setWindow(new CommitMenu(controller));
                break;
            case 2:
                controller.setWindow(new RemoteMenu(controller));
                break;
        }
    }

    public void doubleJawClench() {
        setVisible(false);
        controller.setWindow(null);
    }
}
