import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;

public class FastCollinearPoints {
    private Point[] points;
    
    public FastCollinearPoints(Point[] input) {
        points = input.clone();
        Arrays.sort(points);
        Comparator<Point> c;
        for (int i = 0; i < input.length; i++) {
             c = input[i].slopeOrder();
             Arrays.sort(points, c);
             int length = 2;
             double slope = points[0].slopeTo(points[1]);
             for (int j = 1; j < points.length-1; j++) {
                 
             }
        }
    }
    
}
