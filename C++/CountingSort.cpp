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

void sort(int n, int arr[], int maxval) {
    int output[n];
    int count[maxval+1];
    
    for (int i=0; i<n; i++) count[arr[i]]++;
    for (int i=1; i<=maxval; i++) count[i] += count[i-1];
    for (int i=n-1; i>=0; i--) {
        output[count[arr[i]] - 1] = arr[i];
        count[arr[i]]--;
    }
    for (int i=0; i<n; i++) arr[i] = output[i];
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    
}