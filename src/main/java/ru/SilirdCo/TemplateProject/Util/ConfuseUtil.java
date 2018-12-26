package ru.SilirdCo.TemplateProject.Util;

import java.io.File;

public class ConfuseUtil {
    public static String createShadeContent(long length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (long i = 0; i < length; i++) {
            stringBuilder.append("1");
        }
        return stringBuilder.toString();
    }

    public static char[] massiv = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '_'};

    public static void confuse(File path, int remainingDepth, String shadedContent, String key) {

    }
}
