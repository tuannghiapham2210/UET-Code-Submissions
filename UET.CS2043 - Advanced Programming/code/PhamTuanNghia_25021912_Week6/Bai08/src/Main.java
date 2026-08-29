class Report { //this class only has a single responsibility is to hold the data
    private String title;
    private String content;

    //constructor
    public Report(String title, String content){
        this.title = title;
        this.content = content;
    }

    //public getters 
    public String getTitle(){
        return this.title;
    
    }

    public String getContent(){
        return this.content;
    }

}

interface ReportFormatter{ //the blueprint for a reportformatter
    String format(Report data);
}

//concrete formatters
class JsonFormatter implements ReportFormatter{
    @Override
    public String format(Report data){
        String template = """
            {
              "title": "%s",
              "content": "%s"        
            }
            """; //using text block (triple quotes)
            
        return String.format(template, data.getTitle(), data.getContent());
    }
}

class XmlFormatter implements ReportFormatter{
    @Override 
    public String format(Report data){
        String template = """
            <Report>        
              <title>%s</title>
              <content>%s</content>
            </Report>
            """;
        return String.format(template, data.getTitle(), data.getContent());
    }
}

//service logic (the only responsibilty that this class should have)
class ReportService {
    //holding a reference to a formatter
    ReportFormatter formatter;

    //constructor
    public ReportService(ReportFormatter formatter){
        this.formatter = formatter;
    }

    public String export(Report data){
        return formatter.format(data);
    }
}

public class Main{
    public static void main(String[] args){
        //creating a report
        Report data = new Report("Greetings", "Hello");

        //creating formatters
        ReportFormatter jsonFormatter = new JsonFormatter();
        ReportFormatter xmlFormatter = new XmlFormatter();

        //creating ReportService (only takes in a report and returns the correct format as requested)
        ReportService reportService1 = new ReportService(jsonFormatter);
        ReportService reportService2 = new ReportService(xmlFormatter);

        System.out.println("Json format: ");
        System.out.println(reportService1.export(data));

        System.out.println("\nXml format: ");
        System.out.println(reportService2.export(data));
    }
}