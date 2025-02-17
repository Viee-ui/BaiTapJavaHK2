package Ex6_5;

public class ResizableCircle extends Circle implements Resizable {

    //Constructor
    public  ResizableCircle(double radius) {
        super(radius);
    }


    @Override
    public void resize(int percent) {
        radius *= ( 1 + percent / 100.0 );
    }
}
