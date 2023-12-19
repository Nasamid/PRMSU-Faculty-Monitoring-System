public class FileNode {
    private String fileName;
    private String status;
    private String dateSubmitted;

    public FileNode(String fileName, String status, String dateSubmitted) {
        this.fileName = fileName;
        this.status = status;
        this.dateSubmitted = dateSubmitted;
    }

    public String getFileName() {

        return fileName;
    }

    public String getStatus() {
        status = "not submitted";
        return status;
    }

    public String getDateSubmitted() {
        dateSubmitted = "19/12/2023";
        return dateSubmitted;
    }
}
