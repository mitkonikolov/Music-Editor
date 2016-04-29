# Music-Editor

*** Functionality: ***
* Basic functionality:
The Music Editor allows users to read music files represented as a sequence of instructions and saved in txt format. Users can add, remove, and move notes to and from the musical piece with a combination of the keyboard and the mouse. Users are also able to add two different types of repetition and look at a visual representation of and listen to musical pieces.

* Playing the piece:
	1. Run the MusicEditor class
	2. Press the space bar to start the music

* Using the keyboard and the mouse to add, edit, or move notes:
Adding a note:
	1. If the music is playing, press the keyboard button ’N’ to go to normal 
	mode
	2. Press the keyboard button ‘A’
	3. Click anywhere on or out of the piece to add the desired note
	4. Click once more anywhere after it to make it longer. If you want a note of 	length 1 beat after step 3 click right after the note.
Removing a note
	1. If the music is playing, press the keyboard button ’N’ to go to normal 
	mode
	2. Press the keyboard button ‘D’
	3. Click on the head(the black part) of any note you want to remove
Moving a note:
	1. If the music is playing, press the keyboard button ’N’ to go to normal 
	mode
	2. Press the keyboard button ‘M’
	3. Click on the head of any note you want to move and after that click 
	anywhere in the music piece where you want to move it to

* Understanding repetitions and their representations in the GUI:
(If you would like to read more about repetitions, please refer to http://www.music-mind.com/Music/mpage6.HTM)
The signs for repetitions are represented in the GUI with lines. The color encodings for the lines are:
blue line – repeat sign
red line – inverted repeat sign
yellow line – varied repeat sign
A basic repeat sign is one that repeats the piece either from its start or from the first available inverted repeat sign.
The more advanced varied repeating repeat the piece either from its start or from the first available inverted repeat sign too. However, the 4 beats before the varied repeat sign are always skipped from the piece no matter how many times it is played.
So if a varied repeat sign was put on beat 20, then once the music piece gets to this sign, it will never play the beats from 16 to 20 anymore.
Inverted repeat signs are the same no matter if basic or varied repeating is being used and represented places from which the music piece to start if a repeat sign has been encountered.

* Using the keyboard to add repetitions:
Adding a basic repeat sign:
	1. Press the keyboard button ‘L’
	2. Click with the mouse anywhere on the grid on a beat where the repeat sign 	has to be added
Adding an inverted repeat sign:
	1. Press the keyboard button ‘K’
	2. Click with the mouse anywhere on the grid on a beat where the inverted
	repeat sign has to be added
Adding a varied repeat sign:
	1. Press the keyboard button ‘J’
	2. Click with the mouse anywhere on the grid on a beat where the varied 
	repeat sign has to be added
