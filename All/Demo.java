import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Demo{
    public static void main(String[] args) {
        List<String> fruits = Arrays.asList("Apple", "Banana", "Mango", "Strawberry", "Pineapple");

        // Mapping fruits to their lengths
        Map<String, Integer> fruitLengthMap = fruits.stream()
                .collect(Collectors.toMap(fruit -> fruit, String::length));

        System.out.println("Fruit Length Map: " + fruitLengthMap);

        // Finding the maximum length
        Optional<Integer> maxLength = fruits.stream()
                .map(String::length) // Extracting lengths
                .max( ); // Finding max length

        // Printing the result
        maxLength.ifPresent(length -> System.out.println("Maximum Length: " + length));
    }
}
