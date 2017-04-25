package vorlesung24april;

import java.util.ArrayList;
import java.util.Stack;
import java.util.stream.IntStream;

/**
 * Created by Konstantin on 24.04.2017.
 */
public class Board
{
    int[] board = new int[9];
    int turn = +1;
    Stack<Integer> history = new Stack<>();



    int[] listMoves(){
    return  IntStream.range(0,9).filter(pos -> board[pos] ==0).toArray();

      /** ArrayList<Integer> list = new ArrayList<>();
        for(int pos = 0;pos <=8;pos++){
            if(board[pos] == 0) list.add(pos);
            return list;
        }*/
    }
    void undoMove(){

        board[history.pop()] =0;
        turn = -turn;

    }

    void makeMove(int pos){
        assert pos >= 0 && pos <= 8 && board[pos]==0 &&!threeInRow();
        board[pos] = turn;
        turn = -turn;
        history.push(pos);
    }


    void makeMove(int... positions){
        for (int move:positions)
        {
        makeMove(move);
        }
    }
    boolean threeInRow(){
        int[][] rows = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        for (int[] row:rows)
        {
            int sum = board[row[0]] + board[row[1]]+ board[row[2]];
            if(Math.abs(sum) == 3)return  true;
        }
        return false;
    }

    @Override public  String toString(){
        char[] repr = { '0' , '.', 'X'};
        String s = "";
        for(int i =0;i<=2;i++)
        {
            for(int j =0;j <=2;j++){
                s += repr[board[i*3+j]+1];
                s+="\n";
            }
        }
        return  s;
    }
    Board b = new Board();
}
