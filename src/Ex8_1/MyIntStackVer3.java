package Ex8_1;

public class MyIntStackVer3 {
        private int[] contents;
        private int tos;

        public MyIntStackVer3(int capacity) {
            contents = new int[capacity];
            tos = -1;
        }

        // Push an element and dynamically reallocate if full
        public void push(int element) {
            if (isFull()) {
                // Double the capacity
                int[] newContents = new int[contents.length * 2];
                System.arraycopy(contents, 0, newContents, 0, contents.length);
                contents = newContents;
            }
            contents[++tos] = element;
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

