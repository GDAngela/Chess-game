package Classes;

/**
 * Created by jingjinghuang on 9/7/16.
 */
public class Pairs {
    private int left;
    private int right;

    /**
     *
     * @param leftValue
     * @param rightValue
     */
    public Pairs(int leftValue, int rightValue){
        this.left=leftValue;
        this.right=rightValue;
    }

    /**
     * Return left value of pairs
     * @return
     */
    public int getLeft(){
        return this.left;
    }

    /**
     * Return right value of pairs
     * @return
     */
    public int getRight(){
        return this.right;
    }

    public boolean compair(Pairs pair){
        return this.getLeft()==pair.getLeft() && this.getRight()==pair.getRight();
    }

    public void printPairs(){
        System.out.print(getLeft());
        System.out.print(" ");
        System.out.println(getRight());
    }
}
