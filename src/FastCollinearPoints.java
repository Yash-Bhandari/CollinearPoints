import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {
    private Point[] points;
    private Comparator<Point> originSlope;
    private ArrayList<LineSegment> lines;
    private LineSegment[] lineSegments;
    
    public FastCollinearPoints(Point[] points) {
        this.points = points;
        originSlope = new Point(0, 0).slopeOrder();
        Arrays.sort(points);
        Arrays.sort(points, originSlope);
        lines = new ArrayList<>();
        findLines();
        lineSegments = new LineSegment[lines.size()];
        for (int i = 0; i < lineSegments.length; i++) {
            lineSegments[i] = lines.get(i);
        }
    }
    
    public int numberOfSegments() {
        return lines.size();
    }
    
    public LineSegment[] segments() {
        return lineSegments;
    }
    
    private void findLines() {
        Point p = new Point(0, 0);
        for (int i = 0; i < points.length-3; i++) {
            int length = 1;
            while (i < points.length && points[i++].slopeTo(p) == points[i].slopeTo(p)) {
                length++;
            }
            if (length >= 4) lines.add(new LineSegment(points[i-length], points[i-1]));
        }
    }

}
