import java.util.concurrent.atomic.AtomicLong;

public class RandomEternal {
    long a, c, m;

    private AtomicLong x;

    public RandomEternal(){
        this.a = 25214903917l;
        this.m = 2 ^ 48;
    }

    public RandomEternal withSeed(int c){
        this.c = c;
        x = new AtomicLong(c);
        return this;
    }

    public int next(){
        return (int) ((a*x.getAndIncrement() +c) % m);
    }
}
