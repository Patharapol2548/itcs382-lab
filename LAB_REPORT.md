# Lab Report, Code Quality and Design Quality

Repository URL: https://github.com/Patharapol2548/itcs382-lab.git

## Tools

| Item | Version or name |
| --- | --- |
| JDK | JDK SE 27 |
| Maven | Apache Maven 3.11.1 |
| IDE | Antigravity IDE |
| SonarQube for IDE | SonarLint v4.2.1 |
| AI tool and exact model | Google Gemini 3.8 Flash |

## Task 1. SonarQube on the starter code

Commit of the starter, before any change:

Table 1. SonarQube issues in the starter code

| No. | Rule | File:line | Message |
| --- | --- | --- | --- |
| 1 | java:s2143 | ParcelService.java:Ln1 | java.time API for date and time |
| 2 | java:s1128 | ParcelService.java:Ln6 | remove this unesed improt 'java.util.Date' |
| 3 | java:S1068 | ParcelService.java:12 | Remove this unused "maxParcels" private field. |
| 4 | java:S106 | ParcelService.java:30 | Replace this use of System.out by a logger. |
| 5 | java:S4973 | ParcelService.java:42 | Strings and Boxed types should be compared using "equals()". |
| 6 | java:S106 | ParcelService.java:67 | Replace this use of System.out by a logger. |
| 7 | java:S106 | ParcelService.java:69 | Replace this use of System.out by a logger. |
| 8 | java:S2095 | ParcelService.java:76 | Use try-with-resources or close this "FileWriter" in a "finally" clause. |
| 9 | java:S108 | ParcelService.java:80 | Remove this block of code, fill it in, or add a comment explaining why it is empty. |
| 10 | java:S106 | Main.java:9 | Replace this use of System.out by a logger. |
| 11 | java:S106 | Main.java:15 | Replace this use of System.out by a logger. |
| 12 | java:S106 | Main.java:17 | Replace this use of System.out by a logger. |
| 13 | java:S106 | Main.java:22 | Replace this use of System.out by a logger. |


## Task 2. AI review of the starter code

| Item | Value |
| --- | --- |
| Date | 24 Sat October 2026 |
| AI tool and exact model | Google Gemini 3 Pro High |
| Commit reviewed | Initial commit |
| How the AI read the files (attached the files, opened the folder, or pasted the files) | AI read the files from the folder, Also Attrach the files |
| Output file in the reviews folder | review/parcelpoint-review.md |

Table 2. All findings on the starter code

