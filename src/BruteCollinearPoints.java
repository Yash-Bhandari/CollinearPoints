import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdIn;

public class BruteCollinearPoints {
	ArrayList<LineSegment> lines;
	private LineSegment[] lineSegments;
	private Point[] points;

	public BruteCollinearPoints(Point[] points) {

		this.points = points;
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
				if (j != i) {
					for (int k = j + 1; k < points.length; k++) {
						if (k != j && k != i) {
							for (int l = k + 1; l < points.length; l++) {
								if (l != k && l != j && l != i) {
									if (checkSlopes(points[i], points[j], points[k], points[l]))
										findLine(points[i], points[j], points[k], points[l]);
								}
							}
						}
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
		int[] input = new int[args.length];
		for (int i = 0; i < args.length; i++) {
			input[i] = Integer.parseInt(args[i]);
		}
		Point[] points = new Point[input[0]];
		int current = 0;
		for (int i = 1; i < input.length; i++) {
			int a = input[i++];
			int b = input[i];
			points[current++] = new Point(a, b);
		}
		BruteCollinearPoints a = new BruteCollinearPoints(points);
		System.out.println(a.numberOfSegments());
	}
}