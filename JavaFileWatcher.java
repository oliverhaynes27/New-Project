import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.time.LocalDateTime;

// Required imports for IO Exception and file handling

public class JavaFileWatcher {

    public static final String directoryPath = "C:\\Users\\olive\\OneDrive\\Desktop\\FileTester";

    // Directory path is created for files in the 'FileTester' folder on my Desktop

    private static ArrayList<EventFormatter> eventHistory = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("Monitoring for file activity in" + directoryPath + "...");

    // Main method and print statement to indicate that the Watch Service has started

        try{
        WatchService watchService = FileSystems.getDefault().newWatchService();

        // Creating the watchService, which will monitor the directory from the directoryPath, if any changes occur (i.e. File creation, modification, or deletion)

        Path path = Paths.get(directoryPath);
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);

        // Registering the path to the watchService and specifying the events to watch for
        while(true) {

            WatchKey key = watchService.take();

            for (WatchEvent<?> event : key.pollEvents()) {
                
                Path file = path.resolve((Path)event.context());

                long fileSize = 0;

                if (Files.exists(file))
                {
                    fileSize = Files.size(file);
                }

                EventFormatter eventFormatter = new EventFormatter(
                    event.kind().name(),
                    file.getFileName().toString(),
                    LocalDateTime.now(),
                    file.toAbsolutePath().toString(),
                    fileSize
                );

                eventHistory.add(eventFormatter);
                System.out.println("_______________________________");
                System.out.println("");
                System.out.println(eventFormatter);

            // Seperated output statements for each event for formatting purposes
            }
            key.reset();

            // Resetting the key, so future events can be watched for
        }

    } catch (IOException e) {
        e.printStackTrace();

    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    // Catching of Exceptions for Error Handling


    }
}