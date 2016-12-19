## Pattern Recognition
### Assignment 3 for Algorithms - Princeton | Coursera

#### Intro
A program that recognizes line patterns in a given set of points.

#### Methodology
> Given a point p, the following method determines whether p participates in a set of 4 or more collinear points.
* Think of p as the origin.
* For each other point q, determine the slope it makes with p.
* Sort the points according to the slopes they makes with p.
* Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p. If so, these points, together with p, are collinear.

#### Reference

http://coursera.cs.princeton.edu/algs4/assignments/collinear.html
