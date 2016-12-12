import java.util.Arrays;

public class FastCollinearPoints {
    private Point max;
    private LineSegment[] segments;
    private int n;

    //private int count;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        //varify input
        if(points == null) throw new  NullPointerException();

        Point[] base = Arrays.copyOf(points, points.length);

        for (int i = 0; i < base.length; i++) {
            if (base[i] == null) throw new  NullPointerException();
        }

        Arrays.sort(base);
        for(int i = 0; i < base.length-1; i++) {
            if (base[i].compareTo(base[i+1]) == 0) throw new IllegalArgumentException();
        }

        segments = new LineSegment[2];
        n=0;
        boolean hasInLine;

        for (int i = 0; i < base.length; i++) {
            Point[] data = Arrays.copyOf(base, base.length);
            Arrays.sort(data, base[i].slopeOrder());
            for (int j = 1, k = 1; j < data.length-2; j=j+k) {
                hasInLine=false;
                k = 1;
                max = data[0];
                if (data[0].slopeTo(data[j])==data[0].slopeTo(data[j+2])) {
                    for (k = 0; j+k < data.length; k++) {
                        if (data[0].slopeTo(data[j])==data[0].slopeTo(data[j+k])) {
                            if (max.compareTo(data[j+k])<0) max = data[j+k];
                            if (data[0].compareTo(data[j+k])>0) {
                                hasInLine = true;
                            }
                        }
                        else {
                            break;
                        }
                    }
                    if(!hasInLine) {
                        LineSegment segment = new LineSegment(data[0], max);
                        if(n == segments.length) resize(2*n);
                        segments[n++]=segment;
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

}
