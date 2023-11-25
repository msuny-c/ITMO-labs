package Common;
public enum Directions {
    WEST("запад"),
    OPPOSITE("напротив"),
    HERE("здесь");
    private final String directionDescribe;
    Directions(String directionDescribe) {
        this.directionDescribe = directionDescribe;
    }

    public String getDescribe() {
        return directionDescribe;
    }

    @Override
    public String toString() {
        return getDescribe();
    }
}
