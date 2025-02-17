package Ex8_1;

public class MyIntStackVer2 {
        private int[] contents;
        private int tos;

        public MyIntStackVer2(int capacity) {
            contents = new int[capacity];
            tos = -1;
        }

        // Push an element and return success status
        public boolean push(int element) {
            if (isFull()) {
                return false; // Stack is full
            }
            contents[++tos] = element;
            return true; // Successfully pushed
        }

        public int pop() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack is empty!");
            }
            return contents[tos--];
        }

        public int peek() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack is empty!");
            }
            return contents[tos];
        }

        public boolean isEmpty() {
            return tos < 0;
        }

        public boolean isFull() {
            return tos == contents.length - 1;
        }
    }

