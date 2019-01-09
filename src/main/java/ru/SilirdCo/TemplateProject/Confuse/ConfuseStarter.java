package ru.SilirdCo.TemplateProject.Confuse;

import ru.SilirdCo.TemplateProject.Util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;
import java.util.UUID;

public class ConfuseStarter {
    private static final char[] possibleChars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '_'};

    private File startPath;
    private File target;
    private int depth;
    private String key;

    private String shadeContent;

    public ConfuseStarter(File target, int depth) {
        this.target = target;
        this.depth = depth;
    }

    public void start() throws ConfuseException, IOException {
        if ((target == null) || !target.exists()) {
            throw new ConfuseException("Целевой файл не существует");
        }
        if (depth <= 0) {
            throw new ConfuseException("Глубина поиска должна быть больше 0");
        }

        startPath = target.getParentFile();
        key = createKey();
        shadeContent = createShadeContent(target.length());

        confuse(startPath, depth, "");
        if (!target.delete()) {
            throw new ConfuseException("Скрытие прошло успешно, но целевой файл не был удалён, для успешного ПРАНКА(тм), удалите его вручную");
        }
    }

    private void confuse(File currentPath, int remainingDepth, String currentKey) throws ConfuseException, IOException {
        if (remainingDepth == 0) {
            if (currentKey.equals(key)) {
                File dest = new File(currentPath.getAbsolutePath() + File.separator + UUID.randomUUID());
                Files.copy(target.toPath(), dest.toPath());
            }
            else {
                File shadedFile = new File(currentPath.getAbsolutePath() + File.separator + UUID.randomUUID());
                FileUtil.writeText(shadedFile, shadeContent);
            }
        }
        else {
            remainingDepth--;
            for (char ch : possibleChars) {
                String newKey = currentKey + ch;
                File newPackage = new File(currentPath.getAbsolutePath() + File.separator + ch);
                if (!newPackage.exists()) {
                    if (!newPackage.mkdir()) {
                        throw new ConfuseException("Ошибка создания папки");
                    }
                }
                confuse(newPackage, remainingDepth, newKey);
            }
        }
    }

    private String createKey() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            stringBuilder.append(possibleChars[random.nextInt(possibleChars.length)]);
        }
        return stringBuilder.toString();
    }

    private String createShadeContent(long length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (long i = 0; i < length; i++) {
            stringBuilder.append("1");
        }
        return stringBuilder.toString();
    }

    public static boolean isPossible(char ch) {
        for (char c : possibleChars) {
            if (ch == c) {
                return true;
            }
        }

        return false;
    }

    public File getStartPath() {
        return startPath;
    }

    public void setStartPath(File startPath) {
        this.startPath = startPath;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public File getTarget() {
        return target;
    }

    public void setTarget(File target) {
        this.target = target;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
