package prac2.after;

// Product
interface Report {
    void header();

    void body(String content);

    void footer();
}

class PdfReport implements Report {
    @Override
    public void header() {
        System.out.println("PDF Header: Company Name");
    }

    @Override
    public void body(String content) {
        System.out.println("PDF Body: " + content);
    }

    @Override
    public void footer() {
        System.out.println("PDF Footer: Page Number");
    }
}

class ExcelReport implements Report {
    @Override
    public void header() {
        System.out.println("Excel Header: Row 1 Column 1");
    }

    @Override
    public void body(String content) {
        System.out.println("Excel Body: Cell Data [ " + content + " ]");
    }

    @Override
    public void footer() {
        System.out.println("Excel Footer: End of Sheet");
    }
}

// Creator
abstract class ReportService {
    public void makeReport(String content) {
        Report report = createReport();
        report.header();
        report.body(content);
        report.footer();
    }

    protected abstract Report createReport();
}

// Concrete Creator
class PDFReportService extends ReportService {
    @Override
    protected Report createReport() {
        return new PdfReport();
    }
}

class ExcelReportService extends ReportService {
    @Override
    protected Report createReport() {
        return new ExcelReport();
    }
}

public class Main {
    public static void main(String[] args) {

        System.out.println("--- Export PDF ---");
        ReportService pdfService = new PDFReportService();
        pdfService.makeReport("Monthly Financials");

        System.out.println("\n--- Export Excel ---");
        ReportService excelService = new ExcelReportService();
        excelService.makeReport("Yearly Statistics");
    }
}
