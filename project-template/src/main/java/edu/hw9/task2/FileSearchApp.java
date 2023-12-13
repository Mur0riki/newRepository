package edu.hw9.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class FileSearchTask extends RecursiveTask<List<File>> {
    private final File directory;
    private final String extension;
    private final int minFiles;

    public FileSearchTask(File directory, String extension, int minFiles) {
        this.directory = directory;
        this.extension = extension;
        this.minFiles = minFiles;
    }

    @Override
    protected List<File> compute() {
        List<File> foundFiles = new ArrayList<>();
        List<FileSearchTask> subTasks = new ArrayList<>();

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    FileSearchTask subTask = new FileSearchTask(file, extension, minFiles);
                    subTasks.add(subTask);
                    subTask.fork();
                } else {
                    if (checkFile(file)) {
                        foundFiles.add(file);
                    }
                }
            }
        }

        for (FileSearchTask subTask : subTasks) {
            foundFiles.addAll(subTask.join());
        }

        return foundFiles;
    }

    private boolean checkFile(File file) {
        if (extension != null && !file.getName().endsWith(extension)) {
            return false;
        }

        return true;
    }
}

public class FileSearchApp {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();

        File directory = new File("path/to/directory");

        // Поиск директорий, в которых больше 1000 файлов
        FileSearchTask countTask = new FileSearchTask(directory, null, 1000);
        List<File> result = pool.invoke(countTask);

        System.out.println("Directories with more than 1000 files:");
        for (File file : result) {
            System.out.println(file.getAbsolutePath());
        }

        // Поиск файлов по предикату: расширение .txt
        FileSearchTask extensionTask = new FileSearchTask(directory, ".txt", 0);
        List<File> result2 = pool.invoke(extensionTask);

        System.out.println("Files with .txt extension:");
        for (File file : result2) {
            System.out.println(file.getAbsolutePath());
        }
    }
}
