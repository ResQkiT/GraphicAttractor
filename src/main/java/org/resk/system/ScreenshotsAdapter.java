package org.resk.system;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ScreenshotsAdapter extends MouseAdapter {
    private Render render;
    public ScreenshotsAdapter(Render render) {
        this.render = render;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        render.makeScreenShot();
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
