#include <stdio.h>
#include <string.h>

char a[16] = "32423", stk[15], act[10] = "SHIFT";
int z, i, j, c;

void check() {
  char *ac = "REDUCE TO E -> ";
  for (z = 0; z < c; z++)
    if (stk[z] == '4')
      printf("%s4\n$%s\t%s$\t", ac, stk, a), stk[z] = 'E', stk[z + 1] = '\0';
  for (z = 0; z < c - 2; z++)
    if (stk[z] == '2' && stk[z + 1] == 'E' && stk[z + 2] == '2')
      printf("%s2E2\n$%s\t%s$\t", ac, stk, a), stk[z] = 'E', stk[z + 1] = '\0',
                                               stk[z + 2] = '\0', i -= 2;
  for (z = 0; z < c - 2; z++)
    if (stk[z] == '3' && stk[z + 1] == 'E' && stk[z + 2] == '3')
      printf("%s3E3\n$%s\t%s$\t", ac, stk, a), stk[z] = 'E', stk[z + 1] = '\0',
                                               stk[z + 2] = '\0', i -= 2;
}

int main() {
  c = strlen(a);
  printf("GRAMMAR is -\nE->2E2 \nE->3E3 \nE->4\n\nstack \t input \t "
         "action\n$\t%s$\t",
         a);
  for (i = 0, j = 0; j < c; i++, j++)
    printf("%s\n$%s\t%s$\t", act, stk, a), stk[i] = a[j], stk[i + 1] = '\0',
                                           a[j] = ' ', check();
  check();
  printf(stk[0] == 'E' && stk[1] == '\0' ? "Accept\n" : "Reject\n");
  return 0;
}
