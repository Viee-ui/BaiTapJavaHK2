package Ex8_1;

public class TestMyIntStack {
    public static void main(String[] args) {
        MyIntStack stack = new MyIntStack(3);

        // Test dynamic allocation
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Peek after pushing 3 elements: " + stack.peek()); // Should print 3
        stack.push(4); // Should trigger dynamic reallocation
        System.out.println("Peek after pushing 4th element: " + stack.peek()); // Should print 4

        // Test popping elements
        System.out.println("Pop: " + stack.pop()); // Should print 4
        System.out.println("Pop: " + stack.pop()); // Should print 3

        // Test empty stack exception
        stack.pop();
        stack.pop();
        try {
            stack.pop(); // Should throw an exception
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage()); // Should print "Stack is empty!"
        }
    }
}

