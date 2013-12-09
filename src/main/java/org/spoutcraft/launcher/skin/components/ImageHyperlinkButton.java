/*
 * This file is part of Technic Launcher.
 * Copyright (C) 2013 Syndicate, LLC
 *
 * Technic Launcher is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Technic Launcher is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Technic Launcher.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.spoutcraft.launcher.skin.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.swing.JButton;

import org.spoutcraft.launcher.util.DesktopUtils;

public class ImageHyperlinkButton extends JButton{
	private static final long serialVersionUID = 1L;
	private String url;
	private String drawnOnTopText;

	public ImageHyperlinkButton(String url) {
		this(url, null);
	}

	public ImageHyperlinkButton(String url, String layeredText) {
		this.drawnOnTopText = layeredText;
		this.url = url;
		this.addActionListener(new ButtonClickHandler());
		setBorder(null);
		setOpaque(false);
		setFocusable(false);
		setContentAreaFilled(false);
	}

	private class ButtonClickHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				DesktopUtils.browse((new URL(url).toURI()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		if (this.drawnOnTopText == null) {
			return;
		}

		Graphics2D g2d = (Graphics2D)g;

		g2d.setColor(this.getForeground());
		g2d.setFont(getFont());

		int textHeight =  getFont().getSize();
		int otherTextHeight = getFontMetrics(getFont()).getHeight();

		textHeight = textHeight - (otherTextHeight-textHeight);
		int height = textHeight + (getHeight() - textHeight)/2;
		g2d.drawString(drawnOnTopText, 40, height);
	}

	public void setURL(String url) {
		this.url = url;
	}
}
