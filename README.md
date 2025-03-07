# 🕵️‍♂️🎮 Reverse Engineering Survive Game

## 🔍 Introduction
This project is a reverse-engineered version of an Android game obtained from a decompiled APK. The goal of the game is to survive and reach the correct city based on a user-provided ID. The task was to analyze the decompiled code, identify and fix any existing bugs, and ensure the game functions as expected.

## ✨ Game Overview
- The user inputs an ID to start the game.
- The game determines a sequence of moves the user must follow.
- The user clicks on directional buttons (left, right, up, down) to match the expected sequence.
- If the sequence is correct, the user "survives" and sees a toast message displaying the city name.
- If incorrect, the user "fails."

## Identified Bugs & Fixes

### 1. **Invalid Toast Message Length**
#### **Issue:**
The original code used `Toast.makeText(this, "message", 1).show();`, but `1` is not a valid length parameter for a Toast in Android.
#### **Fix:**
Replaced `1` with `Toast.LENGTH_SHORT`.
```java
Toast.makeText(this, "Survived in " + state, Toast.LENGTH_SHORT).show();
Toast.makeText(this, "You Failed", Toast.LENGTH_SHORT).show();
```

### 2. **Incorrect Direction Parsing from ID**
#### **Issue:**
The original code extracted numbers from the ID but did not properly map them to expected directions (`0-3`). Some numbers caused index errors.
#### **Fix:**
Used `Character.getNumericValue()` and ensured values were mapped to 0-3 range correctly.
```java
steps[i] = Character.getNumericValue(id.charAt(i)) % 4;  // Ensure values are between 0-3
```

### 3. **Missing Debugging Information**
#### **Issue:**
There was no clear indication of why the game was failing.
#### **Fix:**
Added a debugging Toast to show pressed vs expected values.
```java
Toast.makeText(this, "Pressed: " + direction + ", Expected: " + expectedDirection, Toast.LENGTH_SHORT).show();
```

### 4. **Game Completion Condition Not Working Properly**
#### **Issue:**
The game did not properly track progress through the levels.
#### **Fix:**
Incremented `currentLevel` correctly and checked when the game should end.
```java
currentLevel++;
if (currentLevel >= steps.length) {
    finishGame();
}
```

## 🛠️ How I Worked
1. Decompiled the APK using [Java Decompiler].
2. Extracted the Java source files and reviewed the game logic.
3. Identified and fixed critical issues affecting gameplay.
4. Tested the fixed APK on an emulator and real device.


## 🚀 Final Output
Upon successfully following the correct sequence, the Toast message correctly displays:
```
Survived in Florida
```

<img src="https://raw.githubusercontent.com/LitalKhotyakov/Survivegame/master/pic.png" width="288">


## Conclusion
By debugging the decompiled code, I successfully made the game functional. Key fixes included correcting the toast message, ensuring correct ID-to-direction mapping, and fixing game progress tracking. These improvements allow the game to function as originally intended.
