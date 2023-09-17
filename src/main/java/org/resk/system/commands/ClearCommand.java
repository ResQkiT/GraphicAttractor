package org.resk.system.commands;

import org.resk.system.Render;

public class ClearCommand extends RenderCommand {
    public ClearCommand(Render render) {
        super.render = render;
    }

    @Override
    public void exec() {
        render.clear();
    }
}
