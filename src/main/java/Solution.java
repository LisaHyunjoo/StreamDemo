import java.util.*;
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
        System.out.println(combinedPeople());
        System.out.println("If any person's income is greater than 8000.0? " + anyMatchWithIncome());
        System.out.println("Are all people male? " + areALllPeopleMale());
        System.out.println("None people have zero income: " + noneHaveZeroIncome());
        System.out.println("There are " + countFemale() + " female(s).");
        System.out.println("First person in the list is " + findFirstPerson());
        System.out.println("Find any person: " + findAnyPerson());
        System.out.println("Person with highest income is : " + personWithHighestIncome());
        System.out.println(personByGender());
        System.out.println(malesAndFemales());
//        System.out.println(calculateIncome());
        System.out.println(allNamesInStr());
        System.out.println("Total income is " + totalIncome());
        System.out.println(highestIncome());
        System.out.println(idToPersonMap());
        System.out.println(personSet());
        parallelStream();
        iterateNum();
        System.out.println(unorderedPeople());
        System.out.println(limitAndSkip());
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
    static List<Character> AllCharsInNames() {
        return Person.persons()
                .stream()
                .flatMap(p -> p.getName().chars().mapToObj(c -> (char) c))
                .collect(Collectors.toList());
    }

    //    9. **Concatenating Streams (Intermediate Operation):**
//            - Create a new stream by concatenating two lists of persons.
    static List<Person> combinedPeople() {
        List<Person> combinedPersons = Stream.concat(Person.persons().stream(), Person.persons().stream())
                .collect(Collectors.toList());
        return combinedPersons;
    }

    //    10. **AnyMatch (Terminal Operation):**
//            - Check if any person's income is greater than 8000.0.
    static boolean anyMatchWithIncome() {
        return Person.persons()
                .stream()
                .anyMatch(p -> p.getIncome() > 8000);
    }

    //11. **AllMatch (Terminal Operation):**
//            - Check if all persons are male.
    static boolean areALllPeopleMale() {
        return Person.persons()
                .stream()
                .allMatch(Person::isMale);
    }

    //    12. **NoneMatch (Terminal Operation):**
//            - Check if none of the persons have zero income.
    static boolean noneHaveZeroIncome() {
        return Person.persons()
                .stream()
                .noneMatch(p -> p.getIncome() == 0.0);
    }

    //13. **Count (Terminal Operation):**
//            - Count the number of persons.
    static int countFemale() {
        return (int) Person.persons()
                .stream()
                .filter(Person::isFemale)
                .count();
    }

    //    14. **FindFirst (Terminal Operation):**
//            - Find the first person in the list.
    static Optional<String> findFirstPerson() {
        return Person.persons()
                .stream()
                .findFirst()
                .map(Person::getName);
    }

    //    1. **FindAny (Terminal Operation):**
//            - Find any person in the list.
    static Optional<Person> findAnyPerson() {
        return Person.persons()
                .stream()
                .findAny();
    }

    //    16 **Max (Terminal Operation):**
//            - Find the person with the highest income.
//    17 **Min (Terminal Operation):**
//            - Find the person with the lowest income.
    static Optional<Person> personWithHighestIncome() {
        return Person.persons()
                .stream()
                .max(Comparator.comparingDouble(Person::getIncome));
//                .min(Comparator.comparingDouble(Person::getIncome));
    }

    //    18. Group the persons by gender.
//            - Partition the persons into male and female.
    static Map<Person.Gender, List<Person>> personByGender(){
        return Person.persons()
                .stream()
                .collect(Collectors.groupingBy(Person::getGender));
    }

    //    19. **PartitioningBy (Collector):**
    static Map<Boolean, List<Person>> malesAndFemales() {
        return Person.persons()
                .stream()
                .collect(Collectors.partitioningBy(Person::isMale));
    }

//      20. **Joining (Collector):**
//            - Join the names of all persons into a single string.
    static String allNamesInStr(){
        return Person.persons()
                .stream()
                .map(Person::getName)
                .collect(Collectors.joining("," ));
    }

//    21. **Reducing (Collector):**
//            - Calculate the total income of all persons.
    static double totalIncome(){
        return Person.persons()
                .stream()
                .map(Person::getIncome)
                .reduce(0.0, (a,b)->a+b);
//                .collect(Collectors.reducing(0.0, Person::getIncome, Double::sum));
    }

//    22. **CollectingAndThen (Collector):**
//            - Find the person with the highest income using CollectingAndThen.
    static Optional<Person> highestIncome() {
        return (Optional.ofNullable(Person.persons()
                .stream()
                .collect(Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingDouble(Person::getIncome)), Optional::get))));
    }

//    23. **ToMap (Collector):**
//            - Create a map of persons where the key is the ID and the value is the person.
    static Map<Long, Person> idToPersonMap() {
        return Person.persons()
                .stream()
                .collect(Collectors.toMap(Person::getId, p -> p));
    }

//    24. **ToSet (Collector):**
//            - Convert the list of persons to a set.
    static Set<Person> personSet() {
        return Person.persons()
                .stream()
                .collect(Collectors.toSet());
    }

    // 25. **Parallel Stream (Intermediate Operation):**
//            - Use parallel stream to process the list of persons.
    static void parallelStream(){
        Person.persons()
                .parallelStream()
                .forEach(p -> System.out.println("Processing: " + p));
    }

//    26. **Iterating (Intermediate Operation):**
//            - Use iterate to create a stream of 10 integers starting from 1.
    static void iterateNum() {
        Stream.iterate(1, n->n+1).limit(10).forEach(System.out::println);
    }

//    27. **Unordered (Intermediate Operation):**
//            - Use unordered to create an unordered stream of the list of persons.
    static List<Person> unorderedPeople(){
        return Person.persons()
                .stream()
                .unordered()
                .collect(Collectors.toList());
    }

//    28. **Limiting and Skipping (Intermediate Operation):**
//            - Use limit and skip to process only the second and third persons.
    static List<Person> limitAndSkip() {
        return Person.persons()
                .stream()
                .skip(1)
                .limit(2)
                .collect(Collectors.toList());
    }
}

