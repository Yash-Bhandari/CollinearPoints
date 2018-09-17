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
    	for (int i = 0; i < points.length; i++) {
    		Arrays.sort(points, points[i].slopeOrder());
    		/*try {Arrays.sort(points, points[i].slopeOrder());}
    		catch (IllegalArgumentException e) {
    			System.out.println("Can't sort based on " + i + ", " + points[i]);
    			break;
    		}*/
    		findLines(points[i]);
    	}
    }
    
    private void findLines(Point p) {
        for (int i = 1; i < points.length-3; i++) {
            int length = 1;
            while (i < points.length-1 && points[i+1].slopeTo(p) == points[i].slopeTo(p)) {
                length++;
                i++;
            }
            if (length >= 4) lines.add(new LineSegment(points[i-length], points[i-1]));
        }
    }

}
