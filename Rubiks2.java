import java.util.Scanner;
/**
 * This is a Rubik's Cube 2*2*2 simulator Java Program (human scramble-human solve mode).
 * @author Albert Sandru
 * @version 4/11/2021
 */
public class Rubiks2
{
    public static void human(cubicle[][][]c)
    {
        //Needed for graphics!
        GUI g = new GUI();
        
        //Outputting the current state of the cube to the screen.
        g.setCanvas();
        g.leftCube(c);
        g.rightCube(c);
        
        //User friendly instructions.
        System.out.println("************************************************************************************************************");
        System.out.println("*                                                                                                          *");
        System.out.println("* The Rubik's Cube is currently in its solved state.                                                       *");
        System.out.println("* Please enter \"f\",\"r\", or \"u\" into the terminal window.                                                   *");
        System.out.println("* This will make a clockwise rotation of the front, right, or upper face of the Rubik's Cube respectively. *");
        System.out.println("* To make a counterclockwise rotation, simply enter the letter 3 times.                                    *");
        System.out.println("* Please enter any other letter if you wish to end the program.                                            *");
        System.out.println("* If you selected option 3 from the main menu,                                                             *");
        System.out.println("* You may enter \"q\" if you want the computer to solve the cube given its current state.                    *");        
        System.out.println("*                                                                                                          *");
        System.out.println("************************************************************************************************************");
        
        Scanner keyboard = new Scanner(System.in);
        char ch = keyboard.next().charAt(0);
        while(ch=='f' || ch=='r'|| ch=='u')
        {
            if(ch=='f')
            {
                //change contents of the 3 dimensional array (perform a clockwise 4-way swap of everything on the z=1 plane):
                cubicle temp = c[0][1][1];
                c[0][1][1]=c[0][0][1];
                c[0][0][1]=c[1][0][1];
                c[1][0][1]=c[1][1][1];
                c[1][1][1]=temp;
                
                //It is imperative to change the orientation of the stickers. This was not taken care of in the swap above.
                //Loop through all 4 cubicle objects affected by the front turn:
                for(int x=0;x<2;x++)
                {
                    for(int y=0;y<2;y++)
                    {
                        String[]orientations = {c[x][y][1].getColor1Orientation(),c[x][y][1].getColor2Orientation(),c[x][y][1].getColor3Orientation()};
                        for(int i=0; i<orientations.length;i++)
                        {
                            if(orientations[i].equals("up"))
                            {
                                switch(i)
                                {
                                    case 0:
                                    c[x][y][1].setColor1Orientation("right");
                                    break;
                                    case 1:
                                    c[x][y][1].setColor2Orientation("right");
                                    break;
                                    case 2:
                                    c[x][y][1].setColor3Orientation("right");
                                    break;
                                }
                            }
                            else if(orientations[i].equals("right"))
                            {
                                switch(i)
                                {
                                    case 0:
                                    c[x][y][1].setColor1Orientation("down");
                                    break;
                                    case 1:
                                    c[x][y][1].setColor2Orientation("down");
                                    break;
                                    case 2:
                                    c[x][y][1].setColor3Orientation("down");
                                    break;
                                }
                            }
                            else if(orientations[i].equals("down"))
                            {
                                switch(i)
                                {
                                    case 0:
                                    c[x][y][1].setColor1Orientation("left");
                                    break;
                                    case 1:
                                    c[x][y][1].setColor2Orientation("left");
                                    break;
                                    case 2:
                                    c[x][y][1].setColor3Orientation("left");
                                    break;
                                }
                            }
                            else if(orientations[i].equals("left"))
                            {
                                switch(i)
                                {
                                    case 0:
                                    c[x][y][1].setColor1Orientation("up");
                                    break;
                                    case 1:
                                    c[x][y][1].setColor2Orientation("up");
                                    break;
                                    case 2:
                                    c[x][y][1].setColor3Orientation("up");
                                    break;
                                }
                            }
                        }
                    }
                }
                
                //Outputting graphics (colors):
                g.setCanvas();
                g.leftCube(c);
                g.rightCube(c);
            }
            
            if(ch=='r')
            {
                //change contents of the 3 dimensional array (perform a 4-way swap):
                cubicle temp = c[1][1][1];
                c[1][1][1]=c[1][0][1];
                c[1][0][1]=c[1][0][0];
                c[1][0][0]=c[1][1][0];
                c[1][1][0]=temp;
                
                //It is imperative to change the orientation of the stickers. This was not taken care of in the swap above.
                //Loop through all 4 cubicle objects affected by the right turn:
                for(int y=0;y<2;y++)
                {
                    for(int z=0;z<2;z++)
                    {
                        String[]orientations = {c[1][y][z].getColor1Orientation(),c[1][y][z].getColor2Orientation(),c[1][y][z].getColor3Orientation()};
                        for(int i=0; i<orientations.length;i++)
                        {
                            if(orientations[i].equals("front"))
                            {
                                switch(i)
                                {
                                    case 0:
                                    c[1][y][z].setColor1Orientation("up");
                                    break;
                                    case 1:
                                    c[1][y][z].setColor2Orientation("up");
                                    break;
                                    case 2:
                                    c[1][y][z].setColor3Orientation("up");
                                    break;
                                }
                            }
                            else if(orientations[i].equals("up"))
                            {
                                switch(i)
                                {
                                    case 0:
                                    c[1][y][z].setColor1Orientation("back");
                                    break;
                                    case 1:
                                    c[1][y][z].setColor2Orientation("back");
                                    break;
                                    case 2:
                                    c[1][y][z].setColor3Orientation("back");
                                    break;
                                }
                            }
                            else if(orientations[i].equals("back"))
                            {
                                switch(i)
                                {
                                    case 0:
                                    c[1][y][z].setColor1Orientation("down");
                                    break;
                                    case 1:
                                    c[1][y][z].setColor2Orientation("down");
                                    break;
                                    case 2:
                                    c[1][y][z].setColor3Orientation("down");
                                    break;
                                }
                            }
                            else if(orientations[i].equals("down"))
                            {
                                switch(i)
                                {
                                    case 0:
                                    c[1][y][z].setColor1Orientation("front");
                                    break;
                                    case 1:
                                    c[1][y][z].setColor2Orientation("front");
                                    break;
                                    case 2:
                                    c[1][y][z].setColor3Orientation("front");
                                    break;
                                }
                            }
                        }
                    }
                }
                
                //Outputting graphics (colors):
                g.setCanvas();
                g.leftCube(c);
                g.rightCube(c);
            }
            
            if(ch=='u')
            {
                //change contents of the 3 dimensional array (perform a 4-way swap):
                cubicle temp = c[1][1][1];
                c[1][1][1]=c[1][1][0];
                c[1][1][0]=c[0][1][0];
                c[0][1][0]=c[0][1][1];
                c[0][1][1]=temp;
                
                //It is imperative to change the orientation of the stickers. This was not taken care of in the swap above.
                //Loop through all 4 cubicle objects affected by the upper turn:
                for(int x=0;x<2;x++)
                {
                    for(int z=0;z<2;z++)
                    {
                        String[]orientations = {c[x][1][z].getColor1Orientation(),c[x][1][z].getColor2Orientation(),c[x][1][z].getColor3Orientation()};
                        for(int i=0; i<orientations.length;i++)
                        {
                            if(orientations[i].equals("front"))
                            {
                                switch(i)
                                {
                                    case 0:
                                    c[x][1][z].setColor1Orientation("left");
                                    break;
                                    case 1:
                                    c[x][1][z].setColor2Orientation("left");
                                    break;
                                    case 2:
                                    c[x][1][z].setColor3Orientation("left");
                                    break;
                                }
                            }
                            else if(orientations[i].equals("left"))
                            {
                                switch(i)
                                {
                                    case 0:
                                    c[x][1][z].setColor1Orientation("back");
                                    break;
                                    case 1:
                                    c[x][1][z].setColor2Orientation("back");
                                    break;
                                    case 2:
                                    c[x][1][z].setColor3Orientation("back");
                                    break;
                                }
                            }
                            else if(orientations[i].equals("back"))
                            {
                                switch(i)
                                {
                                    case 0:
                                    c[x][1][z].setColor1Orientation("right");
                                    break;
                                    case 1:
                                    c[x][1][z].setColor2Orientation("right");
                                    break;
                                    case 2:
                                    c[x][1][z].setColor3Orientation("right");
                                    break;
                                }
                            }
                            else if(orientations[i].equals("right"))
                            {
                                switch(i)
                                {
                                    case 0:
                                    c[x][1][z].setColor1Orientation("front");
                                    break;
                                    case 1:
                                    c[x][1][z].setColor2Orientation("front");
                                    break;
                                    case 2:
                                    c[x][1][z].setColor3Orientation("front");
                                    break;
                                }
                            }
                        }
                    }
                }
                
                //Outputting graphics (colors):
                g.setCanvas();
                g.leftCube(c);
                g.rightCube(c);
            }
            
            System.out.println("Please enter either \"f\",\"r\", or \"u\":");
            ch=keyboard.next().charAt(0);
        }
        if(ch=='q')
        {
            return;
        }
        System.out.println("You have entered a letter other than f,r, or u. The program will end shortly. Thanks for playing with the 2*2*2 Rubik's Cube!");
        System.exit(0);
    }
}