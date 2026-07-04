import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventFormatter
{

    private String eventType;
    private String fileName;
    private LocalDateTime eventTime;
    private String filePath;
    private long fileSize;

    public EventFormatter(String eventType, String fileName, LocalDateTime eventTime, String filePath, long fileSize)
    {
        this.eventType = eventType;
        this.fileName = fileName;
        this.eventTime = eventTime;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    public String getEventType() 
    {
        return eventType;
    }

    public String getFileName()
    {
        return fileName;
    }

    public LocalDateTime geteventTime()
    {
        return eventTime;
    }
    
    public String getFilePath()
    {
        return filePath;
    }

    public long getFileSize()
    {
        return fileSize;
    }

    @Override
    public String toString()
    {
        return "Event: " + eventType +
               "\nFile: " + fileName +
               "\nTime: " + eventTime +
               "\nPath: " + filePath +
               "\nSize: " + fileSize + "Bytes";
    }

}