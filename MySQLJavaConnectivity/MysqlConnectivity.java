import java.sql.*;
import java.sql.Date;
import java.util.*;

public class MysqlConnectivity {

  public static void main(String[] args) {
    System.out.println("MySQL using Java Connectivity\n");
    while (true) {
      try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/librarydb",
          "root",
          "jay4757:)"
        );
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\n1.Insert\n2.Display\n3.Update\n4.Delete\n");
        System.out.print("Enter Your Choice: ");
        int a = sc.nextInt();

        //switch case
        switch (a) {
          //case 1 : INSERT RECORD
          case 1:
            try {
              
              Scanner scanner = new Scanner(System.in);

              System.out.print("Enter student id: ");
              Integer s_id1 = scanner.nextInt();
              scanner.nextLine();

              System.out.print("Enter your name: ");
              String name1 = scanner.nextLine();
              // scanner.nextInt();

              System.out.print("Enter your date of birth (YYYY/MM/DD): ");
              String dob1 = scanner.nextLine();

              System.out.print("Enter department id: ");
              Integer d_id1 = scanner.nextInt();
                scanner.nextLine();

              System.out.print("Enter department: ");
              String dept = scanner.nextLine();
             
              

              String sql =
                " insert into student" +
                "(s_id, name, dob, d_id, department)" +
                "  values (?, ?, ?, ?, ?)";
              PreparedStatement preparedStmt = con.prepareStatement(sql);
              preparedStmt.setInt(1, s_id1);
              preparedStmt.setString(2, name1);
              preparedStmt.setString(3, dob1);
              preparedStmt.setInt(4, d_id1);
              preparedStmt.setString(5, dept);
              int i = preparedStmt.executeUpdate();
              if (i > 0) {
                System.out.println("Record inserted successfully");
              } else {
                System.out.println("Error Occured");
              }
            } catch (Exception e) { //case 1 try
              System.err.println("Got an exception!");
              // printStackTrace method
              // prints line numbers + call stack
              e.printStackTrace();
              // Prints what exception has been thrown
              System.out.println(e);
            }
            break;
          //case 2: DISPLAY RECORDS
          case 2:
            PreparedStatement ps = con.prepareStatement(
              "select * from student"
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              System.out.println("| " + rs.getInt("s_id"));
              System.out.println("| " + rs.getString("name"));
              System.out.println("| " + rs.getDate("dob"));
              System.out.println("| " + rs.getInt("d_id"));
              System.out.println("| " + rs.getString("department"));
              System.out.println("\n");
            }
            break;
          //case 3: UPDATE RECORDS
          case 3:
            try {
              Scanner scanner = new Scanner(System.in);
              System.out.print("Enter student id: ");
              String s_id1 = scanner.nextLine();

              System.out.print("Enter your name: ");
              String name1 = scanner.nextLine();

              String sql = "UPDATE  student set name = ? where s_id= ? ";
              ps = con.prepareStatement(sql);
              ps.setString(1, name1);
              ps.setString(2, s_id1);
              int i = ps.executeUpdate();
              if (i > 0) {
                System.out.println("Record Updated");
              } else {
                System.out.println("Error Occured");
              }
            } catch (Exception e) {
              System.out.println(e);
            }
            break;
          //case 4: DELETE RECORDS
          case 4:
            {
              Scanner scanner = new Scanner(System.in);
              System.out.print("Enter student name to be deleted: ");
              String name1 = scanner.nextLine();
              String sql = "DELETE FROM student WHERE name = ?";
              PreparedStatement pstmt = con.prepareStatement(sql);
              pstmt.setString(1, name1);
              int i = pstmt.executeUpdate();
              if (i > 0) {
                System.out.println("Record Deleted");
              } else {
                System.out.println("Error Occured");
              }
            }
            break;
          default:
            System.out.println("Enter valid choice");
        } //switch case ends
      } catch (Exception e) { //try statement of class ends
        System.out.println(e);
      } //catch statement of class ends
    } //while loop
  } //main
} //class
