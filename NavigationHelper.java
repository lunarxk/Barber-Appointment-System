package com.domebarbershop;

import javax.swing.*;

public class NavigationHelper {
    public static void goToAndDispose(JFrame current, JFrame next){
        next.setVisible(true);
        current.dispose();
    }

    public static void goTo(JFrame current, JFrame next){
        next.setVisible(true);
    }
}
