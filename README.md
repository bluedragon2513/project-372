# project-372

Authors: 
- Creed Leichtle
- Kenneth Acevedo
- AJ Scollo
- Anthony Nguyen

Description:
- An imperative, interpreted, inferred but strongly-typed, dynamic language.
- Supports functional programming

Steps to Run (Interpreted):
1. Open bash in the project-372 folder.
2. Run `anna.sh`.
    - You may have to `chmod u+x anna.sh`.
    - If you would like to load source code, simply run it as so: `anna.sh <file>`.
    - You may also choose to not load any source code.

Steps to Run (Compiled):
1. Open bash in the project-372 folder.
2. Run `annac.sh <file>`
    - You may have to `chmod u+x annac.sh`
    - The compiled code will be stored in the same directory as file.
        Only the file extension will be changed to .oop
    - The input file must have an extension.
3. After the file has compiled, run `anna.sh <file.oop>`.