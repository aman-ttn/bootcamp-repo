//Design a Data Structure SpecialStack that supports all the stack operations like push(), pop(), isEmpty(), isFull()
// and an additional operation getMin() which should return minimum element from the SpecialStack.
// (Expected complexity ­ O(1))
package collection;
public class Question8designDS {
    private static int min;
    private static Integer stack[];
    private static int top=0;
    public static void main(String[] args) {
        stack=new Integer[10] ;
        push(10);
        push(20);
        push(40);
        push(5);
        push(50);
        push(80);
        push(90);
        push(50);
        push(140);
        push(76);
        push(89);
        pop();
        pop();
        pop();
        pop();
        pop();
        pop();
        System.out.println("Minimum element: "+getMin());
        System.out.println("Is empty: "+isEmpty());
        System.out.println("Is full:"+isFull());
        System.out.println("Elements-");
        for (int i=0;i<=top;i++) {
            System.out.println(stack[i]);
        }
    }
    public static void push(Integer value){

        if(isFull()){
            System.out.println("The stack is full");
        }
        else {


            stack[top]=value;
            top++;
            if(top==1){
                min=stack[top-1];
            }
            min=Math.min(stack[top-1],min);
                }
            }
        public static void pop(){
        if(isEmpty()){
            System.out.println("The stack is empty");
        }
        else {
            if(min==stack[top]){
                for (int i = 0; i <top-1 ; i++) {
                    if(min>stack[i]){
                        min=stack[i];
                    }
                }
            }
            stack[top] = null;
            top--;
        }

        }


    public static boolean isFull(){
        if(stack[stack.length-1]!=null){
            top=stack.length-1;
            return true;
        }
        else{
            return false;
        }
    }
    public static boolean isEmpty(){
        if(stack[0]==null){
            return true;
        }
        else{
            return false;
        }
    }

    public static int getMin(){
        return min;
    }
}
