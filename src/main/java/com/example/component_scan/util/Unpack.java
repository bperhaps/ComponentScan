package com.example.component_scan.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class Unpack {

    public File jar(File file) {
        try {
            return execute(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private File execute(File file) throws IOException {
        ZipInputStream zis = new ZipInputStream(new FileInputStream(file));

        try (zis) {
            return writeFilesToDirectory(zis);
        }
    }

    private File writeFilesToDirectory(ZipInputStream zis) throws IOException {
        File tempDirectory = Files.createTempDirectory("pick_git").toFile();
        boolean writableOwnerOnly = tempDirectory.setWritable(true, true);
        boolean readableOwnerOnly = tempDirectory.setReadable(true, true);
        boolean executableOwnerOnly = tempDirectory.setExecutable(true, true);

        if (!(writableOwnerOnly && readableOwnerOnly && executableOwnerOnly)) {
            throw new IllegalStateException("임시 폴더를 안전하게 생성하는데 실패했습니다.");
        }

        ZipEntry zipEntry = zis.getNextEntry();

        while (zipEntry != null) {

            File newFile = new File(tempDirectory, zipEntry.getName());
            if (zipEntry.isDirectory()) {
                createDirectory(newFile);
            } else {
                createDirectoryInWindowsCase(newFile);
                writeFileToDirectory(zis, newFile);
            }

            zipEntry = zis.getNextEntry();
        }

        return tempDirectory;
    }

    private void createDirectoryInWindowsCase(File newFile) {
        File parent = newFile.getParentFile();
        createDirectory(parent);
    }

    private void createDirectory(File file) {
        if (!file.isDirectory() && !file.mkdirs()) {
            throw new RuntimeException("폴더 생성에 실패했습니다" + file);
        }
    }

    private void writeFileToDirectory(
        ZipInputStream zis,
        File newFile
    ) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(newFile)) {
            int length;
            byte[] buffer = new byte[1024];

            while ((length = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        }
    }

}
