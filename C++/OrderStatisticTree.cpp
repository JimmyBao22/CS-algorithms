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
#include <ext/pb_ds/tree_policy.hpp>
#include <ext/pb_ds/assoc_container.hpp>
using namespace __gnu_pbds;
using namespace std;
typedef long long ll;
typedef long double ld;
#define pb push_back
template <class T> using OSTree = tree<T, null_type, less<T>, rb_tree_tag, tree_order_statistics_node_update>;

OSTree<int> o;

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0); 

    o.insert(1);
    o.insert(2);
    o.insert(4);
    o.insert(8);
    o.insert(16);

    o.erase(2);

    // tree = {0, 4, 8, 16}

    cout << *o.begin() << "\n";            // 1
    cout << *(--o.end()) << "\n";          // 16


        // returns element at that index (start from 0)

    cout << *o.find_by_order(1) << "\n";   // 4
    cout << *o.find_by_order(2) << "\n";   // 8
    cout << *o.find_by_order(4) << "\n";   // 0 (no element)


        // return # elements strictly smaller

    cout << o.order_of_key(-5) << "\n";    // 0
    cout << o.order_of_key(1) << "\n";     // 0
    cout << o.order_of_key(2) << "\n";     // 1
    cout << o.order_of_key(3) << "\n";     // 1
    cout << o.order_of_key(4) << "\n";     // 1
    cout << o.order_of_key(5) << "\n";     // 2

}