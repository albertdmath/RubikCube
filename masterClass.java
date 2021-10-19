import java.util.Scanner;
/**
 * masterClass is the main class from which the user selects one of 3 modes to play.
 * 
 * The goal here is to simulate a 2*2*2 Rubik's Cube in 3 modes: human, computer, and blended. 
 *
 * In human mode, the user can input front, right, and up rotations in order to manually shuffle the Rubik's Cube,
 * and then, the human can enter moves to manually solve it: 
 * (provided the human has knowledge of how to solve a 2*2*2 Rubik's Cube - a relatively easy task given a tutorial).
 * 
 * In computer mode, the computer shuffles the cube with a random set of moves (choosing from front, right, and up rotations),
 * and then it solves the cube using a fairly systematic algorithm (method for blindsolving in humans). A delay might be useful to make the animation
 * more aesthetic.
 * 
 * In blended mode, the human scrambles the cube, and the computer executes a solution.
 * 
 * All three modes will have a graphics system based on Princeton University's StdDraw java code. 
 * The front 3 faces and back 3 faces of the cube will be visible at all times thanks to a split canvas.
 * A 3 dimensional array of objects (type cubicle) will come in handy for storing information about the current state of the cube.
 * 
 * @author Albert Sandru
 * @version 5/20/2021
 */
public class masterClass
{
    public static void main(String[]args)
    {
        /**
         * The array below stores 8 cubicle objects.
         * Consider an xyz axis (3-space), where a 2*2*2 Rubik's Cube exists.
         * For the sake of ease of programming, assume the yellow/blue/orange corner is placed at the origin,
         * and that the cube exists in the octant with all positive values for xyz.
         * Behold! Below: an array of objects. 
         * Fact: only using Front, Right, and Up rotations or turns, one can reach all 3674160 permutations of a 2*2*2 Rubik's Cube.
         * 
         * What is a 2*2*2 Rubik's Cube? In essence, it is 8 cubicles that move around in a special way relative to each other.
         * Each cubicle has 3 stickers that are physically joined (they are inseperable for the purpose of this program).
         * It is of paramount importance to consider that the orientation of a sticker can only be facing in one of six ways:
         * up, down, front, back, right, or left.
         * 
         * I've explored what Std Draw is capable of, so I've designated a class called "GUI" to work in tandem with Rubiks2 and masterClass.
         * GUI deals with the graphics aspect of this project, and it draws two semi-convincing cubes onto the screen.
         * 
         * I've noticed this program takes up quite a bit of computer memory, such that it eventually crashes after a couple hundred moves.
         * Since the type of computer really makes a difference in these situations,
         * I would recommend the user close all browswer tabs in order for maximum performance from Std Draw.
         * 
         * The letters A through X in each cubicle object are used for the computer to solve the Rubik's cube. 
         * The computer uses the blindfolded method for solving the 2*2*2.
         * For more details on how this method works, visit: https://www.youtube.com/watch?v=7pHnmNeoJkQ
         */
        
        Rubiks2 r = new Rubiks2();
        
        cubicle[][][]c = new cubicle[2][2][2];
        c[0][0][0] = new cubicle("yellow","X","blue","S","orange","H","down","back","left");
        c[1][0][0] = new cubicle("yellow","W","red","O","blue","T","down","right","back");
        c[1][0][1] = new cubicle("yellow","V","green","K","red","P","down","front","right");
        c[0][0][1] = new cubicle("yellow","U","orange","G","green","L","down","left","front");
        c[0][1][0] = new cubicle("white","A","orange","E","blue","R","up","left","back");
        c[1][1][0] = new cubicle("white","B","blue","Q","red","N","up","back","right");
        c[1][1][1] = new cubicle("white","C","red","M","green","J","up","right","front");
        c[0][1][1] = new cubicle("white","D","green","I","orange","F","up","front","left");

        System.out.println("Please enter '1' for human mode, '2' for computer mode, and '3' for blended mode:");
        
        Scanner keyboard = new Scanner(System.in);
        int i = keyboard.nextInt();
        if(i==1)
        {
            r.human(c);
            System.exit(0);
        }
        if(i==2)
        {
            //Perform a 1 move scramble first:
            final int scrambleLength=1;
            for(int counter=0;counter<scrambleLength;counter++)
            {
                char[]array={'f','r','u'};
                int random=(int)(3*Math.random());
                moveExecutor(array[random],c);
            }
            //Solve the cube next:
            solver(c);
            //End the program:
            System.exit(0);
        }
        if(i==3)
        {
            //This is blended mode.
            //The human scrambles the cube first:
            r.human(c);
            //Then the computer solves it:
            solver(c);
            //Finally the program ends:
            System.exit(0);
        }
    }
    
