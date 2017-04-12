package univ.lecture.riotapi;
import java.util.Stack;
/**
 * Created by tchi on 2017. 3. 19..
 */
public class Calculator {
   
   public double calculate(String exp) {
      return calculator(exp);
   }

   public double calculator(String postfix){
      double a, b;
      String temp = new String();
      Stack stack = new Stack();
      for (int i = 0; i < postfix.length(); i++) {
         switch (postfix.charAt(i)) { // 숫자가 나올 때마다 temp에 이어붙임
         case '0':
         case '1':
         case '2':
         case '3':
         case '4':
         case '5':
         case '6':
         case '7':
         case '8':
         case '9':
         case '.':
            temp = temp.concat(postfix.charAt(i) + "");
            break;
         case ' ': // 공백이 나오면 stack에 temp를 double형으로 push하고 temp를 새로 선언하여 비움
            if (!temp.isEmpty()) // temp가 비어있지 않을 때만 실행
            {
               stack.push(new Double(temp));
               temp = new String();
            }
            break;
         case '+': // +가 나오면 double형으로 두 번째 pop한 것 + 첫 번째 pop한 것 을 하여 결과를
            // push
            a = (double) stack.pop();
            b = (double) stack.pop();
            stack.push(b + a);
            break;
         case '-': // -가 나오면 double형으로 두 번째 pop한 것 - 첫 번째 pop한 것 을 하여 결과를
            // push
            a = (double) stack.pop();
            b = (double) stack.pop();
            stack.push(b - a);
            break;
         case '*': // *가 나오면 double형으로 두 번째 pop한 것 * 첫 번째 pop한 것 을 하여 결과를
            // push
            a = (double) stack.pop();
            b = (double) stack.pop();
            stack.push(b * a);
            break;
         case '/': /// 가 나오면 double형으로 두 번째 pop한 것 / 첫 번째 pop한 것 을 하여 결과를
            /// push
            a = (double) stack.pop();
            b = (double) stack.pop();
            stack.push(b / a);
            break;

         }
      }
      return (double) stack.pop();
   }
   
   public int precedence(char c) // 연산자의 우선순위를 반환
      {
         switch (c) {
         case '*':
         case '/':
            return 2;
         case '+':
         case '-':
            return 1;
         default:
            return 0;
         }
      }
    public String postfix(String infix){
       //change to rear calculus
    
       boolean endON = false;
        String postfix = new String();
        Stack stack = new Stack();
        
        for (int i = 0; i < infix.length(); i++) {
            switch (infix.charAt(i)) { 
               case '0':
               case '1':
               case '2':
               case '3':
               case '4':
               case '5':
               case '6':
               case '7':
               case '8':
               case '9':
                  
               case '.':
                   postfix = postfix.concat(infix.charAt(i) + "");
                   endON = true;
                   break;
                   
               case '(':
                  //if '(' come out add ' ' and push to stack
                   if (endON == true) {
                      postfix = postfix.concat(" ");
                      endON = false;
                   }
                   stack.push('(');
                   break;
                   
               case ')': 
                  // when ')' come out add ' ' and pop until '(' come out
                   if (endON == true) {
                      postfix = postfix.concat(" ");
                      endON = false;
                   }

                   while (((Character) stack.peek()).charValue() != '(') {
                      postfix = postfix.concat(((Character) stack.pop()).toString());
                      postfix = postfix.concat(" "); // add ' ' when pop once
                   }
                   Object o = stack.pop(); // pop '(' and throw away
                   break;
               case '+':
               case '-':
               case '*':
               case '/':  
               if (endON == true) {
                  postfix = postfix.concat(" ");
                  endON = false;
               }
               while (!stack.isEmpty() && precedence(infix.charAt(i)) <= precedence(((Character) stack.peek()).charValue())) {
                    postfix = postfix.concat(((Character) stack.pop()).toString());
                    postfix = postfix.concat(" "); //add ' ' when pop
               }
               stack.push(infix.charAt(i)); // 
               break;
            }
        }
        while (!stack.isEmpty()) 
            postfix = postfix.concat(" " + ((Character) stack.pop()).toString());

         System.out.println("rear equation : " + postfix);

         return postfix;

    }
    

}