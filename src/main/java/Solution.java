import java.util.List;

public class Solution {
    public static void main(String[] args) {

        System.out.println(malesOnly(Person.persons()));
        namesOnly(Person.persons()).forEach(System.out::println);

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
}
