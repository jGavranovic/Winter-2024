package COMP249.TEST;

public class Driver extends Employee{

    private int c;

    public Driver(int a, int b,int c){
        super(a,b);
    }

    public Driver(){
        super(0,0);
        this.c = 0;
    }


    @Override
    protected void getWage(){

    }

}


