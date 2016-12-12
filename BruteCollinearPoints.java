//import edu.princeton.cs.algs4.StdDraw;
//import edu.princeton.cs.algs4.StdOut;
//import edu.princeton.cs.algs4.In;
import java.util.Arrays;

public class BruteCollinearPoints {
    private Point min;
    private Point max;
    private LineSegment[] segments;
    private int n;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        //varify input
        if(points == null) throw new  NullPointerException();

        Point[] data = Arrays.copyOf(points, points.length);

        for (int i = 0; i < data.length; i++) {
            if (data[i] == null) throw new  NullPointerException();
        }

        Arrays.sort(data);
        for(int i = 0; i < data.length-1; i++) {
            if (data[i].compareTo(data[i+1]) == 0) throw new IllegalArgumentException();
        }

        segments = new LineSegment[2];
        n=0;
        for (int i = 0; i < data.length-3; i++) {
            for (int l = i+1; l < data.length-2; l++) {
                double slope1 = data[i].slopeTo(data[l]);
                if (slope1 ==Double.NEGATIVE_INFINITY) continue;


                mainloop:for (int j = l+1; j < data.length-1; j++) {
                    if (slope1!=data[i].slopeTo(data[j])) continue;
                    else {

                        for (int k = j+1; k < data.length; k++) {
                          if (slope1!=data[i].slopeTo(data[k])) continue;
                            else {
                                min=data[i];
                                max=data[k];
                                LineSegment segment = new LineSegment(min, max);
                                if(n == segments.length) resize(2*n);
                                segments[n++]=segment;
                                break mainloop;
                            }
                        }
                    }
                }
            }
        }
    }

    // the number of line segments
    public           int numberOfSegments() {
        return n;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] temp = new LineSegment[n];
        for (int i = 0; i < n; i++) {
            temp[i] = segments[i];
        }
        segments = temp;
        return segments.clone();
    }

    private void resize(int capacity) {
        assert capacity >= n;
        LineSegment[] temp = new LineSegment[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = segments[i];
        }
        segments = temp;
    }
/**
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);
    }*/
}
