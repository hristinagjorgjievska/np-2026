//package auditoriski.auditoriska2;
//
////Funkciski interfejs - ima samo eden apstrakten metod (apply)
////Poradi toa, moze da se implementira i preku lambda izraz
//interface Operation{
//    int apply(int a, int b);
//}
//
////NACIN 1: Obicna (imenuvana) klasa - konkretna implementacija na Operation1
//class Addition implements Operation1 {
//
//    @Override
//    public int apply(int a, int b) {
//        return a + b;
//    }
//}
//
////Funkciski interfejs - eden metod (getMessage), nema argmenti, vrakja String
//interface MessageProvider{
//    String getMessage();
//}
//
////NACIN 1: Obicna klasa - sekogas vrakja ista, fiksna poraka
//class StaticMessage implements MessageProvider{
//
//    @Override
//    public String getMessage() {
//        return "Hello from a regular class";
//    }
//}
//
//public class InterfaceDemo {
//    public static void main(String[] args) {
//
//        //Interface with arguments
//
//        //Prv nacin: klasa - sozdavame instanca od Addition
//        Operation1 op1 = new Addition();
//        System.out.println("Addition: " + op1.apply(5,3));
//
//        //Vtor nacin: anonimna klasa - nova klasa bez ime, definirana inline
//        //Ednokratna implementacija na Operation1 (mnozi namesto da sobira kako gore)
//        Operation1 op2 = new Operation1() {
//            @Override
//            public int apply(int a, int b) {
//                return a * b;
//            }
//        };
//        System.out.println("Multiplication: " + op2.apply(5,3));
//
//        //Tret nacin: lambda izraz - najkratka verzija, isto kako anonimna klasa
//        // (a,b) -> teloto avtomatski se smeta za return vrednost na apply
//        Operation1 op3 = (a, b) -> a - b;
//        System.out.println("Subtraction: " + op3.apply(5,3));
//
//
//        //Interface without arguments
//        //Prv nacin: klasa
//        MessageProvider m1 = new StaticMessage();
//        System.out.println(m1.getMessage());
//
//        //Vtor nacin: anonimna klasa - inline definicija bez ime
//        MessageProvider m2 = new MessageProvider() {
//            @Override
//            public String getMessage() {
//                return "Hello from an anonymous class!";
//            }
//        };
//        System.out.println(m2.getMessage());
//
//        //Tret nacin: lambda bez parametri - prazni zagradi bidejki getMessage() ne prima argumenti
//        MessageProvider m3 = () -> "Hello from a lambda";
//        System.out.println(m3.getMessage());
//    }
//
//}
