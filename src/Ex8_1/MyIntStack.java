package Ex8_1;

public class MyIntStack {
        private int[] contents;
        private int tos; // Top of the stack

        // Constructor
        public MyIntStack(int capacity) {
            contents = new int[capacity];
            tos = -1;
        }

        // Push an element onto the stack (throws exception if full)
        public void push(int element) {
            if (isFull()) {
                throw new IllegalStateException("Stack is full!");
            }
            contents[++tos] = element;
        }

        // Pop the top element from the stack
        public int pop() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack is empty!");
            }
            return contents[tos--];
        }

        // Peek at the top element without removing it
        public int peek() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack is empty!");
            }
            return contents[tos];
        }

        // Check if the stack is empty
        public boolean isEmpty() {
            return tos < 0;
        }

        // Check if the stack is full
        public boolean isFull() {
            return tos == contents.length - 1;
        }
    }

