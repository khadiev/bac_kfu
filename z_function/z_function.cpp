#include <algorithm>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

vector<int> z_function(const string& s)
{
    int n = s.size();
    vector<int> z(n);
    for (int i = 1, l = -1, r = -1; i < n; ++i)
    {
        if (i < r)
            z[i] = min(r - i, z[i - l]);
        while (i + z[i] < n && s[z[i]] == s[i + z[i]])
            ++z[i];
        if (i + z[i] > r)
            l = i, r = i + z[i];
    }
    return z;
}

int main()
{
    string s;
    cin >> s;
    vector<int> z = z_function(s);
    for (int i : z)
        cout << i << endl;
    return 0;
}
