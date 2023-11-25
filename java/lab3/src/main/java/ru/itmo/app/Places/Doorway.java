package Places;
public class Doorway extends SinglePlace {
    private String width;
    private String length;
    public Doorway(String name) {
        super(name);
    }
    public void setWidth(String width) {
        this.width = width;
    }
    public void setLength(String length) {
        this.length = length;
    }
    public String describe() {
        return getName() + " c шириной " + width + " и длиной " + length;
    }
}
