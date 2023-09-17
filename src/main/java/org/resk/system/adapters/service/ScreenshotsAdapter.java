package org.resk.system.adapters.service;

import org.resk.system.Render;
import org.resk.system.commands.MakeScreenshotCommand;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ScreenshotsAdapter extends MouseAdapter {
    private final Render render;

    public ScreenshotsAdapter(Render render) {
        this.render = render;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        new MakeScreenshotCommand(render).exec();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
