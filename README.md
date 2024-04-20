# project-372

Authors: 
- Creed Leichtle
- Kenneth Acevedo
- AJ Scollo
- Anthony Nguyen

---

Description: Anna-OOP
- An imperative, interpreted, inferred but strongly-typed, dynamic language.
- Supports functional programming

---

Steps to Run (Interpreted):
1. Open bash in the project-372 folder.
2. Run [`anna.sh`](https://github.com/bluedragon2513/project-372/blob/main/anna.sh).
    - You may have to <code>chmod u+x <a href="https://github.com/bluedragon2513/project-372/blob/main/anna.sh">anna.sh</a></code>.
    - If you would like to load source code, simply run it as so: <code><a href="https://github.com/bluedragon2513/project-372/blob/main/anna.sh">anna.sh</a> \<file\></code>.
    - You may also choose to not load any source code.

Steps to Run (Compiled):
1. Open bash in the project-372 folder.
2. Run <code><a href="https://github.com/bluedragon2513/project-372/blob/main/annac.sh">annac.sh</a><file></code>.
    - You may have to <code>chmod u+x <a href="https://github.com/bluedragon2513/project-372/blob/main/annac.sh">annac.sh</a></code>
    - The compiled code will be stored in the same directory as file.
        Only the file extension will be changed to .oop
    - The input file must have an extension.
3. After the file has compiled, run <code><a href="https://github.com/bluedragon2513/project-372/blob/main/anna.sh">anna.sh</a> <file.oop></code>.

---

File Structure:
- `/bin` - the folder containing all of the Java class files
- [`/samples`](https://github.com/bluedragon2513/project-372/tree/main/samples) - the folder containing sample programs written in Anna-OOP
- [`/src`](https://github.com/bluedragon2513/project-372/tree/main/src) - the folder containing all of the Java files to interpret and compile the language
- [`anna.sh`](https://github.com/bluedragon2513/project-372/blob/main/anna.sh) - the main program
- [`annac.sh`](https://github.com/bluedragon2513/project-372/blob/main/annac.sh) - the program that allows compilation first for faster runtimes (used with [`anna.sh`](https://github.com/bluedragon2513/project-372/blob/main/anna.sh))
