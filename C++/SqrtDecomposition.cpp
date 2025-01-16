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

const int MaxN = 1e5+10;
ll arr[MaxN];

struct SqrtDecomp {
    
    int len, n;			// size of blocks and # of blocks
    vector<ll> b;
    
    void init(int x) {
        n = x; len = sqrt(n)+1;
        b.assign(len, 0);
        for (int i=0; i<n; i++) b[i/len] += arr[i];
    }
    
    // set index i
    void update(int i, ll x) {
        int block = i/len;
        b[block] += x - arr[i];
        arr[i] = x;
    }
    
    // query l to r
    ll query(int l, int r) {
        ll sum = 0;
        int left = l/len; int right = r/len;
        if (left == right) {
            for (int i=l; i<=r; i++) sum += arr[i];
        }
        else {
            for (int i=l; i<=(left + 1)*len - 1; i++) sum += arr[i];
            for (int i=left+1; i<=right-1; i++) sum += b[i];
            for (int i=right*len; i<=r; i++) sum += arr[i];
        }
        return sum;
    }
    
    // update l to r
    void update_seg(int l, int r, ll x) {
        int left = l/len; int right = r/len;
        if (left == right) {
            for (int i=l; i<=r; i++) arr[i] += x;
        }
        else {
            for (int i=l; i<=(left + 1)*len - 1; i++) arr[i] += x;
            for (int i=left+1; i<=right-1; i++) b[i] += x;
            for (int i=right*len; i<=r; i++) arr[i] += x;
        }
    }
    
    // query index i
    ll query_index(int i) {
        return arr[i] + b[i/len];
    }
};

struct A {
    int left, right, index;
    A(){}
    A(int a, int b, int c) {
        left = a; right = b; index = c;
    }
};

const int MaxN = 30001, MaxQ = 200001, MaxVal = 1e6+1;
int arr[MaxN], answer[MaxQ], c[MaxVal];
A queries[MaxQ];
int n, q, k;

bool compare(A a, A b) {
    if (a.left/k == b.left/k) {
        return a.right < b.right;
    }
    else {
        return a.left/k < b.left/k;
    }
}

int add(int index, int arr[], int ans) {
    arr[index]++;
    if (arr[index] == 1) ans++;
    return ans;
}

int remove(int index, int arr[], int ans) {
    arr[index]--;
    if (arr[index] == 0) ans--;
    return ans;
}

void MO() {
    sort(queries, queries+q, compare);
    
    int ans = 0;
    int left = 0;
    int right = 0;
    ans = add(arr[0], c, ans);
    for (int i=0; i<q; i++) {
        while (left < queries[i].left) {
            ans = remove(arr[left], c, ans);
            left++;
        }
        while (left > queries[i].left) {
            left--;
            ans = add(arr[left], c, ans);
        }
        while (right < queries[i].right) {
            right++;
            ans = add(arr[right], c, ans);
        }
        while (right > queries[i].right) {
            ans = remove(arr[right], c, ans);
            right--;
        }
        answer[queries[i].index]= ans;
    }
    
    for (int i=0; i<q; i++) {
        cout << answer[i] << "\n";
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    
}