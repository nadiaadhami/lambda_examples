package nadiatests;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.*;

public class Lambda {
    private static final Log log = LogFactory.getLog(Lambda.class);

    public class Person {
        String firstName;
        String lastName;
        public String getFirstName() { return firstName;}
        public String getLastName() { return lastName;}
        Person (String first, String last) {
            this.firstName = first; this.lastName = last;
        }
    }
    public class SortPerson implements  Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
             int n = p1.getLastName().compareTo(p2.getLastName());
             if (n == 0) {
                 return p1.getFirstName().compareTo(p2.getFirstName());
             }
             return n;
         }
    }
    public void testLambda() {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        // descending sort
        Collections.sort(names, (a,b) -> b.compareTo(a));     //GOOD
        log.info("descending sort");
        log.info(names);
        // ascending sort
        Collections.sort(names, (a,b) -> a.compareTo(b));
        log.info("ascending sort");
        log.info(names);
    }
    public void testJava8() {
        List<String> list = new ArrayList<>();
        list.add("one"); list.add("two"); list.add("three"); list.add("four");
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Albert", "Einstein"));
        persons.add(new Person("Thomas", "Edison"));
        persons.add(new Person("Elan","Musk"));
        // JAVA 7
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
            }
        };
        for (String s: list)  {
            System.out.println(s);
        }
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        Collections.sort(persons, new SortPerson());
        // JAVA 8
        ActionListener al8 = e -> System.out.println(e.getActionCommand());
        list.forEach(System.out::println);
        Collections.sort(list, (o1,o2) -> o1.length() - o2.length());
        list.sort(Comparator.comparingInt(String::length));
        //todo syntax error
//        persons.sort(Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName));
    }

    public static void main(String[] args) {
        log.info("java.version=" + System.getProperty("java.version"));
        Lambda lambda = new Lambda();
        lambda.testLambda();
        lambda.testJava8();
    }

}
