import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;

public class BruteCollinearPoints {
    Point[] points;
    ArrayList<LineSegment> lines;
    LineSegment[] lineSegments;

    public BruteCollinearPoints(Point[] input) {
        points = input;
        Arrays.sort(points);
        lines = new ArrayList<LineSegment>();
        iterate();
        toArray();
    }

    public LineSegment[] segments() {
        return lineSegments;
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }

    private void iterate() {
        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                for (int k = j + 1; k < points.length - 1; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        if (points[i].slopeTo(points[j]) == points[j].slopeTo(points[k])) {
                            if (points[j].slopeTo(points[k]) == points[k].slopeTo(points[l])) {
                                addLine(points[i], points[l]);
                            }
                        }
                    }
                }
            }
        }
    }

    private void addLine(Point a, Point b) {
        lines.add(new LineSegment(a, b));
    }

    private void toArray() {
        lineSegments = new LineSegment[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            lineSegments[i] = lines.get(i);
        }
    }

    private static Point[] read(String file) {
        In in = new In("test/" + file);
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
        BruteCollinearPoints a = new BruteCollinearPoints(read("myinput.txt"));
        System.out.println(a.numberOfSegments());
        printLines(a.segments());
    }
}