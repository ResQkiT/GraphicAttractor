package org.resk.system.commands;

import org.resk.system.Render;

public class MakeScreenshotCommand extends RenderCommand{

    public MakeScreenshotCommand(Render render) {
        super.render = render;
    }

    @Override
    public void exec() {
        render.makeScreenShot();
    }
}
