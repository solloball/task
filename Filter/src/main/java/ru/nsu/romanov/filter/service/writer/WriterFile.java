package ru.nsu.romanov.filter.service.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriterFile<T> implements Writer {
    public WriterFile(
            List<T> list,
            String pathFile,
            String path,
            String prefix,
            boolean shouldNotRewrite
    ) {
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
        try (FileWriter writer = new FileWriter(pathFile)) {
            list.forEach(elem -> {
                try {
                    writer.write(elem.toString() + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private void setPath(String path) {
        pathFile = path + "/" + pathFile;
    }

    private void setPrefix(String prefix) {
        pathFile = prefix + pathFile;
    }

    private final List<T> list;
    private String pathFile;
    private boolean shouldNotRewrite;
}
