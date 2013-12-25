package Lab10;

/**
 *
 * @author krzysztof
 */
public class Lokomotywa extends Wielobok {
    
	private static final long serialVersionUID = 1L;
	static int[] X = {0,35,35,50,45,60,55,75,75,70,75,70,55,50,55,
    45,50,45,30,25,30,20,25,20,5,0,5,0};
    static int[] Y = {225,225,250,250,225,225,250,250,280,280,290,300,300,290,280,280,
    290,300,300,290,280,280,290,300,300,290,280,280};
    
     Lokomotywa() {
        super(X, Y);
        //wywołanie konstruktora klasy Polygon
    } 
    //trzeci parametr to liczba wierzchołków – 
    //wyliczana jako minimum z długości obu tablic współrzędnych
}