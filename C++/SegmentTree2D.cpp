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

struct SegTree2D {
    int sizex=1; int sizey=1;
    vector<vector<ll>> tree;
    
    SegTree2D (int n, int m) {		
        while (sizex < n) sizex *= 2;
        while (sizey < m) sizey *= 2;
        tree.assign(2*sizex, vector<ll> (2*sizey));
    }
    
    // random computation from (l1, r1) to (l2-1, r2-1)
    ll comp_seg(int l1, int r1, int l2, int r2) { return comp_segx(l1, l2, r1, r2, 0, 0, sizex); }
    
    ll comp_segx(int l1, int l2, int r1, int r2, int x, int lx, int rx) {
        if (lx >= l2 || rx <= l1) return 0;	// do not intersect this segment
        if (rx - lx == 1) {
            return comp_segy(l1, l2, r1, r2, x, 0, 0, sizey);	
        }
        int m = (lx + rx)/2;
        ll one = comp_segx(l1, l2, r1, r2, 2*x+1, lx, m); 
        ll two = comp_segx(l1, l2, r1, r2, 2*x+2, m, rx);
        return one + two;
    }
    
    ll comp_segy(int l1, int l2, int r1, int r2, int x, int y, int ly, int ry) {
        if (ly >= r2 || ry <= r1) return 0;	// do not intersect this segment
        if (r1 <= ly && ry <= r2) {
            return tree[x][y];				// inside whole segment
        }
        int m = (ly + ry)/2;
        ll one = comp_segy(l1, l2, r1, r2, x, 2*y+1, ly, m); 
        ll two = comp_segy(l1, l2, r1, r2, x, 2*y+2, m, ry);
        return one + two;
    }
    
    // arr[i][j] = v;
    void set(int i, int j, ll v) { setx(i, j, v, 0, 0, sizex); }
    
    void setx(int i, int j, ll v, int x, int lx, int rx) {
        if (rx - lx == 1) {		// in leaf node aka bottom level
            sety(i, j, v, x, 0, 0, sizey); return;
        }
        int m = (lx + rx)/2;
        if (i < m) setx(i, j, v, 2*x+1, lx, m); 	// go to left subtree
        else setx(i, j, v, 2*x+2, m, rx);			// go to right subtree
    }
    
    void sety(int i, int j, ll v, int x, int y, int ly, int ry) {
        if (ry - ly == 1) {		// in leaf node aka bottom level
            tree[x][y] = v; return;
        }
        int m = (ly + ry)/2;
        if (j < m) sety(i, j, v, x, 2*y+1, ly, m); 	// go to left subtree
        else sety(i, j, v, x, 2*y+2, m, ry);			// go to right subtree
        tree[x][y] = tree[x][2*y+1] + tree[x][2*y+2];
    }
    
    void build(vector<vector<ll>> arr) { buildx(arr, 0, 0, sizex); }	// arr is the orig arr
    
    void buildx(vector<vector<ll>> arr, int x, int lx, int rx) {
        if (rx - lx == 1) {		// in leaf node aka bottom level
            if (lx < arr.size()) {
                buildy(arr, x, 0, lx, 0, sizey);
            }
            return;
        }
        int m = (lx + rx)/2;
        buildx(arr, 2*x+1, lx, m);	buildx(arr, 2*x+2, m, rx);
    }
    
    void buildy(vector<vector<ll>> arr, int x, int y, int lx, int ly, int ry) {
        if (ry - ly == 1) {		// in leaf node aka bottom level
            if (ly < arr.size()) tree[x][y] = arr[lx][ly];
            return;
        }
        int m = (ly + ry)/2;
        buildy(arr, x, 2*y+1, lx, ly, m);	buildy(arr, x, 2*y+2, lx, m, ry);
        tree[x][y] = tree[x][2*y+1] + tree[x][2*y+2];
    } 
    
    void print() {
        for (int i=0; i<(int)tree.size(); i++) {
            for (int j=0; j<(int)tree[i].size(); j++) cout << tree[i][j] << " ";
            cout << "\n";
        }
        cout << "\n";
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    
}