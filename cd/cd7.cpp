#include <cstring>
#include <iostream>
using namespace std;

struct production
{
  char lf, rt[10];
  int fl;
} prodn[20], prodn_new[20];
int b = -1, q, n, c = 0;
char alpha[10], extra[10], epsilon = '^';

int main()
{
  cout << "\nEnter number of special chars: ";
  cin >> q;
  cout << "Enter special chars: ";
  for (int i = 0; i < q; i++)
    cin >> alpha[i];
  cout << "\nEnter number of productions: ";
  cin >> n;
  for (int i = 0; i < n; i++)
  {
    string t;
    cout << "Production " << i + 1 << ": ";
    cin >> t;
    prodn[i] = {t[0], {}, 0};
    strcpy(prodn[i].rt, t.substr(3).c_str());
  }

  for (int i = 0; i < n; i++)
    for (int j = i + 1; j < n; j++)
      if (prodn[i].lf == prodn[j].lf && !prodn[i].fl && !prodn[j].fl)
      {
        int k = 0, p = -1;
        while (prodn[i].rt[k] && prodn[j].rt[k] && prodn[i].rt[k] == prodn[j].rt[k])
          extra[++p] = prodn[i].rt[k++];
        if (p >= 0)
        {
          prodn[i].fl = prodn[j].fl = 1;
          prodn_new[++b] = {prodn[i].lf};
          strncpy(prodn_new[b].rt, extra, p + 1);
          prodn_new[b].rt[p + 1] = alpha[c];
          prodn_new[++b] = {alpha[c]};
          strcpy(prodn_new[b].rt, prodn[i].rt[k] ? prodn[i].rt + k : &epsilon);
          prodn_new[++b] = {alpha[c]};
          strcpy(prodn_new[b].rt, prodn[j].rt[k] ? prodn[j].rt + k : &epsilon);
          c++;
        }
      }

  cout << "\n\n********************************\nAFTER LEFT FACTORING\n********************************\n";
  int pc = 1;
  for (int i = 0; i <= b; i++)
    cout << "Production " << pc++ << ": " << prodn_new[i].lf << "->" << prodn_new[i].rt << endl;
  for (int i = 0; i < n; i++)
    if (!prodn[i].fl)
      cout << "Production " << pc++ << ": " << prodn[i].lf << "->" << prodn[i].rt << endl;
}