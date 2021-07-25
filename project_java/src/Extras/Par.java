package Extras;

public class Par<A, B> {
    
    private A valor0;
    private B valor1;
    
    public Par(A value0, B value1) {
        this.valor0 = value0;
        this.valor1 = value1;
    }

    public A getValue0() {
        return this.valor0;
    }


    public B getValue1() {
        return this.valor1;
    }

    public void setValue0(A v){
        this.valor0 = v;
    }

    public void setValue1(B v){
        this.valor1 = v;
    }

    public static <A, B> Par<A, B> de(A value0, B value1){
        return new Par<A,B>(value0, value1);
    }
}
