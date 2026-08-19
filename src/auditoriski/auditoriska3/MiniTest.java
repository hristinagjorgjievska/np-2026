package auditoriski.auditoriska3;

class Dvojka<A, B>{
    private A prv;
    private B vtor;

    public Dvojka(A prv, B vtor) {
        this.prv = prv;
        this.vtor = vtor;
    }

    public A getPrv() {
        return prv;
    }

    public B getVtor() {
        return vtor;
    }

    public static <T extends Number> T poslednElement(T[] array){
        T item = null;
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1){
                item = array[i];
            }
        }
        return item;
    }
}

public class MiniTest {
}
