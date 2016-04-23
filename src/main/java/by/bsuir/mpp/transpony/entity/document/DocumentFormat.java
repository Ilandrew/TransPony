package by.bsuir.mpp.transpony.entity.document;

public enum DocumentFormat {
    XLSX(".xlsx"),
    CSV(".csv"),
    PDF(".pdf");

    private String extension;

    DocumentFormat(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
