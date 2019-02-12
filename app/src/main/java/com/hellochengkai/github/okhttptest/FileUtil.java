package com.hellochengkai.github.okhttptest;

import android.util.Log;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Consumer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chengkai on 19-1-17.
 */

public class FileUtil {
    private static final String TAG = "FileUtil";

    private static final boolean DEBUG = false;

    public static void forEachFile(File file, final Consumer<File> action) {
        try {
            if (isDir(file)) {
                Stream.of(Arrays.asList(file.listFiles()))
                        .filter(File::canRead)
                        .forEach(file1 -> FileUtil.forEachFile(file1, action));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        action.accept(file);
    }

    public static boolean isFile(File file) {
        try {
            return Stream.of(file)
                    .filter(File::exists)
                    .filter(File::isFile)
                    .filter(File::canRead)
                    .count() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isDir(File dir) {
        try {
            return Stream.of(dir)
                    .filter(File::exists)
                    .filter(File::isDirectory)
                    .filter(File::canRead)
                    .count() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean newDir(File dir) {
        try {
            return Stream.of(dir)
                    .filter(f -> !f.exists())
                    .filter(File::mkdir)
                    .filter(FileUtil::isDir)
                    .count() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean newFile(File file) {

        try {
            return Stream.of(file)
                    .filter(f -> {
                        try {
                            return f.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                            return false;
                        }
                    })
                    .filter(FileUtil::isFile)
                    .count() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean newFile(File file, InputStream inputStream) {
        if (!newFile(file)) {
            return false;
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bytes = new byte[1024 * 1024];
            int readLens = 0;
            for (; ; ) {
                Arrays.fill(bytes, (byte) 0);
                int readLen = inputStream.read(bytes);
                if (readLen > 0) {
                    fileOutputStream.write(bytes, 0, readLen);
                    readLens += readLen;
                    if (DEBUG)
                        Log.d(TAG, "newFile: write lens " + readLens);
                } else {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }


    public static boolean del(File file) {
        forEachFile(file, file1 -> file1.delete());
        return file.exists();
    }

    public static List<String> readLines(File file) {
        List<String> list = new ArrayList<>();
        if (!isFile(file))
            return null;
        String line;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static String readStr(File file) {
        StringBuilder builder = new StringBuilder();
        List<String> strings = readLines(file);
        Stream.of(strings).forEach(s -> builder.append(s));
        return builder.toString();
    }
}
