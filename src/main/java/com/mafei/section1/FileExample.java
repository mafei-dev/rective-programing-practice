package com.mafei.section1;

import com.mafei.utils.SubscriberUtil;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
  @Author mafei
*/
public class FileExample {

    public static void main(String[] args) {

        String fileName = "sample1.txt";
        FileService.write(fileName, "i am the sampl-1").subscribe(
                SubscriberUtil.onNext(),
                SubscriberUtil.onError(),
                () -> {
                    System.out.println("file writing completed.");
                    FileService.read(fileName).subscribe(
                            SubscriberUtil.onNext(),
                            SubscriberUtil.onError(),
                            () -> {
                                System.out.println("file reading completed.");
                                FileService.delete(fileName).subscribe(
                                        SubscriberUtil.onNext(),
                                        SubscriberUtil.onError(),
                                        () -> {
                                            System.out.println("file deleting completed.");
                                        }
                                );
                            }
                    );
                }
        );


    }


    private static class FileService {
        private static final Path PATH = Paths.get("src/main/resources/files");

        public static Mono<String> read(final String fileName) {
            return Mono.fromSupplier(() -> getFileContents(fileName));
        }

        public static Mono<Void> write(final String fileName, String content) {
            return Mono.fromRunnable(() -> writeFile(fileName, content));
        }

        public static Mono<Void> delete(final String fileName) {
            return Mono.fromRunnable(() -> deleteFile(fileName));
        }


        public static String getFileContents(final String fileName) {
            try {
                return Files.readString(PATH.resolve(fileName));
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("file 404");
            }
        }

        private static void deleteFile(final String fileName) {
            try {
                Files.delete(PATH.resolve(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static void writeFile(final String fileName, String content) {
            try {
                Files.writeString(PATH.resolve(fileName), content);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("something went wrong.");
            }

        }

    }
}