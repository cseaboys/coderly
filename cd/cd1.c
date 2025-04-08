#include <ctype.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

typedef char *str;
#define say_type(s, tt) printf("%s is %s\n", (s), (tt))
bool isDelimiter(char ch) { return strchr(" ;,(){}[]", ch) != NULL; }
bool isOperator(char ch) { return strchr("+-*/=<>!&|", ch) != NULL; }
bool isKeyword(str s)
{
  str keywords[] = {
      "int",
      "float",
      "double",
      "return",
  };
  int num_keywords = sizeof(keywords) / sizeof(keywords[0]);
  for (int i = 0; i < num_keywords; i++)
    if (strcmp(s, keywords[i]) == 0)
      return true;
  return false;
}
bool isAllDigits(str s)
{
  while (*s)
    if (!isdigit(*s++))
      return false;
  return true;
}
bool isReal(str s)
{
  int d = 0;
  for (int i = 0; s[i]; i++)
    if (s[i] == '.')
      d++;
    else if (!isdigit(s[i]))
      return false;
  return d == 1;
}
bool isIdentifier(str s)
{
  if (!isalpha(s[0]) && s[0] != '_')
    return false;
  for (int i = 1; s[i]; i++)
    if (!isalnum(s[i]) && s[i] != '_')
      return false;
  return true;
}

void parse(str input)
{
  str s = input;
  while (*s)
  {
    if (isDelimiter(*s))
    {
      printf("'%c' is delimiter\n", *s);
      s++;
    }
    else if (isOperator(*s))
    {
      printf("'%c' is operator\n", *s);
      s++;
    }
    else
    {
      char tk[100];
      int len = 0;
      while (*s && !isDelimiter(*s) && !isOperator(*s))
        tk[len++] = *s++;
      tk[len] = '\0';
      if (isKeyword(tk))
        say_type(tk, "keyword");
      else if (isAllDigits(tk))
        say_type(tk, "integer\n");
      else if (isReal(tk))
        say_type(tk, "real number");
      else if (isIdentifier(tk))
        say_type(tk, "identifier");
      else
        say_type(tk, "invalid");
    }
  }
}

int main()
{
  str input = "int a = b + 1.23 +2c;";
  parse(input);
  return 0;
}