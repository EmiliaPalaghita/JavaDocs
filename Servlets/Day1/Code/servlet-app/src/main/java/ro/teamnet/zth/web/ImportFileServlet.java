package ro.teamnet.zth.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Write javadoc
 *
 * @author Emilia.Palaghita
 */
@MultipartConfig
public class ImportFileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  1: Obtain the username from the request instance

        String user = request.getParameter("user");

        // Obtain the File object from the request instance
        Part file = request.getPart("uploadFile");

        // read the lines from CSV file and print the values
        //  2: Replace T with Person
        List<Person> personsFromFile = readLines(file);

        // Set the response type
        response.setContentType("text/html");

        //  6: Print a nice message to the response so the user will be notified of the result
        // TIP: The final text printed on the response should be something like this: "Hello <username>! You successfully imported 4 people. "

        response.getWriter().write("<h2>Hello " + user +
                "! You successfully imported " + personsFromFile.size() + " people.</h2>");
        for (Person aPersonsFromFile : personsFromFile) {
            response.getWriter().write("<h3>" + aPersonsFromFile.toString() + "</h3>");
        }
    }

    /**
     * write javadoc
     *
     * @param file from which the element are read
     * @return a list of elements of generic type, read from the file
     */
    private List<Person> readLines(Part file) throws IOException {
        List<Person> persons = new ArrayList<>();

        //  3: Replace with try-with-resources
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            Stream<String> stream = bufferedReader.lines();
            persons = stream.map((line) -> {
                Person person = new Person();
                String[] parameters = line.split(",");
                person.setFirstName(parameters[0]);
                person.setLastName(parameters[1]);
                person.setAge(Long.valueOf(parameters[2]));
                person.setMarried(Boolean.valueOf(parameters[3]));
                return person;
            }).collect(Collectors.toList());
        }
        //  4: Iterate through the lines of the reader using java streams.
        // TIP: Use map to get the current line
        // TIP: Use split() method for each line (check API documentation)
        // TIP: For Long and Boolean fields you should use valueOf() method
        // TIP: Use Collectors to return a List

        // after implementing the list, let's print it. It will print nicely if you have overridden the toString() method ;)
        persons.forEach(System.out::println);

//         5: Sort the persons list by their age field
        // TIP: use lambda expression (only one line of code is needed to complete this step)

        persons = persons.stream().sorted(Comparator.comparingLong(Person::getAge)).collect(Collectors.toList());

        // let's print again to check if it's sorted
        persons.forEach(System.out::println);

        return persons;
    }

}
