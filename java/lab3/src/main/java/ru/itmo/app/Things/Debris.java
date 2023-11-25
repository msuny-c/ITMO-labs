package Things;
public class Debris extends Thing {
    public Debris(String name) {
        super(name);
    }
    public void makeEasier(String something) {
        System.out.println(getName() + " упростил " + something);
    }
}
