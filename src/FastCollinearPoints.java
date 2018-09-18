import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;

public class FastCollinearPoints {
	private Point[] points;
	private Point[] aux;
	private Comparator<Point> originSlope;
	private ArrayList<LineSegment> lines;
	private LineSegment[] lineSegments;
	private int current;

	public FastCollinearPoints(Point[] points) {
		this.points = points;
		aux = points.clone();
		lines = new ArrayList<>();
		iterate();
		saveArray();
	}

	public int numberOfSegments() {
		return lines.size();
	}

	public LineSegment[] segments() {
		return lineSegments;
	}

	private void saveArray() {
		lineSegments = new LineSegment[lines.size()];
		for (int i = 0; i < lineSegments.length; i++) {
			lineSegments[i] = lines.get(i);
		}
	}

	private void iterate() {
		current = 0;
		while (current < points.length) {
			
		}
		for (int i = 0; i < points.length; i++) {
			Comparator c = aux[i].slopeOrder();
			Arrays.sort(points, c);
			findLines(aux[i]);
		}
	}

	// checks if a line is a repeat before creating one
	private void createLine (Point a, Point b) {
    	LineSegment l = new LineSegment(a, b);
    	boolean copy = false;
    	for (int i = 0; i < lines.size(); i++) {
    		//if (lines.get(i).)
    	}
    			
    }

	// begins at 1 as point p is first point in line
	// slope to first point would return negative infinity
	private void findLines(Point p) {
		for (int i = 1; i < points.length; i++) {
			int length = 2;
			while (i < points.length - 1 && points[i].slopeTo(p) == points[i + 1].slopeTo(p)) {
				length++;
				i++;
			}
			if (length >= 4) {
				/*
				 * Point[] temp = new Point[length]; for (int j = 0; j < length; j++) { temp[j]
				 * = points[i-1-j]; }
				 */
				Point start;
				Point end;
				if (p.compareTo(points[i + 2 - length]) < 0)
					start = points[i + 2 - length];
				else
					start = p;
				if (p.compareTo(points[i]) < 0)
					end = p;
				else
					end = points[i];
				createLine(start, end);
			}
			// System.out.println(length);
		}
	}

	public static void main(String[] args) {
		Point a = new Point(1, 2);
		Point b = new Point(2, 3);
		LineSegment c = new LineSegment(a, b);
		LineSegment d = new LineSegment(a, b);
		System.out.println(c == d);
		/*Point[] points = { new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4), new Point(30, 1) };
		FastCollinearPoints a = new FastCollinearPoints(points);
		// FastCollinearPoints a = readInput("test/input40.txt");
		System.out.println(a.numberOfSegments());
		LineSegment[] b = a.segments();
		for (int i = 0; i < b.length; i++) {
			System.out.println(b[i]);
		}*/
	}

	public static FastCollinearPoints readInput(String file) {
		In in = new In(new File(file));
		int length = in.readInt();
		Point[] points = new Point[length];
		for (int i = 0; i < length; i++) {
			int a = in.readInt();
			int b = in.readInt();
			points[i] = new Point(a, b);
		}
		return new FastCollinearPoints(points);
	}
}
