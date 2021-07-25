package Extras;

public class Triplo<A, B, C> {

    private A valor0;
    private B valor1;
    private C valor2;
    
    public Triplo(A value0, B value1, C value2) {
        this.valor0 = value0;
        this.valor1 = value1;
        this.valor2 = value2;
    }

    public A getValue0() {
        return this.valor0;
    }


    public B getValue1() {
        return this.valor1;
    }

    public C getValue2() {
        return this.valor2;
    }

    public void setValue0(A v){
        this.valor0 = v;
    }

    public void setValue1(B v){
        this.valor1 = v;
    }

    public void setValue2(C v){
        this.valor2 = v;
    }

    public static <A, B, C> Triplo<A, B, C> de(A value0, B value1, C value2){
        return new Triplo<A,B,C>(value0, value1, value2);
    }
}
