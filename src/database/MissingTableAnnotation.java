package database;


public class MissingTableAnnotation extends Exception {


    private static final long serialVersionUID = -6492630862334539001L;
    
    public MissingTableAnnotation(Class<?> c) {
        super(c.getName()+" does not contain the Table annotation.");
    }
}