    /**
     * This method solves the Rubik's Cube.
     */
    public static void solver(cubicle[][][]c)
    {
        //The following arrays contain sets of moves that need to be performed under certain conditions (see video above for solving method):
        char[]B={'r','r','r','f','r','f','r','u','u','u','r','r','r','u','u','u','r','u','r','r','r','f','f','f','r','u','r','r','r','u','u','u','r','r','r','f','r','f','f','f','r','r','r','f','f','f','r'};
        char[]C={'f','r','u','u','u','r','r','r','u','u','u','r','u','r','r','r','f','f','f','r','u','r','r','r','u','u','u','r','r','r','f','r','f','f','f'};
        char[]D={'f','u','u','u','r','r','r','u','u','u','r','u','r','r','r','f','f','f','r','u','r','r','r','u','u','u','r','r','r','f','r','r','f','f','f'};
        char[]F={'f','f','r','u','u','u','r','r','r','u','u','u','r','u','r','r','r','f','f','f','r','u','r','r','r','u','u','u','r','r','r','f','r','f','f'};      
        char[]G={'f','f','u','u','u','r','r','r','u','u','u','r','u','r','r','r','f','f','f','r','u','r','r','r','u','u','u','r','r','r','f','r','r','f','f'};
        char[]I={'f','f','r','f','r','u','u','u','r','r','r','u','u','u','r','u','r','r','r','f','f','f','r','u','r','r','r','u','u','u','r','r','r','f','r','f','f','f','r','r','r','f','f'};
        char[]J={'f','r','f','r','u','u','u','r','r','r','u','u','u','r','u','r','r','r','f','f','f','r','u','r','r','r','u','u','u','r','r','r','f','r','f','f','f','r','r','r','f','f','f'};
        char[]K={'r','f','r','u','u','u','r','r','r','u','u','u','r','u','r','r','r','f','f','f','r','u','r','r','r','u','u','u','r','r','r','f','r','f','f','f','r','r','r'};
        char[]L={'f','f','f','r','f','r','u','u','u','r','r','r','u','u','u','r','u','r','r','r','f','f','f','r','u','r','r','r','u','u','u','r','r','r','f','r','f','f','f','r','r','r','f'};
        char[]M={'u','u','u','r','r','r','u','u','u','r','u','r','r','r','f','f','f','r','u','r','r','r','u','u','u','r','r','r','f','r','r'};
        char[]N={'r','r','r','u','u','u','r','r','r','u','u','u','r','u','r','r','r','f','f','f','r','u','r','r','r','u','u','u','r','r','r','f','r','r','r'};
        char[]O={'r','r','u','u','u','r','r','r','u','u','u','r','u','r','r','r','f','f','f','r','u','r','r','r','u','u','u','r','r','r','f'};
        char[]P={'r','u','u','u','r','r','r','u','u','u','r','u','r','r','r','f','f','f','r','u','r','r','r','u','u','u','r','r','r','f','r'};
        char[]Q={'r','r','r','f','r','u','u','u','r','r','r','u','u','u','r','u','r','r','r','f','f','f','r','u','r','r','r','u','u','u','r','r','r','f','r','f','f','f','r'};
        char[]T={'r','r','f','r','f','r','u','u','u','r','r','r','u','u','u','r','u','r','r','r','f','f','f','r','u','r','r','r','u','u','u','r','r','r','f','r','f','f','f','r','r','r','f','f','f','r','r'};
        char[]U={'f','f','f','r','u','u','u','r','r','r','u','u','u','r','u','r','r','r','f','f','f','r','u','r','r','r','u','u','u','r','r','r','f','r','f'};
        char[]V={'r','f','r','f','r','u','u','u','r','r','r','u','u','u','r','u','r','r','r','f','f','f','r','u','r','r','r','u','u','u','r','r','r','f','r','f','f','f','r','r','r','f','f','f','r','r','r'};
        char[]W={'r','r','f','r','u','u','u','r','r','r','u','u','u','r','u','r','r','r','f','f','f','r','u','r','r','r','u','u','u','r','r','r','f','r','f','f','f','r','r'};
        
        boolean isSolved=isSolved(c);
        while(!isSolved)
        {
            String[]orientations={c[0][1][0].getColor1Orientation(),c[0][1][0].getColor2Orientation(),c[0][1][0].getColor3Orientation()};
            for(int counter1=0;counter1<3;counter1++)
            {
                if(orientations[counter1].equals("up"))
                {
                    String str="";
                    switch(counter1)
                    {
                        case 0:
                            str=c[0][1][0].getLetter1();
                            break;
                        case 1:
                            str=c[0][1][0].getLetter2();
                            break;
                        case 2:
                            str=c[0][1][0].getLetter3();
                            break;
                    }
                    switch(str)
                    {
                        case "A":
                            String str1 = findUnsolvedLetter(c);
                            switch(str1)
                            {
                                case "O":
                                for(int counter2=0;counter2<O.length;counter2++)
                                {
                                    moveExecutor(O[counter2],c);
                                }
                                break;
                                case "P":
                                for(int counter2=0;counter2<P.length;counter2++)
                                {
                                    moveExecutor(P[counter2],c);
                                }
                                break;
                                case "U":
                                for(int counter2=0;counter2<U.length;counter2++)
                                {
                                    moveExecutor(U[counter2],c);
                                }
                                break;
                                case "N":
                                for(int counter2=0;counter2<N.length;counter2++)
                                {
                                    moveExecutor(N[counter2],c);
                                }
                                break;
                                case "M":
                                for(int counter2=0;counter2<M.length;counter2++)
                                {
                                    moveExecutor(M[counter2],c);
                                }
                                break;
                                case "F":
                                for(int counter2=0;counter2<F.length;counter2++)
                                {
                                    moveExecutor(F[counter2],c);
                                }
                                break;
                            }
                        break;
                        case "B":
                        for(int counter2=0;counter2<B.length;counter2++)
                        {
                            moveExecutor(B[counter2],c);
                        }
                        break;
                        case "C":
                        for(int counter2=0;counter2<C.length;counter2++)
                        {
                            moveExecutor(C[counter2],c);
                        }
                        break;
                        case "D":
                        for(int counter2=0;counter2<D.length;counter2++)
                        {
                            moveExecutor(D[counter2],c);
                        }
                        break;
                        case "E":
                            String str2 = findUnsolvedLetter(c);
                            switch(str2)
                            {
                                case "O":
                                for(int counter2=0;counter2<O.length;counter2++)
                                {
                                    moveExecutor(O[counter2],c);
                                }
                                break;
                                case "P":
                                for(int counter2=0;counter2<P.length;counter2++)
                                {
                                    moveExecutor(P[counter2],c);
                                }
                                break;
                                case "U":
                                for(int counter2=0;counter2<U.length;counter2++)
                                {
                                    moveExecutor(U[counter2],c);
                                }
                                break;
                                case "N":
                                for(int counter2=0;counter2<N.length;counter2++)
                                {
                                    moveExecutor(N[counter2],c);
                                }
                                break;
                                case "M":
                                for(int counter2=0;counter2<M.length;counter2++)
                                {
                                    moveExecutor(M[counter2],c);
                                }
                                break;
                                case "F":
                                for(int counter2=0;counter2<F.length;counter2++)
                                {
                                    moveExecutor(F[counter2],c);
                                }
                                break;
                            }
                        break;
                        case "F":
                        for(int counter2=0;counter2<F.length;counter2++)
                        {
                            moveExecutor(F[counter2],c);
                        }
                        break;
                        case "G":
                        for(int counter2=0;counter2<G.length;counter2++)
                        {
                            moveExecutor(G[counter2],c);
                        }
                        break;
                        case "I":
                        for(int counter2=0;counter2<I.length;counter2++)
                        {
                            moveExecutor(I[counter2],c);
                        }
                        break;
                        case "J":
                        for(int counter2=0;counter2<J.length;counter2++)
                        {
                            moveExecutor(J[counter2],c);
                        }
                        break;
                        case "K":
                        for(int counter2=0;counter2<K.length;counter2++)
                        {
                            moveExecutor(K[counter2],c);
                        }
                        break;
                        case "L":
                        for(int counter2=0;counter2<L.length;counter2++)
                        {
                            moveExecutor(L[counter2],c);
                        }
                        break;
                        case "M":
                        for(int counter2=0;counter2<M.length;counter2++)
                        {
                            moveExecutor(M[counter2],c);
                        }
                        break;
                        case "N":
                        for(int counter2=0;counter2<N.length;counter2++)
                        {
                            moveExecutor(N[counter2],c);
                        }
                        break;
                        case "O":
                        for(int counter2=0;counter2<O.length;counter2++)
                        {
                            moveExecutor(O[counter2],c);
                        }
                        break;
                        case "P":
                        for(int counter2=0;counter2<P.length;counter2++)
                        {
                            moveExecutor(P[counter2],c);
                        }
                        break;
                        case "Q":
                        for(int counter2=0;counter2<Q.length;counter2++)
                        {
                            moveExecutor(Q[counter2],c);
                        }
                        break;
                        case "R":
                            String str3 = findUnsolvedLetter(c);
                            switch(str3)
                            {
                                case "O":
                                for(int counter2=0;counter2<O.length;counter2++)
                                {
                                    moveExecutor(O[counter2],c);
                                }
                                break;
                                case "P":
                                for(int counter2=0;counter2<P.length;counter2++)
                                {
                                    moveExecutor(P[counter2],c);
                                }
                                break;
                                case "U":
                                for(int counter2=0;counter2<U.length;counter2++)
                                {
                                    moveExecutor(U[counter2],c);
                                }
                                break;
                                case "N":
                                for(int counter2=0;counter2<N.length;counter2++)
                                {
                                    moveExecutor(N[counter2],c);
                                }
                                break;
                                case "M":
                                for(int counter2=0;counter2<M.length;counter2++)
                                {
                                    moveExecutor(M[counter2],c);
                                }
                                break;
                                case "F":
                                for(int counter2=0;counter2<F.length;counter2++)
                                {
                                    moveExecutor(F[counter2],c);
                                }
                                break;
                            }
                        break;
                        case "T":
                        for(int counter2=0;counter2<T.length;counter2++)
                        {
                            moveExecutor(T[counter2],c);
                        }
                        break;
                        case "U":
                        for(int counter2=0;counter2<U.length;counter2++)
                        {
                            moveExecutor(U[counter2],c);
                        }
                        break;
                        case "V":
                        for(int counter2=0;counter2<V.length;counter2++)
                        {
                            moveExecutor(V[counter2],c);
                        }
                        break;
                        case "W":
                        for(int counter2=0;counter2<W.length;counter2++)
                        {
                            moveExecutor(W[counter2],c);
                        }
                        break;
                    }
                }
            }
            isSolved=isSolved(c);
        }
    }
    
