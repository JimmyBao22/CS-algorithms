#include <iostream>
#include <vector>
#include <map>
#include <cmath>
#include <algorithm>
#include <deque>
#include <queue>
#include <stack>
#include <set>
#include <string>
#include <string.h>
#include <array>
#include <unordered_map>
#include <unordered_set>
#include <iomanip>
using namespace std;
typedef long long ll;
typedef long double ld;
#define pb push_back

// note: points need to be in clockwise/counterclockwise order beforehand
ld ShoelaceArea (pair<ll, ll> points[], int n) {
    ll firstsum=0;
    ll secsum=0;
    for (int i=0; i<n; i++) {
        if (i == n-1) {
            firstsum += points[i].first * points[0].second;
            secsum += points[i].second * points[0].first;
        }
        else {
            firstsum += points[i].first * points[i+1].second;
            secsum += points[i].second * points[i+1].first;
        }
    }
    return abs(firstsum - secsum) / 2.0;
}

ld toRadians(ld degree) {
    return degree * M_PI/180;
}

// counterclockwise (x,y) around (a,b)
pair<ld, ld> Rotate(ld x, ld y, ld a, ld b, ld degree) {
    ld rad = toRadians(degree);
    ld ansx = x*cos(rad) - a*cos(rad) - y*sin(rad) + b*sin(rad) + a;
    ld ansy = x*sin(rad) + y*cos(rad) - a*sin(rad) - b*cos(rad) + b;
    return {ansx, ansy};
}

ll DistSquared(ll pointone[], ll pointtwo[]) {
    return (pointone[0] - pointtwo[0])*(pointone[0] - pointtwo[0]) + (pointone[1] - pointtwo[1])*(pointone[1] - pointtwo[1]);
}

// point[] = {x, y}
bool IsSquare(ll pointone[], ll pointtwo[], ll pointthree[], ll pointfour[]) {
    ll d1 = DistSquared(pointone, pointtwo);
    ll d2 = DistSquared(pointone, pointthree);
    ll d3 = DistSquared(pointone, pointfour);
    
    if (d1 == 0 || d2 == 0 || d3 == 0) return false; 
    
    if (d1 == d2 && 2 * d1 == d3 && 2 * DistSquared(pointtwo, pointfour) == DistSquared(pointtwo, pointthree)) { 
        return true; 
    } 
    
    if (d1 == d3 && 2 * d1 == d2 && 2 * DistSquared(pointtwo, pointthree) == DistSquared(pointtwo, pointfour)) { 
        return true; 
    } 
    
    if (d2 == d3 && 2 * d2 == d1 && 2 * DistSquared(pointtwo, pointthree) == DistSquared(pointfour, pointthree)) { 
        return true; 
    } 
    
    return false;
}

struct Point {
    ld x; ld y;
    Point (ld a, ld b) {
        x = a; y = b;
    }
};

// takes in 3 points. returns +1 if a->b->c is a counterclockwise angle, 
    // -1 if a->b->c is a clockwise angle, and 0 if a->b->c are collinear
double ccw(Point a, Point b, Point c) {
    ld val = (ld)(b.y - a.y) * (c.x - b.x) - (b.x - a.x) * (c.y - b.y);
    return val == 0 ? 0 : val < 0 ? -1 : 1;
}

    // point c lies on segment a-b
bool onSegment(Point a, Point c, Point b) {
    if (c.x <= max(a.x, b.x) && c.x >= min(a.x, b.x) && 
            c.y <= max(a.y, b.y) && c.y >= min(a.y, b.y)) return true; 
        
    return false; 
}

// returns whether a1 - a2 line segment intersects b1 - b2 line segment
bool Intersect(Point a1, Point a2, Point b1, Point b2) {
    double o1 = ccw(a1, a2, b1); 
    double o2 = ccw(a1, a2, b2); 
    double o3 = ccw(b1, b2, a1); 
    double o4 = ccw(b1, b2, a2); 
    
    if (o1 != o2 && o3 != o4) return true;
    
    if (o1 == 0 && onSegment(a1, b1, a2)) return true; 
    if (o2 == 0 && onSegment(a1, b2, a2)) return true; 	  
    if (o3 == 0 && onSegment(b1, a1, b2)) return true; 	  
    if (o4 == 0 && onSegment(b1, a2, b2)) return true; 
    
    return false;
}

	// y = m1x+b1 and y = m2x+b2
Point LineLineIntersection(ld m1, ld b1, ld m2, ld b2) { 
    m1 = -m1; m2 = -m2;
    ld determinant = m1 - m2; 
    
    if (determinant == 0) { 
        // parallel
        return Point(1e18, 1e18);
    } 
    else { 
        ld xx = (b1 - b2)/determinant; 
        ld yy = (m1*b2 - m2*b1)/determinant; 
        return Point(xx, yy); 
    } 
}

Point PointLineIntersection(Point a, Point b, Point c, Point d) { 
    // Line ab represented as a1x + b1y = c1 
    ld a1 = b.y - a.y; 
    ld b1 = a.x - b.x; 
    ld c1 = a1*(a.x) + b1*(a.y); 
    
    // Line cd represented as a2x + b2y = c2 
    ld a2 = d.y - c.y; 
    ld b2 = c.x - d.x; 
    ld c2 = a2*(c.x)+ b2*(c.y); 
    
    ld determinant = a1*b2 - a2*b1; 
    
    if (determinant == 0) { 
        // parallel 
        return Point(1e18, 1e18);
    } 
    else { 
        ld xx = (b2*c1 - b1*c2)/determinant; 
        ld yy = (a1*c2 - a2*c1)/determinant; 
        return Point(xx, yy); 
    } 
} 

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    
}