#include <stdio.h>
#include <string.h>

char *prol[] = {"S", "A", "A", "B", "B", "C", "C"},
     *pror[] = {"A", "Bb", "Cd", "aB", "@", "Cc", "@"},
     *prod[] = {"S->A", "A->Bb", "A->Cd", "B->aB", "B->@", "C->Cc", "C->@"},
     *first[] = {"abcd", "ab", "cd", "a@", "@", "c@", "@"},
     *follow[] = {"$", "$", "$", "a$", "b$", "c$", "d$"}, table[5][6][10];

int numr(char c) {
  switch (c) {
  case 'S':
    return 1;
  case 'A':
    return 2;
  case 'B':
    return 3;
  case 'C':
    return 4;
  case 'a':
    return 1;
  case 'b':
    return 2;
  case 'c':
    return 3;
  case 'd':
    return 4;
  case '$':
    return 5;
  }
  return -1;
}

int main() {
  int i, j;
  for (i = 0; i < 5; i++)
    for (j = 0; j < 6; j++)
      strcpy(table[i][j], " ");
  printf("The following grammar is used for Parsing Table:\n");
  for (i = 0; i < 7; i++)
    printf("%s\n", prod[i]);
  for (i = 0; i < 7; i++) {
    char *p = first[i];
    while (*p) {
      if (*p != '@') {
        int r = numr(prol[i][0]), c = numr(*p);
        if (r != -1 && c != -1)
          strcpy(table[r][c], prod[i]);
      }
      p++;
    }
  }
  for (i = 0; i < 7; i++)
    if (strlen(pror[i]) == 1 && pror[i][0] == '@') {
      char *p = follow[i];
      while (*p) {
        int r = numr(prol[i][0]), c = numr(*p);
        if (r != -1 && c != -1)
          strcpy(table[r][c], prod[i]);
        p++;
      }
    }
  char *t = "abcd$";
  for (j = 1; j <= 5; j++)
    sprintf(table[0][j], "%c", t[j - 1]);
  t = "SABC";
  for (i = 1; i <= 4; i++)
    sprintf(table[i][0], "%c", t[i - 1]);
  printf("\nPredictive Parsing "
         "Table:\n--------------------------------------------------------\n");
  for (i = 0; i < 5; i++) {
    for (j = 0; j < 6; j++)
      printf("%-10s", table[i][j]);
    printf("\n--------------------------------------------------------\n");
  }
  return 0;
}
