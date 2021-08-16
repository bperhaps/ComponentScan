package com.example.component_scan.scanner;

import static java.util.stream.Collectors.toList;

import com.example.component_scan.util.Unpack;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CanonicalPathScanner {

    private final String packagePath;

    public CanonicalPathScanner(String basePackage) {
        this.packagePath = basePackage.replaceAll("[.]", "/");
    }

    public List<String> getAllCanonicalPaths() {
        String basePath = getBasePath();

        if (basePath.endsWith(".jar")) {
            return jarCase(basePath);
        }

        return getAllCanonicalPaths(basePath);
    }

    private List<String> jarCase(String jarPath) {
        File jar = new Unpack().jar(new File(jarPath));
        return getAllCanonicalPaths(jar.getPath());
    }

    private List<String> getAllCanonicalPaths(String basePath) {
        try {
            SourceVisitor sourceVisitor = new SourceVisitor(packagePath);
            Files.walkFileTree(Path.of(basePath), sourceVisitor);

            return sourceVisitor.getPaths().stream()
                .map(path -> path.substring(path.indexOf(packagePath)))
                .map(path -> path.replaceAll("/", "."))
                .map(path -> path.replace(".class", ""))
                .collect(toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getBasePath() {
        try {
            String path = getClassPath();

            if(path.endsWith(".jar")) {
                return path;
            }

            String[] split = path.split("/");
            List<String> pathPieces = List.of(split).subList(0, split.length - 1);

            return String.join("/", pathPieces);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private String getClassPath() throws URISyntaxException {
        String path = getClass()
            .getProtectionDomain()
            .getCodeSource()
            .getLocation()
            .toURI()
            .toString();

        path = path.replace("file:", "");
        if (path.startsWith("jar")) {
            path = path.replace("jar:", "");
            path = path.substring(0, path.indexOf("!/"));
        }

        return path;
    }
}
