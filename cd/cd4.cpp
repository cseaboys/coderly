#include <cstring>
#include <iostream>
#include <string>
using namespace std;

class Parser
{
public:
  int n, nt = 0, t = 0, m[20][20];           // n: number of productions, nt: number of non-terminals, t: number of terminals, m: parsing table
  string p[30], N[20], T[20], f[20], fl[20]; // p: productions, N: non-terminals, T: terminals, f: first sets, fl: follow sets

  // Find index of non-terminal c
  int findN(char c)
  {
    for (int i = 0; i < nt; i++)
      if (N[i][0] == c)
        return i;
    return -1;
  }

  // Find index of terminal c
  int findT(char c)
  {
    for (int i = 0; i < t; i++)
      if (T[i][0] == c)
        return i;
    return -1;
  }

  // Read number of productions and the productions
  void input()
  {
    cin >> n;
    for (int i = 0; i < n; i++)
      cin >> p[i];
  }

  void process()
  {
    // Identify non-terminals from LHS of productions
    for (int i = 0; i < n; i++)
      if (findN(p[i][0]) == -1)
        N[nt++] = p[i].substr(0, 1);

    // Identify terminals from RHS, excluding 'e' and non-terminals
    for (int i = 0; i < n; i++)
      for (int j = 3; j < p[i].size(); j++) // start from index 3, assuming format "A->X"
        if (p[i][j] != 'e' && findN(p[i][j]) == -1 && findT(p[i][j]) == -1)
          T[t++] = string(1, p[i][j]);

    T[t++] = "$";             // Add end marker
    memset(m, -1, sizeof(m)); // Initialize parsing table with -1

    // Read first sets for each non-terminal
    for (int i = 0; i < nt; i++)
      cin >> f[i];
    // Read follow sets for each non-terminal
    for (int i = 0; i < nt; i++)
      cin >> fl[i];

    // Fill the parsing table
    for (int i = 0; i < n; i++)
    {
      int lhs = findN(p[i][0]); // Get index of LHS non-terminal
      char rhs = p[i][3];       // First symbol on RHS

      if (int ti = findT(rhs); ti != -1)      // If RHS starts with a terminal
        m[lhs][ti] = i;                       // Put production index in table
      else if (int ni = findN(rhs); ni != -1) // If RHS starts with a non-terminal
        for (char c : f[ni])                  // For each terminal in first set of that non-terminal
          if (int ti = findT(c); ti != -1)
            m[lhs][ti] = i;

      if (rhs == 'e')          // If production is epsilon
        for (char c : fl[lhs]) // For each terminal in follow set of LHS
          if (int ti = findT(c); ti != -1)
            m[lhs][ti] = i;
    }

    print();
  }

  // Print the parsing table
  void print()
  {
    cout << "\nParsing Table:\n\t";
    for (int i = 0; i < t; i++)
      cout << T[i] << "\t";
    cout << "\n";

    for (int i = 0; i < nt; i++)
    {
      cout << N[i];
      for (int j = 0; j < t; j++)
        cout << "\t" << (m[i][j] != -1 ? p[m[i][j]] : "-");
      cout << "\n";
    }
  }
};

int main()
{
  freopen("./p4_input.txt", "r", stdin); // Read input from input.txt
  Parser p;
  p.input();
  p.process();
}