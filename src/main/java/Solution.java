import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {

        System.out.println(malesOnly(Person.persons()));
        namesOnly(Person.persons()).forEach(System.out::println);
        sortedByIncomeDesc().forEach(System.out::println);
    }

//   1. **Filtering (Intermediate Operation):**
//       - Filter the list of persons to include only males.
    static List<String> malesOnly(List<Person> people){
        people = Person.persons();
        List<String> males = people.stream()
                .filter(Person::isMale)
                .map(Person::getName)
                .toList();
        return males;
    }

//    2. **Mapping (Intermediate Operation):**
//            - Map the list of persons to their names.
    static List<String> namesOnly(List<Person> people){
        people = Person.persons();
        List<String> names = people.stream()
                .map(Person::getName)
                .toList();
        return names;
    }

//    3. **Sorting (Intermediate Operation):**
//            - Sort the list of persons by their income in descending order.
    static List<Person> sortedByIncomeDesc(){
        List<Person> sortedList = Person.persons()
                .stream()
                .sorted(Comparator.comparing(Person::getIncome).reversed())
                .toList();
//                .sorted(Comparator.comparing(Person::getIncome).thenComparing(Person::getName)
//                .toList();
        return sortedList;
    }
}
