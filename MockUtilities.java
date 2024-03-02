import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;

public class MockUtilities {
    private FileInputStream fileInputStream;
    private BufferedReader bufferedReader;
    /**
     * 
     * @param filepath for which the Reader is needed
     * @return BufferedReader for the file in location at @filepath parameter
     */
    private BufferedReader getFileReader(String filePath) {
        try {
            fileInputStream = new FileInputStream(filePath);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bufferedReader;
    }

    /**
     * 
     * @param base_year
     * @param gap
     * @return returns a random date generated between the base_year and (base_year
     *         + gap_year)
     */
    public String getDate() {

        int base_year = 1990; // Counting of the year starts from this year and gap is added to achecive the
                              // random year
        int year_gap = ((int) Math.ceil(Math.random() * 14)); // Specify gap between randomly generated year (currently
                                                              // 1-14) so add 1990+gap=Random_Year
        int majorMonths[] = { 1, 3, 5, 7, 8, 10, 12 };
        int minorMonths[] = { 4, 6, 9, 11 };
        // Counting of the year starts from this year and gap is added to get the random
        // year
        int month = (int) Math.ceil(Math.random() * 12); // returns value from 1 to 12
        int date;
        /*
         * If a month is major then we will choose a day from 31 days, if minor then
         * from 30 days
         * and if its february then check for leap year conditions
         */
        if (Arrays.binarySearch(majorMonths, month) > 0) {
            date = (int) Math.ceil(Math.random() * 31);
        } else if (Arrays.binarySearch(minorMonths, month) > 0) {
            date = (int) Math.ceil(Math.random() * 30);
        } else {
            if (isLeapYear(base_year + year_gap))
                date = (int) Math.ceil(Math.random() * 29);
            else
                date = (int) Math.ceil(Math.random() * 28); // returns value from 1 to 28
        }
        return date + "-" + month + "-" + (base_year + year_gap);
    }

    /**
     * 
     * @param base_year
     * @param gap
     * @return returns a random date generated between the base_year and (base_year
     *         + gap_year)
     */
    public String getDate(int base_year, int gap) {
        int majorMonths[] = { 1, 3, 5, 7, 8, 10, 12 };
        int minorMonths[] = { 4, 6, 9, 11 };
        // Counting of the year starts from this year and gap is added to achecive the
        // random year
        int year_gap = ((int) Math.ceil(Math.random() * gap)); // Specify gap between randomly generated year (currently
                                                               // 1-14) so add 1990+gap=Random_Year
        int month = (int) Math.ceil(Math.random() * 12);// returns value from 1 to 12
        int date;

        if (Arrays.binarySearch(majorMonths, month) > 0) {
            date = (int) Math.ceil(Math.random() * 31);
        } else if (Arrays.binarySearch(minorMonths, month) > 0) {
            date = (int) Math.ceil(Math.random() * 30);
        } else {
            if (isLeapYear(base_year + year_gap))
                date = (int) Math.ceil(Math.random() * 29);
            else
                date = (int) Math.ceil(Math.random() * 28); // returns value from 1 to 28
        }
        return date + "-" + month + "-" + (base_year + year_gap);
    }

    /**
     * 
     * @return returns a random salary generated between 1K-50K
     */
    public int getSalary() {
        int range = 50; // Set the salary range
        int salary = (int) Math.ceil(Math.random() * range) * 1000; // returns salary in range of 1-50K
        return salary;
    }

    /**
     * 
     * @return a randomly generated 10 digit phone numbers
     */
    public String getPhoneNumber() {
        String phoneNumber = "";
        for (int i = 0; i < 10; i++) {
            phoneNumber += (int) Math.ceil((Math.random()) * 9);
        }
        return phoneNumber;
    }

    /**
     * 
     * @param firstName
     * @param lastName
     * @return email is generated as firstName.lastName@host.com
     */
    public String getEmailAddress(String firstName, String lastName) {
        String[] extenstions = { "@gmail.com", "@yahoo.com", "@hotmail.com", "@outlook.com", "@redit.com" };
        String emailAddress = firstName + "." + lastName + extenstions[(int) Math.floor((Math.random()) * 5)];
        return emailAddress;
    }

    /**
     * 
     * @return integer with department id within range 100-105
     */
    public int getDepartmentId() {
        int range = (int) Math.ceil(Math.random() * 5);
        return 100 + range;
    }

    /**
     * 
     * @return randomly generated month with first three initial letters
     */
    public String getMonth() {
        return Month.of((int) Math.ceil(Math.random() * 12))
                .toString()
                .substring(0, 3);
    }

    /**
     * 
     * @param year
     * @return true when given year is leap and false if not
     * 
     */
    public boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
            return true;
        else
            return false;
    }
    /**
     * 
     * @return ArrayList<String> containing the last names from file raw_data/last_name.txt
     */
    public ArrayList<String> getLastNameUsingFile() {
        ArrayList<String> lastNames = new ArrayList<>();
        try {
            BufferedReader bufferedReader = getFileReader("raw_data/last_name.txt");
            String currentLine = bufferedReader.readLine();
            while (currentLine != null) {
                lastNames.add(currentLine);
                currentLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("[MockUtilities] Closed stream somehow!");
            e.printStackTrace();
        }

        return lastNames;
    }
    /**
     * 
     * @return ArrayList<String> containing the first names from file raw_data/first_name.txt
     */
    public ArrayList<String> getFirstNameUsingFile() {
        ArrayList<String> firstNames = new ArrayList<>();
        try {
            BufferedReader bufferedReader = getFileReader("raw_data/first_name.txt");
            String currentLine = bufferedReader.readLine();
            while (currentLine != null) {
                firstNames.add(currentLine);
                currentLine = bufferedReader.readLine();
            }
            bufferedReader.close(); 
        } catch (Exception e) {
            System.out.println("[MockUtilities] Closed stream somehow!");
            e.printStackTrace();
        }

        return firstNames;
    }

    /**
     * 
     * @return ArrayList<String> containing cities from file raw_data/cities.txt
     */
    public ArrayList<String> getCities() {
        ArrayList<String> cities = new ArrayList<>();
        try {
            BufferedReader bufferedReader = getFileReader("raw_data/cities.txt");
            String city = bufferedReader.readLine();
            while (city != null) {
                cities.add(city);
                city = bufferedReader.readLine();
            }
            bufferedReader.close(); 
        } catch (Exception e) {
            System.out.println("[MockUtilities] Closed stream somehow!");
            e.printStackTrace();
        }

        return cities;
    }

}
