#include <bits/stdc++.h>
using namespace std;

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    long long k;
    cin >> n >> m >> k;

    vector<long long> arr(n), brr(m);
    for (int i = 0; i < n; i++)
        cin >> arr[i];
    for (int i = 0; i < m; i++)
        cin >> brr[i];

    sort(arr.begin(), arr.end());
    sort(brr.begin(), brr.end());

    int i = 0, j = 0, cnt = 0;

    while (i < n && j < m)
    {
        long long need = arr[i];
        long long apt = brr[j];

        if (apt < need - k)
            j++;
        else if (apt > need + k)
            i++;
        else
        {
            cnt++;
            i++;
            j++;
        }
    }

    cout << cnt << '\n';
    return 0;
}
