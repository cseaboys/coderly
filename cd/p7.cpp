#include <cstring>
#include <iostream>

using namespace std;

struct production {
  char lf, rt[10];
  int prod_rear, fl;
};

production prodn[20], prodn_new[20];
int b = -1, q, n, m = 0, c = 0;
char alpha[10], extra[10], epsilon = '^';

int main() {
  cout << "\nEnter the number of Special characters (except non-terminals): ";
  cin >> q;
  cout << "Enter the special characters for your production: ";
  for (int i = 0; i < q; i++)
    cin >> alpha[i];

  cout << "\nEnter the number of productions: ";
  cin >> n;
  for (int i = 0; i < n; i++) {
    cout << "Enter production " << i + 1;
    cin >> prodn[i].lf >> prodn[i].rt;
    prodn[i].prod_rear = strlen(prodn[i].rt);
    prodn[i].fl = 0;
  }

  for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
      if (prodn[i].lf == prodn[j].lf) {
        int k = 0, p = -1;
        memset(extra, 0, sizeof(extra));
        while (prodn[i].rt[k] && prodn[j].rt[k]) {
          if (prodn[i].rt[k] == prodn[j].rt[k]) {
            extra[++p] = prodn[i].rt[k];
            prodn[i].fl = prodn[j].fl = 1;
          } else if (p != -1) {
            int h = 0, u = 0;
            prodn_new[++b].lf = prodn[i].lf;
            strcpy(prodn_new[b].rt, extra);
            prodn_new[b].rt[p + 1] = alpha[c];
            prodn_new[++b].lf = alpha[c];
            for (int l = k; l < prodn[j].prod_rear; l++)
              prodn_new[b].rt[h++] = prodn[j].rt[l];
            prodn_new[++b].lf = alpha[c];
            for (int l = k; l <= prodn[i].prod_rear; l++)
              prodn_new[b].rt[u++] = prodn[i].rt[l];
            m = 1;
            break;
          }
          k++;
        }

        if (!prodn[i].rt[k] && !m) {
          int h = 0;
          prodn_new[++b].lf = prodn[i].lf;
          strcpy(prodn_new[b].rt, extra);
          prodn_new[b].rt[p + 1] = alpha[c];
          prodn_new[++b].lf = alpha[c];
          prodn_new[b].rt[0] = epsilon;
          prodn_new[++b].lf = alpha[c];
          for (int l = k; l < prodn[j].prod_rear; l++)
            prodn_new[b].rt[h++] = prodn[j].rt[l];
        }

        if (!prodn[j].rt[k] && !m) {
          int h = 0;
          prodn_new[++b].lf = prodn[i].lf;
          strcpy(prodn_new[b].rt, extra);
          prodn_new[b].rt[p + 1] = alpha[c];
          prodn_new[++b].lf = alpha[c];
          prodn_new[b].rt[0] = epsilon;
          prodn_new[++b].lf = alpha[c];
          for (int l = k; l < prodn[i].prod_rear; l++)
            prodn_new[b].rt[h++] = prodn[i].rt[l];
        }
        c++;
        m = 0;
      }
    }
  }

  cout << "\n\n********************************";
  cout << "\n AFTER LEFT FACTORING ";
  cout << "\n********************************\n";

  for (int i = 0; i <= b; i++) {
    cout << "Production " << i + 1 << " is: " << prodn_new[i].lf << "->"
         << prodn_new[i].rt << endl
         << endl;
  }

  for (int i = 0; i < n; i++) {
    if (!prodn[i].fl) {
      cout << "Production " << i + 1 << " is: " << prodn[i].lf << "->"
           << prodn[i].rt << endl
           << endl;
    }
  }

  return 0;
}
