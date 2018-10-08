import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;

public class FastCollinearPoints {
    private Point[] points;
    private ArrayList<LineSegment> lines;
    private LineSegment[] segments;

    public FastCollinearPoints(Point[] input) {
        lines = new ArrayList<LineSegment>();
        Arrays.sort(input);
        points = input.clone();
        Comparator<Point> c;
        for (int i = 0; i < input.length; i++) {
            c = input[i].slopeOrder();
            Arrays.sort(points);
            Arrays.sort(points, c);
            int length = 2;
            int start = 1;
            int end = 2;
            while (end < points.length) {
                if (points[0].slopeTo(points[start]) == points[0].slopeTo(points[end]) && points[0].compareTo(points[start]) < 0) {
                    length++;
                    end++;
                } else {
                    if (length >= 4) {
                        addLine(points[0], points[start], points[end - 1]);
                    }
                    length = 2;
                    start = end;
                    end++;
                }
            }
            if (length >= 4) {
                addLine(points[0], points[start], points[end - 1]);
            }
        }
        toArray();
    }

    public LineSegment[] segments() {
        return segments;
    }
    
    public int numberOfSegments() {
        return segments.length;
    }

    private void toArray() {
        segments = new LineSegment[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            segments[i] = lines.get(i);
        }
    }

    private void addLine(Point ref, Point a, Point b) {
        Point start;
        Point end;
        if (ref.compareTo(a) < 0)
            start = ref;
        else
            start = a;
        if (ref.compareTo(b) > 0)
            end = ref;
        else
            end = b;
        lines.add(new LineSegment(start, end));
    }

    private static Point[] read(String file) {
        In in = new In(file);
        Point[] input = new Point[in.readInt()];
        for (int i = 0; i < input.length; i++) {
            input[i] = new Point(in.readInt(), in.readInt());
        }
        return input;
    }

    private static void printLines(LineSegment[] lines) {
        for (int i = 0; i < lines.length; i++) {
            System.out.println(lines[i]);
        }
    }

    public static void main(String[] args) {
        FastCollinearPoints a = new FastCollinearPoints(read("test/myinput.txt"));
        System.out.println(a.numberOfSegments());
        printLines(a.segments);
    }
}
