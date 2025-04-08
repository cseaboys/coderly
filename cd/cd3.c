#include <stdio.h>
#include <string.h>

#define eq(s, op) (strchr((s), (op)[0]) != NULL)

int main(void)
{
  char op[3];
  printf("Enter op: ");
  scanf("%2s", op);

  if (eq("&|", op) || op[0] == '!')
    puts("Logical");

  else if (eq("<>", op) || eq("&^|", op))
    puts("Bitwise");

  else if (op[0] == '?' && op[1] == ':')
    puts("Ternary");

  else if ((eq("+-*/%", op) && op[1] == '=') || (eq("=", op)))
    puts("Assignment");

  else if (eq("+-*/%", op))
    puts("Arithmetic");

  else if (eq("<>!=", op) && op[1] == '=')
    puts("Relational");

  return 0;
}
