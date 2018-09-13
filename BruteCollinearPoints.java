import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
	private int numSegments;
	private ArrayList<LineSegment> lines;
	private Point[] points;

	public BruteCollinearPoints(Point[] points) {
		lines = new ArrayList<LineSegment>();
		this.points = points;
		if (points == null) {
			throw new IllegalArgumentException();
		}
		iterate(points);
	}
	
	public int numberOfSegments() {
		return lines.size();
	}

	// Jesus forgive me
	private void iterate(Point[] points) {
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points.length; j++) {
				if (j != i) {
					for (int k = 0; k < points.length; k++) {
						if (k != j && k != i) {
							for (int l = 0; l < points.length; l++) {
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
	}
	
	private boolean checkSlopes(Point p1, Point p2, Point p3, Point p4) {
		return (p1.slopeTo(p2) == p2.slopeTo(p3) && p2.slopeTo(p3) == p3.slopeTo(p4));
	}
	
	private void findLine (Point p1, Point p2, Point p3, Point p4) {
		Point[] temp = {p1, p2, p3, p4};
		Arrays.sort(temp);
		LineSegment a = new LineSegment(points[0], points[3]);
		boolean repeat = false;
		for (int i = 0; i < lines.size(); i++) {
			if (a.equals(lines.get(i))) {
				repeat = true;
			}
		}
		if (!repeat) {
			lines.add(a);
		}
	}
	public static void main(String[] args) {
		LineSegment a = new LineSegment(new Point(45, 3), new Point(50, 7));
		System.out.println(a);
	}
}