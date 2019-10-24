package com.vcs.bb.game.gui;

import java.awt.Image;

public interface Animated {

	void animate();

	Image getImage();

	double getImgPosX();

	double getImgPosY();
	
	int getImgSize();

}
