import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;



/**
 * App
 */
public class App {
    public static void main(String[] args) {
        MockUtilities utility = new MockUtilities();
        int recordsReuired = 0;
        int insertCount = 0;

        System.out.println("How many records you want to generate? (Enter number only!)\n");
        try (BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in))) {
            boolean isNotANumber = true;
            while (isNotANumber) {
                String sp = bReader.readLine();
                try {
                    int a = Integer.parseInt(sp);
                    recordsReuired = a;  
                    isNotANumber = false;                  
                } catch (Exception e) {
                    System.out.println("Please enter a number!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> lastNames = utility.getLastNameUsingFile();
        ArrayList<String> firstNames = utility.getFirstNameUsingFile();
        ArrayList<String> cities = utility.getCities();
        // Decalre pointers to point to the array location
        int lastNamePointer = 0;
        int firstNamePointer = 0;
        int cityPointer = 0;
        int employeeId = 10000;

        try {
            FileWriter fileWriter = new FileWriter("dataToMock.sql");
            while (insertCount < recordsReuired) {
                //generate index from the pointers
                int firstNameIndex = firstNamePointer % (firstNames.size());
                int lastNameIndex = lastNamePointer % (lastNames.size());
                int cityIndex = cityPointer % (cities.size());

                String firstName = firstNames.get(firstNameIndex);
                String lastName = lastNames.get(lastNameIndex);
                String fullName = firstName + " " + lastName;
                int departmentId = utility.getDepartmentId();
    
                String employeeTableName = "ACTIN_TRAINING_EMPLOYEE_SUP";
                String salaryTableName = "ACTIN_TRAINING_SALARY_SUP";
    
                String insertEmployeeQuery = "INSERT INTO " + employeeTableName + " VALUES" +
                        "(" +
                        (++employeeId) + ", " +
                        "\'" + fullName + "\'" + ", " + // To add a string value we add it like 'Value' so we are adding // "\'" at begining and end
                        "\'" + firstName + "\'" + ", " +
                        "\'" + lastName + "\'" + ", " +
                        "TO_DATE(" + "\'" + utility.getDate(1994, 10) + "\'," + "'DD-MM-YYYY')" + ", " +  // For date we use TO_DATE('wantedDate','Format') function
                        "\'" + utility.getEmailAddress(firstName, lastName) + "\'" + ", " +         // mail = firstname.lastname@domain.com
                        utility.getPhoneNumber() + ", " +
                        "TO_DATE(" + "\'" + utility.getDate() + "\'," + "'DD-MM-YYYY')" + ", " +
                        "TO_DATE(" + "\'" + utility.getDate() + "\'," + "'DD-MM-YYYY')" + ", " +
                        "-1, " +
                        "-1, " +
                        departmentId + "," +
                        "\'" + cities.get(cityIndex) + "\'" +
                        ");";
    
                String insertSalaryQuery = "INSERT INTO " + salaryTableName + " VALUES" +
                        "(" +
                        (employeeId) + ", " +
                        departmentId + "," +
                        utility.getSalary() + "," +
                        "\'" + utility.getMonth() + "\'" + "," +
                        "TO_DATE(" + "\'" + utility.getDate() + "\'," + "'DD-MM-YYYY')" + ", " +
                        "TO_DATE(" + "\'" + utility.getDate() + "\'," + "'DD-MM-YYYY')" + ", " +
                        "-1 " + "," +
                        "-1 " +
                        ");";
    
                fileWriter.append(insertEmployeeQuery + "\n");
                fileWriter.append(insertSalaryQuery + "\n");

                firstNamePointer++;
                lastNamePointer++;
                insertCount++;
            }            
            // clear and close resources
            lastNames.clear();
            firstNames.clear();
            cities.clear();

            fileWriter.close();
        }
        catch(IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
