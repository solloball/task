package ru.nsu.romanov.filter.service.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Write list of object to file.
 *
 * @param <T> type iof object.
 */
public class WriterFile<T> {
    public WriterFile(
        List<T> list,
        String pathFile,
        String path,
        String prefix,
        boolean shouldNotRewrite
    ) throws IOException {
        this.pathFile = pathFile;
        this.list = list;
        this.shouldNotRewrite = shouldNotRewrite;

        if (prefix != null) {
            setPrefix(prefix);
        }
        if (path != null) {
            setPath(path);
        }
    }

    public void write() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(pathFile, shouldNotRewrite))) {
            list.forEach(elem -> writer.println(elem.toString()));
        }
    }

    private void setPath(String path) throws IOException {
        pathFile = path + "/" + pathFile;
        Files.createDirectories(Paths.get(path)); // create file if need.
    }

    private void setPrefix(String prefix) {
        pathFile = prefix + pathFile;
    }

    private final List<T> list;
    private String pathFile;
    private final boolean shouldNotRewrite;
}
