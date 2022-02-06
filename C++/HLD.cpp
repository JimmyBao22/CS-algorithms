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

struct A {
    int size;
    int heavy = -1; 	// child at other end of heavy edge from this node
    int head;			// head of heavy path that this node is in
    int stpos;			// position in segment tree
    ll val;
    int parent;
    int depth;
    A () { }
    A (ll a) { val = a; }
};

struct SegTree {
    int size=1;
    vector<ll> tree;
    
    SegTree (int n) {			
        while (size < n) size *= 2;
        tree.assign(2*size, 0);
    }
    
    // random computation on segment (l to r-1)
    ll comp_seg(int l, int r) { return comp_seg(l, r, 0, 0, size); }
    
    ll comp_seg(int l, int r, int x, int lx, int rx) {
        if (lx >= r || rx <= l) return 0;	// do not intersect this segment
        if (l <= lx && rx <= r) return tree[x];	// inside whole segment
        int m = (lx + rx)/2;
        ll one = comp_seg(l, r, 2*x+1, lx, m); 
        ll two = comp_seg(l, r, 2*x+2, m, rx);
        return max(one, two);
    }
    
    void set(int i, ll v) { set(i, v, 0, 0, size); }	// arr[i] = v;
    
    void set(int i, ll v, int x, int lx, int rx) {
        if (rx - lx == 1) {		// in leaf node aka bottom level
            tree[x] = v; return;
        }
        int m = (lx + rx)/2;
        if (i < m) set(i, v, 2*x+1, lx, m); 	// go to left subtree
        else set(i, v, 2*x+2, m, rx);			// go to right subtree
        tree[x] = max(tree[2*x+1], tree[2*x+2]);
    }
    
    void build(ll arr[], int n) {	build(arr, n, 0, 0, size); }	// arr is the orig arr
    
    void build(ll arr[], int n, int x, int lx, int rx) {
        if (rx - lx == 1) {		// in leaf node aka bottom level
            if (lx < n) tree[x] = arr[lx];
            return;
        }
        int m = (lx + rx)/2;
        build(arr, n, 2*x+1, lx, m);	build(arr, n, 2*x+2, m, rx);
        tree[x] = max(tree[2*x+1], tree[2*x+2]);
    }
    
    void print() {
        for (int i=0; i<tree.size(); i++) cout << tree[i] << " ";
        cout << "\n";
    }
};

const int MaxN = 1e5+10;
vector<int> g[MaxN];
A arr[MaxN];
ll starr[MaxN];
int n, curpos=0, q;

int dfs(int node, int p, int d) {
    arr[node].size = 1;
    arr[node].parent = p;
    arr[node].depth = d;
    
    int max_subtree_size = 0;
    for (auto i : g[node]) {
        if (i == p) continue;
        int cursize = dfs(i, node, d+1);
        arr[node].size += cursize;
        if (cursize > max_subtree_size) {
            max_subtree_size = cursize;
            arr[node].heavy = i;
        }
    }
    return arr[node].size;
}

void hld(int node, int head) {
    arr[node].head = head;
    arr[node].stpos = curpos;
    starr[curpos] = arr[node].val;
    curpos++;
    if (arr[node].heavy != -1) {	
        hld(arr[node].heavy, head);
    }
    for (auto i : g[node]) {
        if (i == arr[node].parent || i == arr[node].heavy) continue;
        hld(i, i);					// start a new chain
    }
}

ll query(SegTree s, int a, int b) {
    ll ans=0;
    while (arr[a].head != arr[b].head) {		// while part of different chains
        if (arr[arr[a].head].depth > arr[arr[b].head].depth) {
            swap(a,b);		// swap so now b greater depth
        }
        int current_head = arr[b].head;
        ans = max(ans, s.comp_seg(arr[current_head].stpos, arr[b].stpos+1));
        b = arr[current_head].parent;		// move b to parent of head so you are on a new chain
    }
    if (arr[a].depth > arr[b].depth) {
        swap(a,b);
    }
    // now a and b on same chain
    ans = max(ans, s.comp_seg(arr[a].stpos, arr[b].stpos+1));
    return ans;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> n;
    for (int i=0; i<n; i++) cin >> arr[i].val;

    for (int i=0; i<n-1; i++) {
        int a, b;
        cin >> a >> b;
        a--; b--;
        g[a].push_back(b);
        g[b].push_back(a);
    }

    dfs(0, 0, 0);
    hld(0, 0);

    SegTree s(n);
    s.build(starr, n);

    cin >> q;
    for (int i=0; i<q; i++) {
        int one;
        cin >> one;
        if (one == 0) {			// update
            int two ;		    // node
            ll three;		    // value
            cin >> two >> three;
            two--;
            arr[two].val = three;
            s.set(arr[two].stpos, arr[two].val);
        }
        else {					// query
            int two, three;
            cin >> two >> three;
            two--; three--;
            ll ans = query(s, two, three);
            cout << ans << "\n";
        }
    }
}