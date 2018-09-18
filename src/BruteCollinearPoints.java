import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;

public class BruteCollinearPoints {
	ArrayList<LineSegment> lines;
	private LineSegment[] lineSegments;

	public BruteCollinearPoints(Point[] points) {

		if (points == null) {
			throw new IllegalArgumentException();
		}
		lines = new ArrayList<LineSegment>();
		iterate(points);
	}

	public int numberOfSegments() {
		return lineSegments.length;
	}

	public LineSegment[] segments() {
		return lineSegments;
	}

	// Jesus forgive me
	private void iterate(Point[] points) {
		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				for (int k = j + 1; k < points.length; k++) {
					for (int l = k + 1; l < points.length; l++) {
						if (checkSlopes(points[i], points[j], points[k], points[l]))
							findLine(points[i], points[j], points[k], points[l]);
					}
				}
			}
		}
		lineSegments = new LineSegment[lines.size()];
		for (int i = 0; i < lineSegments.length; i++) {
			lineSegments[i] = lines.get(i);
		}
	}

	// returns true if slopes between all points are equivalent
	private boolean checkSlopes(Point p1, Point p2, Point p3, Point p4) {
		return (p1.slopeTo(p2) == p2.slopeTo(p3) && p2.slopeTo(p3) == p3.slopeTo(p4));
	}

	private void findLine(Point p1, Point p2, Point p3, Point p4) {
		Point[] temp = { p1, p2, p3, p4 };
		Arrays.sort(temp);
		LineSegment a = new LineSegment(temp[0], temp[3]);

		lines.add(a);

	}

	public static void main(String[] args) {
		In in = new In(new File("test/input40.txt"));
		int length = in.readInt();
		Point[] points = new Point[length];
		for (int i = 0; i < length; i++) {
			int a = in.readInt();
			int b = in.readInt();
			points[i] = new Point(a, b);
		}
		System.out.println(points.length);
		FastCollinearPoints a = new FastCollinearPoints(points);
		System.out.println(a.numberOfSegments());
		LineSegment[] lines = a.segments();
		for (int i = 0; i < lines.length; i++) {
			System.out.println(lines[i]);
		}

	}
}