| No. | File:line | Finding | Found by (SonarQube, AI or both) | SonarQube rule or course term | Correct? (yes or no, and why) |    
| --- | --- | --- | --- | --- | --- |
| 1 | `ParcelService.java:76` | `FileWriter` resource is opened without being closed or wrapped in try-with-resources, risking resource leaks and unwritten data. | AI | Must fix | Yes.  |
| 2 | `ParcelService.java:42` | String equality is checked using `==` instead of `.equals()`. | AI | Must fix | Yes.  |
| 3 | `Main.java:8` | `Scanner` resource is opened but never closed. | AI | Should fix | Yes.  |
| 4 | `ParcelService.java:6` | Unused import `java.util.Date`. | AI | Should fix | Yes.  |
| 5 | `ParcelService.java:12` | Unused private field `maxParcels`. | AI | Should fix | Yes.  |
| 6 | `ParcelService.java:80` | Empty `catch (IOException e)` block silently swallows file writing errors. | AI | Must fix | Yes.  |
| 7 | `ParcelService.java:28` | The string literal `"STORED"` is repeated across lines 28 and 68. | AI | Should fix | Yes.  |
| 8 | `Main.java:6` | `main` handles console reading, command string parsing, domain instantiation, and output formatting in a single long loop. | AI | Should fix | Yes.  |
| 9 | `Main.java:6` | Method cohesion: `main` exhibits procedural cohesion because operations are grouped solely by execution order in a command loop. | AI | Should fix | Yes.  |
| 10 | `ParcelService.java:24` | Method cohesion: `storeParcel` exhibits procedural cohesion by chaining locker allocation, console notification (SMS), and file persistence in a sequence. | AI | Should fix | Yes.  |
| 11 | `ParcelService.java:39` | Method cohesion: `pickUp` exhibits procedural cohesion by combining locker release, file persistence, and fee calculation in a sequence. | AI | Should fix | Yes.  |
| 12 | `ParcelService.java:64` | Method cohesion: `printParcels` exhibits logical cohesion by using the boolean flag `forStaff` to select between two distinct output routines. | AI | Should fix | Yes.  |
| 13 | `ParcelService.java:9` | Class cohesion: `ParcelService` shows weak class cohesion because it mixes locker management, console SMS notification, and CSV file persistence. | AI | Should fix | Yes.  |
| 14 | `ParcelService.java:52` | Class cohesion: `fee` does not operate on any attributes of `ParcelService`, exhibiting feature envy toward `Parcel`. | AI | Should fix | Yes.  |
| 15 | `ParcelService.java:28` | Coupling: Content coupling because `ParcelService` directly mutates the public field `parcel.status`. | AI | Must fix | Yes.  |
| 16 | `ParcelService.java:44` | Coupling: Content coupling because `ParcelService` directly mutates `parcel.status`. | AI | Must fix | Yes.  |
| 17 | `ParcelService.java:67` | Coupling: Content coupling because `ParcelService` directly accesses the public field `parcel.status`. | AI | Should fix | Yes.  |
| 18 | `Main.java:19` | Coupling: Control coupling because `Main` passes boolean flags (`false` at line 19 and `true` at line 21) to control the execution path of `service.printParcels`. | AI | Should fix | Yes.  |
| 19 | `ParcelService.java:64` | Coupling: Control coupling because the parameter `boolean forStaff` dictates internal method branching. | AI | Should fix | Yes.  |
| 20 | `ParcelService.java:52` | Coupling: Stamp coupling because `fee` accepts an entire `Parcel` object when it only uses the parcel's `size` property. | AI | Should fix | Yes.  |
| 21 | not found | Coupling: Common coupling (shared global state or public static variables between classes) was not found. | AI | Note | Yes.  |
| 22 | `Main.java:22` | Strong coupling: `Main` reaches through `ParcelService` into its internal collection to call `Locker::isFree`, violating the Law of Demeter. | AI | Should fix | Yes.  |
| 23 | `ParcelService.java:41` | Strong coupling: `ParcelService` reaches through `locker` to inspect `parcel.getCode()`. | AI | Note | Yes.  |
| 24 | `Parcel.java:13` | Connascence: Connascence of Convention because classes rely on implicit String literals (`"NEW"`, `"STORED"`, `"PICKED_UP"`). | AI | Should fix | Yes.  |
| 25 | `Locker.java:5` | Connascence: Connascence of Convention because `Locker`, `Parcel`, and `ParcelService` rely on implicit integer codes (`1, 2, 3`) for sizes. | AI | Should fix | Yes.  |
| 26 | `Main.java:13` | Connascence: Connascence of Position because callers depend on the exact positional order of `Parcel` constructor parameters. | AI | Note | Yes.  |
| 27 | `ParcelService.java:26` | Connascence: Connascence of Algorithm because `ParcelService` relies on integer magnitude comparison (`locker.getSize() >= parcel.getSize()`) to determine fit. | AI | Should fix | Yes.  |
| 28 | `Parcel.java:7` | Encapsulation: Field `public String status` is not private. | AI | Must fix | Yes.  |
| 29 | `ParcelService.java:20` | Encapsulation: `getLockers()` returns the internal mutable `List<Locker>` directly, allowing callers to alter it. | AI | Must fix | Yes.  |

2.1 Name two problems that only the AI review found.
- Answer : 
 1. Weak class cohesion: ParcelService.java handles too many responsibilities (locker management, SMS notification, and CSV persistence).
 2. Encapsulation breach: Parcel.java uses public String status, allowing external classes to directly modify its state.

2.2 Name one problem that only SonarQube found, or write none.
 - Answer : Rule java:S106 regarding the use of System.out instead of a logger.

2.3 Name one AI finding that was wrong or too vague, and explain why. If every finding was right, explain how you checked them.
 - Answer : Finding #20 (Stamp Coupling at ParcelService.java:52) was overly strict, as passing a Parcel object to calculate its fee is standard OOP design practice.

## Task 3. Fix and check

Table 3. What you did with each SonarQube issue in Table 1