    /**
     * This method executes moves. It's based on Rubik's 2 human method.
     */
    public static void moveExecutor(char ch, cubicle[][][]c)
    {
        GUI g = new GUI();
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
    }
    
    /**
     * This method finds an unsolved letter.
     */
    public static String findUnsolvedLetter(cubicle[][][]c)
    {
        if(c[1][0][0].getColor1().equals("yellow")&&c[1][0][0].getColor2().equals("red")&&c[1][0][0].getColor3().equals("blue")&&c[1][0][0].getColor1Orientation().equals("down"))
        {
        }
        else
        {
            return "O";
        }
        if(c[1][0][1].getColor1().equals("yellow")&&c[1][0][1].getColor2().equals("green")&&c[1][0][1].getColor3().equals("red")&&c[1][0][1].getColor1Orientation().equals("down"))
        {
        }
        else
        {
            return "P";
        }
        if(c[0][0][1].getColor1().equals("yellow")&&c[0][0][1].getColor2().equals("orange")&&c[0][0][1].getColor3().equals("green")&&c[0][0][1].getColor1Orientation().equals("down"))
        {
        }
        else
        {
            return "U";
        }
        if(c[1][1][0].getColor1().equals("white")&&c[1][1][0].getColor2().equals("blue")&&c[1][1][0].getColor3().equals("red")&&c[1][1][0].getColor1Orientation().equals("up"))
        {
        }
        else
        {
            return "N";
        }
        if(c[1][1][1].getColor1().equals("white")&&c[1][1][1].getColor2().equals("red")&&c[1][1][1].getColor3().equals("green")&&c[1][1][1].getColor1Orientation().equals("up"))
        {
        }
        else
        {
            return "M";
        }
        if(c[0][1][1].getColor1().equals("white")&&c[0][1][1].getColor2().equals("green")&&c[0][1][1].getColor3().equals("orange")&&c[0][1][1].getColor1Orientation().equals("up"))
        {
        }
        else
        {
            return "F";
        }
        return "";
    }
    
    /**
     * This method checks if the cube is solved.
     */
    public static boolean isSolved(cubicle[][][]c)
    {
        cubicle[][][]d = new cubicle[2][2][2];
        d[0][0][0] = new cubicle("yellow","X","blue","S","orange","H","down","back","left");
        d[1][0][0] = new cubicle("yellow","W","red","O","blue","T","down","right","back");
        d[1][0][1] = new cubicle("yellow","V","green","K","red","P","down","front","right");
        d[0][0][1] = new cubicle("yellow","U","orange","G","green","L","down","left","front");
        d[0][1][0] = new cubicle("white","A","orange","E","blue","R","up","left","back");
        d[1][1][0] = new cubicle("white","B","blue","Q","red","N","up","back","right");
        d[1][1][1] = new cubicle("white","C","red","M","green","J","up","right","front");
        d[0][1][1] = new cubicle("white","D","green","I","orange","F","up","front","left");
        if(c.equals(d))
        {
            return true;
        }
        return false;
    }
}