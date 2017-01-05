package com.kamesuta.mc.bnnwidget.component;

import static org.lwjgl.opengl.GL11.*;

import javax.annotation.Nonnull;

import com.kamesuta.mc.bnnwidget.WEvent;
import com.kamesuta.mc.bnnwidget.WPanel;
import com.kamesuta.mc.bnnwidget.WRenderer;
import com.kamesuta.mc.bnnwidget.position.Area;
import com.kamesuta.mc.bnnwidget.position.Point;
import com.kamesuta.mc.bnnwidget.position.R;
import com.kamesuta.mc.bnnwidget.render.OpenGL;

import net.minecraft.util.ResourceLocation;

public class MPanel extends WPanel {
	public static final @Nonnull ResourceLocation background = new ResourceLocation("signpic", "textures/gui/background.png");
	public static boolean tryNew;

	public MPanel(final @Nonnull R position) {
		super(position);
	}

	@Override
	public void draw(final @Nonnull WEvent ev, final @Nonnull Area pgp, final @Nonnull Point p, final float frame, final float popacity) {
		final Area a = getGuiPosition(pgp);
		final float op = getGuiOpacity(popacity);

		if (tryNew) {
			WRenderer.startShape();
			OpenGL.glColor4f(0f, 0f, 0f, op*.5f);
			draw(a);
			OpenGL.glLineWidth(1f);
			OpenGL.glColor4f(1f, 1f, 1f, op);
			draw(a, GL_LINE_LOOP);
		} else {
			WRenderer.startTexture();
			OpenGL.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			texture().bindTexture(background);
			drawBack(a);
		}
		super.draw(ev, pgp, p, frame, popacity);
	}

	public static void drawBack(final @Nonnull Area a) {
		drawTextureModalSize(a.x1(), a.y1(), a.w()/2, a.h()/2, 0, 0, a.w()/2, a.h()/2);
		drawTextureModalSize(a.x1()+a.w()/2, a.y1(), a.w()/2, a.h()/2, 256-a.w()/2, 0, a.w()/2, a.h()/2);
		drawTextureModalSize(a.x1(), a.y1()+a.h()/2, a.w()/2, a.h()/2, 0, 256-a.h()/2, a.w()/2, a.h()/2);
		drawTextureModalSize(a.x1()+a.w()/2, a.y1()+a.h()/2, a.w()/2, a.h()/2, 256-a.w()/2, 256-a.h()/2, a.w()/2, a.h()/2);
	}
}
