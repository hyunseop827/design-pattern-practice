package prac2.before;

class PdfReport {
    public void header() {
        System.out.println("PDF Header: Company Name");
    }
    public void body(String content) {
        System.out.println("PDF Body: " + content);
    }
    public void footer() {
        System.out.println("PDF Footer: Page Number");
    }
}

class ExcelReport {
    public void header() {
        System.out.println("Excel Header: Row 1 Column 1");
    }
    public void body(String content) {
        System.out.println("Excel Body: Cell Data [ " + content + " ]");
    }
    public void footer() {
        System.out.println("Excel Footer: End of Sheet");
    }
}

// Bad Design: OCP Violation, High Coupling
class ReportService {
    public void makeReport(String type, String content) {
        if (type.equals("PDF")) {
            PdfReport pdf = new PdfReport();
            // PDF generation flow
            pdf.header();
            pdf.body(content);
            pdf.footer();
        } else if (type.equals("EXCEL")) {
            ExcelReport excel = new ExcelReport();
            // Excel generation flow
            excel.header();
            excel.body(content);
            excel.footer();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ReportService service = new ReportService();

        System.out.println("--- Export PDF ---");
        service.makeReport("PDF", "Monthly Financials");

        System.out.println("\n--- Export Excel ---");
        service.makeReport("EXCEL", "Yearly Statistics");
    }
}
