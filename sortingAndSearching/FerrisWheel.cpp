#include <bits/stdc++.h>
using namespace std;

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    long long x;
    cin >> n >> x;

    vector<long long> arr(n);
    for (int i = 0; i < n; i++)
        cin >> arr[i];

    sort(arr.begin(), arr.end());

    int i = 0, j = n - 1;
    long long cnt = 0;

    while (i <= j)
    {
        if (arr[i] + arr[j] <= x)
        {
            i++;
            j--;
        }
        else
        {
            j--;
        }
        cnt++;
    }

    cout << cnt << '\n';
    return 0;
}
