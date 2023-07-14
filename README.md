# Tetris
Tetris is a classic game that can be found almost anywhere, and has solidified its place as one of the most famous retro games ever. The game consists of arrangements of blocks falling in a set board area, with the goal of placing these blocks to form rows across the entire board, clearing the row of blocks, and adding points to your overall score. If a block reaches the top, you lose. 
## About this project
The creation of this repository was shortly after I learned Java, and I intended to recreate Tetris to ensure I knew what I was doing when it came to Java (it was my first low-level language, and my first statically typed language). Evidently looking back on the code, I did not know what I was doing. I came back to the project 9 months later and realized how terrible the code was, and started to make it better, reworking it from the ground up.
<br /> <br />
In a completely unrelated string of events, I decided that I wanted a 'boilerplate' project that could be made in any language to test my knowledge of the language. This project worked out to be the answer because it only uses printing in a command line for its graphics, and keyboard input for controls. These attributes allow for the project to be made relatively easily in any language.
<br /> <br />
Thusly, this repository will conain a folder for each language that I've learned containing a command line interpretation of Tetris written in that language.

## What a game should look like
Blocks are represented in groups of pieces ([]), and the board is drawn using dashes (-) and pipes (|) with special characters being used for the corners. Because of this, an average gamestate might look something like this : <br />
```
Score: 0
┌------------------┐
|                  |
|                  |
|                  |
|                  |
|                  |
|                  |
|                  |
|                  |
|                  |
|      [][]        |
|      []          |
|      []          |
|                  |
|                  |
|                  |
|                  |
|                  |
|                  |
|                  |
|                  |
|                  |
|                  |
|                  |
|                  |
|                  |
└------------------┘
```
This may vary from version to version, as printing in different languages is slightly different, but that is the general idea of what the board and a block being drawn on it should look like.

