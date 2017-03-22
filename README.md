# An NSFW to ASCII Art Generator
An application for turning Pr0nHub images into ASCII art!

This application was originally created for the [SexTechHack](http://sexhack.tech/) 2016 hackathon. 

Upon the user typing a pr0n category, the user will be given a random still from the Pr0nHub website related to that category but converted to an ASCII art representation.

18+ users only!

Note: This program works best when your terminal is large, full-screen or with a small font size. 
Future improvements may include dynamic resizing to address this.

## To use the program:

Dependencies:
- [Java 8 JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) (other versions may work, but are not tested)
- [jsoup](https://jsoup.org/) (included in src)
- [Thumbnailator](https://github.com/coobird/thumbnailator) (included in src)

### Compilation
To compile the program execute the following command when inside the `src` directory:
```
javac -cp ".:thumbnailator-0.4.8.jar:jsoup-1.10.1.jar" nsfwtoascii/*.java
```
If on Windows, replace the `:`'s with `;`'s.

### Running
To run the program execute the following command when inside the `src` directory:
```
java -cp ".:thumbnailator-0.4.8.jar:jsoup-1.10.1.jar" nsfwtoascii/NsfwToAscii
```
Again, if on Windows replace the `:`'s with `;`'s.

Enjoy ;)
