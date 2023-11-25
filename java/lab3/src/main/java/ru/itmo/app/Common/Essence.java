package Common;
public abstract class Essence {
    private String name;
    protected Essence(String name) {
        setName(name);
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
}
