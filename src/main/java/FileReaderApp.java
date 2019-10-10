import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReaderApp {

    public String testMain() {

        File myFile = new File("D:\\Developer\\javaDeveloper\\JavaProjects\\FileReaderTest\\src\\main\\java\\dane.txt");

        List personList = listPerson(myFile);

        Person oldest = findOldestPerson(personList);

        String personInfo = getPersonInfo(oldest);
        int personCounter = getPersonCounter(personList);

        return "Users: " + personCounter + " | " + personInfo;
    }

    private static int getPersonCounter(List personList) {

        return personList.size();
    }

    private static String getPersonInfo(Person oldest) {

        String personInfo;

        LocalDate today = LocalDate.now();
        int age = today.getYear() - oldest.getDateOfBirth().getYear();

        if (oldest.getPhoneNumber() != null) {
            personInfo = oldest.getName() + " " + oldest.getSurname() + " " + age + " " + oldest.getPhoneNumber();
        } else {
            personInfo = oldest.getName() + " " + oldest.getSurname() + " " + age;
        }

        return personInfo;
    }

    private static List listPerson(File myFile){

        List<Person> personList = new ArrayList<>();

        try (Scanner scanner = new Scanner(myFile)) {

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] elements = line.split(",");
                String name = elements[0];
                String surname = elements[1];
                String date = elements[2];
                String phone = null;
                if (elements.length > 3) {
                    phone = elements[3];
                }

                LocalDate convertedDate = LocalDate.parse(date);

                Person person = new Person(name, surname, convertedDate, phone);

                personList.add(person);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found");
        }

        return personList;
    }

    private static Person findOldestPerson(List<Person> personList) {
        Person oldest = null;

        for (Person person : personList) {
            if (oldest == null || oldest.getDateOfBirth().isAfter(person.getDateOfBirth())) {
                oldest = person;
            }
        }

        return oldest;
    }
}
