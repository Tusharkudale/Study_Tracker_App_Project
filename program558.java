import java.util.*;
import java.time.LocalDate;
import java.io.*;

//DONE
class StudyLog
{
    public LocalDate Date;
    public String Subject;
    public double Duration;
    public String Description;
    

    public StudyLog(LocalDate A,String B,double C, String D)
    {
        this.Date = A;
        this.Subject = B;
        this.Duration = C;
        this.Description = D;
        
    }

    @Override
    public String toString()
    {
        return Date+" | "+Subject+" | "+Duration+" | "+Description;
    }

    //getter methods
    public LocalDate getDate()
    {
        return Date;
    }

    public String  getSubject()
    {
        return Subject;
    }

    public double getDuration()
    {
        return Duration;
    }

    public String getDescription()
    {
        return Description;
    }
}

class StudyTracker
{
    //Data Structure to hold the data about study
    private ArrayList <StudyLog> Database = new ArrayList <StudyLog> ();

    public void InsertLog()
    {
        Scanner Scannerobj = new Scanner(System.in);

        System.out.println("---------------------------------------------------------------------");
        System.out.println("-------------Please enter the valid details of your study------------");
        System.out.println("---------------------------------------------------------------------");

        LocalDate Dateobj = LocalDate.now();

        System.out.println("Please provide the name of subject like C/C++/Java/OS/DS");
        String sub = Scannerobj.nextLine();

        System.out.println("Enter the time period of your study in hours");
        double dur = Scannerobj.nextDouble();
        Scannerobj.nextLine();

        System.out.println("Please provide the description about the study for future reference");
        String desc = Scannerobj.nextLine();

        StudyLog Studyobj = new StudyLog(Dateobj,sub,dur,desc);

        Database.add(Studyobj);

        System.out.println("Study Log gets stored successfully");
        System.out.println("---------------------------------------------------------------------");
        
    }

    public void DisplayLog()
    {
        System.out.println("---------------------------------------------------------------------");
        if(Database.isEmpty())
        {
            System.out.println("Database is empty nothing to display anything");
            System.out.println("------------------------------------------------------------------");
            return;
        }

        System.out.println("---------------------------------------------------------------------");
        System.out.println("Log report from marvellous study tracker");
        System.out.println("---------------------------------------------------------------------");

        for(StudyLog sobj : Database)
        {
            System.out.println(sobj);
        }
        System.out.println("---------------------------------------------------------------------");
    }

    public void ExportCSV()
    {
     if(Database.isEmpty())
        {
            System.out.println("Database is empty nothing to display");
            System.out.println("------------------------------------------------------------------");
            return;
        }

        String FileName = "MarvellousStudy.csv";
        
        //Create new  CSV file
        try(FileWriter fwobj = new FileWriter(FileName))
        {
            //CSV Header
            fwobj.write("Date,Subject,Duration,Description\n");


            for(StudyLog sobj : Database)
            {
                fwobj.write(sobj.getDate() + ","+
                            sobj.getSubject().replace(","," ")+ ","+
                            sobj.getDuration()+ ","+
                            sobj.getDescription().replace(","," ")+"\n"
                );
            }
            
            System.out.println("Log create Successfully");
        }
        catch(Exception eobj)
        {
            System.out.println("Exception occured while creating CSV");
            System.out.println("Report this issue to marvellous infosystems");
        }

    }
    public void SummarybyDate()
    {
        System.out.println("---------------------------------------------------------------------");

        if(Database.isEmpty())
        {
            System.out.println("Database is empty nothing to display anything");
            System.out.println("------------------------------------------------------------------");
            return;
        }

        System.out.println("---------------------------------------------------------------------");
        System.out.println("Summary by Date From Marvellous Study Tracker");
        System.out.println("---------------------------------------------------------------------");

        TreeMap <LocalDate,Double> tobj = new TreeMap <LocalDate,Double>();

        LocalDate lobj = null;
        double d,old;

        for(StudyLog sobj : Database)
        {
            lobj = sobj.getDate();
            d = sobj.getDuration();

            if(tobj.containsKey(lobj))
            {
                old = tobj.get(lobj);
                tobj.put(lobj,d+old);
            }   
            else 
            {
                tobj.put(lobj,d);
            }
        }

        //Display the details as per date

        for(LocalDate ldbj : tobj.keySet())
        {
            System.out.println("Date : "+ldbj+" Total Study "+tobj.get(ldbj));
        }

        System.out.println("---------------------------------------------------------------------");
    
    }
    
    public void SummarybySubject()
    {
        System.out.println("---------------------------------------------------------------------");

        if(Database.isEmpty())
        {
            System.out.println("Database is empty nothing to display anything");
            System.out.println("------------------------------------------------------------------");
            return;
        }

        System.out.println("---------------------------------------------------------------------");
        System.out.println("Summary by Subject From Marvellous Study Tracker");
        System.out.println("---------------------------------------------------------------------");

        TreeMap <String,Double> tobj = new TreeMap <String,Double>();

        double d,old;
        String s;

        for(StudyLog sobj : Database)
        {
            s = sobj.getSubject();
            d = sobj.getDuration();

            if(tobj.containsKey(s))
            {
                old = tobj.get(s);
                tobj.put(s,d+old);
            }   
            else 
            {
                tobj.put(s,d);
            }
        }

        //Display the details as per subject

        for(String str : tobj.keySet())
        {
            System.out.println("subject : "+str+" Total Study "+tobj.get(str));
        }

        System.out.println("---------------------------------------------------------------------");
    
    }
}

class program558      //StudyTrackerStarter
{
    public static void main(String A[])
    {
        StudyTracker stobj = new StudyTracker();
        Scanner Scannerobj = new Scanner(System.in);

        System.out.println("---------------------------------------------------------------------");
        System.out.println("----------Welcome to Marvellous Study Tracker Application------------");
        System.out.println("---------------------------------------------------------------------");

           int iChoice = 0;
        
        do
        {
         
            System.out.println("Please select the appropriate option");
            System.out.println("1: Insert new Study Log into Database");
            System.out.println("2: View All Study Logs");
            System.out.println("3: Summary of Study Log by Date");
            System.out.println("4: Summary of Study Log by Subject");
            System.out.println("5: Export  Study Log to CSV file");
            System.out.println("6: Exit the application");


            iChoice = Scannerobj.nextInt();

            switch(iChoice)
            {
                case 1:     //Insert new Study Log into Database

                    stobj.InsertLog();
                    break;

                case 2:     //View All Study Logs

                    stobj.DisplayLog();
                    break;

                case 3:     //Summary of Study Log by Date

                    stobj.SummarybyDate();
                    break;

                case 4:         //Summary of Study Log by Subject

                    stobj.SummarybySubject();
                    break;

                case 5:         //Export  Study Log to CSV file
                    stobj.ExportCSV();
                    break;

                case 6:     //Exit the application
                    System.out.println("---------------------------------------------------------------------");
                    System.out.println("Thank for using application");
                    System.out.println("---------------------------------------------------------------------");
                    break;
                
                default:

                    System.out.println("Please enter valid option");
            } 
            
        }while(iChoice != 6);
    }
}