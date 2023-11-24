package lab10_task2_queue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MyFIFO_App {
	// method stutter that accepts a queue of integers as a parameter and replaces
	// every element of the queue with two copies of that element
	// front [1, 2, 3] back
	// becomes
	// front [1, 1, 2, 2, 3, 3] back
	public static <E> void stutter(Queue<E> input) {
		Queue<E> q = new LinkedList<E>();
		while (!input.isEmpty()) {
			q.add(input.poll());
		}
		while (!q.isEmpty()) {
			input.add(q.peek());
			input.add(q.poll());

		}
	}
	// Method mirror that accepts a queue of strings as a parameter and appends the
	// queue's contents to itself in reverse order
	// front [a, b, c] back

	// becomes
	// front [a, b, c, c, b, a] back
	public static <E> void mirror(Queue<E> input) {
		Queue<E> temp = new LinkedList<>();
		for (E element : input) {
            temp.add(element);
        }
		
		Stack<E> s = new Stack<>();
		
		while (!temp.isEmpty()) {
			s.push(temp.poll());
		}
		
		while (!s.isEmpty()) {
			input.add(s.pop());

		}
	}

	public static void main(String[] args) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(1);
		q.offer(2);
		q.offer(3);
		stutter(q);
//		mirror(q);
		System.out.println(q);
	}
}
