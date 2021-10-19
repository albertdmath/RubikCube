/**
 * cubicle is the object that represents a 1*1*1 cubicle on the 2*2*2 Rubik's Cube. 
 * There are 8 cubicles represented in the Rubiks2 class by a 
 * 3 dimensional array of objects with capacity 2 in each dimension. 
 * When dealing with Rubik's Cubes, both the location of the cubicle
 * in 3 space and the orientation of each of the three stickers matters.
 * @author Albert Sandru
 * @version 4/11/2021
 */
public class cubicle
{
    private String color1;
    private String color2;
    private String color3;
    private String color1orientation;
    private String color2orientation;
    private String color3orientation;
    
    //letters used for the computer to solve the Ruibk's Cube
    private String letter1;
    private String letter2;
    private String letter3;

    /**
     * Constructor for objects of class cubicle
     */
    public cubicle(String color1,String letter1,String color2,String letter2,String color3,String letter3,String color1orientation,String color2orientation,String color3orientation)
    {
        this.color1=color1;
        this.color2=color2;
        this.color3=color3;
        this.color1orientation=color1orientation;
        this.color2orientation=color2orientation;
        this.color3orientation=color3orientation;
        
        this.letter1=letter1;
        this.letter2=letter2;
        this.letter3=letter3;
    }
    //setters
    public void setColor1(String str)
    {
        color1 = str;
    }
    public void setColor2(String str)
    {
        color2 = str;
    }
    public void setColor3(String str)
    {
        color3 = str;
    }
    public void setColor1Orientation(String str)
    {
        color1orientation = str;
    }
    public void setColor2Orientation(String str)
    {
        color2orientation = str;
    }
    public void setColor3Orientation(String str)
    {
        color3orientation = str;
    }
    public void setLetter1(String str)
    {
        letter1=str;
    }
    public void setLetter2(String str)
    {
        letter2=str;
    }
    public void setLetter3(String str)
    {
        letter3=str;
    }
    
    //getters 
    public String getColor1()
    {
        return color1;
    }
    public String getColor2()
    {
        return color2;
    }
    public String getColor3()
    {
        return color3;
    }
    public String getColor1Orientation()
    {
        return color1orientation;
    }
    public String getColor2Orientation()
    {
        return color2orientation;
    }
    public String getColor3Orientation()
    {
        return color3orientation;
    }
    public String getLetter1()
    {
        return letter1;
    }
    public String getLetter2()
    {
        return letter2;
    }
    public String getLetter3()
    {
        return letter3;
    }
}