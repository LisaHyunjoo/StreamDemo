import com.sun.source.doctree.SeeTree;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    public static void main(String[] args) {

        System.out.println(malesOnly(Person.persons()));
        namesOnly(Person.persons()).forEach(System.out::println);
        sortedByIncomeDesc().forEach(System.out::println);
        System.out.println("Distinct Genders are " + distinctGender());
        System.out.println();
        System.out.println("First three people on the list");
        firstThreePeople().forEach(System.out::println);
        System.out.println();
        System.out.println("List of people on the list skipped first two people");
        skipTwoPerson().forEach(System.out::println);
        System.out.println();
        displayNames();
        System.out.println();
        System.out.println(AllCharsInNames());
        System.out.print(combinedPersons());

    }

    //   1. **Filtering (Intermediate Operation):**
//       - Filter the list of persons to include only males.
    static List<String> malesOnly(List<Person> people) {
        people = Person.persons();
        List<String> males = people.stream()
                .filter(Person::isMale)
                .map(Person::getName)
                .toList();
        return males;
    }

    //    2. **Mapping (Intermediate Operation):**
//            - Map the list of persons to their names.
    static List<String> namesOnly(List<Person> people) {
        people = Person.persons();
        List<String> names = people.stream()
                .map(Person::getName)
                .toList();
        return names;
    }

    //    3. **Sorting (Intermediate Operation):**
//            - Sort the list of persons by their income in descending order.
    static List<Person> sortedByIncomeDesc() {
        List<Person> sortedList = Person.persons()
                .stream()
                .sorted(Comparator.comparing(Person::getIncome).reversed())
                .toList();
//                .sorted(Comparator.comparing(Person::getIncome).thenComparing(Person::getName))
//                .toList();
        return sortedList;
    }

    //    4. **Distinct (Intermediate Operation):**
//            - Find the distinct genders in the list of persons.
    static List<Person.Gender> distinctGender() {
        List<Person.Gender> genders = Person.persons()
                .stream()
                .map(Person::getGender)
                .distinct()
                .toList();
        return genders;
    }

    //    5. **Limit (Intermediate Operation):**
//            - Limit the list of persons to the first 3.
    static List<Person> firstThreePeople() {
        List<Person> top3 = Person.persons()
                .stream()
                .limit(3)
                .toList();

        return top3;
    }

    //    6. **Skip (Intermediate Operation):**
//            - Skip the first 2 persons in the list.
    static List<Person> skipTwoPerson() {
        List<Person> skippedPeople = Person.persons()
                .stream()
                .skip(2)
                .toList();
        return skippedPeople;
    }

    //7. **Peek (Intermediate Operation):**
//            - Use peek to print the names of all persons in the list.
    static void displayNames() {
        Person.persons()
                .stream()
                .peek(p -> System.out.println("Person name" + p.getName()))
                .forEach(System.out::println);

    }
//
//    8. **FlatMap (Intermediate Operation):**
//            - Map each person to a stream of characters representing their names.
    static List<Character> AllCharsInNames(){
        return Person.persons()
                .stream()
                .flatMap(p -> p.getName().chars().mapToObj(c -> (char)c))
                .collect(Collectors.toList());
    }

//    9. **Concatenating Streams (Intermediate Operation):**
//            - Create a new stream by concatenating two lists of persons.
    static List<Person> combinedPersons(){
        List<Person> combinedPersons = Stream.concat(Person.persons().stream(), Person.persons().stream())
               .collect(Collectors.toList());
       return combinedPersons;
    }



}
