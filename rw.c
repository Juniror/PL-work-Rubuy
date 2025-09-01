
#include <stdio.h>

int main()
{
    FILE *fptr;
    char filename[100];
    char text[100];
    int command;

    printf("Enter the file name to work with (.txt): ");
    scanf("%s", filename);

    fptr = fopen(filename, "r+");
    if (fptr == NULL)
    {
        printf("File not found!\n");
        return 1;
    }

    while (1)
    {
        printf("1.read\n2.write\n3.exit : ");
        scanf("%d", &command);

        if (command == 1)
        {
            char ch;
            rewind(fptr);
            while ((ch = fgetc(fptr)) != EOF)
            {
                putchar(ch);
            }
            printf("\n");
        }
        else if (command == 2)
        {
            printf("Enter text: ");
            scanf(" %[^\n]", text);
            fseek(fptr, 0, SEEK_END);
            fputs(text, fptr);
            fflush(fptr);
        }
        else if (command == 3)
        {
            break;
        }
        else
        {
            printf("Invalid command!\n");
        }
    }

    fclose(fptr);
    return 0;
}
