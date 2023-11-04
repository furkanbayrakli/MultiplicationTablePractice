package oopproje;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ChildTest {
      private Child child;
      @Test
      void testName(){
       child= new Child("furkan");
       assertTrue(child.getName().compareToIgnoreCase("furkan")==0);
      }
      @Test
      void testReport(){
       child= new Child("furkan");
       Reports report=new Reports();
       child.setReport(report);
       assertTrue(report.equals(child.getReport()));
      }
      @Test
      void testReports(){
       child= new Child("furkan");
       child.setReport(new Reports());
       child.addReport();
       assertTrue(child.getReports().contains(child.getReport()));
      }
      @Test
      void testDate(){
       child= new Child("furkan");
       child.setEndDate();
       child.setStartDate();
       assertTrue(child.getEndDate().equals(new Date()) && child.getStartDate().equals(new Date()));
      }


}