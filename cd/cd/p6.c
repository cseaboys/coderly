#include <ctype.h>
#include <stdbool.h>
#include <stdio.h>
#include <string.h>

#define NT 4 // number of nonterminals: S, A, B, C
#define NP 6 // number of productions
#define SET_SIZE 20

// Our grammar productions (no spaces for brevity)
char *prod[NP] = {"S->AB", "A->a", "A->#", "B->Cd", "C->b", "C->#"};

// nonterminals in order (index 0: S, 1: A, 2: B, 3: C)
char nonterms[NT] = {'S', 'A', 'B', 'C'};

// FIRST and FOLLOW sets (each as a string of symbols)
char FIRST[NT][SET_SIZE] = {""};
char FOLLOW[NT][SET_SIZE] = {""};

// Helper: returns index of nonterminal ch in nonterms[]
int ntIndex(char ch) {
  for (int i = 0; i < NT; i++)
    if (nonterms[i] == ch)
      return i;
  return -1;
}

// Add symbol to set (setName: "FIRST" or "FOLLOW") for nonterminal index i.
// Prints a debug step if symbol was newly added.
bool addSymbol(char set[][SET_SIZE], int i, char sym, const char *setName) {
  if (strchr(set[i], sym))
    return false;
  int len = strlen(set[i]);
  set[i][len] = sym;
  set[i][len + 1] = '\0';
  printf("Step: Added '%c' to %s(%c) -> {%s}\n", sym, setName, nonterms[i],
         set[i]);
  return true;
}

// Checks if symbol is terminal (epsilon '#' is treated as terminal)
bool isTerminal(char ch) { return !isupper(ch); }

// Compute FIRST sets iteratively.
void computeFIRST() {
  bool changed = true;
  printf("\n--- Computing FIRST sets ---\n");
  while (changed) {
    changed = false;
    // For each production X->Y...
    for (int i = 0; i < NP; i++) {
      char LHS = prod[i][0];
      int X = ntIndex(LHS);
      // pointer to RHS (after "->")
      char *rhs = strstr(prod[i], "->") + 2;
      bool allEps = true;
      for (int j = 0; rhs[j] != '\0'; j++) {
        char sym = rhs[j];
        if (isTerminal(sym)) { // terminal (or epsilon)
          if (sym != '#' || (rhs[j + 1] == '\0')) {
            if (addSymbol(FIRST, X, sym, "FIRST"))
              changed = true;
          }
          allEps = (sym == '#');
          break; // stop after a terminal is found
        } else { // nonterminal: add its FIRST (except epsilon)
          int Y = ntIndex(sym);
          for (int k = 0; k < strlen(FIRST[Y]); k++) {
            char fsym = FIRST[Y][k];
            if (fsym != '#' && addSymbol(FIRST, X, fsym, "FIRST"))
              changed = true;
          }
          // If FIRST(Y) does not contain epsilon, break.
          if (!strchr(FIRST[Y], '#')) {
            allEps = false;
            break;
          }
        }
      }
      // If every symbol in RHS can be ε, add ε to FIRST(X)
      if (allEps)
        changed |= addSymbol(FIRST, X, '#', "FIRST");
    }
  }
  printf("\nFinal FIRST sets:\n");
  for (int i = 0; i < NT; i++)
    printf("FIRST(%c) = {%s}\n", nonterms[i], FIRST[i]);
}

// Compute FOLLOW sets iteratively.
void computeFOLLOW() {
  bool changed = true;
  // start symbol gets '$'
  addSymbol(FOLLOW, ntIndex('S'), '$', "FOLLOW");
  printf("\n--- Computing FOLLOW sets ---\n");
  while (changed) {
    changed = false;
    // For each production X->Y...
    for (int i = 0; i < NP; i++) {
      char X = prod[i][0];
      char *rhs = strstr(prod[i], "->") + 2;
      int len = strlen(rhs);
      for (int pos = 0; pos < len; pos++) {
        char sym = rhs[pos];
        if (isupper(sym)) { // nonterminal in RHS
          int A = ntIndex(sym);
          // Look at the "beta" (symbols after sym)
          bool betaEps = true;
          for (int pos2 = pos + 1; pos2 < len; pos2++) {
            char next = rhs[pos2];
            if (isTerminal(next)) {
              if (next != '#')
                changed |= addSymbol(FOLLOW, A, next, "FOLLOW");
              betaEps = false;
              break;
            } else {
              int B = ntIndex(next);
              // add FIRST(B) except epsilon to FOLLOW(A)
              for (int k = 0; k < strlen(FIRST[B]); k++) {
                char f = FIRST[B][k];
                if (f != '#' && addSymbol(FOLLOW, A, f, "FOLLOW"))
                  changed = true;
              }
              if (!strchr(FIRST[B], '#')) {
                betaEps = false;
                break;
              }
            }
          }
          // If beta is empty or can derive ε, add FOLLOW(X) to FOLLOW(A)
          if (betaEps) {
            int Xindex = ntIndex(X);
            for (int k = 0; k < strlen(FOLLOW[Xindex]); k++) {
              char f = FOLLOW[Xindex][k];
              if (addSymbol(FOLLOW, A, f, "FOLLOW"))
                changed = true;
            }
          }
        }
      }
    }
  }
  printf("\nFinal FOLLOW sets:\n");
  for (int i = 0; i < NT; i++)
    printf("FOLLOW(%c) = {%s}\n", nonterms[i], FOLLOW[i]);
}

int main() {
  printf("Grammar:\n");
  for (int i = 0; i < NP; i++)
    printf("  %s\n", prod[i]);

  computeFIRST();
  computeFOLLOW();

  return 0;
}
