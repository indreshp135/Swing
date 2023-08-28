package com.sahil.basketballapp.model;

import java.awt.Color;

public class ThemeManager {

    public class Theme {
        private Color backgroundColor;
        private Color textColor;

        public Theme(Color backgroundColor, Color textColor) {
            this.backgroundColor = backgroundColor;
            this.textColor = textColor;
        }

        public Color getBackgroundColor() {
            return backgroundColor;
        }

        public Color getTextColor() {
            return textColor;
        }
    }

    private Theme currentTheme;
    private Theme lightTheme;
    private Theme darkTheme;
    private boolean isDarkMode;

    public ThemeManager() {
        lightTheme = new Theme(Color.WHITE, Color.BLACK);
        darkTheme = new Theme(new Color(50, 50, 50, 225), Color.WHITE);
        isDarkMode = false;
        currentTheme = lightTheme; // Default to light mode
    }

    public void toggleTheme() {
        isDarkMode = !isDarkMode;
        currentTheme = isDarkMode ? darkTheme : lightTheme;
    }

    public Theme getCurrentTheme() {
        return currentTheme;
    }

    public Theme getLightTheme() {
        return lightTheme;
    }
}