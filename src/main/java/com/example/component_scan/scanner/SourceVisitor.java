package com.example.component_scan.scanner;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SourceVisitor implements FileVisitor<Path> {

    private final List<String> paths;
    private final String baseRoot;

    public SourceVisitor(String baseRoot) {
        this(new ArrayList<>(), baseRoot);
    }

    public SourceVisitor(List<String> paths, String baseRoot) {
        this.paths = paths;
        this.baseRoot = baseRoot;
    }

    public List<String> getPaths() {
        return paths;
    }

    @Override
    public FileVisitResult preVisitDirectory(
        Path dir, BasicFileAttributes attrs
    ) throws IOException {
        return FileVisitResult.CONTINUE;

    }

    @Override
    public FileVisitResult visitFile(
        Path file, BasicFileAttributes attrs
    ) throws IOException {
        if (!file.toFile().getName().endsWith(".class")) {
            return FileVisitResult.CONTINUE;
        }

        String canonicalPath = file.toFile().getCanonicalPath();
        if (!canonicalPath.contains(baseRoot)) {
            return FileVisitResult.CONTINUE;
        }

        paths.add(canonicalPath);

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(
        Path file, IOException exc
    ) throws IOException {
        return FileVisitResult.CONTINUE;

    }

    @Override
    public FileVisitResult postVisitDirectory(
        Path dir, IOException exc
    ) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}
