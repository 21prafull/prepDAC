/* Program to implement stack application algorithms  Expression conversion & Evaluation 
==========================================================================================*/ 
import java.util.Stack;

public class StackAppMain {
    
    public static boolean isOperand(char ch){
        return ( ( ch >= 65 && ch <= 90 ) || ( ch >= 97 && ch <= 122 ) || ( ch >= 48 && ch <= 57 ) );
    }

    //we are defining priority to operators programmatically
    public static int priority(char opr){
        switch( opr ){
            case '+':
            case '-': return 1;

            case '*':
            case '/':
            case '%': return 2;
        }
        return -1;
    }

    public static String infixToPostfix(String infix){
        //initially we required an empty stack & empty postfix expression 
        Stack<Character> s = new Stack<Character>();
        StringBuilder postfix = new StringBuilder();

        //step-1: start scanning infix expression from left to right
        for( int i = 0 ; i < infix.length() ; i++ ){
            char cur_ele = infix.charAt( i );
            //step-2: if cur ele is an operand then apend it into the postfix expression
            if( isOperand( cur_ele ) ){
                postfix.append(cur_ele);
            }else{//step-3: if cur ele is an operator
                //while( if stack is not empty && priority(topmost ele) >= priority(cur ele) )
                while( !s.empty() && priority( s.peek() ) >= priority( cur_ele ) ){
                    //pop ele from the stack and append it into the postfix expression
                    postfix.append( s.pop() );
                }
                //push cur ele onto the stack
                s.push(cur_ele);
            }
        }//step-4: repeat step-2 & step-3 till the end of infix expression
        //step-5: pop all remaining ele's one by one from the stack and append them into the postfix expression
        while( !s.empty() ){
            postfix.append(s.pop() );
        }

        return ( postfix.toString() );
    }

    public static String infixToPrefix(String infix){
        //initially we required an empty stack & empty prefix expression 
        Stack<Character> s = new Stack<Character>();
        StringBuilder prefix = new StringBuilder();

        //step-1: start scanning infix expression from right to left
        for( int i = infix.length()-1 ; i >= 0 ; i-- ){
            char cur_ele = infix.charAt( i );
            //step-2: if cur ele is an operand then apend it into the prefix expression
            if( isOperand( cur_ele ) ){
                prefix.append(cur_ele);
            }else{//step-3: if cur ele is an operator
                //while( if stack is not empty && priority(topmost ele) > priority(cur ele) )
                while( !s.empty() && priority( s.peek() ) > priority( cur_ele ) ){
                    //pop ele from the stack and append it into the prefix expression
                    prefix.append( s.pop() );
                }
                //push cur ele onto the stack
                s.push(cur_ele);
            }
        }//step-4: repeat step-2 & step-3 till the end of infix expression
        //step-5: pop all remaining ele's one by one from the stack and append them into the prefix expression
        while( !s.empty() ){
            prefix.append(s.pop() );
        }

        //reverse prefix expression and return it to the calling function
        return ( prefix.reverse().toString() );
    }

    public static int calculate(int op1, int op2, char opr){
        switch( opr ){
            case '+' : return ( op1 + op2 );
            case '-' : return ( op1 - op2 );
            case '*' : return ( op1 * op2 );
            case '/' : return ( op1 / op2 );
        }
        return -1;
    }

    public static int postfixEvaluation(String postfix){
        Stack<Integer> s = new Stack<Integer>();
        int result;

        //step-1: start scanning postfix expression from left to right
        for( int i = 0 ; i < postfix.length() ; i++ ){
            char cur_ele = postfix.charAt(i);
            //step-2: if cur ele is an operand then push it onto the stack
            if( isOperand(cur_ele) ){
                s.push( cur_ele - '0' );
            }else{//step-3: if cur element is an operator
                /* - pop two elements from the stack in such a way that, first popped ele will be the second
                operand and second popped element will be the first operand. */
                int op2 = s.pop();
                int op1 = s.pop();
                //- perform cur element operation between op1 & op2 and push back result onto the stack.
                result = calculate(op1, op2, cur_ele);
                s.push(result);
            }//step-4: repeat step-2 & step-3 till the end of postfix expression
        }
        //step-5: pop final result from the stack and return to the calling function.
        return ( s.pop() );
    }

    public static void main(String[] args) {
        //String infix = "a*b/c*d+e*f-g+h";
        String infix = "4*5+6/8*9+7-2";

        System.out.println("infix expression is  : "+infix);
        
        String postfix = infixToPostfix(infix);
        //String prefix = infixToPrefix(infix);

        System.out.println("postfix expression is : "+postfix);
        //System.out.println("prefix expression is  : "+prefix);
        System.out.println("result is : "+postfixEvaluation(postfix));
    }    
}
