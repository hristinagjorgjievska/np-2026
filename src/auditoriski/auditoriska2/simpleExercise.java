//package auditoriski.auditoriska2;
//
////VOVED VO LAMBDA IZRAZI I FUNKCISKI INTERFEJSI
//
////BEZ LAMBDA IZRAZI - DOLG NACIN SO SOZDAVANJE NA CELOSNO NOVA KLASA (ADDITION)
//
//import java.util.function.Consumer;
//import java.util.function.Function;
//import java.util.function.Predicate;
//import java.util.function.Supplier;
//
//interface Operation1{
//    int apply(int a, int b);
//}
//
//class Addition implements Operation1{
//
//    @Override
//    public int apply(int a, int b) {
//        return a + b;
//    }
//}
//
//public class simpleExercise{
//    public static void main() {
//        Operation1 operation = new Addition();
//        System.out.println(operation.apply(5,3));
//
//
//        //SO LAMBDA IZRAZ - KRATENKA NA CELOTO PISUVANJE NA NOVA KLASA SAMO ZA DA SE DEFINIRA STO PRAVI METODOT
//        Operation1 operation1 = (a,b) -> a + b;
//        System.out.println(operation1.apply(5, 3));
//
//        Operation1 operation2 = (a, b) -> a - b;
//        System.out.println(operation2.apply(5,3));
//    }
//}
//
////OBLIK NA LAMBDA IZRAZ
////(VLEZNI PARAMETRI) -> TELO/REZULTAT
//
////GOTOVI VGRADENI INTERFEJSI VO JAVA
////1. Function<String, Integer> length = zemi string, vrati broj (pr. dolzina)
////2. Predicate<Integer> isEven = zemi broj, vrati true/false
////3. Consumer<String> print = zemi string, ne vrakjaj nisto (pr. samo ispecati go ili napravi nesto so nego)
////4. Supplier<Long> time = ne zemas nisto, samo vrati nesto
//
////PRIMERI:
////1. Function
////Function<String, Integer> length = s -> s.length();
//
////2. Predicate
////Predicate<Integer> isEven = n -> n % 2 == 0;
//
////3. Consumer
////Consumer<String> print = s -> System.out.println(s);
//
////4. Supplier
////Supplier<Long> time = () -> System.currentTimeMillis();