| No. in Table 1 | Fixed or kept | Commit, or the reason for keeping it |
| --- | --- | --- |
| 1 | kept | because System.out is required for command-line output.|   
| 2 | kept | because System.out is required for command-line output.|
| 3 | kept | because System.out is required for command-line output.|
| 4 | kept | because System.out is required for command-line output.|
| 5 | kept | because standard time API conversion is optional for this exercise.|
| 6 | fixed | Removed unused private field maxParcels.|
| 7 | kept | because System.out is required for command-line output.|
| 8 | fixed | Replaced == with .equals() for string comparison.|
| 9 | kept | because System.out is required for command-line output.|
| 10 | kept | because System.out is required for command-line output.|
| 11 | fixed | Wrapped FileWriter in try-with-resources statement.|
| 12 | fixed | Added comment inside empty catch block to explain intentional silence.|

Number of SonarQube issues that remain after your fixes:

The two design problems that you fixed

| Design problem (course term) | What you changed | Commit |
| --- | --- | --- |
| Encapsulation Breach | Changed public String status in Parcel.java to private and added getter/setter accessors. | |
| Content Coupling | Replaced == string comparison in ParcelService.java with .equals() | |

Output of the program after your fixes, for the commands `store P1 6501 2`, `pickup P1 5` and `staff`, and the content of parcels.csv:

```text
> store P1 6501 2
    SMS to 6501: parcel P1 is in locker 2
    Locker 2
> pickup P1 5
    Fee: 30.0
> staff
    P1 6501 PICKED_UP
    Free lockers: 3

parcels.csv content:
P1,PICKED_UP
```

## Task 4. AI-generated poor and good designs

| Item | bikeshare-poor | bikeshare-good |
| --- | --- | --- |
| Date | | |
| AI tool and exact model | | |
| Prompt (B or C as given, or your changed prompt in full) | | |
| Builds with mvn package (yes or no) | | |
| Number of classes, interfaces, enums and records | | |
| SonarQube issues, total | | |
| SonarQube issues, by rule | | |
| AI review findings in Check 1 | | |
| AI review findings in Check 2 | | |
| Review output file in the reviews folder | | |

4.1 Did SonarQube report any cohesion or coupling problem in bikeshare-poor? Quote the rule and the message if it did.
 - Answer : SonarQube focuses on static code quality and probable bugs rather than architecture-level design issues like cohesion or coupling.

4.2 Did the AI review tell the two versions apart? Give one finding for bikeshare-poor that does not appear for bikeshare-good. 
 - Answer : The AI review identified weak class cohesion in bikeshare-poor for mixing menu handling, fee logic, and file I/O in a single class, whereas bikeshare-good successfully separated these into distinct classes and interface abstractions.

4.3 Which version would you rather extend with a new member type, such as a visitor who pays 4 baht per minute, and why?
 - bikeshare-good. Because member types are encapsulated in a dedicated enum or strategy class, allowing the new VISITOR type and its fee rule to be added cleanly without altering the core station or menu handling logic (adhering to the Open/Closed Principle).

## Task 5. Reflection

5.1 What does SonarQube check, and what does it not check? Answer with examples from this lab.
 - Sonar is check a low-level code quality, bug , and syntax based on code smells, However it does not check a high-level design issues like cohesion or coupling.

5.2 When should you not trust the AI review? Give an example from this lab.
 - I should not trust the AI review when it comes to overly strict design rules, when it fails to provide reasonable justification, or when it generates false positives on valid implementation choices. An example from this lab was when the AI falsely flagged standard object parameter passing as a coupling issue without considering practical software design.

5.3 What will you do differently in Project Phase 2 after this lab?
 - I will rely on SonarQube for continuous code quality checks while using AI reviews specifically for architectural evaluation. Crucially, I will manually verify all AI findings before making design decisions.

## AI usage declaration

| AI tool and exact model | Purpose | Files or parts that it produced or changed | How you checked its output |
| --- | --- | --- | --- |
| Google Gemini 3.8 Flash | Code quality and design review (Task 2 & Task 4) | Generated reviews/parcelpoint-review.md, reviews/bikeshare-poor-review.md, and reviews/bikeshare-good-review.md | Manually checked every reported line number and issue against the actual Java source code to verify correctness. |
| Google Gemini 3.8 Flash | Code generation for poor and good designs (Task 4) | Generated Java source files and pom.xml under bikeshare-poor/ and bikeshare-good/ | Built both projects using mvn package, executed CLI commands to confirm functionality, and inspected class design. |
| Google Gemini 3.6 Flash | Code fix suggestions and report assistance (Task 3 & Task 5) | Refactored ParcelService.java and Parcel.java, and assisted with LAB_REPORT.md | Compiled and ran the application with test inputs (store, pickup, staff), checked SonarQube issues, and reviewed report answers. | 