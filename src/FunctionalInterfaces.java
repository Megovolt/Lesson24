import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class FunctionalInterfaces {

    public static void main(String[] args) {
//        m1();
//        m2();
//        m3();
//        m4();
//        m5();
//        Optional ();

    }

    private static void m1(){
        Predicate<Integer> isPositive = x -> x > 0;
        System.out.println(isPositive.test(5));
        System.out.println(isPositive.test(-7));

        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);

        System.out.println(sumAll(numbers, n->true));
        System.out.println(sumAll(numbers, n->n%2==0));
        System.out.println(sumAll(numbers, n->n>3));
    }

    private static int sumAll(List<Integer> numbers, Predicate<Integer> p){
        int total = 0;
        for (int number: numbers){
            if (p.test(number)){
                total += number;
            }
        }
        return total;
    }

    private static void m2(){
        BinaryOperator<Integer> multiply = (x, y) -> x*y;

        System.out.println(multiply.apply(3, 5));
        System.out.println(multiply.apply(10, -2));

        BinaryOperator<StringBuilder> op = (sb1, sb2) -> sb1.append(sb2);//StringBuilder::append - можно еще так
        System.out.println(op.apply(new StringBuilder("Functional Interface"), new StringBuilder("in Java 8")));


    }

    private static void m3(){
        UnaryOperator<Integer> square = x -> x*x;
        System.out.println(square.apply(5));

        UnaryOperator<StringBuilder> op = sb -> reverseAndToUpperCase(sb);
        System.out.println(op.apply(new StringBuilder("Java 8")));
    }

    private static StringBuilder reverseAndToUpperCase(StringBuilder sb){
        return new StringBuilder(sb.insert(0, "it-courses.by ")
                .append(".html")
                .toString()
                .replace(" ", "_")
                .toLowerCase());
    }

    private static void m4(){
        Function<Integer, String> convert = x-> String.valueOf(x) + " roubles";
        Function<String, Integer> stringForNumber = x -> Integer.valueOf(x) * 100;

        System.out.println(convert.apply(5));// interFace Function<T, R> {R apply(T);}
        System.out.println(stringForNumber.apply("100"));

        Consumer<Integer> printer = x -> System.out.printf("%d roubles \n", x);
        printer.accept(600);//interFace Consumer<T> {void accept(T t);}
    }

    private static void m5(){
        Supplier<User> userFactory = () -> {
            Scanner in = new Scanner(System.in);

            System.out.println("Enter name");

            String name = in.nextLine();

            return new User(name);
        };

        User user1 = userFactory.get();
        User user2 = userFactory.get();

        System.out.println("user 1 name " + user1.getName());
        System.out.println("user 2 name " + user2.getName());

    }

   static class User{
        private String name;
        public User(String name){
            this.name = name;
        }

       public String getName() {
           return name;
       }
   }

   private static void sorting(){
        List<String> names = Arrays.asList("peter", "anna", "mike", "olga");

       Collections.sort(names, new Comparator<String>() {

           @Override
           public int compare(String a, String b) {
               return b.compareTo(a);
           }
       });

       Collections.sort(names, (String a, String b) -> b.compareTo(a));

       Collections.sort(names, (a, b) -> b.compareTo(a));

       Collections.sort(names, Comparator.reverseOrder());

       names.sort(Comparator.reverseOrder());
   }

   public static Integer StringToNumberNotOptional(String string){
       Integer result;
       try {
           result = Integer.valueOf(string);
       }catch (NumberFormatException e){
           result = null;
       }
       return result;

   }

   public static Optional<Integer> strTonumber(String str){
       Optional<Integer> result;
       try {
           result = Optional.ofNullable(Integer.valueOf(str));
       }catch (NumberFormatException e){
           result = Optional.empty();
       }
       return result;
   }

   private static void Optional(){
       List<String> strings = Arrays.asList("9", null, "two", "2");

       List<Integer> numbers = strings.stream()
//               .map(string -> StringToNumberNotOptional(string))
////               .filter(min -> num != null) чтобы убрать ошибку с null
//               .map(number -> number * 100)
//               .collect(Collectors.toList());
               .map(string -> strTonumber(string))
               .filter(optional -> optional.isPresent())//оставить только значения
               .map(optional -> optional.get())
               .map(number -> number*100)
               .collect(Collectors.toList());

   }
   

}
