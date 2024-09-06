package Homework4;

public abstract class ClassTemplate {

    @NullableWarning
    private String id;

    public String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }

    public void print(){
        System.out.println("print content in console");
    }
}
