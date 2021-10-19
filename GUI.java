/**
 * GUI handles the graphics aspect of my 2*2*2 Rubik's Cube.
 * @author Albert Sandru.
 * @version 5/11/2021.
 */
public class GUI
{
    public void setCanvas()
    {
         //Clear the screen.
         StdDraw.clear();
         //The canvas is a rectangle.
         StdDraw.setCanvasSize(1400,700);
         //Self-Explanatory Text. Two different views of the Rubik's Cube are provided using StdDraw.
         StdDraw.text(0.25,0.8, "Front View");
         StdDraw.text(0.75,0.8, "Rear View");
         //This line splits the canvas in two parts.
         StdDraw.line(0.5,0,0.5,1);
    }
    public void leftCube(cubicle[][][]c)
    {                     
        /**
         * This method in GUI fills the stickers of the cube on the left side of the canvas with color using the filledPolygon function from StdDraw.
         * It accomplishes this with the help of a master array which contains the 2D coordinates of 12 polygons (all are 4 sided).
         * These polygons correspond to a maximum of 12 stickers visible on a 2*2*2 Rubik's Cube from any given angle.
         * At the very end, this method draws a few thin black lines in order to outline the cube and box off each polygon for a more aesthetic look.
         */
        
        int masterIndex=0;
        double masterArray[][]={{0.1,0.1,0.2,0.2},{0.1,0.3,0.3,0.1},{0.135,0.17,0.26,0.23},{0.55,0.6,0.6,0.55},{0.1,0.1,0.2,0.2},{0.3,0.5,0.5,0.3},
                                {0.1,0.135,0.23,0.2},{0.5,0.55,0.55,0.5},{0.325,0.325,0.35,0.35},{0.15,0.35,0.4,0.2},{0.2,0.2,0.3,0.3},{0.1,0.3,0.3,0.1},
                                {0.3,0.3,0.325,0.325},{0.1,0.3,0.35,0.15},{0.325,0.325,0.35,0.35},{0.35,0.55,0.6,0.4},{0.23,0.26,0.35,0.325},{0.55,0.6,0.6,0.55},
                                {0.2,0.2,0.3,0.3},{0.3,0.5,0.5,0.3},{0.3,0.3,0.325,0.325},{0.3,0.5,0.55,0.35},{0.2,0.23,0.325,0.3},{0.5,0.55,0.55,0.5}};
        for(int x=0;x<2;x++)
        {
            for(int y=0;y<2;y++)
            {
                for(int z=0;z<2;z++)
                {
                    String[]orientations = {c[x][y][z].getColor1Orientation(),c[x][y][z].getColor2Orientation(),c[x][y][z].getColor3Orientation()};
                    String[]colors = {c[x][y][z].getColor1(),c[x][y][z].getColor2(),c[x][y][z].getColor3()};
                    for(int i=0;i<3;i++)
                    {
                        if(orientations[i].equals("front"))
                        {
                            switch(colors[i])
                            {
                                case "white":
                                StdDraw.setPenColor(StdDraw.WHITE);
                                break;
                                case "yellow":
                                StdDraw.setPenColor(StdDraw.YELLOW);
                                break;
                                case "blue":
                                StdDraw.setPenColor(StdDraw.BLUE);
                                break;
                                case "green":
                                StdDraw.setPenColor(StdDraw.GREEN);
                                break;
                                case "red":
                                StdDraw.setPenColor(StdDraw.RED);
                                break;
                                case "orange":
                                StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
                                break; 
                            }
                            StdDraw.filledPolygon(masterArray[masterIndex],masterArray[masterIndex+1]);
                            masterIndex+=2;
                        }
                    }
                    for(int i=0;i<3;i++)
                    {
                        if(orientations[i].equals("right"))
                        {
                            switch(colors[i])
                            {
                                case "white":
                                StdDraw.setPenColor(StdDraw.WHITE);
                                break;
                                case "yellow":
                                StdDraw.setPenColor(StdDraw.YELLOW);
                                break;
                                case "blue":
                                StdDraw.setPenColor(StdDraw.BLUE);
                                break;
                                case "green":
                                StdDraw.setPenColor(StdDraw.GREEN);
                                break;
                                case "red":
                                StdDraw.setPenColor(StdDraw.RED);
                                break;
                                case "orange":
                                StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
                                break; 
                            }
                            StdDraw.filledPolygon(masterArray[masterIndex],masterArray[masterIndex+1]);
                            masterIndex+=2;
                        }   
                    }
                    for(int i=0;i<3;i++)
                    {
                       if(orientations[i].equals("up"))
                       {
                            switch(colors[i])
                            {
                                case "white":
                                StdDraw.setPenColor(StdDraw.WHITE);
                                break;
                                case "yellow":
                                StdDraw.setPenColor(StdDraw.YELLOW);
                                break;
                                case "blue":
                                StdDraw.setPenColor(StdDraw.BLUE);
                                break;
                                case "green":
                                StdDraw.setPenColor(StdDraw.GREEN);
                                break;
                                case "red":
                                StdDraw.setPenColor(StdDraw.RED);
                                break;
                                case "orange":
                                StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
                                break; 
                            }
                            StdDraw.filledPolygon(masterArray[masterIndex],masterArray[masterIndex+1]);
                            masterIndex+=2;
                       }    
                    }
                }
            }
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.line(0.1,0.1,0.3,0.1);
        StdDraw.line(0.3,0.1,0.35,0.2);
        StdDraw.line(0.1,0.3,0.3,0.3);
        StdDraw.line(0.3,0.3,0.35,0.4);
        StdDraw.line(0.1,0.5,0.3,0.5);
        StdDraw.line(0.3,0.5,0.35,0.6);
        StdDraw.line(0.1,0.1,0.1,0.5);
        StdDraw.line(0.2,0.1,0.2,0.5);
        StdDraw.line(0.3,0.1,0.3,0.5);
        StdDraw.line(0.325,0.15,0.325,0.55);
        StdDraw.line(0.35,0.2,0.35,0.6);
        StdDraw.line(0.1,0.5,0.17,0.6);
        StdDraw.line(0.17,0.6,0.35,0.6);
        StdDraw.line(0.2,0.5,0.26,0.6);
        StdDraw.line(0.135,0.55,0.325,0.55);
    }
    public void rightCube(cubicle[][][]c)
    {
        /**
         * This method in GUI fills the stickers of the cube on the right side of the canvas with color using the filledPolygon function from StdDraw.
         * It accomplishes this with the help of a master array which contains the 2D coordinates of 12 polygons (all are 4 sided).
         * These polygons correspond to a maximum of 12 stickers visible on a 2*2*2 Rubik's Cube from any given angle.
         * At the very end, this method draws a few thin black lines in order to outline the cube and box off each polygon for a more aesthetic look.
         */
              
        int masterIndex=0;
        double masterArray[][]={{0.725,0.7,0.8,0.825},{0.16,0.2,0.2,0.16},{0.8,0.8,0.825,0.825},{0.2,0.4,0.355,0.16},{0.7,0.7,0.8,0.8},{0.2,0.4,0.4,0.2},
                                {0.75,0.725,0.825,0.85},{0.12,0.16,0.16,0.12},{0.825,0.825,0.85,0.85},{0.16,0.355,0.31,0.12},{0.8,0.8,0.825,0.825},{0.4,0.6,0.55,0.355},
                                {0.7,0.7,0.8,0.8},{0.4,0.6,0.6,0.4},{0.825,0.825,0.85,0.85},{0.355,0.55,0.5,0.31},{0.625,0.6,0.7,0.725},{0.16,0.2,0.2,0.16},
                                {0.6,0.6,0.7,0.7},{0.2,0.4,0.4,0.2},{0.65,0.625,0.725,0.75},{0.12,0.16,0.16,0.12},{0.6,0.6,0.7,0.7},{0.4,0.6,0.6,0.4}};
        for(int x=0;x<2;x++)
        {
            for(int y=0;y<2;y++)
            {
                for(int z=0;z<2;z++)
                {
                    String[]orientations = {c[x][y][z].getColor1Orientation(),c[x][y][z].getColor2Orientation(),c[x][y][z].getColor3Orientation()};
                    String[]colors = {c[x][y][z].getColor1(),c[x][y][z].getColor2(),c[x][y][z].getColor3()};
                    for(int i=0;i<3;i++)
                    {
                        if(orientations[i].equals("down"))
                        {
                            switch(colors[i])
                            {
                                case "white":
                                StdDraw.setPenColor(StdDraw.WHITE);
                                break;
                                case "yellow":
                                StdDraw.setPenColor(StdDraw.YELLOW);
                                break;
                                case "blue":
                                StdDraw.setPenColor(StdDraw.BLUE);
                                break;
                                case "green":
                                StdDraw.setPenColor(StdDraw.GREEN);
                                break;
                                case "red":
                                StdDraw.setPenColor(StdDraw.RED);
                                break;
                                case "orange":
                                StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
                                break; 
                            }
                            StdDraw.filledPolygon(masterArray[masterIndex],masterArray[masterIndex+1]);
                            masterIndex+=2;
                        }
                    }
                    for(int i=0;i<3;i++)
                    {
                        if(orientations[i].equals("left"))
                        {
                            switch(colors[i])
                            {
                                case "white":
                                StdDraw.setPenColor(StdDraw.WHITE);
                                break;
                                case "yellow":
                                StdDraw.setPenColor(StdDraw.YELLOW);
                                break;
                                case "blue":
                                StdDraw.setPenColor(StdDraw.BLUE);
                                break;
                                case "green":
                                StdDraw.setPenColor(StdDraw.GREEN);
                                break;
                                case "red":
                                StdDraw.setPenColor(StdDraw.RED);
                                break;
                                case "orange":
                                StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
                                break; 
                            }
                            StdDraw.filledPolygon(masterArray[masterIndex],masterArray[masterIndex+1]);
                            masterIndex+=2;
                        }   
                    }
                    for(int i=0;i<3;i++)
                    {
                       if(orientations[i].equals("back"))
                       {
                            switch(colors[i])
                            {
                                case "white":
                                StdDraw.setPenColor(StdDraw.WHITE);
                                break;
                                case "yellow":
                                StdDraw.setPenColor(StdDraw.YELLOW);
                                break;
                                case "blue":
                                StdDraw.setPenColor(StdDraw.BLUE);
                                break;
                                case "green":
                                StdDraw.setPenColor(StdDraw.GREEN);
                                break;
                                case "red":
                                StdDraw.setPenColor(StdDraw.RED);
                                break;
                                case "orange":
                                StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
                                break; 
                            }
                            StdDraw.filledPolygon(masterArray[masterIndex],masterArray[masterIndex+1]);
                            masterIndex+=2;
                       }    
                    }
                }
            }
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.line(0.6,0.2,0.6,0.6);
        StdDraw.line(0.7,0.2,0.7,0.6);
        StdDraw.line(0.8,0.2,0.8,0.6);
        StdDraw.line(0.825,0.16,0.825,0.55);
        StdDraw.line(0.85,0.12,0.85,0.5);
        StdDraw.line(0.6,0.6,0.8,0.6);
        StdDraw.line(0.8,0.6,0.85,0.5);
        StdDraw.line(0.6,0.4,0.8,0.4);
        StdDraw.line(0.8,0.4,0.85,0.31);
        StdDraw.line(0.6,0.2,0.8,0.2);
        StdDraw.line(0.8,0.2,0.85,0.12);
        StdDraw.line(0.6,0.2,0.65,0.12);
        StdDraw.line(0.625,0.16,0.825,0.16);
        StdDraw.line(0.7,0.2,0.75,0.12);
        StdDraw.line(0.65,0.12,0.85,0.12);
    }
}