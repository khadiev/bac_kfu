#include <iostream>
#include <string>
#include <vector>

using namespace std;

vector<int> prefix_function(const string& s)
{
    int n = s.size();
    vector<int> p(n);
    for (int i = 1; i < n; ++i)
    {
        int t = p[i - 1];
        while (t > 0 && s[i] != s[t])
            t = p[t - 1];
        if (s[t] == s[i])
            p[i] = t + 1;
    }
    return p;
}

int main()
{
    string s;
    cin >> s;
    vector<int> p = prefix_function(s);
    for (int i : p)
        cout << i << endl;
    return 0;
}
