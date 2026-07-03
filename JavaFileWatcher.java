import java.io.IOException;
import java.nio.file.*;

// Required imports for IO Exception and management of file handling

public class JavaFileWatcher {

    public static final String directoryPath = "C:\\Users\\olive\\OneDrive\\Desktop\\FileTester";

    // Directory path is created for files in the 'FileTester' folder on my Desktop

    public static void main(String[] args) {
        System.out.println("Watch Service started");

        try{
        WatchService watchService = FileSystems.getDefault().newWatchService();

        Path path = Paths.get(directoryPath);
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);

        while(true) {

            WatchKey key = watchService.take();

            for (WatchEvent<?> event : key.pollEvents()) {
                Path file = path.resolve((Path)event.context());
                System.out.println("Event : " + event.kind());
                System.out.println("Location = " + file.toFile().getAbsolutePath());
                System.out.println("Name = " + file.toFile().getName());
                System.out.println("Timestamp = " + file.toFile().lastModified());
            }
            key.reset();
        }

    } catch (IOException e) {
        e.printStackTrace();

    } catch (InterruptedException e) {
        e.printStackTrace();
    }


    }
}